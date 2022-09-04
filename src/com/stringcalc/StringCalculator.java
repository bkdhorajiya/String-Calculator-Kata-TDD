package com.stringcalc;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {
	int diff,start;
    String c;
	public int add(String numbers) {
		String num[] = numberseparator(numbers);
		int n = num.length;
		throwExceptionForNegative(num, n);
		return calsum(num,n);
	}
	
	private String[] numberseparator(String numbers) {
		start = 0;
		diff = 1;
		if(numbers.isEmpty())
			return new String[0];
		else if(isEvenprecededCustomDelimiter(numbers))
		{
				start = 1;
				diff = 2;
                c="1";
				return splitUsingCustomDelimiter(numbers);
		}
		else if(isOddprecededCustomDelimiter(numbers))
		{
				start = 0;
				diff = 2;
                c="0";               
				return splitUsingCustomDelimiter(numbers);
		}
		else if(isCustomDelimiterString(numbers))
		{
                c="";
				return splitUsingCustomDelimiter(numbers);
		}
		return splitUsingCommaAndNewLine(numbers);
	}
	
	private boolean isOddprecededCustomDelimiter(String numbers) {
		return numbers.startsWith("0//");
	}
	
	private boolean isEvenprecededCustomDelimiter(String numbers) {
		return numbers.startsWith("1//");
	}
	
	private String[] splitUsingCustomDelimiter(String numbers) {
		Matcher m = Pattern.compile(c + "//(.*)\n(.*)").matcher(numbers);
		m.matches();
		String customDelim = m.group(1);
		String num=m.group(2);
		return num.split(Pattern.quote(customDelim));
	}
	
	private String[] splitUsingCommaAndNewLine(String numbers) {
		String[] num=numbers.split(",|\n");
		return num;
	}
	
	private boolean isCustomDelimiterString(String numbers) {
		return numbers.startsWith("//");
	}	
		
	private void throwExceptionForNegative(String[] num, int n) {
		ArrayList<String> negative = new ArrayList<String>();
		
		for(int i=0; i<n; i++) {
			if(isnumber(num[i]) && toInt(num[i])<0) {
				negative.add(num[i]);
			}
		}
		if(negative.size()>0) {
			throw new RuntimeException("negatives not allowed: " + String.join(", ",negative));
		}
	}
	private int calsum(String[] num, int n) {
		int sum=0;
		for(int i=start;i<n;i+=diff)
		{
			if(isnumber(num[i]) && toInt(num[i])<1000) {
				sum += addnumbervalue(num[i]);
			}
			else if(!isnumber(num[i]))	
			{
				sum += addalphavalue(num[i]);
			}
		}
		return sum;
	}
	
	private int toInt(String num) {
		return Integer.parseInt(num);
	}
	
	private int addalphavalue(String num) {
		return (num.charAt(0) - 'a' + 1);
	}

	private int addnumbervalue(String num) {
		return (num.charAt(0) - '0');
	}

	private boolean isnumber(String num) {
		return ((int)num.charAt(0) <= 57);		
	}
}
