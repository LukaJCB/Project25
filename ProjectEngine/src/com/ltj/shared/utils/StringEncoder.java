package com.ltj.shared.utils;

import android.annotation.SuppressLint;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

public class StringEncoder {

	private Cipher ecipher;
	private SecretKey key;
	

	@SuppressLint("TrulyRandom") 
	public StringEncoder(){
		try {
			ecipher = Cipher.getInstance("AES");
			KeyGenerator keyGen = KeyGenerator.getInstance("AES");
			key = keyGen.generateKey();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		}
	}

	public String encrypt(String str) {
		try {
			ecipher.init(Cipher.ENCRYPT_MODE, key);
			byte[] utf8 = str.getBytes("UTF8");

			byte[] enc = ecipher.doFinal(utf8);

			return new sun.misc.BASE64Encoder().encode(enc);
		} catch (javax.crypto.BadPaddingException e) {
		} catch (IllegalBlockSizeException e) {
		} catch (UnsupportedEncodingException e) {
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String decrypt(String str) {
		try {
			ecipher.init(Cipher.DECRYPT_MODE, key);
			byte[] dec = new sun.misc.BASE64Decoder().decodeBuffer(str);

			byte[] utf8 = ecipher.doFinal(dec);

			return new String(utf8, "UTF8");
		} catch (javax.crypto.BadPaddingException e) {
		} catch (IllegalBlockSizeException e) {
		} catch (UnsupportedEncodingException e) {
		} catch (java.io.IOException e) {
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
