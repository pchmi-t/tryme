package com.tryme.core;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;


public final class PasswordService {

	/**
	 * An instance of PasswordService.
	 */
	private static PasswordService instance;

	/**
	 * A private constructor for singelton approach.
	 */
	private PasswordService() {
	}

	/**
	 * Encrypt the password.
	 * 
	 * @param plaintext the user's password in plain text.
	 * @return the encrypted password.
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 */
	public synchronized String encrypt(String plaintext) throws 
	NoSuchAlgorithmException, UnsupportedEncodingException{
	
		MessageDigest md = MessageDigest.getInstance("SHA");
		md.update(plaintext.getBytes("UTF-8"));
		byte raw[] = md.digest();
		String hash = Base64.getEncoder().encodeToString(raw);
		return hash;
	}

	/**
	 * Decrypt the password.
	 * 
	 * @param encryptedPassword
	 * @return
	 */
	public synchronized String decrypt(String encryptedPassword) {
		return null;
	}
	
	/**
	 * Get the instance.
	 * @return the PasswordService instance
	 */
	public static synchronized PasswordService getInstance() {
		return instance == null ? new PasswordService() : instance;
	}
}