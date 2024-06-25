package com.instagram.controller;


import com.instagram.Response.MessageResponse;
import com.instagram.exception.UserException;
import com.instagram.insta.modal.User;
import com.instagram.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/id/{userId}")
    public ResponseEntity<User> findUserByIdHandler(@PathVariable Integer userId) throws UserException {
        User user = userService.findUserById(userId);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<User> findUserByUsernameHandler(@PathVariable String username) throws UserException {
        User user = userService.findUserByUsername(username);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/follow/{followUserId}")
    public ResponseEntity<MessageResponse> followerUserHandler(@PathVariable Integer followUserId){
//        MessageResponse res = userService.followUser(followUserId);
        return null;
    }

    @PutMapping("/unfollow/{followUserId}")
    public ResponseEntity<MessageResponse> unfollowUserHandler(@PathVariable Integer unfollowUserId){
//        MessageResponse res = userService.followUser(followUserId);
        return null;
    }

    @PutMapping("/req")
    public ResponseEntity<MessageResponse> findUserProfileHandler(@RequestHeader("Authorization") String token){
        return null;
    }

    @GetMapping("/m/{userIds}")
    public ResponseEntity<List<User>> findUserByUserIdsHandler(@PathVariable List<Integer> userIds) throws UserException {
        List<User> users = userService.findUserByIds(userIds);
        return new ResponseEntity<>(users, HttpStatus.OK);

    }
//api
    @GetMapping("/search")
    public ResponseEntity<List<User>> searchUserHandler(@RequestParam("q") String query) throws UserException{
        List<User> users = userService.searchUser(query);
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }

    public ResponseEntity<User> updateUserHandler(@RequestHeader("Authorization") String token, @RequestBody User user){
//        User updatedUser = userService.updateUserDetails(user,user)
                return null;
    }

}