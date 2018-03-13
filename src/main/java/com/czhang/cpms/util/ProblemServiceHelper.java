package com.czhang.cpms.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.UUID;

public class ProblemServiceHelper {

	public static UUID convertStringToUUID(String str){
		return new UUID(new BigInteger(str.substring(0, 16), 16).longValue(),
		        new BigInteger(str.substring(16), 16).longValue());
	}
	
	public static String readFromInputStream(InputStream inputStream) throws IOException {
		StringBuilder resultStringBuilder = new StringBuilder();
		try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
			String line;
			while ((line = br.readLine()) != null) {
				resultStringBuilder.append(line).append("\n");
			}
		}
		return resultStringBuilder.toString();
	}
}
