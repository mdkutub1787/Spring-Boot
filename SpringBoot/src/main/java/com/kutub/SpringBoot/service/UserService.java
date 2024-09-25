package com.kutub.SpringBoot.service;

import com.kutub.SpringBoot.entity.User;
import com.kutub.SpringBoot.repository.UserRepository;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;


    public void saveUser(User u) throws MessagingException {

        String toEmail = u.getEmail();
        String subject = "Registration Confirmation";
        String body = String.format("Thanks %s, \n Stay with us and your \n User Name is %s.", u.getName(), u.getEmail());

        emailService.sendSimpleEmail(toEmail, subject, body);

        userRepository.save(u);
    }


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User findById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

    public void updateUser(User u) {
        userRepository.save(u);
    }

}
