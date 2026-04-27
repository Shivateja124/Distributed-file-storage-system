package com.shiva.DDFSS.service;

import com.shiva.DDFSS.model.Users;
import com.shiva.DDFSS.repository.UserRepository;
import org.springframework.stereotype.Service;
@Service
public class UserService {

    private final UserRepository userrepo;

    public UserService(UserRepository userrepo) {
        this.userrepo = userrepo;
    }

    public String register(Users user) {
        Users emailUser = userrepo.findByEmail(user.getEmail());
        if (emailUser != null) {
            return "User already exists with this email";
        }

        Users usernameUser = userrepo.findByName(user.getUsername());
        if (usernameUser != null) {
            return "User already exists with this username";
        }
        userrepo.register(user);
        return "Registration Done";
    }

    public String login(Users user) {

        Users details = null;

        // 🔹 Check whether input is email or username
        if (user.getEmail() != null && !user.getEmail().isEmpty()) {
            details = userrepo.findByEmail(user.getEmail());
        } else if (user.getUsername() != null && !user.getUsername().isEmpty()) {
            details = userrepo.findByName(user.getUsername());
        }


        // 🔹 User not found
        if (details == null) {
            return "User not found";
        }
        // 🔹 Password check
        if (!details.getPassword().equals(user.getPassword())) {
            return "Invalid credentials";
        }

        return "Login successful";
    }


}
