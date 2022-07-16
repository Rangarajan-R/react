package com.tweetapp;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import com.tweetapp.exception.PasswordMatchException;
import com.tweetapp.exception.PasswordMismatchException;
import com.tweetapp.model.TweetDetails;
import com.tweetapp.model.UserDetails;
import com.tweetapp.service.TweetService;
import com.tweetapp.service.UserService;
import com.tweetapp.serviceImpl.TweetServiceImpl;
import com.tweetapp.serviceImpl.UserServiceImpl;
import com.tweetapp.utils.TweetAppConstants;


public class TweetApp {
	
	
	static Scanner scanner = new Scanner(System.in);
	static boolean isLoggedIn = false;
	private static UserService userService = new UserServiceImpl();
	private static TweetService tweetService = new TweetServiceImpl();
	private static String username;

	public static void main(String[] args) {
		int option;
		while (true) {
			System.out.println("--------------Menu------------");
			if (!isLoggedIn) {
				System.out.println("1.Register\n2.Login\n3.Forget Password");
				option = Integer.parseInt(scanner.nextLine());
				switch (option) {
				case 1:
					System.out.println(register());
					break;
				case 2:
					isLoggedIn = login();
					if (!isLoggedIn) {
						username = null;
					}
					System.out.println(username);
					break;
				case 3:
					forgotPassword();
					break;
				default:
					System.out.println("Enter the correct option");
				}
			} else {
				System.out.println(
						"1.Post a tweet\n2.View my tweets\n3.View all tweets\n4.View all users\n5.View All users and Tweets\n6.Reset Password\n7.Logout");
				option = Integer.parseInt(scanner.nextLine());
				switch (option) {
				case 1:
					System.out.println(postATweet(username));
					break;
				case 2:
					List<String> tweets = viewTweetByUser(username);
					if (tweets != null)
						tweets.forEach(System.out::println);
					else
						System.out.println("No tweets Found");
					break;
				case 3:
					List<String> allTweets = viewAllTweets();
					if (allTweets != null)
						allTweets.forEach(System.out::println);
					else
						System.out.println("No tweets Found");
					break;
				case 4:
					List<String> users = viewAllUsers();
					if (users != null)
						users.forEach(System.out::println);
					else
						System.out.println("No users Found");
					break;
				case 5:
					Map<String, List<String>> userTweet = viewAllUserTweet();
					for (String key : userTweet.keySet()) {
						System.out.println(key + "'s Tweets are: ");
						userTweet.get(key).forEach(System.out::println);
						System.out.println();
					}
					break;
				case 6:
					isLoggedIn = resetPassword(username);
					break;
				case 7:
					isLoggedIn = logOut();
					break;
				default:
					System.out.println("Select correct option");
				}
			}
		}
	}

	public static String register() {
		UserDetails userDetails = new UserDetails();
		System.out.println("In Register Method ");
		System.out.println("Enter first Name");
		userDetails.setFirstName(scanner.nextLine());
		System.out.println("Enter last Name");
		userDetails.setLastName(scanner.nextLine());
		System.out.println("Enter gender Name");
		String gender = scanner.nextLine();
		if (gender.equalsIgnoreCase("male") || gender.equalsIgnoreCase("female") || gender.equalsIgnoreCase("others")) {
			userDetails.setGender(gender);
		} else {
			System.out.println("Invalid Gender should be Male ,Female or Others");
			return "registration failed";
		}
		System.out.println("Enter Date of Birth in year-month-day format");
		String dob = scanner.nextLine();
		try {
			userDetails.setDob(Date.valueOf(dob));
		} catch (Exception e) {
			System.out.println("Invalid Date Format");
			return "registration failed";
		}
		System.out.println("Enter User Name");
		String username = scanner.nextLine();
		String regex = TweetAppConstants.EMAIL_FORMAT;
		if (Pattern.compile(regex).matcher(username).matches()) {
			userDetails.setUserName(username);
		} else {
			System.out.println("Username must follow proper email standards");
			return "registration failed";
		}
		System.out.println("Enter Password");
		String pass = scanner.nextLine();
		if (pass.length() < 5 || pass.length() > 10) {
			System.out.println("Password must be more or equal to 8 characters and less than 10 characters");
			return "registration failed";
		}
		userDetails.setPassword(pass);
		userDetails.setStatus(false);
		return userService.register(userDetails);
	}

	public static boolean login() {
		System.out.println("In Login Method");
		System.out.println("Enter User Name");
		String uname = scanner.nextLine();
		System.out.println("Enter Password");
		String password = scanner.nextLine();
		if (uname == null || password == null || uname.trim().isEmpty() || password.trim().isEmpty()) {
			System.out.println("Login unsuccessful --> User name or password is empty");
			return false;
		} else {
			username = uname;
			return userService.login(username, password);
		}
	}

	public static int generateUniqueId() {
		UUID idOne = UUID.randomUUID();
		String str = "" + idOne;
		int uid = str.hashCode();
		String filterStr = "" + uid;
		str = filterStr.replaceAll("-", "");
		return Integer.parseInt(str);
	}

	public static String postATweet(String username) {
		TweetDetails tweet = new TweetDetails();
		tweet.setTweetId(generateUniqueId());
		tweet.setUsername(username);
		System.out.println("Enter your tweet to post");
		tweet.setTweet(scanner.nextLine());
		return tweetService.postATweet(tweet);
	}

	public static List<String> viewAllTweets() {
		try {
			return tweetService.viewAllPosts();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return null;
		}
	}

	public static List<String> viewAllUsers() {
		try {
			return userService.viewAllUsers();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return null;
		}
	}

	public static List<String> viewTweetByUser(String username) {
		try {
			return tweetService.viewTweetByUser(username);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return null;
		}
	}

	private static Map<String, List<String>> viewAllUserTweet() {
		return tweetService.viewTweetByAllUser();
	}

	public static boolean resetPassword(String username) {
		try {
			System.out.println("Enter your old password");
			String oldPassword = scanner.nextLine();
			System.out.println("Enter your new password");
			String newPassword = scanner.nextLine();
			System.out.println("Re-Enter your new password");
			String newCheckPassword = scanner.nextLine();
			if (newPassword.length() < 5 || newPassword.length() > 10) {
				System.out.println("Password should be between 5 to 10 characters");
				return true;
			}
			if (!newPassword.equals(newCheckPassword)) {
				throw new PasswordMismatchException("Re-entered password mismatch");
			}
			if (newPassword.equals(oldPassword)) {
				throw new PasswordMatchException("old password and new password should not be same");
			}
			if (userService.resetPassword(username, oldPassword, newPassword)) {
				System.out.println("Reset successfully");
				return false;
			}
			System.out.println("Reset Failed");
			return true;
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return true;
		}
	}

	public static void forgotPassword() {
		try {
			System.out.println("Enter your username");
			String username = scanner.nextLine();
			System.out.println("Enter your new password");
			String newPassword = scanner.nextLine();
			System.out.println("Re-Enter your new password");
			String newCheckPassword = scanner.nextLine();
			if (newPassword.length() < 5 || newPassword.length() > 10) {
				System.out.println("Password should be between 5 to 10 characters");
			}
			if (!newPassword.equals(newCheckPassword)) {
				throw new PasswordMismatchException("Re-entered password mismatch");
			}
			if (userService.forgetPassword(username, newPassword)) {
				System.out.println("Reset successfully");
			} else
				System.out.println("Reset Failed");
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	public static boolean logOut() {
		if (userService.logout(username)) {
			System.out.println("Logged out successfully");
			return false;
		}
		System.out.println("Logout Failed");
		return true;
	}
}
