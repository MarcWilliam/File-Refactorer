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
public class CFile implements GUItabelList.ITable {

	public String Path,
			oldName,
			newName;
	public File file;

	/**
	 *
	 * @param Path the main directory Path
	 * @param newName the new file name
	 * @param file
	 */
	public CFile(String Path, String newName, File file) {
		this.Path = Path;
		this.oldName = file.getName();
		this.newName = newName;
		this.file = file;
	}

	public CFile() {
	}

	/**
	 * rename the file to the name in this.newName checks is the name is different and if a file exists with the same name
	 *
	 * @return true if and only if the renaming succeeded; false otherwise
	 */
	public boolean rename() {
		File NewFile = new File(this.Path + "\\" + this.newName);
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
	 * undo the previous renaming rename the file to the name in this.oldName checks is the name is different and if a file exists with the same name
	 *
	 * @return true if and only if the renaming succeeded; false otherwise
	 */
	public boolean undoRename() {
		File NewFile = new File(this.Path + "\\" + this.oldName);

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
