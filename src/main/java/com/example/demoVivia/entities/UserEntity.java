package com.example.demoVivia.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.example.demoVivia.models.UserModel;

@Entity
@Table(name = "users")
public class UserEntity extends UserModel {

}
