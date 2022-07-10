package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationProviderImpl implements AuthenticationProvider {
	
	@Autowired
	UserDetailsServiceImpl userDetailsService;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationCredentialsNotFoundException {
		// TODO 自動生成されたメソッド・スタブ
		authentication.isAuthenticated();
		
		//入力されたユーザー名とパスワードを取得
		String username = authentication.getName();
		String password = authentication.getCredentials().toString();
		System.out.println(username);
		System.out.println(password);
		//user検索
		UserDetails user = userDetailsService.loadUserByUsername(username);
		
		if(!user.getPassword().equals(password)) {
			throw new AuthenticationCredentialsNotFoundException("ユーザーIDまたはパスワードが誤っています");
		}
		
		UsernamePasswordAuthenticationToken authenticationResult = new UsernamePasswordAuthenticationToken(user,
		        authentication.getCredentials(), user.getAuthorities());
		
		 authenticationResult.setDetails(authentication.getDetails());
		 authenticationResult.isAuthenticated();
		 return authenticationResult;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// POST で送信されたユーザー名とパスワードで認証を行う
	    return UsernamePasswordAuthenticationToken.class
	        .isAssignableFrom(authentication);
	}

}
