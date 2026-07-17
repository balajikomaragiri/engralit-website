package com.engralit.website.repository;

import com.engralit.website.entity.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {

    List<ChatMessage> findByUserEmailOrderBySentAtAsc(String userEmail);

    long countByUserEmailAndSenderAndIsReadFalse(String userEmail, String sender);

    // For admin: distinct list of students who've messaged
    @org.springframework.data.jpa.repository.Query("SELECT DISTINCT c.userEmail FROM ChatMessage c")
    List<String> findDistinctUserEmails();

    long countBySenderAndIsReadFalse(String sender);
}