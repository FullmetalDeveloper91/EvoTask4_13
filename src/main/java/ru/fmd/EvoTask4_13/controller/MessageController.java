package ru.fmd.EvoTask4_13.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.fmd.EvoTask4_13.dto.Message;
import ru.fmd.EvoTask4_13.repository.iRepository;

import java.util.Optional;

@RestController
@RequestMapping("/message")
public class MessageController {
    private final iRepository<Message> repository;

    public MessageController(iRepository<Message> repository) {
        this.repository = repository;
    }

    @GetMapping
    public Iterable<Message> getMessages() {
        return repository.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Message> findMessageById(@PathVariable int id) {
        return repository.getById(id);
    }

    @PostMapping
    public ResponseEntity<Message> addMessage(@RequestBody Message message) {
        repository.add(message);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Message> updateMessage(@PathVariable int id, @RequestBody Message message) {
        return repository.update(id, message) == -1 ? addMessage(message) : new ResponseEntity<>(message, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteMessage(@PathVariable int id) {
        return repository.delete(id) ? HttpStatus.OK : HttpStatus.NOT_FOUND;
    }
}
