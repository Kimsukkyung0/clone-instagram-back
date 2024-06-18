package com.instagram.service;

import com.instagram.exception.UserException;
import com.instagram.insta.modal.User;
import jdk.jshell.spi.ExecutionControl;

import java.util.List;

public interface UserService {
    public User registerUser(User user) throws UserException;
    public User findUserById(Integer userId) throws UserException;
    public List<User> findUserByIds(List<Integer> userId) throws UserException;
    public User findUserProfile(String token) throws UserException;
    public User findUserByUsername(String username) throws UserException;
    public String followUser(Integer followUserId, Integer reqUserId) throws UserException;
    public String unfollowUser(Integer reqUserId, Integer followUserId) throws UserException;
    public List<User> searchUser(String query) throws UserException;
    public User updateUserDetails(User updatedUser,User existingUser) throws UserException;



}
