package com.example.sweater;


import com.example.sweater.Repository.UserRepository;
import com.example.sweater.domain.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class RegistrationController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/greeting")
    public String greeting(Map<String, Object> model) {
        Iterable<Users> users = userRepository.findAll();
        model.put("users", users);
        return "greeting";
    }

    @PostMapping("addUser")
    public String addNewUser(@RequestParam String name, @RequestParam String email, Map<String, Object> model){
        Users n = new Users(name, email);
        userRepository.save(n);
        Iterable<Users> users = userRepository.findAll();
        model.put("users", users);
        return "greeting";
    }
}


