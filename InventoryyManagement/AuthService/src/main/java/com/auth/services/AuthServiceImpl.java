package com.auth.services;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth.dto.AuthDTO;
import com.auth.dto.JwtAuthResponse;
import com.auth.dto.LoginDTO;
import com.auth.entity.Auth;
import com.auth.entity.Roles;
import com.auth.exceptions.UserAlreadyExistException;
import com.auth.exceptions.UserNotFoundException;
import com.auth.externalservices.AdminClient;
import com.auth.externalservices.MerchantClient;
import com.auth.pojo.Admin;
import com.auth.pojo.Merchant;
import com.auth.repository.AuthRepository;
import com.auth.repository.RolesRepository;
import com.auth.security.JwtTokenProvider;

@Service 
public class AuthServiceImpl implements AuthService{
	
	private AuthRepository authRepository;
	private RolesRepository rolesRepository;
	
	private MerchantClient merchantClient;
	private AdminClient adminClient;
	
	private PasswordEncoder passwordEncoder;
	
	private AuthenticationManager authenticationManager;
	
	private JwtTokenProvider jwtTokenProvider;
	
	@Autowired
	public void setAuthRepository(AuthRepository authRepository) {
		this.authRepository = authRepository;
	}
	
	@Autowired	
	public void setRolesRepository(RolesRepository rolesRepository) {
		this.rolesRepository = rolesRepository;
	}

	@Autowired	
	public void setMerchantClient(MerchantClient merchantClient) {
		this.merchantClient = merchantClient;
	}
	
	@Autowired
	public void setAdminClient(AdminClient adminClient) {
		this.adminClient = adminClient;
	}

	@Autowired
	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	@Autowired
	public void setAuthenticationManager(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}
	
	@Autowired
	public void setJwtTokenProvider(JwtTokenProvider jwtTokenProvider) {
		this.jwtTokenProvider = jwtTokenProvider;
	}

	@Override
	public AuthDTO addMerchantAuth(AuthDTO authDTO) {
		
		//Checking if UserId Already present
		if(authRepository.existsById(authDTO.getUserId())) {
			throw new UserAlreadyExistException("User with Id: "+authDTO.getUserId()+" already present");
		}
		
		//Checking if Email Already present
		 if(authRepository.existsByEmail(authDTO.getEmail())){
			 throw new UserAlreadyExistException("User with Email Id: "+ authDTO.getEmail() +" already Exists");
		 }
		
		Merchant merchant = authDTO.getMerchant();
		merchant.setMerchantId(authDTO.getUserId());
		merchantClient.registerMerchant(merchant);
		
		Auth auth = new Auth();
		
		auth.setUserId(authDTO.getUserId());
		auth.setEmail(authDTO.getEmail());
		auth.setPassword(passwordEncoder.encode(authDTO.getPassword()));
		
		Set<Roles> set = new HashSet<>();
		
		//Merchant Role
		Roles role = rolesRepository.findByName("ROLE_MERCHANT");
		set.add(role);
		
		auth.setRoles(set);
		
		authRepository.save(auth);
		
		return authDTO;
	}
	
	@Override
	public AuthDTO addAdminAuth(AuthDTO authDTO) {
		//Checking if UserId Already present
		if(authRepository.existsById(authDTO.getUserId())) {
			throw new UserAlreadyExistException("User with id "+authDTO.getUserId()+" already present");
		}
		
		 if(authRepository.existsByEmail(authDTO.getEmail())){
			 throw new UserAlreadyExistException("USer with Email Id: "+ authDTO.getEmail() +" already Exists");
		 }
		
		Admin admin = authDTO.getAdmin();
		admin.setAdminId(authDTO.getUserId());
		adminClient.registerAdmin(admin);
		
		Auth auth = new Auth();
		
		auth.setUserId(authDTO.getUserId());
		auth.setEmail(authDTO.getEmail());
		auth.setPassword(passwordEncoder.encode(authDTO.getPassword()));
		
		Set<Roles> set = new HashSet<>();
		
		//Admin Role
		Roles role = rolesRepository.findByName("ROLE_ADMIN");
		set.add(role);
		
		//Merchant Role
		Roles role1 = rolesRepository.findByName("ROLE_MERCHANT");
		set.add(role1);
		
		auth.setRoles(set);
		authRepository.save(auth);
		
		return authDTO;
	}

	@Override
	public JwtAuthResponse login(LoginDTO loginDTO) {
		
		Optional<Auth> authOptional = authRepository.findByUserIdOrEmail(loginDTO.getUserIdOrEmail(), loginDTO.getUserIdOrEmail());
		
		if(authOptional.isEmpty()) {
			throw new UserNotFoundException(" User With Credential "+ loginDTO.getUserIdOrEmail() +" Not Found");
		}
		
		if(authRepository.existsByEmail(loginDTO.getUserIdOrEmail())) {
//			String userId = authRepository.findBy
		}
		
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
						loginDTO.getUserIdOrEmail(),
						loginDTO.getPassword()
						));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String token = jwtTokenProvider.generateToken(authentication);
		
		String role = "ROLE_MERCHANT";
		
		if(authOptional.isPresent()) {
			
			Auth loggedInUser = authOptional.get();
			Boolean isAdmin = loggedInUser.getRoles().stream().anyMatch( r -> r.getName().equals("ROLE_ADMIN"));
			
			if(isAdmin) role = "ROLE_ADMIN";
		}
		
		JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
		jwtAuthResponse.setAccessToken(token);
		jwtAuthResponse.setRole(role);
		
		return jwtAuthResponse;
	}

	@Override
	public String deleteAuthData(String userId) {
		Optional<Auth> authOptional = authRepository.findById(userId);
		
		if(authOptional.isEmpty()) {
			throw new UserNotFoundException(" User With Credential "+ userId +" Not Found");
		}
		
		String role = "ROLE_MERCHANT";
		
		Auth loggedInUser = authOptional.get();
		
		if(authOptional.isPresent()) {
			
			Boolean isAdmin = loggedInUser.getRoles().stream().anyMatch( r -> r.getName().equals("ROLE_ADMIN"));
			
			if(isAdmin) role = "ROLE_ADMIN";
		}
		
		if(role == "ROLE_ADMIN") {
			adminClient.deleteAdmin(userId);
		}
		else {
			merchantClient.deleteMerchant(userId);
		}
		
		authRepository.delete(loggedInUser);
		return "User With Id: "+userId+" deleted successfully";
	}

	

}
