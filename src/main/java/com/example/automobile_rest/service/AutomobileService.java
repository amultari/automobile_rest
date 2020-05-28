package com.example.automobile_rest.service;

import java.util.List;

import com.example.automobile_rest.model.Automobile;

public interface AutomobileService {
	
	List<Automobile> listAll();
	
	Automobile get(Long idInput);
	
	Automobile save(Automobile input);
	
	void delete(Automobile input);

}
