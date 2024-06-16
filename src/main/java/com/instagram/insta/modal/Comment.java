package com.instagram.insta.modal;


import com.instagram.insta.dto.UserDto;
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

    private Integer commentId;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "id",
                    column = @Column(name = "user-id")),
            @AttributeOverride(name = "email", column = @Column(name = "user-email"))
    })
    private UserDto user;

    private String content;

    @Embedded
    @ElementCollection
    private Set<UserDto> likedByUsers = new HashSet<UserDto>();

    private LocalDateTime createdAt;



}
