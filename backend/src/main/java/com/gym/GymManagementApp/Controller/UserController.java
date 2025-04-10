package com.gym.GymManagementApp.Controller;

import com.gym.GymManagementApp.Entity.User;
import com.gym.GymManagementApp.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserRepository userRepository ;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        User newuser = userRepository.findByUsername(username);
        if(newuser == null){ return new ResponseEntity<>(HttpStatus.NOT_FOUND) ;
        }
        newuser.setUsername(user.getUsername());
        newuser.setPassword(passwordEncoder.encode(user.getPassword()));
        newuser.setSex(user.getSex());
        newuser.setRoles(user.getRoles());
        newuser.setPhn_no(user.getPhn_no());

        userRepository.save(newuser) ;

        return new ResponseEntity<>(HttpStatus.ACCEPTED);

    }

    @DeleteMapping
    public ResponseEntity<?> deleteUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        User user = userRepository.findByUsername(username) ;

        if(user != null){
            userRepository.delete(user);
            return new ResponseEntity<>(HttpStatus.OK) ;
              }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND) ;
    }



}
