package edu.cst.csv.function;

import java.io.File; // Import the File class
import java.io.FileNotFoundException; // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
import java.util.function.Function;

/*
 * Developer: Mohammad, Bahar
 */

public class ReadFile <T> {
	
	private String fileName;
	private Function<String, T> function;
	private int skipRow = 1;

	private ReadFile(String fileName, Function<String, T> function) {
		this.fileName = fileName;
		this.function = function;
	}
	
	private static <T> int process(final String fileName, Function<String, T> function, int skipRow) {
		File myObj = new File(fileName);
		
		int operationCount = 0;

		// try-with-resources: Scanner will be closed automatically
		try (Scanner myReader = new Scanner(myObj)) {
			
			for (int i = 0; i < skipRow; i++) {
				 myReader.nextLine();
			}
			
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				T t = function.apply(data);
				System.out.println(t); // Match: printing the transformed object 't'
				operationCount++; 
			}
		} catch (Exception e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		
		return operationCount;
	}
	
	public static <T> ReadFile <T> instanceOf(String fileName, Function<String, T> function) {
		return new ReadFile <T>(fileName, function);
	}
	
	public int execute() {
		return process(this.fileName, this.function, 1);
	}
}