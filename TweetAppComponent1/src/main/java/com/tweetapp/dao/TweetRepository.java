package com.tweetapp.dao;

import java.util.List;

import com.tweetapp.model.TweetDetails;

public interface TweetRepository {

	
	public boolean postTweet(TweetDetails tweet);
	public List<TweetDetails> findAll();

}
