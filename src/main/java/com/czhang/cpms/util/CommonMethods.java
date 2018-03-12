package com.czhang.cpms.util;

import java.math.BigInteger;
import java.util.UUID;

public class CommonMethods {

	public static UUID convertStringToUUID(String str){
		return new UUID(new BigInteger(str.substring(0, 16), 16).longValue(),
		        new BigInteger(str.substring(16), 16).longValue());
	}
}
