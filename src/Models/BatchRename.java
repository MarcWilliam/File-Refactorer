package Models;

import java.io.*;
import java.util.*;

public class BatchRename {

	public static interface IRename {

		public String rename(final String target, final String replacement, final String oldName,final  boolean isFile);
	}

	protected ArrayList<CFile> CFiles;

	public boolean forFile = true, forDir = false, forSubDir = false;

	public String target = null, replacement = null, originPath = null;

	public IRename Action = null;

	public void setAll(IRename Action, String target, String replacement, String originPath, boolean forFile, boolean forDir, boolean forSubDir) {
		this.Action = Action;
		this.target = target;
		this.replacement = replacement;
		this.originPath = originPath;
		this.forFile = forFile;
		this.forDir = forDir;
		this.forSubDir = forSubDir;
	}

	public boolean validate() {
		return target != null
				&& replacement != null
				//&& forDir != forSubDir
				&& new File(this.originPath).exists()
				&& new File(this.originPath).isDirectory();
	}

	public boolean Prepair() {
		if (!this.validate()) {
			return false;
		}
		make(this.originPath);
		return true;
	}

	private void make(final String Path) {
		this.CFiles = new ArrayList<>();
		File[] listOfFiles = new File(Path).listFiles();// get an array of all files and folder in that path

		for (File tempFile : listOfFiles) {
			if (tempFile.isFile() && forFile) {
				this.CFiles.add(new CFile(Path, this.Action.rename(this.target, this.replacement, tempFile.getName(), tempFile.isFile()), tempFile));
			} else if (tempFile.isDirectory()) {
				if (forDir) {
					this.CFiles.add(new CFile(Path, this.Action.rename(this.target, this.replacement, tempFile.getName(), tempFile.isFile()), tempFile));
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
	 *
	 * @return
	 */
	public ArrayList<CFile> getCFiles() {
		return this.CFiles;
	}
}
