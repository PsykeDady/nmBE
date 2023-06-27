package co.psyke.nanosoftma.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public final class Encryption {
	private Encryption() {}


	public static String cryptedPsk(String psk) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(psk);
		return encodedPassword;
	}	
}
