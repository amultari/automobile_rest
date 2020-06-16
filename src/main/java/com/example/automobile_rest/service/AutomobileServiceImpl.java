package com.example.automobile_rest.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.automobile_rest.exceptions.AutomobileNotFoundException;
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

		Specification<Automobile> specificationCriteria = (root, query, cb) -> {

			List<Predicate> predicates = new ArrayList<Predicate>();

			if (!StringUtils.isEmpty(automobileExample.getMarca()))
				predicates.add(cb.like(cb.upper(root.get("marca")), "%" + automobileExample.getMarca().toUpperCase() + "%"));

			if (!StringUtils.isEmpty(automobileExample.getModello()))
				predicates.add(cb.like(cb.upper(root.get("modello")), "%" + automobileExample.getModello().toUpperCase() + "%"));

			if (!StringUtils.isEmpty(automobileExample.getTarga()))
				predicates.add(cb.like(cb.upper(root.get("targa")), "%" + automobileExample.getTarga().toUpperCase() + "%"));

			if (automobileExample.getDataImmatricolazione() != null)
				predicates.add(cb.greaterThanOrEqualTo(root.get("dataImmatricolazione"),
						automobileExample.getDataImmatricolazione()));

			if (automobileExample.getInProduzione() != null)
				if (automobileExample.getInProduzione())
					predicates.add(cb.isTrue(root.get("inProduzione")));
				else
					predicates.add(cb.isFalse(root.get("inProduzione")));

			return cb.and(predicates.toArray(new Predicate[predicates.size()]));
		};

		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

		return automobileRepository.findAll(specificationCriteria, paging);
	}

	@Override
	public Automobile get(Long idInput) {
		return automobileRepository.findById(idInput)
				.orElseThrow(() -> new AutomobileNotFoundException("Element with id " + idInput + " not found."));
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
