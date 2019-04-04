package net.srinathr.cheerio.service;

import net.srinathr.cheerio.models.UserModel;
import net.srinathr.cheerio.persistence.UserRepository;
import net.srinathr.cheerio.persistence.dao.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

@Service
public class UserService {
	private static final Logger LOG = LogManager.getLogger(UserService.class);
	
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

	public int bulkCreate() {
		Path path = Paths.get("src","main","resources","cheerio.csv");
		Set<User> users = new HashSet<>();
		try(Stream<String> lines = Files.lines(path)) {
			lines.forEach(line -> {
				String[] split = line.split(",");
				if(split.length == 3) {
					User user = new User();
					user.setEmail(split[0].toLowerCase());
					user.setFirstname(split[1]);
					user.setLastname("");
					user.setPassword(encoder.encode(split[2]));
					user.setRole("USER");
					LOG.info("Adding user: "+user);
					users.add(user);
				}
			});
			userRepository.saveAll(users);
		}
		catch (IOException e) {
			LOG.error("Error processing file:",e);
		}
		catch (Exception e) {
			LOG.error("Error while saving userdata",e);
		}
		return users.size();
	}
	
}
