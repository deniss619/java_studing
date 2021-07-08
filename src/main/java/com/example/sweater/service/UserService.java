package com.example.sweater.service;

import com.example.sweater.Repository.UserRepository;
import com.example.sweater.domain.Role;
import com.example.sweater.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.mail.MessagingException;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    MailSender mailSender;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

    public boolean addUser(User user) throws MessagingException {
        User userFromDB = userRepository.findByUsername(user.getUsername());
        if (userFromDB != null){
            return false;
        }
        user.setActive(false);
        user.setRoles(Collections.singleton(Role.USER));
        userRepository.save(user);

        if (!StringUtils.isEmpty(user.getEmail())){

            //sendMail(user.getId());


//            String message = String.format(
//                    "Hello, %s \n"+
//                            "Welcome to Sweater. Please, visit next link: <a href='http://localhost:8080/activate/%s'>link</a>",
//                    user.getUsername(),
//                    user.getActivationCode()
//            );
//
//            mailSender.send(user.getEmail(), "Activation code", message);

        }

        return true;

    }

    public boolean activateUser(String code) {
        User user = userRepository.findByActivationCode(code);
        if (user == null){
            return false;
        }
        user.setActive(true);
        user.setActivationCode(null);
        userRepository.save(user);
        return true;
    }

    public void sendMail(Integer id) throws MessagingException {
        if (userRepository.findById(id).isPresent()) {
            User user = userRepository.findById(id).get();
            user.setActivationCode(UUID.randomUUID().toString());
            System.out.println(user.getUsername());
            String message = String.format(
                    "Hello, %s.  \n" +
                            "Welcome to Sweater. Please, visit this: <a href='http://localhost:8080/activate/%s'>link</a>",
                    user.getUsername(),
                    user.getActivationCode()
            );
            System.out.println(message);
            mailSender.send(user.getEmail(), "Activation code", message);
        }
//        Optional<User> usr = userRepository.findById(id);
//        User user = usr.get();
//        user.setActivationCode(UUID.randomUUID().toString());
//        String message = String.format(
//                "Hello, %s.  \n" +
//                        "Welcome to Sweater. Please, visit this: <a href='http://localhost:8080/activate/%s'>link</a>",
//                user.getUsername(),
//                user.getActivationCode()
//        );
//        System.out.println(message);
//        mailSender.send(user.getEmail(), "Activation code", message);

    }
}
