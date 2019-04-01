package net.srinathr.cheerio.support;

import org.springframework.security.core.context.SecurityContextHolder;

public class SessionUtil {
	
	public static String getLoggedInUserName() {
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}
	
	public static void invalidateSession() {
		SecurityContextHolder.clearContext();
	}

}
