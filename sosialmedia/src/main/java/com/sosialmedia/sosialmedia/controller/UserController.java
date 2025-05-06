package com.sosialmedia.sosialmedia.controller;

import com.sosialmedia.sosialmedia.entity.User;
import com.sosialmedia.sosialmedia.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User created = userService.creat(user);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUser() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PutMapping({"id"})
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User user) {
        User userup = userService.update(id, user);
        return new ResponseEntity<>(userup, HttpStatus.OK);
    }
@DeleteMapping({"id"})
    public ResponseEntity<String> delete(@PathVariable Long id) {
        userService.delete(id);
        return new ResponseEntity<>("User is delete with succesfull", HttpStatus.NO_CONTENT);
    }


}
