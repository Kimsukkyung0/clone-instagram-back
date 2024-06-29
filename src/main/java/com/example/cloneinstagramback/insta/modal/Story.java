package com.example.cloneinstagramback.insta.modal;

import com.example.cloneinstagramback.insta.dto.UserDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name="Stories")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Story {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long storyId;

    @AttributeOverrides({
            @AttributeOverride(name = "id",
                    column = @Column(name = "user-id")),
            @AttributeOverride(name = "email", column = @Column(name = "user-email"))
    })
    private UserDto user;

    @NotNull
    private String image;
    private String caption;
    private LocalDateTime timestamp;
}
