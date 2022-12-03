package year2020.day2;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.IOUtils;

public class Day2b {
	
//	private static final String FILENAME = "src/main/resources/2020/day2InputSample.txt";
	private static final String FILENAME = "src/main/resources/2020/day2Input.txt";

	public static void main(String[] args) throws IOException {
		List<Password> passwords = readPasswords();
		
		long count = passwords.stream()
				.filter(Password::isValidByPosition)
				.count();
		
		System.out.println("result: " + count);
	}
	
	private static List<Password> readPasswords() throws IOException {
		List<String> lines = IOUtils.readLines(new FileInputStream(FILENAME), StandardCharsets.UTF_8);
		
		return lines.stream()
				.map(line -> processLine(line))
				.collect(Collectors.toList());
	}

	private static Password processLine(String line) {
		String[] split = line.split(" ");
		String[] minMax = split[0].split("-");
		
		int minimum = Integer.parseInt(minMax[0]);
		int maximum = Integer.parseInt(minMax[1]);
		char character = split[1].charAt(0);
		String password = split[2];
		
		System.out.println(minimum);
		System.out.println(maximum);
		System.out.println(character);
		System.out.println(password);
		
		PasswordPolicy passwordPolicy = new PasswordPolicy(minimum, maximum, character);
		return new Password(password, passwordPolicy);
	}

}
