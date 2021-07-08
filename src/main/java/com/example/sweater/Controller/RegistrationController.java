package com.example.sweater.Controller;


import com.example.sweater.domain.User;
import com.example.sweater.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.mail.MessagingException;
import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    UserService userService;

    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Map<String, Object> model) throws MessagingException {

        if (!userService.addUser(user)){
            model.put("message", "User exists");
            return "registration";
        }
        return String.format("redirect:/registrationSuccession/%s", user.getId());
    }

    @GetMapping("/activate/{code}")
    public String activate(Model model, @PathVariable String code){
        boolean isActivated = userService.activateUser(code);
        if (isActivated){
            model.addAttribute("message","User successfully activated");
        } else {
            model.addAttribute("message","Activation code is not found");
        }
        return "login";
    }

    @GetMapping("/registrationSuccession/{id}")
    public String registrationSuccession(@PathVariable Integer id, Map<String, Object> model) throws MessagingException {
        model.put("user_id", id);
        return "registrationSuccession";

    }
    @PostMapping("/registrationSuccession/{id}")
    public String sendMailAgain(@PathVariable Integer id, Model model) throws MessagingException{
        userService.sendMail(id);
        System.out.println(id);
        model.addAttribute("user_id", id);
        System.out.println(id);
        return "registrationSuccession";
    }

}
