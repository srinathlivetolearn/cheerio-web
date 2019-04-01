package net.srinathr.cheerio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.srinathr.cheerio.models.MessageModel;
import net.srinathr.cheerio.persistence.MessageRepository;
import net.srinathr.cheerio.persistence.dao.Message;
import net.srinathr.cheerio.support.SessionUtil;

@Service
public class MessageService {
	private MessageRepository messageRepository;
	
	@Autowired
	public MessageService(MessageRepository messageRepository) {
		this.messageRepository = messageRepository;
	}
	
	public List<Message> getMessages() {
		return messageRepository.getMessagesByReceiverId(SessionUtil.getLoggedInUserName());
	}
	
	public void saveMessage(MessageModel messageModel) {
		Message message = new Message();
		message.setSenderId(messageModel.getSenderId());
		message.setReceiverId(messageModel.getReceiverId());
		message.setBody(messageModel.getBody());
		messageRepository.save(message);
	}

}
