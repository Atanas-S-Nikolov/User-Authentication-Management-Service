package com.uams.model.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import java.util.Objects;

@Entity
@Table(name = "search_history")
public class SearchHistoryEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id")
    private String id;

    @Column(name = "search")
    private String search;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    public SearchHistoryEntity() {
        this(null, null, null);
    }

    public SearchHistoryEntity(String id, String search, UserEntity user) {
        this.id = id;
        this.search = search;
        this.user = user;
    }

    public String getId() {
        return id;
    }

    private void setId(String id) {
        this.id = id;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SearchHistoryEntity)) return false;
        SearchHistoryEntity that = (SearchHistoryEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(search, that.search) && Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, search, user);
    }
}
