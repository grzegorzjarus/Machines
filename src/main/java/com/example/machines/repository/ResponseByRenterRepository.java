package com.example.machines.repository;

import com.example.machines.model.ResponseByRenter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResponseByRenterRepository extends JpaRepository<ResponseByRenter, Long> {

    ResponseByRenter save(ResponseByRenter save);

    ResponseByRenter findResponseById(long id);

    List<ResponseByRenter> findAllByOfferId(long offerId);
}
