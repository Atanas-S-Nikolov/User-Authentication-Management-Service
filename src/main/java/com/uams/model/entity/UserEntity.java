package com.uams.model.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

import static java.lang.Boolean.FALSE;

@Entity
@Table(name = "user")
public class UserEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "is_logged_in")
    private Boolean isLoggedIn;

    @OneToMany(mappedBy = "user")
    @Column(name = "search_history")
    private Collection<SearchHistoryEntity> searchHistory;

    public UserEntity() {
        this(null, null, null, null, null, FALSE, new ArrayList<>());
    }

    public UserEntity(String id, String name, String lastName, String username, String password, Boolean isLoggedIn, Collection<SearchHistoryEntity> searchHistory) {
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

    private void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    private void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    private void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    private void setPassword(String password) {
        this.password = password;
    }

    public Boolean getLoggedIn() {
        return isLoggedIn;
    }

    private void setLoggedIn(Boolean loggedIn) {
        isLoggedIn = loggedIn;
    }

    public Collection<SearchHistoryEntity> getSearchHistory() {
        return searchHistory;
    }

    private void setSearchHistory(Collection<SearchHistoryEntity> searchHistory) {
        this.searchHistory = searchHistory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserEntity)) return false;
        UserEntity that = (UserEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(lastName, that.lastName) && Objects.equals(username, that.username) && Objects.equals(password, that.password) && Objects.equals(isLoggedIn, that.isLoggedIn) && Objects.equals(searchHistory, that.searchHistory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, lastName, username, password, isLoggedIn, searchHistory);
    }
}
