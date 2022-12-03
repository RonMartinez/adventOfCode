package year2020.day4;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

public class Day4b {
	
//	private static final String FILENAME = "src/main/resources/2020/day4bInputValid.txt";
//	private static final String FILENAME = "src/main/resources/2020/day4bInputInvalid.txt";
	private static final String FILENAME = "src/main/resources/2020/day4Input.txt";
	
	public static void main(String[] args) throws IOException {
		List<Passport> passports = readPassports();
		
		PassportValidatorb passportValidatorb = new PassportValidatorb();
		
		List<Passport> validPassports = passports.stream()
				.filter(passportValidatorb::isValid)
				.collect(Collectors.toList());
		
		System.out.println(validPassports.size());
	}
	
	private static List<Passport> readPassports() throws IOException {
		List<String> lines = IOUtils.readLines(new FileInputStream(FILENAME), StandardCharsets.UTF_8);
		
		List<Passport> passports = new ArrayList<>();
		
		Passport passport = null;
		
		for(String line : lines) {
			if(StringUtils.isBlank(line)) {
				passport = addPassport(passports, passport);
			} else {
				if(passport == null) {
					passport = new Passport();
				}
				processLine(passport, line);
			}
		}
		passport = addPassport(passports, passport);
		
		return passports;
	}

	private static Passport addPassport(List<Passport> passports, Passport passport) {
		if(passport != null) {
			passports.add(passport);
			passport = null;
		}
		return passport;
	}

	private static void processLine(Passport passport, String line) {
		List<String> properties = Arrays.asList(StringUtils.split(line, " "));
		
		properties.forEach(p -> processProperty(passport, p));
	}

	private static void processProperty(Passport passport, String property) {
		String[] keyValuePair = StringUtils.split(property, ":");
		String key = keyValuePair[0];
		String value = keyValuePair[1];

		if("byr".equals(key)) {
			passport.setBirthYear(value);
		} else if("iyr".equals(key)) {
			passport.setIssueYear(value);
		} else if("eyr".equals(key)) {
			passport.setExpirationYear(value);
		} else if("hgt".equals(key)) {
			passport.setHeight(value);
		} else if("hcl".equals(key)) {
			passport.setHairColor(value);
		} else if("ecl".equals(key)) {
			passport.setEyeColor(value);
		} else if("pid".equals(key)) {
			passport.setPassportId(value);
		} else if("cid".equals(key)) {
			passport.setCountryId(value);
		} else {
			System.out.println("Unknown key: " + key);
		}
	}

}
