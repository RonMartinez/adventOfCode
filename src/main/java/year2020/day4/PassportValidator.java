package year2020.day4;

import org.apache.commons.lang3.StringUtils;

public class PassportValidator {

	public boolean isValid(Passport passport) {
		return StringUtils.isNotBlank(passport.getBirthYear())
				&& StringUtils.isNotBlank(passport.getIssueYear())
				&& StringUtils.isNotBlank(passport.getExpirationYear())
				&& StringUtils.isNotBlank(passport.getHeight())
				&& StringUtils.isNotBlank(passport.getHairColor())
				&& StringUtils.isNotBlank(passport.getEyeColor())
				&& StringUtils.isNotBlank(passport.getPassportId())
//				&& StringUtils.isNotBlank(passport.getCountryId())
				;
	}
}
