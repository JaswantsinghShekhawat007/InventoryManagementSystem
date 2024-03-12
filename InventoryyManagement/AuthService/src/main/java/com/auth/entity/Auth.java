package com.auth.entity;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="authdata")
public class Auth {

	@Id
	private String userId;
	
	@Column( nullable = false, unique = true )
	private String email;
	
	@Column ( nullable = false )
	private String password;	
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name="auth-roles",
		joinColumns = @JoinColumn( name="auth-id", referencedColumnName = "userId" ),
		inverseJoinColumns = @JoinColumn( name="role-id", referencedColumnName = "id" )
	)
	private Set<Roles> roles;
	
}
