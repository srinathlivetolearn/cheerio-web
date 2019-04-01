package net.srinathr.cheerio.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import net.srinathr.cheerio.persistence.UserRepository;
import net.srinathr.cheerio.persistence.dao.User;

@Service
public class PersistentUserDetailsService implements UserDetailsService {
	
	private UserRepository userRepository;
	
	@Autowired
	public PersistentUserDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(username)
			.orElseThrow(() -> new UsernameNotFoundException("User not found: "+username));
		return new AppUserPrincipal(user);
	}

}
