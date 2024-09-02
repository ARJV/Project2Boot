package ru.yashnov.Project2Boot.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.yashnov.Project2Boot.models.Book;
import ru.yashnov.Project2Boot.models.Person;
import ru.yashnov.Project2Boot.repositories.PeopleRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PeopleService {

    private final PeopleRepository peopleRepository;

    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public List<Person> findAll() {
        return peopleRepository.findAll();
    }

    public Person findOne(int id) {
        return peopleRepository.findById(id).orElse(null);
    }

    @Transactional
    public void save(Person person) {
        peopleRepository.save(person);
    }

    @Transactional
    public void update(int id, Person updatePerson) {
        updatePerson.setId(id);
        peopleRepository.save(updatePerson);
    }

    @Transactional
    public void delete(int id) {
        peopleRepository.deleteById(id);
    }

    public Optional<Person> findPersonByName(String name) {
        return peopleRepository.findPersonByName(name);
    }

    public List<Book> getAssignedBooksByPersonId(int id) {
        Optional<Person> person = peopleRepository.findById(id);
        return person.map(Person::getBooks).orElse(null);
    }
}
