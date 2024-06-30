package com.example.cloneinstagramback.repository;

import com.example.cloneinstagramback.insta.modal.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
