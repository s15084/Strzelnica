package dao;

import model.Person;

import java.util.List;

public interface PersonDAO {

    void addPerson(Person person);
    List<Person> getAllPersons();
    void updatePerson(Person person);
    void removePerson(Long id);
}
