package com.uams.model.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class LogoutDto {

    private final String username;

    @JsonCreator
    public LogoutDto(@JsonProperty("username") String username) {
        this.username = username;
    }

    @JsonProperty("username")
    public String getUsername() {
        return username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LogoutDto)) return false;
        LogoutDto logoutDto = (LogoutDto) o;
        return Objects.equals(username, logoutDto.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }
}
