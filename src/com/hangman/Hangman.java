package com.hangman;

import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Hangman {

	public static void main(String[] args) {
		
		System.out.println("Welcome to Hangman!");
		
		String randomWord = "";
		String correctWord = "";
		int wordLength = 0;
		int guessCount = 8;
		Scanner scanner = new Scanner(System.in);
		List<Character> guessLetters = new ArrayList<>();
		
		
		boolean checkValid = false;
		ArrayList<String> randomwordlist = readWordList("Random_Words.txt");
		
		// Check if the randomly generated word contains only letters
		while (!checkValid) {
			randomWord = getRandomWord(randomwordlist);
			wordLength = randomWord.length();
		
			for (int i = 0; i < wordLength; i++) {			
				char a = randomWord.charAt(i);
				if(!Character.isLetter(a)) {
					continue;
				}	
				
			checkValid = true;			
			}
		}
				
		//System.out.println(randomWord);
		//System.out.println(wordLength);
		correctWord = randomWord.toUpperCase();
		
		while(true) {
				
			int rightCount = 0;
			System.out.println();
			System.out.println("You have " + guessCount + " guesses left.");
			System.out.print("Your guess: ");
			String inputGuessLetter = scanner.next();
			Character firstCharGuess = inputGuessLetter.charAt(0);
		
			if (guessLetters.contains(Character.toUpperCase(firstCharGuess))) {
				System.out.println("Please input another letter.");
				continue;
			}
			
			guessLetters.add(Character.toUpperCase(firstCharGuess));
			
			
			if (correctWord.contains(inputGuessLetter.toUpperCase())){
				System.out.println("That guess is correct.");
				
				System.out.print("The word now looks like this: ");

				for (int j = 0; j < wordLength; j++) {
					if (guessLetters.contains(correctWord.charAt(j))) {
						
						System.out.print(correctWord.charAt(j));
						rightCount++;
					} else {
						System.out.print("-");
					}
				}
				System.out.println();
				if (rightCount == wordLength) {
					System.out.println("You guessed the word: " + correctWord);
					System.out.println("You win.");
					break;
				}	
	
			} else {
				System.out.println("There are no " + inputGuessLetter.toUpperCase() + "'s in the word.");
				guessCount--;
				if (guessCount == 0) {
					System.out.println("You are completely hung.");
					System.out.println("The word was " + correctWord);
					System.out.println("You lose.");
					break;
				} else {
					System.out.print("The word now looks like this: ");

					for (int j = 0; j < wordLength; j++) {
						if (guessLetters.contains(correctWord.charAt(j))) {
							
							System.out.print(correctWord.charAt(j));
						} else {
							System.out.print("-");
						}
					}
				}
				
			}	
		}	
		scanner.close();
	}
		
	private static ArrayList<String> readWordList(String filename){
		
		ArrayList<String> randomwordlist = new ArrayList<>();
		
		try(BufferedReader reader = new BufferedReader(new FileReader(filename))){
			String line;
			while ((line = reader.readLine())!= null) {
				randomwordlist.add(line.trim());
			}
		} catch (IOException e) {
				e.printStackTrace();
		}
			
		return randomwordlist;		
	}
		
	private static String getRandomWord(ArrayList<String> randomwordlist) {
		Random random = new Random();
		int position = random.nextInt(randomwordlist.size());
		return randomwordlist.get(position);
	}
}
		
		
			
			

			
		
		
		
		



