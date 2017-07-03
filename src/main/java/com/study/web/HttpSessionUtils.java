package com.study.web;

import javax.servlet.http.HttpSession;

import com.study.domain.User;

public class HttpSessionUtils {
	public static final String USER_SESSION_KEY = "sessionedUser";
	
	public static boolean isLoginUser(HttpSession session){
		return session.getAttribute(USER_SESSION_KEY) != null;
	}
	
	public static User getUserFromSession(HttpSession session){
		if(isLoginUser(session)){
			return null;
		}
		return (User)session.getAttribute(USER_SESSION_KEY);
	}
}
