package com.example.automobile_rest.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.automobile_rest.model.Automobile;

public interface AutomobileRepository extends CrudRepository<Automobile, Long>{

}
