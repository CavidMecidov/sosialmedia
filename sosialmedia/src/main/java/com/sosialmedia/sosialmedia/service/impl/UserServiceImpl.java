package com.sosialmedia.sosialmedia.service.impl;

import com.sosialmedia.sosialmedia.dto.UserResponse;
import com.sosialmedia.sosialmedia.entity.User;
import com.sosialmedia.sosialmedia.repository.UserRepository;
import com.sosialmedia.sosialmedia.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public User update(Long id, User userup) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        User currentUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Username not found"));

        if (!currentUser.getUserid().equals(id)) {
            throw new RuntimeException("You can only update your own account");
        }

        User existUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));


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

        return userRepository.save(existUser);

    }

    @Override
    public void delete(Long id) {
        String username = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        if(!user.getUserid().equals(id)){
            throw new RuntimeException("You can only  delete own accaunt");
        }
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

    @Override
    public Optional<User> getByUserName(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("İstifadəçi tapılmadı"));

        return Optional.of(user);
    }

    public List<UserResponse> searchUsers(String name, String surname, String username, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<User> userPage = userRepository.searchUsers(name, surname, username, pageable);

        return userPage.stream()
                .map(user -> new UserResponse(
                        user.getUserid(), // Bu metodu "getUserid()" istifadə edərək götürün
                        user.getUsername(),
                        user.getName(),
                        user.getSurname(),
                        user.getEmail(),
                        user.getGender(),
                        user.getBio(),
                        user.getPhoneNumber(),
                        user.getProfilePictureUrl(),
                        user.getCreatedDate(),
                        user.getModifiedDate()
                ))
                .collect(Collectors.toList());


    }


}