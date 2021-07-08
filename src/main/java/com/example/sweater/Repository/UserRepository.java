package com.example.sweater.Repository;


import org.springframework.data.repository.CrudRepository;
import com.example.sweater.domain.User;


public interface UserRepository extends CrudRepository<User, Integer> {
    User findByUsername(String name);

    User findByActivationCode(String code);

}