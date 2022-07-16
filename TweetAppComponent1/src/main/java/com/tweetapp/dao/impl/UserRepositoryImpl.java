package com.tweetapp.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tweetapp.config.JDBCConnection;
import com.tweetapp.dao.UserRepository;
import com.tweetapp.model.UserDetails;
import com.tweetapp.utils.TweetAppConstants;

public class UserRepositoryImpl implements UserRepository{
Connection conn=JDBCConnection.getConnection();
	
	public boolean addUser(UserDetails user) {
		if(conn!=null) {
			try {
				String query=TweetAppConstants.INSERT_VALUES;
				PreparedStatement stmt= conn.prepareStatement(query);
				stmt.setString(1, user.getUserName());
				stmt.setString(2, user.getFirstName());
				stmt.setString(3, user.getLastName());
				stmt.setString(4, user.getGender());
				stmt.setDate(5, user.getDob());
				stmt.setBoolean(6, user.isStatus());
				stmt.setString(7, user.getPassword());
				stmt.execute();
				return true;
			}
			catch(Exception ex) {
				return false;
			}
		}
		return false;
	}
	
	public UserDetails findbyId(String username) {
		UserDetails user=null;
		if(conn!=null) {
			try {
			
				String query=TweetAppConstants.SELECT_FROM_USERNAME;
				PreparedStatement stmt=conn.prepareStatement(query);
				stmt.setString(1, username);
				ResultSet quser=stmt.executeQuery();
				if(quser.next()) {
				user=new UserDetails(quser.getString("username"),quser.getString("firstname"),quser.getString("lastname"),
						quser.getString("gender"),quser.getDate("dob"),quser.getBoolean("status"),quser.getString("password"));
				}
				return user;
				
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println(e);
				return user;
			}
		}
		return user;
	}
	public List<UserDetails> findAll(){
		List<UserDetails> users=new ArrayList<>();
		if(conn!=null) {
			try {
				Statement stmt=conn.createStatement();
				String query=TweetAppConstants.SELECT_ALL;
				ResultSet quser=stmt.executeQuery(query);
				while(quser.next()) {
				UserDetails user=new UserDetails(quser.getString("username"),quser.getString("firstname"),quser.getString("lastname"),
						quser.getString("gender"),quser.getDate("dob"),quser.getBoolean("status"),quser.getString("password"));
				users.add(user);
				}
				return users;
				
			} catch (SQLException e) {
				e.printStackTrace();
				return users;
			}
		}
		return users;
	}
	
	public boolean updateStatus(UserDetails user) {
		if(conn!=null) {
			try {
				String query=TweetAppConstants.UPDATE_DATA;
				PreparedStatement stmt=conn.prepareStatement(query);
				stmt.setBoolean(1, !user.isStatus());
				stmt.setString(2, user.getUserName());
				System.out.println("Updated status");
				return stmt.execute();
			} catch (SQLException e) {
				System.out.println("unable to update status");
				return false;
			}
			
		}
		System.out.println("unable to update status");
		return false;
	}
	public boolean updatePassword(UserDetails user) {
		if(conn!=null) {
			try {
				String query=TweetAppConstants.UPDATE_PASSWORD;
				PreparedStatement stmt=conn.prepareStatement(query);
				stmt.setString(1, user.getPassword());
				stmt.setString(2, user.getUserName());
				System.out.println("Updated status");
				return stmt.execute();
			} catch (SQLException e) {
				System.out.println("unable to update status");
				return false;
			}
			
		}
		System.out.println("unable to update status");
		return false;
	}
}
