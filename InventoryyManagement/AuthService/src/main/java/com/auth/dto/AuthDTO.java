package com.auth.dto;

import com.auth.entity.Admin;
import com.auth.entity.Merchant;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthDTO {
	
	private String userId;
	
	@NotNull(message = "Email Field Empty")
	@Email(message = "Invalid Email")
	private String email;
	
	/*
		Password should contains at least 8 characters and at most 20 characters.
		It should contains at least one digit.
		It should contains at least one upper case alphabet.
		It should contains at least one lower case alphabet.
		It should contains at least one special character which includes !@#$%&*()-+=^.
		It should doesn’t contain any white space.
		
		where:
		^ represents starting character of the string.
		(?=.*[0-9]) represents a digit must occur at least once.
		(?=.*[a-z]) represents a lower case alphabet must occur at least once.
		(?=.*[A-Z]) represents an upper case alphabet that must occur at least once.
		(?=.*[@#$%^&-+=()] represents a special character that must occur at least once.
		(?=\\S+$) white spaces don’t allowed in the entire string.
		.{8, 20} represents at least 8 characters and at most 20 characters.
		$ represents the end of the string.
	 */
	
	@Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$", message = "Invalid Password Rule")
	private String password;
	
	
	transient private Merchant merchant;
	
	transient private Admin admin;
	
}


