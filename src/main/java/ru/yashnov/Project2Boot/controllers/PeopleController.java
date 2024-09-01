package ru.yashnov.Project2Boot.controllers;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.yashnov.Project2Boot.dao.BookDAO;
import ru.yashnov.Project2Boot.dao.PersonDAO;
import ru.yashnov.Project2Boot.models.Person;
import ru.yashnov.Project2Boot.util.PersonValidator;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PersonDAO personDAO;
    private final BookDAO bookDAO;
    private final PersonValidator personValidator;

    public PeopleController(PersonDAO personDAO, BookDAO bookDAO, PersonValidator personValidator) {
        this.personDAO = personDAO;
        this.bookDAO = bookDAO;
        this.personValidator = personValidator;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("people", personDAO.index());
        return "people/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personDAO.show(id));
        model.addAttribute("books", bookDAO.getAssignedBooksByPersonId(id));
        return "people/show";
    }

    @GetMapping("/new")
    public String newPerson(Model model) {
        model.addAttribute("person", new Person());
        return "people/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult) {
        personValidator.validate(person, bindingResult);

        if (bindingResult.hasErrors()) {
            return "people/new";
        }

        personDAO.save(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("person", personDAO.show(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult,
                         @PathVariable("id") int id) {

        if (!personDAO.show(id).getName().equals(person.getName())) {
            personValidator.validate(person, bindingResult);
        }

        if (bindingResult.hasErrors()) {
            return "people/edit";
        }

        personDAO.update(id, person);
        return "redirect:/people";
    }

    @DeleteMapping("{id}")
    public String delete(@ModelAttribute("person") Person person,
                         @PathVariable("id") int id) {
        personDAO.delete(id);
        return "redirect:/people";
    }
}
