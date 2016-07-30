package BatchFileRename;

import java.io.*;

public class FileRenameing {

	public static String[][] Replace(String PathLocation, String Target, String Replacement, Boolean isFile, Boolean isDir) {
		File folder = new File(PathLocation);
		File[] listOfFiles = folder.listFiles();
		String cells[][] = new String[listOfFiles.length][3];

		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile() && isFile) {
				String NewName = listOfFiles[i].getName().replaceAll(Target, Replacement);
				renameFile(listOfFiles[i], PathLocation + "\\" + NewName);
				cells[i][0] = String.valueOf(i);
				cells[i][1] = "File || " + listOfFiles[i].getName();
				cells[i][2] = NewName;

			} else if (listOfFiles[i].isDirectory() && isDir) {
				String NewName = listOfFiles[i].getName().replaceAll(Target, Replacement);
				renameFile(listOfFiles[i], PathLocation + "\\" + NewName);
				cells[i][0] = String.valueOf(i);
				cells[i][1] = "Dire || " + listOfFiles[i].getName();
				cells[i][2] = NewName;
			}
		}
		return cells;
	}

	public static String[][] Test(String PathLocation, String Target, String Replacement, Boolean isFile, Boolean isDir) {

		File folder = new File(PathLocation);
		File[] listOfFiles = folder.listFiles();
		String cells[][] = new String[listOfFiles.length][3];

		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile() && isFile) {
				String NewName = listOfFiles[i].getName().replaceAll(Target, Replacement);
				cells[i][0] = String.valueOf(i);
				cells[i][1] = "File || " + listOfFiles[i].getName();
				cells[i][2] = NewName;
			} else if (listOfFiles[i].isDirectory() && isDir) {
				String NewName = listOfFiles[i].getName().replaceAll(Target, Replacement);
				cells[i][0] = String.valueOf(i);
				cells[i][1] = "Dire || " + listOfFiles[i].getName();
				cells[i][2] = NewName;
			}
		}
		return cells;
	}

	public static boolean renameFile(File OldFile, String Path) {
		File NewFile = new File(Path);
		if (NewFile.exists()) {
			return false;
		}
		return OldFile.renameTo(NewFile);
	}
}
