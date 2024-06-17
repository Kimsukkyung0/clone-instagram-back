package com.instagram.service;

import com.instagram.exception.UserException;
import com.instagram.insta.dto.UserDto;
import com.instagram.insta.modal.User;
import com.instagram.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplement implements UserService{

    @Autowired
    private UserRepository userRep;

    @Override
    public User registerUser(User user) throws UserException {
        Optional<User> isEmailExist = userRep.findByEmail(user.getEmail());
        if(isEmailExist.isPresent()){
            throw new UserException("Email is Already Exist");
        }

        Optional<User> isUsernameExist = userRep.findByUsername(user.getUsername());
        if(isUsernameExist.isPresent()){
            throw new UserException("userName is Already Exist");
        }
        if(user.getPassword().isEmpty() || user.getUsername().isEmpty() || user.getUserId().describeConstable().isEmpty()){
            throw new UserException("all field are required to fill");
        }

        return userRep.save(User.builder()
                .userId(user.getUserId())
                .email(user.getEmail())
                .password(user.getPassword())
                .username(user.getUsername()).build());
    }

    @Override
    public User findUserById(Integer userId) throws UserException {
        Optional<User> opt = userRep.findById(userId);
        if(opt.isPresent()){
            return opt.get();
        }
        throw new UserException("user does not exists. id : "+userId);
    }

    @Override
    public User findUserProfile(String token) throws UserException {
        return null;
    }

    @Override
    public User findUserByUsername(String username) throws UserException {
        Optional<User> opt = userRep.findByUsername(username);
        if(opt.isPresent()){
            return opt.get();
        }
        throw new UserException("user does not exists. username : "+username);
    }

    @Override
    public String followUser(Integer followUserId, Integer reqUserId) throws UserException {
        User reqUser = findUserById(reqUserId);
        User followUser = findUserById(followUserId);

        UserDto follower = new UserDto(followUser);
        return "";
    }

    @Override
    public String unfollowUser(Integer reqUserId, Integer followUserId) throws UserException {
        return "";
    }

    @Override
    public List<User> searchUser(String query) throws UserException {
        return List.of();
    }

    @Override
    public User updateUserDetails(User updatedUser) throws UserException {
        return null;
    }
}
