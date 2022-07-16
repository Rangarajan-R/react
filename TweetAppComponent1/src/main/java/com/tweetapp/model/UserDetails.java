package com.tweetapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetails {

	private String userName;
    private String firstName;
    private String lastName;
    private String gender;
    private Date dob;
    private boolean status;
    private String password;

}
