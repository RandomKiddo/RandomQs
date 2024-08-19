/*
Random Questions #3:
Convert a given integer N int words (i.e. 22 -> "Twenty Two")
Inspired by an Instagram post and a GeeksForGeeks Page (https://www.geeksforgeeks.org/convert-number-to-words/)

This solution is brute-force and is definitely not the neatest nor the most efficient way to solve
this problem. 

To run, run:
java NumberToWords.java --num <num>

Or edit the given run.sh file and run it in bash. 

The choice to use Java was completely arbitrary. 
*/

import java.util.*;

public class NumberToWords {
	public static void main(String[] args) {
		String arg = null;
		Integer N = 0; 
		for (int i = 0; i < args.length; ++i) {
			if (args[i].equalsIgnoreCase("--num")) {
				try {
					arg = args[i+1];
				} catch (IndexOutOfBoundsException err) {
					System.out.println("--num Error: No Number Specified");
					return;
				}
			}
		}
		if (arg == null) { 
			System.out.println("Arg Error: Missing Required Argument Flag --num <num>");
			return;
		} else {
			try {
				N = Integer.valueOf(arg);
			} catch (NumberFormatException err) {
				System.out.println("Number Format Error: Number Formatting Incorrectly Or Too Large");
				return;
			} 
		}

		System.out.println(N + " " + intToStr(N));
	}

	public static HashMap<Integer, String> getMappings() {
		/**
		 * Returns digit and number mappings to their string word (20 -> "Twenty")
		 * @return A HashMap mapping
		 * @see java.util.HashMap
		 * */

		HashMap<Integer, String> mappings = new HashMap<>(); 

		mappings.put(1, "One");
		mappings.put(2, "Two");
		mappings.put(3, "Three");
		mappings.put(4, "Four");
		mappings.put(5, "Five");
		mappings.put(6, "Six");
		mappings.put(7, "Seven");
		mappings.put(8, "Eight");
		mappings.put(9, "Nine");

		mappings.put(10, "Ten");
		mappings.put(11, "Eleven");
		mappings.put(12, "Twelve");
		mappings.put(13, "Thirteen");
		mappings.put(14, "Fourteen");
		mappings.put(15, "Fifteen");
		mappings.put(16, "Sixteen");
		mappings.put(17, "Seventeen");
		mappings.put(18, "Eighteen");
		mappings.put(19, "Nineteen");

		mappings.put(20, "Twenty");
		mappings.put(30, "Thirty");
		mappings.put(40, "Fourty");
		mappings.put(50, "Fifty");
		mappings.put(60, "Sixty");
		mappings.put(70, "Seventy");
		mappings.put(80, "Eighty");
		mappings.put(90, "Ninety");

		return mappings;
	}

	public static String intToStr(int N) {
		/**
		 * Converts an integer to a string in words (4 -> "Four")
		 * @param N Integer to convert
		 * @return The string representation in words
		 * */

		ArrayList<String> total = new ArrayList<>(); 
		String rstr = new StringBuilder().append(((Integer)N).toString()).reverse().toString();
		HashMap<Integer, String> mappings = getMappings();
		final String opts[] = { "", "Thousand", "Million", "Billion" };

		boolean combine = true; 
		int cnt = 0; 
		for (int i = 0; i < rstr.length(); ++i) {
			if (combine) {
				total.add(0, opts[cnt]);
				char first = rstr.charAt(i);
				char second;
				try {
					second = rstr.charAt(i+1);
				} catch (IndexOutOfBoundsException err) {
					second = '0';
				}
				if (second == '0') {
					total.add(0, mappings.get((int)(first-'0')));
				} else if (second == '1') {
					total.add(0, mappings.get(Integer.valueOf(second+""+first)));
				} else if (first != '0') {
					total.add(0, mappings.get((int)(first-'0')));
					total.add(0, mappings.get(Integer.valueOf(second+"0")));
				} else {
					total.add(0, mappings.get(Integer.valueOf(second+"0")));
				}
				++i;
				++cnt;
			} else {
				char c = rstr.charAt(i);
				if (c != '0') {
					total.add(0, "Hundred");
					total.add(0, mappings.get((int)(c-'0')));
				}
			}
			combine = !combine;
		}

		String last = "";
		for (String s : total) {
			last += s + " ";
		}

		return last;
	}
}