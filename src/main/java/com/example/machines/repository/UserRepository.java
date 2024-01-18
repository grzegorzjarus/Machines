package com.example.machines.repository;

import com.example.machines.model.Owner;
import com.example.machines.model.User;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByEmail (String email);

  //  User updateUserByOwner(Owner owner);

    //@Query("SELECT m FROM Machine m WHERE m.owner.Id = :ownerId")
//    @Query("SELECT u FROM User u WHERE u.owner IS NOT NULL")
//    Optional<User> findOwner();
}
