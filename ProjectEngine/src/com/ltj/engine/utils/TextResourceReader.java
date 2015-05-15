package com.ltj.engine.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.content.Context;

public class TextResourceReader {

	private TextResourceReader(){
	
	}
	
	public static String readTextFileFromResource(Context c, int resId){
		
		StringBuilder body = new StringBuilder();
		try {
			InputStream in = c.getResources().openRawResource(resId);
			InputStreamReader inReader = new InputStreamReader(in);
			BufferedReader buffReader = new BufferedReader(inReader);
			
			String nextLine;
			
			while ((nextLine = buffReader.readLine()) != null){
				body.append(nextLine);
				body.append('\n');
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return body.toString();
	}
}
