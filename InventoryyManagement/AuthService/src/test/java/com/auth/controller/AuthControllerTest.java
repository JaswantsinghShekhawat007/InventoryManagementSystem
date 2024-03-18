package com.auth.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.auth.dto.AuthDTO;
import com.auth.dto.JwtAuthResponse;
import com.auth.dto.LoginDTO;
import com.auth.externalservices.MerchantClient;
import com.auth.services.AuthService;

@ExtendWith(MockitoExtension.class)
class AuthControllerTest {

    @Mock
    AuthService authService;
    @Mock
    MerchantClient merchantClient;

    @InjectMocks
    AuthController authController;

    @Test
    void testRegisterMerchant() {
        AuthDTO authDTO = new AuthDTO();

        when(authService.addMerchantAuth(authDTO)).thenReturn(authDTO);

        ResponseEntity<AuthDTO> response = authController.registerMerchant(authDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(authDTO, response.getBody());
    }

    @Test
    void testRegisterAdmin() {
        AuthDTO authDTO = new AuthDTO();

        when(authService.addAdminAuth(authDTO)).thenReturn(authDTO);

        ResponseEntity<AuthDTO> response = authController.registerAdmin(authDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(authDTO, response.getBody());
    }

    @Test
    void testLogin() {
        LoginDTO loginDTO = new LoginDTO();
        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();

        when(authService.login(loginDTO)).thenReturn(jwtAuthResponse);
        ResponseEntity<JwtAuthResponse> expectedResponse = new ResponseEntity<>(jwtAuthResponse, HttpStatus.ACCEPTED);

        ResponseEntity<JwtAuthResponse> response = authController.login(loginDTO);

        assertEquals(expectedResponse.getStatusCode(), response.getStatusCode());
        assertEquals(expectedResponse.getBody(), response.getBody());
//        verify(merchantClient).getResponse(jwtAuthResponse);
    }
}
