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
import java.util.Optional;


@RestController
@RequestMapping("admin")
public class AdminController {

    @Autowired private UserRepository userRepository ;
    @Autowired private TrainerRepository trainerRepository ;
    @Autowired private UserService userService ;

    @GetMapping("users")
    public ResponseEntity allUsers(){
        List<User> all = userRepository.findAll();
        return new ResponseEntity<>(all, HttpStatus.OK) ;
    }

    @GetMapping("trainers")
    public ResponseEntity<?> allTrainers(){
        List<Trainer> all = trainerRepository.findAll();
        return new ResponseEntity<>(all, HttpStatus.OK) ;
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> delUser(@PathVariable String id){

        if(userRepository.existsById(id)){
            userRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.ACCEPTED) ;
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND) ;
    }

    @DeleteMapping("/trainer/{id}")
    public ResponseEntity<?> delTrainer(@PathVariable String id){

        if(trainerRepository.existsById(id)){
            trainerRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.ACCEPTED) ;
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND) ;
    }

    @PostMapping
    public void addTrainer(@RequestBody Trainer trainer){
        trainerRepository.save(trainer) ;
    }

    @PutMapping("trainer/{id}")
    public ResponseEntity<Object> updateTrainer(@PathVariable String id,@RequestBody Trainer trainer){
        Optional<Trainer> byId = trainerRepository.findById(id);
        if(byId.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND) ;
        }
        Trainer updatedTrainer = byId.get() ;
        updatedTrainer.setName(trainer.getName());
        updatedTrainer.setAge(trainer.getAge());
        updatedTrainer.setSex(trainer.getSex());
        updatedTrainer.setSpeciality(trainer.getSpeciality());

        trainerRepository.save(updatedTrainer) ;

        return new ResponseEntity<>(HttpStatus.ACCEPTED) ;
    }

//    @DeleteMapping("trainee/{id}")
//    public ResponseEntity<?> deleteTrainer(@PathVariable String id){
//        if(trainerRepository.existsById(id)){
//            trainerRepository.deleteById(id);
//            return new ResponseEntity<>(HttpStatus.ACCEPTED) ;
//        }
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND) ;
//    }

    @PostMapping("new_admin")
    public ResponseEntity<Object> newAdmin(@RequestBody User user){
        userService.saveAdmin(user) ;
        return new ResponseEntity<>(HttpStatus.OK) ;
    }

    @GetMapping("view-plans")
    public String plans(){
        String plans = " 1 month = 1000 INR && 3 months = 2500 INR && 12 months = 8000" ;
        return plans ;
    }







}
