package ru.yashnov.Project2Boot.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.yashnov.Project2Boot.models.Person;

import java.util.List;
import java.util.Optional;

// Более не используется
@Component
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;

    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index() {
        return jdbcTemplate.query("SELECT * FROM Person",
                new BeanPropertyRowMapper<>(Person.class));
    }

    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO Person(name, birth_date) VALUES (?, ?)",
                person.getName(), person.getBirth_date());
    }

    public Person show(int id) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE id=?",
                new BeanPropertyRowMapper<>(Person.class), id).stream().findAny().orElse(null);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Person WHERE id=?", id);
    }

    public void update(int id, Person person) {
        jdbcTemplate.update("UPDATE Person SET name=?, birth_date=? WHERE id=?",
                person.getName(), person.getBirth_date(), id);
    }

    public Optional<Person> show(String name) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE name=?",
                new BeanPropertyRowMapper<>(Person.class), name).stream().findAny();
    }
}
