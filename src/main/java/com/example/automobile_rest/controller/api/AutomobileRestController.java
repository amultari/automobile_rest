package com.example.automobile_rest.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.automobile_rest.model.Automobile;
import com.example.automobile_rest.service.AutomobileService;

@RestController
@RequestMapping(value = "/api/automobile", produces = { MediaType.APPLICATION_JSON_VALUE })
public class AutomobileRestController {

	@Autowired
	private AutomobileService automobileService;

	@GetMapping("/{idInput}")
	public Automobile getAutomobile(@PathVariable(required = true) Long idInput) {
		return automobileService.get(idInput);
	}

	@GetMapping
	public List<Automobile> getAll() {
		return automobileService.listAll();
	}

	@PostMapping("/search")
	public ResponseEntity<Page<Automobile>> searchAndPagination(@RequestBody Automobile automobileExample,
			@RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize,
			@RequestParam(defaultValue = "id") String sortBy) {

		Page<Automobile> results = automobileService.searchAndPaginate(automobileExample, pageNo, pageSize, sortBy);

		return new ResponseEntity<Page<Automobile>>(results, new HttpHeaders(), HttpStatus.OK);
	}

	@PostMapping
	public Automobile createNewAutomobile(@RequestBody Automobile automobileInput) {
		return automobileService.save(automobileInput);
	}

	@PutMapping("/{id}")
	public Automobile updateAutomobile(@RequestBody Automobile automobileInput, @PathVariable Long id) {
		Automobile automobileToUpdate = automobileService.get(id);
		automobileToUpdate.setMarca(automobileInput.getMarca());
		automobileToUpdate.setModello(automobileInput.getModello());
		automobileToUpdate.setTarga(automobileInput.getTarga());
		automobileToUpdate.setDataImmatricolazione(automobileInput.getDataImmatricolazione());
		automobileToUpdate.setInProduzione(automobileInput.getInProduzione());
		return automobileService.save(automobileToUpdate);
	}

	@DeleteMapping("/{id}")
	public void deleteAutomobile(@PathVariable(required = true) Long id) {
		automobileService.delete(automobileService.get(id));
	}

}
