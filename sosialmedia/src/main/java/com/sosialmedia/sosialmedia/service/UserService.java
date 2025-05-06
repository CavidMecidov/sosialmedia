package com.sosialmedia.sosialmedia.service;

import com.sosialmedia.sosialmedia.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
User creat(User user);
User update(Long id,User userup);
void delete(Long id);
User getByUserId(Long id);
List<User> getAllUsers();

}
