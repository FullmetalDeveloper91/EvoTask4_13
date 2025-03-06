package ru.fmd.EvoTask4_13.repository;

import org.springframework.stereotype.Component;
import ru.fmd.EvoTask4_13.dto.Message;

import java.time.LocalDateTime;
import java.util.*;

@Component
public class MessageRepository implements iRepository<Message> {

    private final List<Message> messages = new ArrayList<>(Arrays.asList(
            new Message(1, "Greetings", "Hello!", LocalDateTime.of(2025, 1,25,13,57)),
            new Message(2, "For all", "How are you?", LocalDateTime.of(2025, 1,26,14,24)),
            new Message(3, "BoardGame", "Are we playing today?", LocalDateTime.of(2025, 1,27,11,42))
    ));
    @Override
    public List<Message> getAll() {
        return List.copyOf(messages);
    }

    @Override
    public Optional<Message> getById(int id) {
        return messages.stream().filter(p -> p.getId() == id).findFirst();
    }

    @Override
    public void add(Message message) {
        messages.add(message);
    }

    @Override
    public int update(int id, Message message) {
        int index = -1;

        for(Message p : messages){
            if(p.getId() == id){
                index = messages.indexOf(p);
                messages.set(index, message);
            }
        }

        return index;
    }

    @Override
    public boolean delete(int id) {
        return messages.removeIf(p-> p.getId() == id);
    }
}
