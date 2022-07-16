package com.tweetapp.service;

import com.tweetapp.model.UserDetails;
import java.util.List;

public interface UserService {
    String register(UserDetails userDetails);
    boolean login(String username,String password);
    boolean logout(String username);
    List<String> viewAllUsers();
    boolean resetPassword(String username,String old,String newPassword);
	boolean forgetPassword(String username, String newPassword);
}
