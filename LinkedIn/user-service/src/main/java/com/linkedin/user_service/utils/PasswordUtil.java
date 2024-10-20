package com.linkedin.user_service.utils;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Component;

@Component
public class PasswordUtil {

	public static String hashPassword(String plainTextPassword){
		return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
	}

	public static boolean checkPassword(String plainTextPassword, String hasedPassword){
		return BCrypt.checkpw(plainTextPassword,hasedPassword);
	}

}
