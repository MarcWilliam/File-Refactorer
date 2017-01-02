/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author marcw
 */
public class Test {

	public static boolean t(String Name, boolean state) {
		if (state) {
			System.out.println("Sucsess : " + Name);
		} else {
			System.err.println("Failed  : " + Name);
		}
		return state;
	}
}
