package com.example.automobile_rest.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
	public List<Automobile> getAll(){
		return automobileService.listAll();
	}

}
