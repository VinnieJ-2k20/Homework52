package com.gmail.ptimofejev;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

/* 2. Напишите программу, которая примет на вход два
текстовых файла, а вернет один. Содержимым этого файла
должны быть слова, которые одновременно есть и в первом и
во втором файле. */

public class Main {

	public static void main(String[] args) {

		File source1 = new File("Source 1.txt");
		File source2 = new File("Source 2.txt");
		File combined = new File("combined.txt");
		try {
			System.out.println(combined.createNewFile());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		writeToFile(combined, selectCommonWords(separateWords(readFileToString(source1)), separateWords(readFileToString(source2))));
		
	}

	public static String readFileToString(File input) {
		StringBuilder sb1 = new StringBuilder();
		String nextline = null;
		try (BufferedReader br1 = new BufferedReader(new FileReader(input))) {
			while ((nextline = br1.readLine()) != null) {
				sb1.append(nextline);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb1.toString();
	}

	public static String[] separateWords(String input) {
		String[] words = new String[0];
		StringTokenizer st1 = new StringTokenizer(input, ",.:; ()\"");
		while (st1.hasMoreTokens()) {
			words = Arrays.copyOf(words, words.length + 1);
			words[words.length - 1] = st1.nextToken();
		}
		return words;
	}

	public static String[] selectCommonWords(String[] first, String[] second) {
		String[] result = new String[0];
		for (int i = 0; i < first.length; i++) {
			if (isRepeatedWord(first, i)) {
				continue;
			}
			for (int j = 0; j < second.length; j++) {
				if (first[i].equals(second[j])) {
					result = Arrays.copyOf(result, result.length + 1);
					result[result.length - 1] = first[i];
					break;
				}
			}
		}
		return result;
	}

	public static boolean isRepeatedWord(String[] words, int index) {
		for (int i = 0; i < index; i++) {
			if (words[i].equals(words[index])) {
				return true;
			}
		}
		return false;
	}

	public static void writeToFile(File file, String[] toWrite) {
		try (BufferedWriter bw1 = new BufferedWriter(new FileWriter(file))) {
			for (int i = 0; i < toWrite.length; i++) {
				bw1.write(toWrite[i]);
				bw1.newLine();
			}
			System.out.println("File \"combined.txt\" successfully written.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
