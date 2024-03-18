package com.auth.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.HashSet;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

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

@ExtendWith(MockitoExtension.class)
class AuthServiceImplTest {

    @Mock
    AuthRepository authRepository;
    @Mock
    RolesRepository rolesRepository;
    @Mock
    MerchantClient merchantClient;
    @Mock
    AdminClient adminClient;
    @Mock
    PasswordEncoder passwordEncoder;
    @Mock
    AuthenticationManager authenticationManager;
    @Mock
    JwtTokenProvider jwtTokenProvider;

    @InjectMocks
    AuthServiceImpl authService;

    @Test
    void testAddMerchantAuth() {
        AuthDTO authDTO = new AuthDTO();
        authDTO.setUserId("userId");
        authDTO.setEmail("test@example.com");
        authDTO.setPassword("Password123");
        Merchant merchant = new Merchant();
        merchant.setName("MerchantName");
        authDTO.setMerchant(merchant);

        when(authRepository.existsById("userId")).thenReturn(false);
        when(authRepository.existsByEmail("test@example.com")).thenReturn(false);
        when(rolesRepository.findByName("ROLE_MERCHANT")).thenReturn(new Roles());

        assertDoesNotThrow(() -> authService.addMerchantAuth(authDTO));
    }

    @Test
    void testAddMerchantAuthUserAlreadyExists() {
        AuthDTO authDTO = new AuthDTO();
        authDTO.setUserId("userId");
        authDTO.setEmail("test@example.com");
        authDTO.setPassword("Password123");
        Merchant merchant = new Merchant();
        merchant.setName("MerchantName");
        authDTO.setMerchant(merchant);

        when(authRepository.existsById("userId")).thenReturn(true);

        assertThrows(UserAlreadyExistException.class, () -> authService.addMerchantAuth(authDTO));
    }

    @Test
    void testAddAdminAuth() {
        AuthDTO authDTO = new AuthDTO();
        authDTO.setUserId("userId");
        authDTO.setEmail("test@example.com");
        authDTO.setPassword("Password123");
        Admin admin = new Admin();
        admin.setName("AdminName");
        authDTO.setAdmin(admin);

        when(authRepository.existsById("userId")).thenReturn(false);
        when(authRepository.existsByEmail("test@example.com")).thenReturn(false);
        when(rolesRepository.findByName("ROLE_ADMIN")).thenReturn(new Roles());
        when(rolesRepository.findByName("ROLE_MERCHANT")).thenReturn(new Roles());

        assertDoesNotThrow(() -> authService.addAdminAuth(authDTO));
    }

    @Test
    void testAddAdminAuthUserAlreadyExists() {
        AuthDTO authDTO = new AuthDTO();
        authDTO.setUserId("userId");
        authDTO.setEmail("test@example.com");
        authDTO.setPassword("Password123");
        Admin admin = new Admin();
        admin.setName("AdminName");
        authDTO.setAdmin(admin);

        when(authRepository.existsById("userId")).thenReturn(true);

        assertThrows(UserAlreadyExistException.class, () -> authService.addAdminAuth(authDTO));
    }

    @Test
    void testLogin() {
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setUserIdOrEmail("userIdOrEmail");
        loginDTO.setPassword("Password123");

        Auth auth = new Auth();
        auth.setRoles(new HashSet<>());
        Optional<Auth> authOptional = Optional.of(auth);

        when(authRepository.findByUserIdOrEmail("userIdOrEmail", "userIdOrEmail")).thenReturn(authOptional);
        when(authenticationManager.authenticate(any())).thenReturn(null);
        when(jwtTokenProvider.generateToken(any())).thenReturn("token");

        JwtAuthResponse jwtAuthResponse = authService.login(loginDTO);

        assertNotNull(jwtAuthResponse);
        assertEquals("ROLE_MERCHANT", jwtAuthResponse.getRole());
    }

    @Test
    void testLoginUserNotFound() {
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setUserIdOrEmail("userIdOrEmail");
        loginDTO.setPassword("Password123");

        Optional<Auth> authOptional = Optional.empty();

        when(authRepository.findByUserIdOrEmail("userIdOrEmail", "userIdOrEmail")).thenReturn(authOptional);

        assertThrows(UserNotFoundException.class, () -> authService.login(loginDTO));
    }
}
