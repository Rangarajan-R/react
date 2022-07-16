package com.tweetapp.utils;

public final class TweetAppConstants {

	public static final String REGISTRATION_SUCCESSFUL_LOGIN_IN = "Registration successful, Login in...";
	public static final String USER_ALREADY_REGISTERED_LOGIN_NOW = "User already registered, Login Now";
	public static final String USERNAME_NOT_FOUND_RESISTER = "Username Not Found, Resister";
	public static final String OLD_PASSWORD_AND_NEW_PASSWORD_NOT_MATCHED = "Old password and new Password Not Matched";
	public static final String SELECT_FROM_USERNAME = "Select * from userdetails where username=?;";
	public static final String SELECT_ALL = "Select * from userdetails;";
	public static final String UPDATE_DATA = "update userdetails set status=? where username=?";
	public static final String UPDATE_PASSWORD = "update userdetails set password=? where username=?";
	public static final String INSERT_VALUES = "Insert into userdetails(username,firstname,lastname,gender,dob,status,password) values (?,?,?,?,?,?,?)";
	public static final String INSERT_INTO_TWEETDETAILS= "insert into tweetdetails(tweetid,username,tweet) values (?,?,?);";
	public static final String SELECT_FROM_TWEETDETAILS = "select * from tweetdetails;";
	public static final String TWEET_ADDED_SUCCESSFULLY = "Tweet added Successfully";
	public static final String EMAIL_FORMAT = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

}

