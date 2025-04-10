package com.gym.GymManagementApp.Controller;

import com.gym.GymManagementApp.Entity.Trainer;
import com.gym.GymManagementApp.Entity.User;
import com.gym.GymManagementApp.Repository.TrainerRepository;
import com.gym.GymManagementApp.Repository.UserRepository;
import com.gym.GymManagementApp.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("public")
public class PublicController {

    @Autowired private UserRepository userRepository ;
    @Autowired private TrainerRepository trainerRepository ;
    @Autowired private UserService userService ;

    @PostMapping
    public ResponseEntity<?> signup(@RequestBody User user){
        userService.saveNewUser(user) ;
        return new ResponseEntity<>(HttpStatus.CREATED) ;
    }

    @GetMapping
    public ResponseEntity<?> viewTrainers(){
        List<Trainer> all = trainerRepository.findAll();
        return new ResponseEntity<>(all,HttpStatus.OK) ;
    }

//    @DeleteMapping("user/{id}")
//    public ResponseEntity<Object> deleteUser(@PathVariable String id){
//        if(userRepository.existsById(id)){
//            userRepository.deleteById(id);
//        return new ResponseEntity<>(HttpStatus.OK) ;
//        }
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND) ;
//
//    }




}
