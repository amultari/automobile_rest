package com.example.automobile_rest.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.automobile_rest.model.Automobile;

public interface AutomobileService {

	List<Automobile> listAll();

	Page<Automobile> searchAndPaginate(Automobile automobileExample, Integer pageNo, Integer pageSize, String sortBy);

	Automobile get(Long idInput);

	Automobile save(Automobile input);

	void delete(Automobile input);

}
