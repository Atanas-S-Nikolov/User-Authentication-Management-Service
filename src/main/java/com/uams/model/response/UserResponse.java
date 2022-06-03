package com.uams.model.response;

import org.apache.commons.lang3.StringUtils;

import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserResponse {

    private final String name;
    private final String lastName;
    private final String username;
    private final String password;
    private final Collection<String> searchHistory;

    public UserResponse(String name, String lastName, String username, String password, Collection<String> searchHistory) {
        this.name = name;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.searchHistory = searchHistory.stream()
                .filter(StringUtils::isNotBlank)
                .collect(Collectors.toSet());
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Collection<String> getSearchHistory() {
        return searchHistory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserResponse)) return false;
        UserResponse userResponse = (UserResponse) o;
        return Objects.equals(name, userResponse.name) && Objects.equals(lastName, userResponse.lastName) && Objects.equals(username, userResponse.username) && Objects.equals(password, userResponse.password) && Objects.equals(searchHistory, userResponse.searchHistory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, lastName, username, password, searchHistory);
    }
}
