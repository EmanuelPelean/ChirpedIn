/**
 * Just an area to test out code snippets
 */
package com.chirpedin.util;

/**
 * @author timjohnson
 *
 */
public class SandBox {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		String firstString = "jsp dog cat HTML jss";
		String secondString = "jsp no elephant HTML jss";

		System.out.println(createListOfCommonWordsInTwoStrings(firstString, secondString));

	}

	private static String createListOfCommonWordsInTwoStrings(String firstString, String secondString) {
		String commonElementsString = "";
		String[] firstArr = firstString.split(" ");
		String[] secondArr = secondString.split(" ");

		for (String word : firstArr) {
			for (int i = 0; i < secondArr.length; i++) {
				if (secondArr[i].equals(word))
					commonElementsString += word + " ";
			}
		}
		return commonElementsString;
	}
}
