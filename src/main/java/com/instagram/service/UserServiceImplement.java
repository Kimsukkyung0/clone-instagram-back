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
    public List<User> findUserByIds(List<Integer> userId) throws UserException {
        List<User> users = userRep.findAllUserByUserIds(userId);
        return users;
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

        UserDto follower = new UserDto();
        follower.setEmail(reqUser.getEmail());
        follower.setId(reqUser.getUserId());
        follower.setName(reqUser.getName());
        follower.setUserImage(reqUser.getImage());
        follower.setUsername(reqUser.getUsername());

        UserDto following = new UserDto();
        follower.setEmail(follower.getEmail());
        follower.setId(follower.getId());
        follower.setName(follower.getName());
        follower.setUserImage(follower.getUserImage());
        follower.setUsername(follower.getUsername());

        reqUser.getFollowing().add(following);
        followUser.getFollower().add(follower);

        userRep.save(reqUser);
        userRep.save(followUser);
        return "you are following "+followUser.getUsername();
    }

    @Override
    public String unfollowUser(Integer reqUserId, Integer followUserId) throws UserException {
        User reqUser = findUserById(reqUserId);
        User followUser = findUserById(followUserId);

        UserDto follower = new UserDto();
        follower.setEmail(reqUser.getEmail());
        follower.setId(reqUser.getUserId());
        follower.setName(reqUser.getName());
        follower.setUserImage(reqUser.getImage());
        follower.setUsername(reqUser.getUsername());

        UserDto following = new UserDto();
        follower.setEmail(follower.getEmail());
        follower.setId(follower.getId());
        follower.setName(follower.getName());
        follower.setUserImage(follower.getUserImage());
        follower.setUsername(follower.getUsername());

        reqUser.getFollowing().remove(following);
        followUser.getFollower().remove(follower);

        userRep.save(reqUser);
        userRep.save(followUser);
        return "you have unfollowed "+followUser.getUsername();
    }

    @Override
    public List<User> searchUser(String query) throws UserException {
        List<User> users = userRep.findByQuery(query);
        if(users.size() == 0){
            throw new UserException("user does not exists");
        }
        return users;
    }

    @Override
    public User updateUserDetails(User updatedUser,User existingUser) throws UserException {
        if(updatedUser.getEmail()!=null){
            existingUser.setEmail(updatedUser.getEmail());
        }
        if(updatedUser.getUsername()!=null){
            existingUser.setUsername(updatedUser.getUsername());
        }
        if(updatedUser.getPassword()!=null){
            existingUser.setUserId(updatedUser.getUserId());
        }
        if(updatedUser.getImage()!=null){
            existingUser.setImage(updatedUser.getImage());
        }
        if (updatedUser.getBio()!=null){
            existingUser.setBio(updatedUser.getBio());
        }
        if(updatedUser.getName()!=null){
            existingUser.setName(updatedUser.getName());
        }
        if(updatedUser.getMobile()!=null){
            existingUser.setMobile(updatedUser.getMobile());
        }
        if(updatedUser.getGender()!=null){
            existingUser.setGender(updatedUser.getGender());
        }
        if(updatedUser.getWebsite()!=null){
            existingUser.setWebsite(updatedUser.getWebsite());
        }
        if(updatedUser.getUserId().equals(existingUser.getUserId())){
            return userRep.save(existingUser);
        }
        throw new UserException("can not update user details");

    }
}
