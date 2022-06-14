package com.uams.service;

import com.uams.exception.UserConstraintViolationException;
import com.uams.exception.InvalidCredentialsException;
import com.uams.exception.UserLoginStatusException;
import com.uams.model.entity.SearchHistoryEntity;
import com.uams.model.entity.UserEntity;
import com.uams.model.internal.User;
import com.uams.repository.SearchHistoryRepository;
import com.uams.repository.UserRepository;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

import static com.uams.util.DtoConverter.toEntity;
import static com.uams.util.DtoConverter.toInternal;
import static java.util.stream.Collectors.toList;

public class UserService implements IUserService {

    private final UserRepository repository;
    private final SearchHistoryRepository searchHistoryRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository repository, SearchHistoryRepository searchHistoryRepository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.searchHistoryRepository = searchHistoryRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User saveUser(User user) {
        return executeRepositoryCall(() -> {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            UserEntity entity = repository.save(toEntity(user));
            return toInternal(entity);
        });
    }

    @Transactional
    @Override
    public User updateUserLoginStatus(String username, String password) {
        return executeRepositoryCall(() -> {
            User user = toInternal(findUser(username, password));
            checkLoginStatus(true, user.getLoggedIn());
            user.setLoggedIn(true);
            repository.updateLoginStatus(user.getId(), user.getLoggedIn());
            return user;
        });
    }

    @Transactional
    @Override
    public User updateUserLoginStatus(String username) {
        return executeRepositoryCall(() -> {
            Optional<UserEntity> optionalEntity = repository.findByUsername(username);

            User user = new User();
            if (optionalEntity.isPresent()) {
                user = toInternal(optionalEntity.get());
            }

            checkLoginStatus(false, user.getLoggedIn());
            user.setLoggedIn(false);
            repository.updateLoginStatus(user.getId(), user.getLoggedIn());
            return user;
        });
    }

    @Override
    public User updateSearchHistory(String search, String username) {
        return executeRepositoryCall(() -> {
            Optional<UserEntity> optionalEntity = repository.findByUsername(username);

            User user = new User();
            if (optionalEntity.isPresent()) {
                UserEntity entity = optionalEntity.get();

                if (!searchHistoryRepository.findBySearchAndUserId(search, entity).isPresent()) {
                    searchHistoryRepository.save(new SearchHistoryEntity(null, search, entity));
                }
                user = toInternal(entity);
            }

            return user;
        });
    }

    @Transactional
    @Override
    public void deleteUser(String username, String password) {
        executeRepositoryCall(() -> {
            UserEntity entity = findUser(username, password);
            List<String> searchHistoryIds = entity.getSearchHistory().stream()
                    .map(SearchHistoryEntity::getId)
                    .collect(toList());

            searchHistoryRepository.deleteAllById(searchHistoryIds);
            repository.delete(entity);
        });
    }

    private UserEntity findUser(String username, String password) {
        Optional<UserEntity> optionalEntity = repository.findByUsername(username);

        if (optionalEntity.isPresent()) {
            UserEntity entity = optionalEntity.get();
            if (passwordEncoder.matches(password, entity.getPassword())) {
                return entity;
            }
        }

        throw new InvalidCredentialsException("Invalid credentials");
    }

    private void checkLoginStatus(boolean isLoggedInStatus, boolean currentLoginStatus) {
        if (isLoggedInStatus) {
            if (currentLoginStatus) {
                throw new UserLoginStatusException("User is already logged in");
            }
        } else {
            if (!currentLoginStatus) {
                throw new UserLoginStatusException("User is not logged in");
            }
        }
    }

    private <T> T executeRepositoryCall(Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (DataIntegrityViolationException exception) {
            throw new UserConstraintViolationException("User is already registered");
        }
    }

    private void executeRepositoryCall(Runnable runnable) {
        runnable.run();
    }
}
