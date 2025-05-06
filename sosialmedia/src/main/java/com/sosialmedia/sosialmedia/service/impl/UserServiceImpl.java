package com.sosialmedia.sosialmedia.service.impl;

import com.sosialmedia.sosialmedia.entity.User;
import com.sosialmedia.sosialmedia.repository.UserRepository;
import com.sosialmedia.sosialmedia.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    @Override
    public User creat(User user) {
        return userRepository.save(user);
    }

    @Override
    public User update(Long id, User userup) {
        User exsitngUser= userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found: " + id));

        userup.setName(userup.getName());
        userup.setSurname(userup.getSurname());
        userup.setGender(userup.getGender());
        userup.setBirthdate(userup.getBirthdate());
        userup.setBio(userup.getBio());
        userup.setEmail(userup.getEmail());
        userup.setPhoneNumber(userup.getPhoneNumber());
        userup.setUsername(userup.getUsername());
        userup.setPassword(userup.getPassword());
        userup.setModifiedDate(userup.getModifiedDate());

        return userRepository.save(exsitngUser);

    }

    @Override
    public void delete(Long id) {
userRepository.deleteById(id);
    }

    @Override
    public User getByUserId(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found: " + id));
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
