package ru.yashnov.Project2Boot.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.yashnov.Project2Boot.dao.PersonDAO;
import ru.yashnov.Project2Boot.models.Person;

@Component
public class PersonValidator implements Validator {

    private final PersonDAO personDAO;

    public PersonValidator(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }


    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;

        if (personDAO.show(person.getName()).isPresent()) {
            errors.rejectValue("name", "", "Пользователь с таким именем уже существует");
        }
    }
}
