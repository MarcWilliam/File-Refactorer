/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import GUI.*;
import java.io.*;

/**
 *
 * @author marcw
 */
public class CFile implements CTable.ITable {

	public String Path,
			oldName,
			oldExtension,
			newName,
			newExtension;
	public File file;

	public CFile(String Path, File file, final boolean ignoreExtension) {
		this.Path = Path;
		this.oldName = this.newName = file.getName();
		this.oldExtension = this.newExtension = "";
		this.file = file;

		// get the file extension
		if (this.file.isFile() && ignoreExtension) {
			int i = this.file.getName().lastIndexOf('.');
			if (i > 0) {
				this.oldExtension = this.newExtension = this.oldName.substring(i);
				this.oldName = this.newName = this.oldName.substring(0, i);
			}
		}
	}

	public CFile() {
	}

	/**
	 * rename the file to the name in this.newName
	 * checks is the name is different and if a file exists with the same name
	 *
	 * @return true if and only if the renaming succeeded; false otherwise
	 */
	public boolean rename() {
		File NewFile = new File(this.Path + "\\" + this.newName + this.newExtension);
		if (NewFile.exists() || this.file.getName().equals(this.newName) || this.oldName.equals(this.newName)) {
			return false;
		} else {
			if (this.file.renameTo(NewFile)) {
				this.file = NewFile;
				return true;
			}
			return false;
		}
	}

	/**
	 * undo the previous renaming rename the file to the name in this.oldName
	 * checks is the name is different and if a file exists with the same name
	 *
	 * @return true if and only if the renaming succeeded; false otherwise
	 */
	public boolean undoRename() {
		File NewFile = new File(this.Path + "\\" + this.oldName + this.oldExtension);

		if (NewFile.exists()) {
			return false;
		} else {
			if (this.file.renameTo(NewFile)) {
				this.file = NewFile;
				return true;
			}
			return false;
		}
	}

	@Override
	public String[] getCells() {
		return new String[]{
			(this.newName.equals(this.oldName) ? "  " : "* ") + (this.file.isFile() ? "File" : "Folder"),
			this.oldName,
			this.newName};
	}

	@Override
	public String[] getColumNames() {
		return new String[]{
			"Type",
			"Original Name",
			"New Name"};
	}

}
