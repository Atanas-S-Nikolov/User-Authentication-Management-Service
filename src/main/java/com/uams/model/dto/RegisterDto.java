package com.uams.model.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RegisterDto {

    private final String name;
    private final String lastName;
    private final String username;
    private final String password;

    @JsonCreator
    public RegisterDto(
            @JsonProperty("name") String name,
            @JsonProperty("last_name") String lastName,
            @JsonProperty("username") String username,
            @JsonProperty("password") String password)
    {
        this.name = name;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("last_name")
    public String getLastName() {
        return lastName;
    }

    @JsonProperty("username")
    public String getUsername() {
        return username;
    }

    @JsonProperty("password")
    public String getPassword() {
        return password;
    }

}
