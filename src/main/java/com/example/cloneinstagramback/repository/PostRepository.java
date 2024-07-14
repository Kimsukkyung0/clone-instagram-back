package com.example.cloneinstagramback.repository;

import com.example.cloneinstagramback.insta.modal.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    @Query("select p from posts p where p.user.userId=?1")
    List<Post> findByUserId(Long userId);

    @Query("select p from posts p where p.user.userId IN:users order by p.createdAt DESC")
    List<Post> findAllPostByUserIdsSortedByDate(List<Long> userIds);
}
