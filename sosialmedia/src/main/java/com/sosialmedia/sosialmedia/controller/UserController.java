package com.sosialmedia.sosialmedia.controller;

import com.sosialmedia.sosialmedia.dto.UserResponse;
import com.sosialmedia.sosialmedia.entity.User;
import com.sosialmedia.sosialmedia.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;


    @PreAuthorize("hasRole('USER')")
    @GetMapping("/my-information")
    public ResponseEntity<UserResponse> getMyInformation(){
        UserResponse userResponses =  userService.getMyInformation();
        return  new ResponseEntity<>(userResponses,HttpStatus.OK);
    }
    @GetMapping("/allUsers")
    public ResponseEntity<List<UserResponse>> getAllUser() {
        List<UserResponse> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PutMapping("/{id}")//
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User user) {
        User userup = userService.update(id, user);
        return new ResponseEntity<>(userup, HttpStatus.OK);
    }
     @PreAuthorize("hasRole(ADMIN)")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        userService.delete(id);
        return new ResponseEntity<>("User is delete with succesfull", HttpStatus.NO_CONTENT);
    }

    @GetMapping("/search")
    public ResponseEntity<List<UserResponse>> searchUsers(@RequestParam(value = "name", required = false, defaultValue = "") String name, @RequestParam(value = "surname", required = false, defaultValue = "") String surname, @RequestParam(value = "username", required = false, defaultValue = "") String username, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size) {
        List<UserResponse> users = userService.searchUsers(name, surname, username, page, size);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}



