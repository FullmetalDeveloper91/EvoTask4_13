package ru.fmd.EvoTask4_13.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.fmd.EvoTask4_13.dto.Person;
import ru.fmd.EvoTask4_13.repository.PersonRepository;
import ru.fmd.EvoTask4_13.repository.iRepository;

import java.util.Optional;

@RestController
@RequestMapping("/person")
public class PersonController {

    private final iRepository<Person> repository;

    public PersonController(iRepository<Person> repository) {
        this.repository = repository;
    }

    @GetMapping
    public Iterable<Person> getPerson() {
        return repository.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Person> findPersonById(@PathVariable int id) {
        return repository.getById(id);
    }

    @PostMapping
    public ResponseEntity<Person> addPerson(@RequestBody Person person) {
        repository.add(person);
        return new ResponseEntity<>(person, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable int id, @RequestBody Person person) {
        return repository.update(id, person) == -1 ? addPerson(person) : new ResponseEntity<>(person, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpStatus deletePerson(@PathVariable int id) {
        return repository.delete(id) ? HttpStatus.OK : HttpStatus.NOT_FOUND;
    }
}
