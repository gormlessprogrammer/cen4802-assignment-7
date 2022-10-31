package test;
import spellcheck.Lilly_Spellchecker;

import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SpellCheckerTest {
	
	Lilly_Spellchecker test_Spellchecker;

	@BeforeEach
	void setUp() throws Exception {
		test_Spellchecker = new Lilly_Spellchecker();
	}
	
	@Test
	void forceCreateDictionaryDelete() throws IOException {
		if (new File("src/dictionary.txt").exists()) {
			System.out.println("Deleting src/dictionary.txt for science!");
			File dictionary = new File("src/dictionary.txt");
			dictionary.delete();
			System.out.println("Dictionary has been deleted!");
		}
		if (new File("src/testStates.txt").exists()) {
			System.out.println("Deleting src/testStates.txt for science...");
			File testState = new File("src/testStates.txt");
			testState.delete();
			System.out.println("testStates has been deleted!!!");
		}
		
		Lilly_Spellchecker.createDictionary();
		Assertions.assertTrue(new File("src/dictionary.txt").exists());
		// this is supposed to fail!!
		Assertions.assertFalse(new File("src/testStates.txt").exists());
		
	}
	

	@AfterEach
	void tearDown() throws Exception {
	}


}
