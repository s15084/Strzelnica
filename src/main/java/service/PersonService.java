package service;

import model.Person;

import java.util.List;

public interface PersonService {

    void addPerson(Person person);
    List<Person> getAllPersons();
    void updatePerson(Person person);
    void removePerson(Long id);
}
