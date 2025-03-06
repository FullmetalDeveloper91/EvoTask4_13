package ru.fmd.EvoTask4_13.repository;

import org.springframework.stereotype.Component;
import ru.fmd.EvoTask4_13.dto.Person;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class PersonRepository implements iRepository<Person> {
    private final List<Person> persons = new ArrayList<>(Arrays.asList(
            new Person(1, "Ivan", "Ivanovich", "Ivanov", LocalDate.of(1999, 2,3)),
            new Person(2, "Петр", "Петрович", "Петров", LocalDate.of(2002, 2,2)),
            new Person(3, "Евгений", "Васильевич", "Васин", LocalDate.of(2005, 4,8)),
            new Person(4, "Максим", "Яковлевич", "Окопский", LocalDate.of(1978, 6,5))
    ));

    public List<Person> getAll() {
        return List.copyOf(persons);
    }

    public void add(Person person){
        persons.add(person);
    }

    public Optional<Person> getById(int id){
        return persons.stream().filter(p -> p.getId() == id).findFirst();
    }

    public int update(int id, Person person){
        int index = -1;

        for(Person p : persons){
            if(p.getId() == id){
                index = persons.indexOf(p);
                persons.set(index, person);
            }
        }

        return index;
    }

    public boolean delete(int id){
        return persons.removeIf(p-> p.getId() == id);
    }
}
