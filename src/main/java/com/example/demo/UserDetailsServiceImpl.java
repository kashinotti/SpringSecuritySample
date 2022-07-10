package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.Repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
//			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//			LoginUser setUser = new LoginUser();
//			String password = encoder.encode("test");
//			
//			setUser.setName(username);
//			setUser.setPassword(password);
//			userRepository.save(setUser);
			
			
//			LoginUser loginUser = userRepository.findByUsername(username);
//			System.out.println(loginUser.getPassword());
//			List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
//			GrantedAuthority authority = new SimpleGrantedAuthority("USER");
//			grantList.add(authority);
//			
//			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//			System.out.println(encoder.encode(loginUser.getPassword()));
//			UserDetails userDetails = (UserDetails)new User(loginUser.getName(), encoder.encode(loginUser.getPassword()), grantList);
//			return userDetails;
			System.out.println(username);
			LoginUser loginUser = userRepository.findByUsername(username);
			System.out.println(loginUser.getName());
			System.out.println(loginUser.getPassword());
			System.out.println(loginUser.getAuthority());
			UserDetails user = new LoginUserDetails(loginUser);
			return user;
			
		} catch(Exception e) {
			throw new UsernameNotFoundException("user not found.", e);
		}

//		try {
//			User user = userRepository.findByUsername(username);
//			String password = user.getPassword();
//			Collection<GrantedAuthority> authorities = new ArrayList<>();
//			authorities.add(new SimpleGrantedAuthority(user.getAuthority()));
//			
//			return new UserDetailsImpl(username, password, authorities);
//		} catch(Exception e) {
//			throw new UsernameNotFoundException("user not found.", e);
//		}
	}

}
