package com.uams.model.internal;

import java.util.*;

import static java.lang.Boolean.FALSE;

public class User {

    private String id;
    private String name;
    private String lastName;
    private String username;
    private String password;
    private Boolean isLoggedIn;
    private Collection<String> searchHistory;

    public User() {
        this(null, null, null, null, null, FALSE, new ArrayList<>());
    }

    public User(String id, String name, String lastName, String username, String password, Boolean isLoggedIn, Collection<String> searchHistory) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.isLoggedIn = isLoggedIn;
        this.searchHistory = searchHistory;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(Boolean loggedIn) {
        isLoggedIn = loggedIn;
    }

    public Collection<String> getSearchHistory() {
        return searchHistory;
    }

    public void setSearchHistory(Collection<String> searchHistory) {
        this.searchHistory = searchHistory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(name, user.name) && Objects.equals(lastName, user.lastName) && Objects.equals(username, user.username) && Objects.equals(password, user.password) && Objects.equals(isLoggedIn, user.isLoggedIn) && Objects.equals(searchHistory, user.searchHistory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, lastName, username, password, isLoggedIn, searchHistory);
    }
}
