package one.digitalinnovation.personapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import one.digitalinnovation.personapi.dtos.MessageResponseDTO;
import one.digitalinnovation.personapi.entities.Person;
import one.digitalinnovation.personapi.repositories.PersonRepository;

@Service
public class PersonService {

	private PersonRepository repository;
	
	@Autowired
	public PersonService(PersonRepository repository) {
		this.repository = repository;
	}
	
	public MessageResponseDTO create(Person person) {
		person = repository.save(person);
		return MessageResponseDTO
				.builder()
				.message("Person create with ID " + person.getId().toString())
				.build();
	}
}
