package net.srinathr.cheerio.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import net.srinathr.cheerio.models.MessageModel;
import net.srinathr.cheerio.persistence.dao.Message;
import net.srinathr.cheerio.service.MessageService;
import net.srinathr.cheerio.support.SessionUtil;

@Controller
public class MessageController {
	private static final Logger LOG = LogManager.getLogger(MessageController.class);
	
	private MessageService messageService;

	public MessageService getMessageService() {
		return messageService;
	}

	@Autowired
	public void setMessageService(MessageService messageService) {
		this.messageService = messageService;
	}
	
	@GetMapping("/inbox")
	public ModelAndView getMessagesForUser() {
		ModelAndView mav = new ModelAndView("inbox");
		List<Message> messages = messageService.getMessages();
		mav.addObject("messages",messages);
		return mav;
	}
	
	@PostMapping(value="/message",consumes= {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
	public ModelAndView sendMessage(@RequestBody MultiValueMap<String, String> inputs) {
		ModelAndView mav = new ModelAndView("message-sent");
		MessageModel message = new MessageModel();
		message.setSenderId(SessionUtil.getLoggedInUserName());
		message.setReceiverId(inputs.getFirst("to"));
		message.setBody(inputs.getFirst("body"));
		try {
			messageService.saveMessage(message);
		}catch (Exception e) {
			LOG.error("Error saving message: ",e);
			return new ModelAndView("redirect:error");
		}
		LOG.info("Message sent",message);
		return mav;
	}

	@PostMapping(value="/send",consumes= {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
	public ModelAndView sendMessageToAdmin(@RequestBody MultiValueMap<String, String> inputs) {
		ModelAndView mav = new ModelAndView("message-sent");
		MessageModel message = new MessageModel();
		message.setSenderId(SessionUtil.getLoggedInUserName());
		message.setReceiverId("admin");
		message.setBody(inputs.getFirst("body"));
		try {
			messageService.saveMessage(message);
		}catch (Exception e) {
			LOG.error("Error saving message: ",e);
			return new ModelAndView("redirect:error");
		}
		LOG.info("Message sent",message);
		return mav;
	}
	
	@GetMapping("/newmessage")
	public ModelAndView sendMessageForm() {
		return new ModelAndView("message");
	}

	@GetMapping("/send")
	public ModelAndView sendMessage() {
		return new ModelAndView("send");
	}

}
