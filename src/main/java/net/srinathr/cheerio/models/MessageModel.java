package net.srinathr.cheerio.models;

public class MessageModel {
	private String body;
	
	private String senderId;
	
	private String receiverId;

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getSenderId() {
		return senderId;
	}

	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}

	public String getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(String receiverId) {
		this.receiverId = receiverId;
	}

	@Override
	public String toString() {
		return "MessageModel [body=" + body + ", senderId=" + senderId + ", receiverId=" + receiverId + "]";
	}
	
	
}
