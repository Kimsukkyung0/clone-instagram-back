package com.example.cloneinstagramback.service;

import com.example.cloneinstagramback.exception.UserException;
import com.example.cloneinstagramback.insta.modal.User;

import java.util.List;

public interface UserService {
    public User registerUser(User user) throws UserException;
    public User findUserById(Long userId) throws UserException;
    public List<User> findUserByIds(List<Long> userId) throws UserException;
    public User findUserProfile(String token) throws UserException;
    public User findUserByUsername(String username) throws UserException;
    public String followUser(Long followUserId, Long reqUserId) throws UserException;
    public String unfollowUser(Long reqUserId, Long followUserId) throws UserException;
    public List<User> searchUser(String query) throws UserException;
    public User updateUserDetails(User updatedUser,User existingUser) throws UserException;



}
