package com.example.cloneinstagramback.service;

import com.example.cloneinstagramback.exception.PostException;
import com.example.cloneinstagramback.exception.UserException;
import com.example.cloneinstagramback.insta.modal.Post;
import com.example.cloneinstagramback.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface PostService {

    Post createPost(Post post, Long userId) throws UserException;
    String deletePost(Long postId, Long userId) throws UserException, PostException;
    List<Post> findPostListByUserId(Long userId) throws UserException;
    Post findPostByPostId(Long postId) throws PostException;
    List<Post> findAllPostsByUserIds(List<Long> userIds) throws UserException,PostException;
    String savedPost(Long postId, Long userId) throws PostException, UserException;
    String unSavedPost(Long postId, Long userId) throws PostException, UserException;
    Post likePost(Long postId, Long userId) throws UserException, PostException;
    Post unlikePost(Long postId, Long userId) throws UserException, PostException;
}
