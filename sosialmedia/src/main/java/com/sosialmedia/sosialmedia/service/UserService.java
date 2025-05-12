package com.sosialmedia.sosialmedia.service;

import com.sosialmedia.sosialmedia.dto.UserResponse;
import com.sosialmedia.sosialmedia.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User update(Long id, User userup);

    void delete(Long id);

    User getByUserId(Long id);

    List<User> getAllUsers();

    Optional<User> getByUserName(String username);

    List<UserResponse> searchUsers(String name, String surname, String username, int page, int size);


}
