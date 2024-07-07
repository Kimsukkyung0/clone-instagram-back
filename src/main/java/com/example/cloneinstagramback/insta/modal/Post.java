package com.example.cloneinstagramback.insta.modal;

import com.example.cloneinstagramback.insta.dto.UserDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="posts")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long postId;

    private String caption;

    private String image;
    private String location;
    private LocalDateTime createdAt;

    @AttributeOverrides({
            @AttributeOverride(name = "id",
                    column = @Column(name = "user-id")),
            @AttributeOverride(name = "email", column = @Column(name = "user-email"))
    })
    private UserDto user;

    @OneToMany
    private List<Comment> comments = new ArrayList<Comment>();

    @ElementCollection
    @JoinTable(name="likedByUsers",joinColumns = @JoinColumn(name="userId"))
    private Set<UserDto> likedByUsers = new HashSet<UserDto>();
}
