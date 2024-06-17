package com.instagram.insta.modal;

import com.instagram.insta.dto.UserDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="user")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString(exclude = "password")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer userId;

    private String username;
    private String email;
    private String mobile;
    private String website;
    private String bio;
    private String gender;
    private String image;
    private String password;

    @Embedded
    @ElementCollection
    private Set<UserDto> follower = new HashSet<>();

    @Embedded
    @ElementCollection
    private Set<UserDto> following = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Story> stories = new ArrayList<>();

    @ManyToMany
    private List<Post> savedPost = new ArrayList<>();

}
