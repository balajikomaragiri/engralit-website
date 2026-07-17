package com.engralit.website.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "chat_messages")
@Getter
@Setter
@NoArgsConstructor
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userEmail;      // which student this conversation belongs to

    @Column(length = 1000)
    private String message;

    private String sender;         // "USER" or "ADMIN"

    private boolean isRead = false;

    private LocalDateTime sentAt = LocalDateTime.now();
}