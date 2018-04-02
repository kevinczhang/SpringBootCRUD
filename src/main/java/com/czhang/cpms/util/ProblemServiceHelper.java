package com.czhang.cpms.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.List;
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
	
	public static <E extends Enum<E>> int getIndex(List<E> enums, String str) {
		for(E e : enums){
			if(e.name().equals(str))
				return enums.indexOf(e);
		}
		return -1;
	}

	public static int[] convertToIntArray(String str) {
		if(str == null || str.length() == 0)
			return new int[]{};
		String[] strArray = str.split(", ");
		int[] res = new int[strArray.length];
		for(int i = 0; i < strArray.length; i++){
			res[i] = Integer.parseInt(strArray[i]);
		}
		return res;
	}
}
