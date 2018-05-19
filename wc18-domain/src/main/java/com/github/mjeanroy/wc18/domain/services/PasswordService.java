package com.github.mjeanroy.wc18.domain.services;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class PasswordService {

	/**
	 * Encode plain text password to an encrypted hash value.
	 *
	 * @param plainText Plain text password.
	 * @return Encrypted value.
	 */
	public String encode(String plainText) {
		return BCrypt.hashpw(plainText, BCrypt.gensalt());
	}

	public boolean match(String plainText, String hash) {
		return BCrypt.checkpw(plainText, hash);
	}
}
