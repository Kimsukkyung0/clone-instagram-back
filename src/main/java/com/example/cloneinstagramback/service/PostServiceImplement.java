package com.example.cloneinstagramback.service;

import com.example.cloneinstagramback.exception.PostException;
import com.example.cloneinstagramback.exception.UserException;
import com.example.cloneinstagramback.insta.dto.UserDto;
import com.example.cloneinstagramback.insta.modal.Post;
import com.example.cloneinstagramback.insta.modal.User;
import com.example.cloneinstagramback.repository.PostRepository;
import com.example.cloneinstagramback.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class PostServiceImplement implements PostService{
    @Autowired
    private PostRepository postRep;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRep;



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
        Post post = findPostByPostId(postId);
        if(post.getUser().getId().equals(userId)){
            postRep.deleteById(post.getPostId());
            return "Post deleted Successfully";
        }
        throw new PostException("You cannot delete other user's post");
    }

    @Override
    public List<Post> findPostListByUserId(Long userId) throws UserException {
        List<Post> posts = postRep.findByUserId(userId);
        if(posts.isEmpty()){
            throw new UserException("this user does not have any post");
        }

        return posts;
    }

    @Override
    public Post findPostByPostId(Long postId) throws PostException {
        Optional<Post> opt = postRep.findById(postId);
        if(opt.isPresent()){
            return opt.get();
        }
        throw new PostException("Post not found with id" + postId);
    }

    @Override
    public List<Post> findAllPostsByUserIds(List<Long> userIds) throws UserException, PostException {
        List<Post> posts = postRep.findAllPostByUserIdsSortedByDate(userIds);
        if(posts.isEmpty()){
            throw new UserException("No post available");
        }
        return posts;
    }

    @Override
    public String savedPost(Long postId, Long userId) throws PostException, UserException {
        Post post = findPostByPostId(postId);
        User user = userService.findUserById(userId);
        if(user.getSavedPost().contains(post)){
            user.getSavedPost().add(post);
            userRep.save(user);
        }
        return "Post Saved Successfully";
    }

    @Override
    public String unSavedPost(Long postId, Long userId) throws PostException, UserException {
        Post post = findPostByPostId(postId);
        User user = userService.findUserById(userId);
        if(user.getSavedPost().remove(post)){
            user.getSavedPost().add(post);
            userRep.save(user);
        }
        return "Post Removed Successfully";
    }

    @Override
    public Post likePost(Long postId, Long userId) throws UserException, PostException {
        Post post = findPostByPostId(postId);
        User user = userService.findUserById(userId);

        UserDto userDto = new UserDto();
        userDto.setEmail(user.getEmail());
        userDto.setId(user.getUserId());
        userDto.setName(user.getName());
        userDto.setUserImage(user.getImage());
        userDto.setUsername(user.getUsername());
        return null;
    }

    @Override
    public Post unlikePost(Long postId, Long userId) throws UserException, PostException {
        return null;
    }
}
