package com.example.cloneinstagramback.insta.dto;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class UserDto {
    private Long id;
    private String username;
    private String email;
    private String name;
    private String userImage;

}
