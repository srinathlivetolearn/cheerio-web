package net.srinathr.cheerio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import net.srinathr.cheerio.models.UserModel;
import net.srinathr.cheerio.persistence.UserRepository;
import net.srinathr.cheerio.persistence.dao.User;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder encoder;

	public UserRepository getUserRepository() {
		return userRepository;
	}

	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public PasswordEncoder getEncoder() {
		return encoder;
	}

	public void setEncoder(PasswordEncoder encoder) {
		this.encoder = encoder;
	}
	
	public void createUser(UserModel userModel) {
		User user = new User();
		user.setEmail(userModel.getEmail());
		user.setPhone(userModel.getPhone());
		user.setFirstname(userModel.getFirstname());
		user.setLastname(userModel.getLastname());
		user.setRole(userModel.getRole());
		user.setPassword(encoder.encode(userModel.getPassword()));
		userRepository.save(user);
	}
	
}
