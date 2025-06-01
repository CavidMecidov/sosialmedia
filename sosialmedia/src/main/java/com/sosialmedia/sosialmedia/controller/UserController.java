package com.sosialmedia.sosialmedia.controller;

import com.sosialmedia.sosialmedia.dto.BaseResponse;
import com.sosialmedia.sosialmedia.dto.UserResponse;
import com.sosialmedia.sosialmedia.entity.User;
import com.sosialmedia.sosialmedia.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;


    @PreAuthorize("hasRole('USER')")
    @GetMapping("/my-information")
    public BaseResponse<UserResponse> getMyInformation() {
        var userResponses = userService.getMyInformation();
        BaseResponse<UserResponse> baseResponse = new BaseResponse<>();
        baseResponse.setSuccess(true);
        baseResponse.setData(userResponses);
        baseResponse.setMessage("My information: " + userResponses);
        return baseResponse;
    }
    @GetMapping("/allUsers")
    public BaseResponse<List<UserResponse>> getAllUser() {
        List<UserResponse> users = userService.getAllUsers();
        BaseResponse<List<UserResponse>> baseResponse = new BaseResponse<>();
        baseResponse.setData(users);
        baseResponse.setSuccess(true);
        baseResponse.setMessage("All users: " + users);
        return baseResponse;
    }

    @PutMapping("/{id}")//
    public BaseResponse<User> update(@PathVariable Long id, @RequestBody User user) {
        var userUp = userService.update(id, user);
        BaseResponse<User> baseResponse = new BaseResponse<>();
        baseResponse.setData(userUp);
        baseResponse.setSuccess(true);
        baseResponse.setMessage("User updated" + userUp);
        return baseResponse;
    }
     @PreAuthorize("hasRole(ADMIN)")
    @DeleteMapping("/{id}")
     public BaseResponse<String> delete(@PathVariable Long id) {
         userService.delete(id);
         BaseResponse<String> baseResponse = new BaseResponse<>();
         baseResponse.setSuccess(true);
         baseResponse.setMessage("User delete successful");
         baseResponse.setData("Deleted user with id: " + id);
         return baseResponse;
     }
    @GetMapping("/search")
    public BaseResponse<List<UserResponse>> searchUsers(@RequestParam(value = "name", required = false, defaultValue = "") String name, @RequestParam(value = "surname", required = false, defaultValue = "") String surname, @RequestParam(value = "username", required = false, defaultValue = "") String username, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size) {
        List<UserResponse> users = userService.searchUsers(name, surname, username, page, size);
        BaseResponse<List<UserResponse>> baseResponse = new BaseResponse<>();
        baseResponse.setData(users);
        baseResponse.setMessage("Searched users:" + users);
        baseResponse.setSuccess(true);
        return baseResponse;
    }
}



