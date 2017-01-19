/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import Interfaces.ITableRow;
import java.io.*;

/**
 *
 * @author marcw
 */
public class CFile implements ITableRow {

	public class State {

		public String name,
				extension,
				path;

		public CFile.State parent;

		public boolean active;//if this is the active state or not

		public State() {
		}

		public State(String name, String extension, String path, CFile.State parent, boolean active) {
			this.name = name;
			this.extension = extension;
			this.path = path;
			this.parent = parent;
			this.active = active;
		}

		private String getPath() {
			CFile.State currentNode = this;
			String result = "";
			while (currentNode.parent != null) {
				currentNode = currentNode.parent;
				String temp = currentNode.name;
				result = "\\" + temp + result;
			}
			return currentNode.path + result;
		}

		public String getFullName() {
			return this.name + this.extension;
		}

		public String getFullPath() {
			return this.getPath() + "\\" + this.name + this.extension;
		}
	}

	public CFile.State source,
			destination;

	public File file;

	public CFile() {
	}

	public CFile(String path, File file, CFile parent, final boolean ignoreExtension) {
		this.source = new State(file.getName(), "", path, parent.source, true);
		this.destination = new State(file.getName(), "", path, parent.destination, false);

		this.file = file;

		// get the file extension
		if (this.file.isFile() && ignoreExtension) {
			int i = this.file.getName().lastIndexOf('.');
			if (i > 0) {
				this.source.extension = this.destination.extension = this.source.name.substring(i);
				this.source.name = this.destination.name = this.source.name.substring(0, i);
			}
		}
	}

	/**
	 * rename the file to the name in this.source.name
	 * checks is the name is different and if a file exists with the same name
	 *
	 * @return true if and only if the renaming succeeded; false otherwise
	 */
	public boolean rename() {
		File NewFile = new File(this.destination.getFullPath());
		if (NewFile.exists() || this.file.getName().equals(this.source.name) || this.destination.name.equals(this.source.name)) {
			return false;
		} else {
			if (this.file.renameTo(NewFile)) {
				this.source.active = false;
				this.destination.active = true;
				this.file = NewFile;
				return true;
			}
			return false;
		}
	}

	/**
	 * undo the previous renaming rename the file to the name in this.destination.name
	 * checks is the name is different and if a file exists with the same name
	 *
	 * @return true if and only if the renaming succeeded; false otherwise
	 */
	public boolean undoRename() {
		File NewFile = new File(this.source.getFullPath());

		if (NewFile.exists()) {
			return false;
		} else {
			if (this.file.renameTo(NewFile)) {
				this.source.active = true;
				this.destination.active = false;
				this.file = NewFile;
				return true;
			}
			return false;
		}
	}

	@Override
	public Object[] getCells() {
		return new Object[]{
			(this.source.name.equals(this.destination.name) ? "  " : "* ") + (this.file.isFile() ? "File" : "Folder"),
			this.destination.name,
			this.source.name
		};
	}

	@Override
	public String[] getColumNames() {
		return new String[]{
			"Type",
			"Original Name",
			"New Name"
		};
	}

}
