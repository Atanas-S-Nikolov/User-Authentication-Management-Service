package com.uams.model.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RegisterDto)) return false;
        RegisterDto that = (RegisterDto) o;
        return Objects.equals(name, that.name) && Objects.equals(lastName, that.lastName) && Objects.equals(username, that.username) && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, lastName, username, password);
    }
}
