package com.shiva.DDFSS.controller;
import com.shiva.DDFSS.model.Users;
import com.shiva.DDFSS.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public String Register(@RequestBody Users user){
       return  service.register(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody Users user){
        return  service.login(user);
    }
}
