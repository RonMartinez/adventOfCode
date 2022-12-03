package year2020.day1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day1Original {
	
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader("/home/administrator/day1Input.txt"));
		
		List<Integer> integers = new ArrayList<>();

		String line = reader.readLine();
		while(line != null) {
			System.out.println(line);
			integers.add(Integer.parseInt(line));

			line = reader.readLine();
		}
		
		int number1 = 0;
		int number2 = 0;
		int number3 = 0;
		
		boolean found = false;
		for(int i = 0; i < integers.size() && !found; i++) {
			number1 = integers.get(i);
			for(int j = i+1; j < integers.size() && !found; j++) {
				number2 = integers.get(j);
				for(int k = j+1; k < integers.size() && !found; k++) {
					number3 = integers.get(k);	

					if((number1 + number2 + number3) == 2020) {
						found = true;
					}
				}
			}
		}
		
		System.out.println("number1: " + number1);
		System.out.println("number2: " + number2);
		System.out.println("number3: " + number3);
		System.out.println("result: " + number1 * number2 * number3);
		
	}

}
