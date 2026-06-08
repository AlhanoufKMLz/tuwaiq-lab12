package com.example.demo.Repository;

import com.example.demo.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthRepository extends JpaRepository<User, Integer> {

    User findUserByUsername(String username);

    User findUserById(Integer id);
}
