package year2020.day4;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

public class PassportValidatorb {

	public boolean isValid(Passport passport) {
		return isValidBirthYear(passport)
				&& isValidIssueYear(passport)
				&& isValidExpirationYear(passport)
				&& isValidHeight(passport)
				&& isValidHairColor(passport)
				&& isValidEyeColor(passport)
				&& isValidPassportId(passport)
				&& isValidCountryId(passport);
	}

	private boolean isValidCountryId(Passport passport) {
		return true;
		// return StringUtils.isNotBlank(passport.getCountryId());
	}

	private boolean isValidPassportId(Passport passport) {
		String passportId = passport.getPassportId();
		if(StringUtils.isBlank(passportId)) {
			return false;
		}

		Pattern pattern = Pattern.compile("^\\d{9}$");
		Matcher matcher = pattern.matcher(passportId);
		return matcher.find();
	}

	private boolean isValidEyeColor(Passport passport) {
		List<String> validColors = Arrays.asList(
				"amb",
				"blu",
				"brn",
				"gry",
				"grn",
				"hzl",
				"oth"
				);
		String eyeColor = passport.getEyeColor();
		return validColors.contains(eyeColor);
	}

	private boolean isValidHairColor(Passport passport) {
		String hairColor = passport.getHairColor();
		if(StringUtils.isBlank(hairColor)) {
			return false;
		}

		Pattern pattern = Pattern.compile("^#[0-9a-f]{6}$");
		Matcher matcher = pattern.matcher(hairColor);
		return matcher.find();
	}

	private boolean isValidHeight(Passport passport) {
		String height = passport.getHeight();
		if(StringUtils.isBlank(height)) {
			return false;
		}
		
		Integer heightNumber = null;
		String units = null;
		
		Pattern pattern = Pattern.compile("^(\\d+)(cm|in)$");
		Matcher matcher = pattern.matcher(height);
		if(matcher.find()) {
			heightNumber = parseInt(matcher.group(1));
			units = matcher.group(2);
		}
		
		return heightNumber != null
				&& units != null
				&& (
					(
							"cm".equals(units)
							&& 150 <= heightNumber
							&& heightNumber <= 193
					)
					||
					(
							"in".equals(units)
							&& 59 <= heightNumber
							&& heightNumber <= 76
					)
				);
	}

	private boolean isValidExpirationYear(Passport passport) {
		String expirationYear = passport.getExpirationYear();
		if(StringUtils.isBlank(expirationYear)) {
			return false;
		}

		Pattern pattern = Pattern.compile("^\\d+{4}$");
		Matcher matcher = pattern.matcher(expirationYear);
		
		Integer integer = parseInt(expirationYear);
		return integer != null
				&& 2020 <= integer
				&& integer <= 2030
				&& matcher.find();
	}

	private boolean isValidIssueYear(Passport passport) {
		String issueYear = passport.getIssueYear();
		if(StringUtils.isBlank(issueYear)) {
			return false;
		}

		Pattern pattern = Pattern.compile("^\\d+{4}$");
		Matcher matcher = pattern.matcher(issueYear);
		
		Integer integer = parseInt(issueYear);
		return integer != null
				&& 2010 <= integer
				&& integer <= 2020
				&& matcher.find();
	}

	private boolean isValidBirthYear(Passport passport) {
		String birthYear = passport.getBirthYear();
		if(StringUtils.isBlank(birthYear)) {
			return false;
		}

		Pattern pattern = Pattern.compile("^\\d+{4}$");
		Matcher matcher = pattern.matcher(birthYear);
		
		Integer integer = parseInt(birthYear);
		return integer != null
				&& 1920 <= integer
				&& integer <= 2002
				&& matcher.find();
	}

	private Integer parseInt(String value) {
		Integer integer = null;
		try {
			integer = Integer.parseInt(value);	
		} catch (NumberFormatException  e) {
			// TODO: handle exception
		}
		return integer;
	}

}
