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
public class StringManipulation {

	/**
	 * The following method converts all the letters into upper/lower case, depending on their position near a space or other special chars.
	 *
	 * @param imput
	 * @return
	 */
	public static String capitalizeString(String imput) {
		char[] chars = imput.toLowerCase().toCharArray();
		boolean found = false;
		for (int i = 0; i < chars.length; i++) {
			if (!found && Character.isLetter(chars[i])) {
				chars[i] = Character.toUpperCase(chars[i]);
				found = true;
			} else if (Character.isWhitespace(chars[i]) || chars[i] == '.' || chars[i] == '\'') { // You can add other chars here
				found = false;
			}
		}
		return String.valueOf(chars);
	}

	/**
	 * example givenString = "ram is good boy"
	 * Output will be: Ram Is Good Boy
	 *
	 * @param imput
	 * @return
	 */
	public static String toTitleCase(String imput) {
		String[] arr = imput.split(" ");
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < arr.length; i++) {
			sb.append(Character.toUpperCase(arr[i].charAt(0)))
					.append(arr[i].substring(1)).append(" ");
		}
		return sb.toString().trim();
	}
}
