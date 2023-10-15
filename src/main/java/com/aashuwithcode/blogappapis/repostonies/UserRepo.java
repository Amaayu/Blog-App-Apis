package com.aashuwithcode.blogappapis.repostonies;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aashuwithcode.blogappapis.entities.User;

public interface UserRepo extends JpaRepository<User,Integer> {
    
}
