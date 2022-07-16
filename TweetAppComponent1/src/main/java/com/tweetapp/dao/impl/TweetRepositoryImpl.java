package com.tweetapp.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tweetapp.config.JDBCConnection;
import com.tweetapp.dao.TweetRepository;
import com.tweetapp.model.TweetDetails;
import com.tweetapp.utils.TweetAppConstants;

public class TweetRepositoryImpl implements TweetRepository {

	Connection connection=JDBCConnection.getConnection();
	public boolean postTweet(TweetDetails tweet) {
		if(null!=connection) {
			try {
				String query=TweetAppConstants.INSERT_INTO_TWEETDETAILS;
				PreparedStatement stmt=connection.prepareStatement(query);
				stmt.setInt(1, tweet.getTweetId());
				stmt.setString(2, tweet.getUsername());
				stmt.setString(3, tweet.getTweet());
				return stmt.execute();
			}
			catch(Exception ex) {
				return false;
			}
		}
		return false;
	}
	public List<TweetDetails> findAll(){
		List<TweetDetails> tweets=new ArrayList<>();
		if(connection!=null) {
			try {
				Statement stmt=connection.createStatement();
				String query=TweetAppConstants.SELECT_FROM_TWEETDETAILS;
				ResultSet result=stmt.executeQuery(query);
				while(result.next()) {
					TweetDetails tweet=new TweetDetails(result.getInt("tweetid"),result.getString("username"),result.getString("tweet"));
					tweets.add(tweet);
				}
				return tweets;
			}
			catch(Exception ex) {
				return tweets;
			}
		}
		return tweets;
	}
}
