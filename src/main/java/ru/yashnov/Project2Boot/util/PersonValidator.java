package ru.yashnov.Project2Boot.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.yashnov.Project2Boot.models.Person;
import ru.yashnov.Project2Boot.services.PeopleService;

@Component
public class PersonValidator implements Validator {

    private final PeopleService peopleService;

    public PersonValidator(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;

        if (peopleService.findPersonByName(person.getName()).isPresent()) {
            errors.rejectValue("name", "", "Пользователь с таким именем уже существует");
        }
    }
}
