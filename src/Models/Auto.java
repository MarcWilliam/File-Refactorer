/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author marcw
 */
public class Auto {

	static BulkRefactor.IRename Series = (final CFile file, final String... params) -> {
		file.newName = Auto.Series(file.oldName);
	};

	static BulkRefactor.IRename Movies = (final CFile file, final String... params) -> {
		file.newName = Auto.Movies(file.oldName);
	};

	static public String Series(String imput) {
		imput = RemoveChar(imput);
		imput = RemoveWords(imput);
		imput = TrimStringStart(imput);
		imput = TrimStringEnd(imput);

		return imput;
	}

	static public String Movies(String imput) {
		imput = RemoveChar(imput);
		imput = RemoveWords(imput);
		imput = TrimStringStart(imput);
		imput = TrimStringEnd(imput);

		return imput;
	}

	static private String TrimStringStart(String imput) {

		char[] Result = imput.trim().toCharArray();
		String output = "";

		for (int i = 0; i < Result.length; i++) {

			//remove leading - & . & _
			if (output.isEmpty() && Result[i] == '.') {
				continue;
			}

			output += Result[i];
		}

		return output;
	}

	static private String TrimStringEnd(String imput) {

		char[] Result = imput.trim().toCharArray();
		int endIndex = 0;

		for (int i = Result.length - 1; i >= 0; i--) {
			if (Result[i] == '.') {
				endIndex++;
			}
			break;
		}

		return new String(Result, 0, Result.length - endIndex);
	}

	static private String RemoveChar(String imput) {

		char[] Result = imput.trim().toCharArray();
		String output = Result.length > 0 ? "" + Result[0] : "";
		for (int i = 1; i < Result.length; i++) {

			// if consecutive spaces & - don't add
			if ((Result[i - 1] == ' ' && Result[i] == ' ')
					|| (Result[i - 1] == '-' && Result[i] == '-')
					|| (Result[i - 1] == '[' && Result[i] == '[')
					|| (Result[i - 1] == ']' && Result[i] == ']')
					|| (Result[i - 1] == '(' && Result[i] == '(')
					|| (Result[i - 1] == ')' && Result[i] == ')')) {
				// if consecutive spaces & - don't add
			} else if (Result[i] == '.' && !(Character.isUpperCase(Result[i - 1]))) {
				// replace . with a space if the character befor it is not uppercase
				output += ' ';
			} else if (Result[i] == '_') {
				// remove _
				output += ' ';
			} else {
				output += Result[i];
			}
		}
		return output;
	}

	static private String RemoveWords(String imput) {
		String[] Arr = {
			"HDTV ",
			"x264",
			"[eztv]",
			"1080p",
			"720p",
			"BluRay",
			"YIFY",
			"BrRip"
		};
		for (int i = 0; i < Arr.length; i++) {
			imput = imput.replace(Arr[i], "");
		}
		return imput;
	}

	static private String FixYear(String imput) {
		return imput;
	}

	static private String FixSerie(String imput) {
		return imput;
	}
}
