package com.stringcalc;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class StringCalculatorTest {
	/*
	 * Test-1
	 * take numbers as a string separated by comma and will return their sum
	 */	
	
	//task- should return 0 on Empty string
	@Test
	void given_EmptyString_return_0() {
		assertEquals(new StringCalculator().add(""), 0);
	}
	
	//task :  For single character in string should return that character
	@Test
	void given_SingleCharString_return_thatchar() {
		assertEquals(new StringCalculator().add("1"), 1);
	}
	
	//task :  For comma separated numbers return addition of all the numbers
	@Test
	void given_numberstring_return_sum() {
		assertEquals(new StringCalculator().add("1,2"), 3);
	}

	//Test - 2 Allow to handle unknown amount of numbers
	@Test
	void ShouldAllowtoHandleUnknownAmountofNumber() {
		assertEquals(new StringCalculator().add("1,2,3,5,6"), 17);
	}
	
	//Test - 3 Allow alphabets to be included with numbers
	@Test
	void ShouldAllowtoHandleAlphabetwithNumber() {
		assertEquals(new StringCalculator().add("1,e,d,5,6"), 21);
	}
	
	//Test - 4,5 Throw Exception for negative numbers and print all negative
	@Test
	void given_Negative_return_Exception() {
		
		try{
			new StringCalculator().add("-1,-2,3");
			fail("Exception should be thrown");
		}
		catch(RuntimeException e) {
			String s ="negatives not allowed: -1, -2";	
			assertEquals(s,e.getMessage());
		}
	}
	
	//Test - 6 Number bigger than 1000 should be ignored
	@Test
	void shouldIngnoreNumberBiggerThan1000() {
		assertEquals(new StringCalculator().add("2,1000"),2);		
	}
	
	//Test - 7 Allow Newline instead of comma
	@Test
	void AllowNewlineInsteadofComma() {
		assertEquals(new StringCalculator().add("1\n2,3"),6);		
	}
	
	//Test - 8: Support different delimiters
	@Test
	public void SupportDifferentDelimiters() {
		assertEquals(new StringCalculator().add("//;\n1;2"),3);
	}
	
	//Test - 9: Support Odd/Even Addition - Checking odd Addition
	@Test
	public void oddAddition() {
		assertEquals(new StringCalculator().add("0//**\n1**2**3"),4);
	}
	
	//Test - 9: Support Odd/Even Addition - Checking Even Addition
	@Test
	public void EvenAddition() {
		assertEquals(new StringCalculator().add("1//**\n1**2**3"),2);
	}
}
