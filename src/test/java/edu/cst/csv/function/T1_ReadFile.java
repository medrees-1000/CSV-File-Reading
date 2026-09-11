package edu.cst.csv.function;

import java.util.function.Function;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Iterator;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import edu.cst.csv.function.records.Stock;

public class T1_ReadFile {
	
	/*
	 * Developer: Mohammad, Bahar
	 */

	static String fileName = "C:\\Users\\idree\\Desktop\\Downloads\\Energy-CST3650.csv";
	
	@Test void t0() {
		
		Function<String,Float> cleanUp = e -> {
			String currentValue = e.trim();
			float returnValue = 0;
			if (!currentValue.equals("-")) 
				returnValue = Float.parseFloat(currentValue);
			return returnValue;
		};
		
		Function<String, Stock>  stocksFunction = row -> {
			
			String[] columns = row.split(",");
			
			int rank = Integer.parseInt(columns[0]);
			String symbol = columns[1];
			float rating = Float.parseFloat(columns[2]);
			float marketCapInBillions = Float.parseFloat(columns[3]) / 1_000_000_000 ;
			float dividend = cleanUp.apply(columns[4]);
			
			return new Stock(rank, symbol, rating, marketCapInBillions,dividend);
		};
		
		int actual = ReadFile.instanceOf(fileName, stocksFunction).execute();
		int expected = 232;
		
		assertEquals(expected, actual);
	}
    
	@Test void t1() {
		
		Function<String, String> hello = e -> "function function what is your function!";
		
		int actual = ReadFile.instanceOf(fileName, String::toLowerCase).execute();
		int expected = 232;
		
		assertEquals(expected, actual);
	}
	
	@Test void t2() {
		
		Predicate<Float> isGoodDividend =  e -> e > 0.03F;
		float[] divends = {.01f, 0.03f, 0.04f, 0.05f};
		
		for (int i = 0; i < divends.length; i++) {
			if (isGoodDividend.test(divends[i])) 
				System.out.println("yeild:" + divends[i]);
		}
	}
	
	@Test void t3() {
		Function<Float, String> isPassing = e -> e > 65.999 ? "passing" : "failing";
		float[] score = { 61f, 67f, 73f, 44f};
		
		for (int i = 0; i < score.length; i++) {
			String status = isPassing.apply(score[i]);
			
			if (status.equals("passing")) 
				System.out.println("Score :" + score[i] + " " + status);	
			else 
				System.out.println("Score :" + score[i] + " " + status);
			
		}
	}
}
