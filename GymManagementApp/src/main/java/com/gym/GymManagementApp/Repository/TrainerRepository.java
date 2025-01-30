package com.gym.GymManagementApp.Repository;

import com.gym.GymManagementApp.Entity.Trainer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainerRepository extends MongoRepository<Trainer,String> {

}
