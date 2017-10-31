package ts24.com.vn.web.security;

import org.springframework.security.authentication.jaas.JaasGrantedAuthority;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import ts24.com.vn.web.security.jass.UserPrincipal;

public class AuthenticationUtilities {

	public static UserPrincipal getCurrentJassUser(){
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			JaasGrantedAuthority jaasGrantedAuthority = (JaasGrantedAuthority)(auth.getAuthorities().toArray()[0]);		
			UserPrincipal userPrincipal = (UserPrincipal)jaasGrantedAuthority.getPrincipal();
			return userPrincipal;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public static User getCurrentUser(){
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			return (User) auth.getPrincipal();
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
