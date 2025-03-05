package ru.fmd.EvoTask4_13.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.fmd.EvoTask4_13.dto.Person;
import ru.fmd.EvoTask4_13.repository.PersonRepository;

import java.util.Optional;

@RestController
@RequestMapping("/person")
public class PersonController {

    private final PersonRepository repository;

    public PersonController(PersonRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public Iterable<Person> getPerson() {
        return repository.getPersons();
    }

    @GetMapping("/{id}")
    public Optional<Person> findPersonById(@PathVariable int id) {
        return repository.getPersonById(id);
    }

    @PostMapping
    public ResponseEntity<Person> addPerson(@RequestBody Person person) {
        repository.addPerson(person);
        return new ResponseEntity<>(person, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable int id, @RequestBody Person person) {
        return repository.updatePerson(id, person) == -1 ? addPerson(person) : new ResponseEntity<>(person, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpStatus deletePerson(@PathVariable int id) {
        return repository.deletePerson(id) ? HttpStatus.OK : HttpStatus.NOT_FOUND;
    }
}
