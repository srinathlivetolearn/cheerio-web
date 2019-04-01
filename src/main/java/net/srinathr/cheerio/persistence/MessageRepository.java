package net.srinathr.cheerio.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.srinathr.cheerio.persistence.dao.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
		List<Message> getMessagesByReceiverId(String receiverId);
}
