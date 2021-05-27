package com.example.sweater.Controller;


import com.example.sweater.Repository.UserRepository;
import com.example.sweater.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class HomeController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        Iterable<User> users = userRepository.findAll();
        model.put("users", users);
        return "greeting";
    }

    @PostMapping("addUser")
    public String addNewUser(@RequestParam String name, @RequestParam String email, Map<String, Object> model){
        User n = new User(name, email);
        userRepository.save(n);
        Iterable<User> users = userRepository.findAll();
        model.put("users", users);
        return "greeting";
    }
}


