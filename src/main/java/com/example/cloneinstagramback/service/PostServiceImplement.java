package com.example.cloneinstagramback.service;

import com.example.cloneinstagramback.exception.PostException;
import com.example.cloneinstagramback.exception.UserException;
import com.example.cloneinstagramback.insta.dto.UserDto;
import com.example.cloneinstagramback.insta.modal.Post;
import com.example.cloneinstagramback.insta.modal.User;
import com.example.cloneinstagramback.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PostServiceImplement implements PostService{
    @Autowired
    private PostRepository postRep;

    private UserService userService;

    @Override
    public Post createPost(Post post,Long userId) throws UserException {
        User user = userService.findUserProfile(String.valueOf(userId));
        UserDto userDto = new UserDto();
        userDto.setEmail(user.getEmail());
        userDto.setId(user.getUserId());
        userDto.setName(user.getName());
        userDto.setUserImage(user.getImage());
        userDto.setUsername(user.getUsername());

        post.setUser(userDto);

        Post createdPost = postRep.save(post);
        return createdPost;
    }

    @Override
    public String deletePost(Long postId, Long userId) throws UserException, PostException {
        return "";
    }

    @Override
    public List<Post> findPostListByUserId(Long userId) throws UserException {
        return List.of();
    }

    @Override
    public Post findPostByPostId(Long postId) throws PostException {
        return null;
    }

    @Override
    public List<Post> findAllPostsByUserIds(List<Long> userIds) throws UserException, PostException {
        return List.of();
    }

    @Override
    public String savedPost(Long postId, Long userId) throws PostException, UserException {
        return "";
    }

    @Override
    public String unSavedPost(Long postId, Long userId) throws PostException, UserException {
        return "";
    }

    @Override
    public Post likePost(Long postId, Long userId) throws UserException, PostException {
        return null;
    }

    @Override
    public Post unlikePost(Long postId, Long userId) throws UserException, PostException {
        return null;
    }
}
