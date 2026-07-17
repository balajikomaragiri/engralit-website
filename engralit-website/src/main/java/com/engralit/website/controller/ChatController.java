package com.engralit.website.controller;

import com.engralit.website.entity.ChatMessage;
import com.engralit.website.repository.ChatMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
public class ChatController {

    private final ChatMessageRepository chatMessageRepository;

    // Student sends a message
    @PostMapping("/send")
    public ChatMessage send(@RequestBody Map<String, String> payload, Authentication authentication) {
        ChatMessage msg = new ChatMessage();
        msg.setUserEmail(authentication.getName());
        msg.setMessage(payload.get("message"));
        msg.setSender("USER");
        return chatMessageRepository.save(msg);
    }

    // Student loads their own conversation
    @GetMapping("/messages")
    public List<ChatMessage> getMyMessages(Authentication authentication) {
        List<ChatMessage> messages = chatMessageRepository.findByUserEmailOrderBySentAtAsc(authentication.getName());
        // Mark admin replies as read once the student views them
        messages.stream()
                .filter(m -> m.getSender().equals("ADMIN") && !m.isRead())
                .forEach(m -> { m.setRead(true); chatMessageRepository.save(m); });
        return messages;
    }

    // Unread admin-reply count, shown as a badge on the widget
    @GetMapping("/unread-count")
    public long unreadCount(Authentication authentication) {
        return chatMessageRepository.countByUserEmailAndSenderAndIsReadFalse(authentication.getName(), "ADMIN");
    }
}