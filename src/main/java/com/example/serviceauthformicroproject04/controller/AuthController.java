package com.example.serviceauthformicroproject04.controller;

import com.example.serviceauthformicroproject04.errorResponse.ErrorResponse;
import com.example.serviceauthformicroproject04.exception.LoginException;
import com.example.serviceauthformicroproject04.exception.RegistrationException;
import com.example.serviceauthformicroproject04.model.TokenResponse;
import com.example.serviceauthformicroproject04.model.User;
import com.example.serviceauthformicroproject04.service.PersonService;
import com.example.serviceauthformicroproject04.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Vladislav Domaniewski
 */

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final PersonService personService;
    private final TokenService tokenService;

    @PostMapping
    public ResponseEntity<String> register(@RequestBody User user) {
        personService.register(user.getPersonId(), user.getPersonSecret());
        return ResponseEntity.ok("Registered");
    }

    @PostMapping("/token")
    public TokenResponse getToken(@RequestBody User user) {
        personService.checkCredentials(
                user.getPersonId(), user.getPersonSecret());
        return new TokenResponse(
                tokenService.personGeneratedToken(user.getPersonId()));
    }

    @ExceptionHandler({RegistrationException.class, LoginException.class})
    public ResponseEntity<ErrorResponse> handleUserRegistrationException(RuntimeException ex) {
        return ResponseEntity
                .badRequest()
                .body(new ErrorResponse(ex.getMessage()));
    }
}
