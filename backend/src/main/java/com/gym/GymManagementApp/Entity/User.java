package com.gym.GymManagementApp.Entity;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@Document
public class User {

    private String username ;
    private String password ;
    private String sex ;
    private List<String> roles ;
    @Id
    private String id ;
    private int phn_no ;


}
