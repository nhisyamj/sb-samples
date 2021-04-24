package com.nhisyamj.wso2;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/users")
@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/create")
    public ResponseEntity createUser(@RequestBody UserDTO userDTO) throws Exception {
        Boolean isUserCreated = userService.createUser(userDTO);
        if (isUserCreated) {
            return ResponseEntity.ok("user successfully created");
        } else {
            return ResponseEntity.ok("Failed to created user");
        }
    }
}
