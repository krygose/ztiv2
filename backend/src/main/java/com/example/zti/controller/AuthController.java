package com.example.zti.controller;


import com.example.zti.common.problem.InternalServerErrorProblem;
import com.example.zti.model.LoginMessage;
import com.example.zti.model.LoginRequest;
import com.example.zti.service.token.TokenService;
import com.example.zti.service.user.model.UserDto;
import com.example.zti.service.user.sql.UserSqlService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private static final Logger log = LoggerFactory.getLogger(AuthController.class);
    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;
    private UserSqlService userSqlService;


    public AuthController(TokenService tokenService, AuthenticationManager authenticationManager, UserDetailsService userDetailsService, UserSqlService userSqlService) {
        this.tokenService = tokenService;
        this.authenticationManager = authenticationManager;
        this.userSqlService = userSqlService;
    }

    @PostMapping("/api/token")
    public LoginMessage token(@RequestBody LoginRequest userLogin) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userLogin.email(), userLogin.password()));

            UserDto user = userSqlService.getUserByEmail(userLogin.email());
            LoginMessage loginMessage = new LoginMessage(tokenService.generateToken(authentication), user.id());
            return loginMessage;

        } catch (Exception e) {
            log.error(
                    "Unable to retrieve tracks due to an unexpected error message={}", e.getMessage(), e);
            throw new InternalServerErrorProblem();
        }
    }
}