package com.example.sweater.Repository;


import org.springframework.data.repository.CrudRepository;
import com.example.sweater.domain.Users;


public interface UserRepository extends CrudRepository<Users, Integer> {

}