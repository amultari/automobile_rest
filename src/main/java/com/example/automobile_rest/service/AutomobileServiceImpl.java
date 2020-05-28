package com.example.automobile_rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.automobile_rest.model.Automobile;
import com.example.automobile_rest.repository.AutomobileRepository;

@Service
public class AutomobileServiceImpl implements AutomobileService {

	@Autowired
	private AutomobileRepository automobileRepository;

	@Override
	public List<Automobile> listAll() {
		return (List<Automobile>) automobileRepository.findAll();
	}

	@Override
	public Automobile get(Long idInput) {
		return automobileRepository.findById(idInput)
				.orElseThrow(() -> new RuntimeException("Element with id " + idInput + " not found."));
	}

	@Override
	public Automobile save(Automobile input) {
		return automobileRepository.save(input);
	}

}
