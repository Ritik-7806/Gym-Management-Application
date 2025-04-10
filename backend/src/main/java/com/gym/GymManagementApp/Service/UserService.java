package com.gym.GymManagementApp.Service;


import com.gym.GymManagementApp.Entity.User;
import com.gym.GymManagementApp.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserService {

    @Autowired private UserRepository userRepository ;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    public boolean saveNewUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        //user.setRoles(Arrays.asList("user"));
        userRepository.save(user);
        return true;

    }

    public boolean saveAdmin(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER","ADMIN"));
        userRepository.save(user);
        return true;
    }

}
