package net.srinathr.cheerio.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import net.srinathr.cheerio.models.UserModel;
import net.srinathr.cheerio.service.UserService;
import net.srinathr.cheerio.support.SessionUtil;

@Controller
public class UserController {
	private static final Logger LOG = LogManager.getLogger(UserController.class);
	@Autowired
	private UserService userService;
	
	@GetMapping("/")
	public String hello() {
		return "hello";
	}

	@GetMapping("/bulk")
	public String ingestBulkUsers() {
		userService.bulkCreate();
		return "hello";
	}
	
	@GetMapping("/signup")
	public String register() {
		return "register";
	}
	
	@GetMapping("/login")
	public ModelAndView redirectToSignin(@RequestParam(value="failed",required=false)boolean hasError) {
		ModelAndView mav = new ModelAndView("signin");
		if(hasError) {
			mav.addObject("error", "Invalid username or password please try again.");
		}
		return mav;
	}
	
	/*@PostMapping("/login")
	public String doLogin() {
		LOG.info("Session created for user:"+SessionUtil.getLoggedInUserName());
		return "redirect:inbox";
	}*/
	
	@GetMapping("/logout")
	public String logout() {
		SessionUtil.invalidateSession();
		return "signin";
	}
	
	@PostMapping(value="/signup",consumes= {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
	public String signup(@RequestBody MultiValueMap<String, String> inputs) {
		try {
			UserModel usermodel = new UserModel();
			usermodel.setEmail(inputs.getFirst("email"));
			usermodel.setFirstname(inputs.getFirst("firstname"));
			usermodel.setLastname(inputs.getFirst("lastname"));
			usermodel.setRole(inputs.getFirst("role"));
			usermodel.setPassword(inputs.getFirst("password"));
			userService.createUser(usermodel);
			LOG.info("User created successfully: "+usermodel);
		}catch (Exception e) {
			LOG.error(e);
			return "redirect:/error";
		}
		return "signin";
	}

}
