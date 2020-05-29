package com.example.automobile_rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
	public Page<Automobile> searchAndPaginate(Automobile automobileExample, Integer pageNo, Integer pageSize,
			String sortBy) {
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

		Page<Automobile> pagedResult = automobileRepository.findAll(paging);

		return pagedResult;
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

	@Override
	public void delete(Automobile input) {
		automobileRepository.delete(input);
	}

}
