package com.kutub.SpringBoot.restcontroller;

import com.kutub.SpringBoot.entity.User;
import com.kutub.SpringBoot.service.UserService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@CrossOrigin("*")
public class UserRestController {

    @Autowired
    private UserService userService;


    @PostMapping("/save")
    public  void saveDep(@RequestBody User u) throws MessagingException {
        userService.saveUser(u);
    }
}
