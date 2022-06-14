package com.uams.controller;

import com.uams.model.dto.CredentialDto;
import com.uams.model.dto.LogoutDto;
import com.uams.model.dto.RegisterDto;
import com.uams.model.response.LogoutResponse;
import com.uams.model.response.UserResponse;
import com.uams.service.IUserService;

import javax.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.uams.util.DtoConverter.toInternal;
import static com.uams.util.DtoConverter.toUserResponse;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController("user-controller")
@RequestMapping(path = "user/api/v1", produces = APPLICATION_JSON_VALUE)
@Validated
public class UserController {

    private final IUserService service;

    @Autowired
    public UserController(IUserService service) {
        this.service = service;
    }

    @PostMapping(path = "/register", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponse> registerUser(@RequestBody RegisterDto registerDto) {
        UserResponse response = toUserResponse(service.saveUser(toInternal(registerDto)));
        return ResponseEntity.status(CREATED).body(response);
    }

    @PostMapping(path = "/login", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponse> loginUser(@RequestBody CredentialDto credentialDto) {
        UserResponse response = toUserResponse(service.updateUserLoginStatus(credentialDto.getUsername(), credentialDto.getPassword()));
        return ResponseEntity.status(OK).body(response);
    }

    @PostMapping(path = "/logout", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<LogoutResponse> logoutUser(@RequestBody LogoutDto logoutDto) {
        service.updateUserLoginStatus(logoutDto.getUsername());
        return ResponseEntity.status(OK).body(new LogoutResponse("User logged out"));
    }

    @PatchMapping(path = "/search-history/{search}/username/{username}")
    public ResponseEntity<UserResponse> updateSearchHistory(
            @PathVariable("search") @NotBlank String search,
            @PathVariable("username") @NotBlank String username)
    {
        UserResponse response = toUserResponse(service.updateSearchHistory(search, username));
        return ResponseEntity.status(OK).body(response);
    }

    @DeleteMapping(path = "/unregister", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> unregisterUser(@RequestBody CredentialDto credentialDto) {
        service.deleteUser(credentialDto.getUsername(), credentialDto.getPassword());
        return ResponseEntity.status(NO_CONTENT).build();
    }
}
