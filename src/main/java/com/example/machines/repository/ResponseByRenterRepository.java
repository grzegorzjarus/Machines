package com.example.machines.repository;

import com.example.machines.model.ResponseByRenter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResponseByRenterRepository extends JpaRepository<ResponseByRenter, Long> {

    ResponseByRenter save(ResponseByRenter save);
}
