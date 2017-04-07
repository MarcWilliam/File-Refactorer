/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author marcw
 */
public class AutoTest {

	public AutoTest() {
	}

	@BeforeClass
	public static void setUpClass() {
	}

	@AfterClass
	public static void tearDownClass() {
	}

	@Before
	public void setUp() {
	}

	@After
	public void tearDown() {
	}

	/**
	 * Test of Series method, of class Auto.
	 */
	@Test
	public void testSeries() {
		/*
		System.out.println("Series");
		String imput = "";
		String expResult = "";
		String result = Auto.Series(imput);
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");*/
	}

	/**
	 * Test of Movies method, of class Auto.
	 */
	@Test
	public void testMovies() {
		System.out.println("Movies");

		//assertEquals(expResult, result);
		assertEquals("hi", Auto.Movies("hi"));
		assertEquals("hi", Auto.Movies(" hi "));
		assertEquals("h i", Auto.Movies(" h i "));
		assertEquals("h-i", Auto.Movies(" h--i "));
	}

}
