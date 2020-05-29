package com.example.automobile_rest.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.automobile_rest.model.Automobile;

public interface AutomobileRepository extends PagingAndSortingRepository<Automobile, Long>, JpaSpecificationExecutor<Automobile>{

}
