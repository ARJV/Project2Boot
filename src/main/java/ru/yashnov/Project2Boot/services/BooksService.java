package ru.yashnov.Project2Boot.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.yashnov.Project2Boot.models.Book;
import ru.yashnov.Project2Boot.models.Person;
import ru.yashnov.Project2Boot.repositories.BookRepository;
import ru.yashnov.Project2Boot.repositories.PeopleRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BooksService {

    private final BookRepository bookRepository;
    private final PeopleRepository peopleRepository;

    public BooksService(BookRepository bookRepository, PeopleRepository peopleRepository) {
        this.bookRepository = bookRepository;
        this.peopleRepository = peopleRepository;
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Book findOne(int id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Transactional
    public void save(Book person) {
        bookRepository.save(person);
    }

    @Transactional
    public void update(int id, Book updatePerson) {
        updatePerson.setId(id);
        bookRepository.save(updatePerson);
    }

    @Transactional
    public void delete(int id) {
        bookRepository.deleteById(id);
    }

    @Transactional
    public void assign(int id, int personId) {
        Optional<Person> person = peopleRepository.findById(personId);
        Optional<Book> book = bookRepository.findById(id);

        if (book.isPresent() && person.isPresent()) book.get().setOwner(person.get());
    }

    @Transactional
    public void release(int id) {
        Optional<Book> book = bookRepository.findById(id);
        book.ifPresent(value -> value.setOwner(null));
    }

    public String getOwnerNameByBookId(int id) {
        Book book = bookRepository.findById(id).orElse(null);
        if (book.getOwner() == null) return null;
        return book.getOwner().getName();
    }

    public List<Book> searchBooks(String q) {
        return bookRepository.findBooksByTitleStartingWith(q);
    }
}
