package com.shiva.DDFSS.controller;


import com.shiva.DDFSS.repository.TestRepo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HomeController {

    private final TestRepo repo;

    public HomeController(TestRepo repo) {
        this.repo = repo;
    }

    @GetMapping("/")
    public String home(){
        return "Welcome to Scorpion World";
    }

    @GetMapping("/testdb")
    public String testdb(){
        return repo.testdb();
    }
}
