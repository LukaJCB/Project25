package com.ltj.java.utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class JoglTextResourceReader {
public static String readTextFileFromResource(String path){
	BufferedReader buffReader = null;
	StringBuilder body = new StringBuilder();
	try {
		InputStream in = new FileInputStream(path);
		InputStreamReader inReader = new InputStreamReader(in);
		buffReader = new BufferedReader(inReader);

		String nextLine;

		while ((nextLine = buffReader.readLine()) != null){
			body.append(nextLine);
			body.append('\n');
		}
	} catch (IOException e) {
		e.printStackTrace();
	} finally {

		try {
			if (buffReader != null){
				buffReader.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

		return body.toString();
	}
}
