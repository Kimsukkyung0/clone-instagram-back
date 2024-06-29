package com.example.cloneinstagramback.insta.modal;


import com.example.cloneinstagramback.insta.dto.UserDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="comments")
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long commentId;

    @AttributeOverrides({
            @AttributeOverride(name = "id",
                    column = @Column(name = "user-id")),
            @AttributeOverride(name = "email", column = @Column(name = "user-email"))
    })
    private UserDto user;

    private String content;

    @ElementCollection
    private Set<UserDto> likedByUsers = new HashSet<UserDto>();

    private LocalDateTime createdAt;



}
