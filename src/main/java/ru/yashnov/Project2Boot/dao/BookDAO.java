package ru.yashnov.Project2Boot.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.yashnov.Project2Boot.models.Book;

import java.util.List;

// Более не используется
@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;

    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index() {
        return jdbcTemplate.query("SELECT * FROM Book",
                new BeanPropertyRowMapper<>(Book.class));
    }

    public void save(Book book) {
        jdbcTemplate.update("INSERT INTO Book(title, author, year) VALUES (?, ?, ?)",
                book.getTitle(), book.getAuthor(), book.getYear());
    }

    public Book show(int id) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE id=?",
                new BeanPropertyRowMapper<>(Book.class), id).stream().findAny().orElse(null);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Book WHERE id=?", id);
    }

    public void update(int id, Book book) {
        jdbcTemplate.update("UPDATE Book SET title=?, author=?, year=? WHERE id=?",
                book.getTitle(), book.getAuthor(), book.getYear(), id);
    }

    public void assign(int id, int personId) {
        jdbcTemplate.update("UPDATE Book SET person_id=? WHERE id=?", personId, id);
    }

    public String getOwnerNameById(int id) {
        return jdbcTemplate.queryForObject("SELECT name FROM book LEFT JOIN person ON book.person_id = person.id WHERE book.id=?",
                String.class, id);
    }

    public void release(int id) {
        jdbcTemplate.update("UPDATE book SET person_id = null WHERE book.id=?", id);
    }

    public List<Book> getAssignedBooksByPersonId(int person_id) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE person_id=?",
                new BeanPropertyRowMapper<>(Book.class), person_id);
    }
}
