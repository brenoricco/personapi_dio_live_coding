package one.digitalinnovation.personapi.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import one.digitalinnovation.personapi.dtos.MessageResponseDTO;
import one.digitalinnovation.personapi.dtos.PersonDTO;
import one.digitalinnovation.personapi.services.PersonService;

@RestController
@RequestMapping("/api/v1/people")
public class PersonController {

	private PersonService personService;
	
	@Autowired
	public PersonController(PersonService personService) {
		this.personService = personService; 
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public MessageResponseDTO create(@Valid @RequestBody PersonDTO personDto) {
		return personService.create(personDto);
	}
	
	
}
