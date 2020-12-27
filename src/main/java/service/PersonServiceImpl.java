package service;

import dao.PersonDAO;
import dao.PersonDAOImpl;
import model.Person;

import java.util.List;

public class PersonServiceImpl implements PersonService{

    private PersonDAO personDAO = new PersonDAOImpl();

    @Override
    public void addPerson(Person person) {
       personDAO.addPerson(person);
    }

    @Override
    public List<Person> getAllPersons() {
        return personDAO.getAllPersons();
    }

    @Override
    public void updatePerson(Person person) {
        personDAO.updatePerson(person);
    }

    @Override
    public void removePerson(Long id) {
        personDAO.removePerson(id);
    }
}
