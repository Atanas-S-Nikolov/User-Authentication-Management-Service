package com.uams.repository;

import com.uams.model.entity.SearchHistoryEntity;
import com.uams.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SearchHistoryRepository extends JpaRepository<SearchHistoryEntity, String> {

    @Query("select s from SearchHistoryEntity s where s.search = :search and s.user = :user")
    Optional<SearchHistoryEntity> findBySearchAndUserId(@Param(value = "search") String search,
                                                        @Param(value = "user") UserEntity user);
}
