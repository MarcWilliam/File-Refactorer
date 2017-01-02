
import Models.Auto;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author marcw
 */
public class testAuto {

	public static void main(String[] args) {

		Test.t("hi", Auto.Movies("hi").equals("hi"));
		Test.t(" hi ", Auto.Movies(" hi ").equals("hi"));
		Test.t(" h i ", Auto.Movies(" h i ").equals("h i"));
		Test.t(" h--i ", Auto.Movies(" h--i ").equals("h-i"));
	}

}
