package year2020.day2;

import org.apache.commons.lang3.StringUtils;

public class Password {
	
	private String password;
	private PasswordPolicy passwordPolicy;

	public Password(String password, PasswordPolicy passwordPolicy) {
		this.password = password;
		this.passwordPolicy = passwordPolicy;
	}
	
	public boolean isValid() {
		int matches = StringUtils.countMatches(password, passwordPolicy.getCharacter());
		return matches >= passwordPolicy.getMinimum()
				&& matches <= passwordPolicy.getMaximum();
	}

	public boolean isValidByPosition() {
		boolean firstMatch = password.charAt(passwordPolicy.getMinimum() - 1) == passwordPolicy.getCharacter();
		boolean secondMatch = password.charAt(passwordPolicy.getMaximum() - 1) == passwordPolicy.getCharacter();
		return firstMatch ^ secondMatch;
	}

	/* Getters / Setters */

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public PasswordPolicy getPasswordPolicy() {
		return passwordPolicy;
	}

	public void setPasswordPolicy(PasswordPolicy passwordPolicy) {
		this.passwordPolicy = passwordPolicy;
	}
	
}
