package com.rest.hash;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.rest.exceptions.HashException;

public class HashGenerator {
	public static String generateHash(String text) throws HashException {
		try {
			MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
			byte[] passBytes = text.getBytes();
			byte[] passHash = sha256.digest(passBytes);
			return passHash.toString();
		} catch (NoSuchAlgorithmException e) {
			throw new HashException(e.getCause());
		}

	}
}
