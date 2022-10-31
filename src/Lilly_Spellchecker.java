//Name: Trevor Lilly
//Date: 1/16/2022
//Program Name: Lilly_Spellchecking
//Description: A program that looks at two separate lists, compares them for discrepancies. 

import static java.lang.System.out;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.PrintWriter;

public class Lilly_Spellchecker {

	public static void main(String[] args) throws IOException, FileNotFoundException {
		
	// preparing each arraylist, one for the dictionary the other for misspelled words
		ArrayList<String> dictionary = new ArrayList<>();
		ArrayList<String> testFile = new ArrayList<>();
		
		// establishing the directory where we're gonna keep our .txt files
		File srcFile = new File ("src/");
		FilenameFilter txtFiles = new FilenameFilter() {

			// a .txt file filter, since src/ points to a java file.
			@Override
			public boolean accept(File src, String name) {
				if (name.endsWith(".txt")) {
					return true;
				} else {
					return false;
				}
			}	
		};
		
		// For the sake of our sanity, i'm printing out the available file paths to choose from to make testing/choosing files easier
		File[] srcList = srcFile.listFiles(txtFiles);
		// if there's ONLY one file, we're going to make a generic dictionary & test file. so if you mindlessly press enter, you won't get an error
		if (srcList.length <= 1 ) {
			createDictionary();
			out.println(srcList.length +  " available file path(s) to choose from: \n");
			for (File file : srcList) {
				out.println("> " +file.getPath());
			}
		} else 		
		for (File file : srcList) {
			out.println("> " +file.getPath());
		// the program now checks specifically for the generic testfiles, if it's not there - it'll make them. 
			if (!new File("src/dictionary.txt").exists() || !new File("src/testStates.txt").exists()) {
				createDictionary();
			} 
		}
		out.println(srcList.length +  " available file path(s) to choose from: \n");

		
		// since we're not cool with hardcoded files... we're letting /you/, the user put in your own files. 
		out.print("\nEnter the file path of the dictionary file (example: src/dictionary.txt). "
				+ "\nIf left blank, the default dictionary file provided will be used. \n >>> ");
		Scanner dInput  = new Scanner(System.in);
		String dString 	= dInput.nextLine();
		
		// a cute little conditional that'll have you use the default dictionary file if you enter nothing. makes testing a cinch.
		if (dString.length() <= 0) {
			dString = ("src/dictionary.txt");
			out.println("Using default dictionary file: " + dString);
		}

		out.print("\nEnter the file path of the test file (example: src/testStates.txt). "
				+ "\nIf left blank, the default test file in the src/ directory will be used. \n >>> ");
		Scanner tInput  = new Scanner(System.in);
		String tString 	= tInput.nextLine();
		
		// likewise with the test file, if you enter nothing - you'll get the testStates file.
		if (tString.length() <= 0) {
		tString = ("src/testStates.txt");
		out.println("Using default test file: " + tString);
		}

		File dFile = new File(dString);
		
		// oh, did you type gibberish? haha get dictionary'd 
			if (!dFile.exists()) {
				out.println(dString + " cannot be resolved, using default dictionary file.");
				dFile = new File("src/dictionary.txt");
				dString = ("src/dictionary.txt");
			}
			
		File tFile = new File(tString);
		
		// if testfile no exist. u git da teststate, capiche??
		if (!tFile.exists()) {
			out.println(tString + " cannot be resolved, using default test file.");
			tFile = new File("src/testStates.txt");
			tString = ("src/testStates.txt");
		}
	
		//scanner to read each file
		Scanner dScan = new Scanner(dFile);
		Scanner tScan = new Scanner(tFile);
	
	//each line of the dictionary file is sent to an array list
		while (dScan.hasNext()) {
			String line = dScan.nextLine();
			dictionary.add(line);
		}
			dScan.close();
			dInput.close();
			
	//each line of the testfile is sent to an array list
		while (tScan.hasNext()) {
			String line = tScan.nextLine();
			testFile.add(line);
		}
			tScan.close();
			tInput.close();
	
			//both array lists are sent to be compared 		
			compare(dictionary, testFile, dString, tString);
			System.exit(0);
	}
	
	private static void compare(ArrayList<String> dictionary, ArrayList<String> testFile, String dString, String tString) {
		// so, for every word that doesn't match the dictionary file, it goes into the noMatch list
		ArrayList<String> noMatch = new ArrayList<>();
		
		// using a loop, we iterate through the dictionary and compare each word from testFile to it
		for (int i = 0; i < dictionary.size(); i++) {
		// for every item that doesn't match using the != conditional, they're added to the nomatch list
				if (!testFile.get(i).equals(dictionary.get(i))) {
					noMatch.add(testFile.get(i));
				} 
		}
		// just a conditional that'll print there's nothing "misspelled" if there's no mismatches. 
		// hey, the spell checker can't say you're wrong if the dictionary says you're correct, right?
		if (noMatch.size() == 0) {
			out.print("\nAccording to: " + dString + ", There are no misspelled words!");
		} else 	
		out.print("\nThere are " + noMatch.size() + " word(s) spelled incorrectly. \nHere is a list: " + noMatch);
	} 

	public static void createDictionary() {
		//out.println("Creating dictionary & testState file.");
		ArrayList<String> newDictionary = new ArrayList<>();
		newDictionary.add("Alabama");
		newDictionary.add("Alaska");
		newDictionary.add("Arizona");
		newDictionary.add("Arkansas");
		newDictionary.add("California");
		newDictionary.add("Colorado");
		newDictionary.add("Connecticut");
		newDictionary.add("Delaware");
		newDictionary.add("Florida");
		newDictionary.add("Georgia");
		
		ArrayList<String> newTestState = new ArrayList<>();
		newTestState.add("labama1");
		newTestState.add("Alaka");
		newTestState.add("Arizona");
		newTestState.add("Arkansas");
		newTestState.add("California");
		newTestState.add("Colorado");
		newTestState.add("Connecticut");
		newTestState.add("Delaware");
		newTestState.add("Florida");
		newTestState.add("Georgia");
		
		
		try { 
			PrintWriter dm = new PrintWriter("src/dictionary.txt");
			PrintWriter tm = new PrintWriter("src/testStates.txt");
			for (String state : newDictionary) {
				dm.println(state);
			}
			out.println("Generic dictionary file created.");
			for (String test : newTestState) {
				tm.println(test);
			}
			out.println("Generic test file created.");
			dm.close();
			tm.close();

		} catch (IOException e) {
			out.print("bruh wtf did u dooooooooooooooo lmao");
			e.printStackTrace();
		}
	}
}
