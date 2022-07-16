package com.tweetapp.dao;

import java.sql.Connection;
import java.util.List;

import com.tweetapp.config.JDBCConnection;
import com.tweetapp.model.UserDetails;

public interface UserRepository {

	Connection conn = JDBCConnection.getConnection();

	public boolean addUser(UserDetails user);

	public UserDetails findbyId(String username);

	public List<UserDetails> findAll();

	public boolean updateStatus(UserDetails user);

	public boolean updatePassword(UserDetails user);

}
