package Models;

import java.io.*;
import java.util.*;

public class BulkRefactor {

	public static interface IRename {

		public String rename(final String param1, final String param2, final File oldFile, final boolean ignoreExtension);
	}

	protected ArrayList<CFile> CFiles;

	public boolean forFile,
			forDir,
			forSubDir,
			ignoreExtension;

	public String param1,
			param2,
			originPath;

	public IRename Action;

	/**
	 *
	 * @param Action
	 * @param param1
	 * @param param2
	 * @param originPath
	 * @param forFile
	 * @param forDir
	 * @param forSubDir
	 * @param ignoreExtension
	 */
	public BulkRefactor(IRename Action, String param1, String param2, String originPath, boolean forFile, boolean forDir, boolean forSubDir, boolean ignoreExtension) {
		this.Action = Action;
		this.param1 = param1;
		this.param2 = param2;
		this.originPath = originPath;
		this.forFile = forFile;
		this.forDir = forDir;
		this.forSubDir = forSubDir;
	}

	/**
	 * validate all the fields of the class
	 *
	 * @return
	 */
	public boolean validate() {
		return param1 != null
				&& param2 != null
				//&& forDir != forSubDir
				&& new File(this.originPath).exists()
				&& new File(this.originPath).isDirectory();
	}

	/**
	 * fill the array of CFiles with the new name
	 *
	 * @return true if an only if Success
	 */
	public boolean Prepair() {
		if (!this.validate()) {
			return false;
		}
		make(this.originPath);
		return true;
	}

	/**
	 * fill the array of CFiles with the new name
	 *
	 * @param Path used for recursion to loop all sub directory
	 */
	private void make(final String Path) {
		this.CFiles = new ArrayList<>();
		File[] listOfFiles = new File(Path).listFiles();// get an array of all files and folder in that path

		for (File tempFile : listOfFiles) {
			if (tempFile.isFile() && forFile) {
				this.CFiles.add(new CFile(Path, this.Action.rename(this.param1, this.param2, tempFile, this.ignoreExtension), tempFile));
			} else if (tempFile.isDirectory()) {
				if (forDir) {
					this.CFiles.add(new CFile(Path, this.Action.rename(this.param1, this.param2, tempFile, this.ignoreExtension), tempFile));
				} else if (forSubDir) {
					// recurgent here
				}
			}
		}
	}

	/**
	 * loop all the CFiles and rename them
	 */
	public void rename() {
		if (this.CFiles == null) {
			return;
		}
		for (CFile tempCFile : this.CFiles) {
			tempCFile.rename();
		}
	}

	/**
	 * loop all the CFiles and undo the previous rename
	 */
	public void undoRename() {
		if (this.CFiles == null) {
			return;
		}
		for (CFile tempCFile : this.CFiles) {
			tempCFile.undoRename();
		}
	}

	/**
	 * @return the array of CFiles
	 */
	public ArrayList<CFile> getCFiles() {
		return this.CFiles;
	}
}
