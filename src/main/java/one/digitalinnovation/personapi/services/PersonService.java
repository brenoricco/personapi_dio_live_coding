package one.digitalinnovation.personapi.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import one.digitalinnovation.personapi.dtos.MessageResponseDTO;
import one.digitalinnovation.personapi.dtos.PersonDTO;
import one.digitalinnovation.personapi.entities.Person;
import one.digitalinnovation.personapi.exceptions.PersonNotFoundException;
import one.digitalinnovation.personapi.mappers.PersonMapper;
import one.digitalinnovation.personapi.repositories.PersonRepository;

@Service
public class PersonService {

	private PersonRepository repository;
	
	private final PersonMapper personMapper = PersonMapper.INSTANCE;
	
	@Autowired
	public PersonService(PersonRepository repository) {
		this.repository = repository;
	}
	
	@Transactional
	public MessageResponseDTO create(PersonDTO personDto) {
		Person personToSave = personMapper.toModel(personDto);
		Person personSaved = repository.save(personToSave);
		return MessageResponseDTO
				.builder()
				.message("Person create with ID " + personSaved.getId().toString())
				.build();
	}

	@Transactional(readOnly = true)
	public List<PersonDTO> listAll() {
		List<Person> allPerson = repository.findAll();
		return allPerson.stream()
				.map(personMapper::toDTO)
				.collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public PersonDTO findById(Long id) throws PersonNotFoundException {
		Person person = verifyWithExists(id);
		
		return personMapper.toDTO(person);
	}

	@Transactional
	public void delete(Long id) throws PersonNotFoundException {
		Person person = verifyWithExists(id);
		repository.delete(person);
	}
	
	private Person verifyWithExists(Long id) throws PersonNotFoundException {
		return repository.findById(id)
			.orElseThrow(() -> new PersonNotFoundException(id));
	}
	
}
