package com.tweetapp.service;

import com.tweetapp.model.TweetDetails;

import java.util.List;
import java.util.Map;

public interface TweetService {
    String postATweet(TweetDetails tweet);
    List<String> viewAllPosts();
    List<String> viewTweetByUser(String username);
	Map<String, List<String>> viewTweetByAllUser();
}
