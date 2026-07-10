package com.engralit.website.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    @Column(unique = true, nullable = false)
    private String email;

    private String password;   // will store the ENCRYPTED password, never plain text

    private String role = "STUDENT";   // could be "STUDENT" or "ADMIN" later

    public Long getId() {
        return  this.id;
    }
}