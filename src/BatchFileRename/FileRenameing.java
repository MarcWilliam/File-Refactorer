package BatchFileRename;

import java.io.*;
import java.util.*;
import java.util.regex.*;

public class FileRenameing {

	public static void main(String[] args) {
		Scanner Scan = new Scanner(System.in);
		System.out.print("        Path : ");
		String Path = Scan.nextLine();
		System.out.print("      Target : ");
		String Target = Scan.nextLine();
		System.out.print(" Replacement : ");
		String Replacement = Scan.nextLine();
		System.out.print("    is regex : ");
		String temp = Scan.nextLine();
		if (!isYes(temp)) {
			Target = Pattern.quote(Target);
		}
		System.out.print("   All Files : ");
		temp = Scan.nextLine();
		Boolean isFile = false;
		if (isYes(temp)) {
			isFile = true;
		}
		System.out.print(" All Folders : ");
		temp = Scan.nextLine();
		Boolean isDir = false;
		if (isYes(temp)) {
			isDir = true;
		}
		Test(Path, Target, Replacement, isFile, isDir);
		System.out.print("Save Changes : ");
		temp = Scan.nextLine();
		if (isYes(temp)) {
			Replace(Path, Target, Replacement, isFile, isDir);
		}
	}

	public static void Replace(String PathLocation, String Target, String Replacement, Boolean isFile, Boolean isDir) {
		String leftAlignFormat = "| %-3d | %-55s | %-55s |%n";
		System.out.format("+-----+---------------------------------------------------------+---------------------------------------------------------+%n");
		System.out.format("| No  | Original Name                                           | New Name                                                |%n");
		System.out.format("+-----+---------------------------------------------------------+---------------------------------------------------------+%n");

		File folder = new File(PathLocation);
		File[] listOfFiles = folder.listFiles();
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile() && isFile) {
				String NewName = listOfFiles[i].getName().replaceAll(Target, Replacement);
				renameFile(listOfFiles[i], PathLocation + "\\" + NewName);
				System.out.format(leftAlignFormat, i, "Fil - " + listOfFiles[i].getName(), NewName);
			} else if (listOfFiles[i].isDirectory() && isDir) {
				String NewName = listOfFiles[i].getName().replaceAll(Target, Replacement);
				renameFile(listOfFiles[i], PathLocation + "\\" + NewName);
				System.out.format(leftAlignFormat, i, "Dir - " + listOfFiles[i].getName(), NewName);
			}
		}
		System.out.format("+-----+---------------------------------------------------------+---------------------------------------------------------+%n");
	}

	public static void Test(String PathLocation, String Target, String Replacement, Boolean isFile, Boolean isDir) {
		String leftAlignFormat = "| %-3d | %-55s | %-55s |%n";
		System.out.format("+-----+---------------------------------------------------------+---------------------------------------------------------+%n");
		System.out.format("| No  | Original Name                                           | New Name                                                |%n");
		System.out.format("+-----+---------------------------------------------------------+---------------------------------------------------------+%n");

		File folder = new File(PathLocation);
		File[] listOfFiles = folder.listFiles();
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile() && isFile) {
				String NewName = listOfFiles[i].getName().replaceAll(Target, Replacement);
				System.out.format(leftAlignFormat, i, "Fil - " + listOfFiles[i].getName(), NewName);
			} else if (listOfFiles[i].isDirectory() && isDir) {
				String NewName = listOfFiles[i].getName().replaceAll(Target, Replacement);
				System.out.format(leftAlignFormat, i, "Dir - " + listOfFiles[i].getName(), NewName);
			}
		}
		System.out.format("+-----+---------------------------------------------------------+---------------------------------------------------------+%n");
	}

	public static boolean renameFile(File OldFile, String Path) {
		File NewFile = new File(Path);
		if (NewFile.exists()) {
			return false;
		}
		return OldFile.renameTo(NewFile);
	}

	public static boolean isYes(String temp) {
		return "Yes".equals(temp) || "YES".equals(temp) || "YEs".equals(temp) || "YeS".equals(temp) || "Y".equals(temp) || "y".equals(temp);
	}
}
