package app;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Person;
import service.PersonService;
import service.PersonServiceImpl;

import java.util.List;

public class PersonController {

    private PersonService personService = new PersonServiceImpl();
    private ObservableList<Person> personObservableList = FXCollections.observableArrayList();

    public void addPerson(Person person){
        personService.addPerson(person);
    }

    public ObservableList<Person> getAllPersons(){
        if(!personObservableList.isEmpty()){
            personObservableList.clear();
        }
        personObservableList = FXCollections.observableList((List<Person>) personService.getAllPersons());
        return personObservableList;
    }

    public void updatePerson(Person person){
        personService.updatePerson(person);
    }
}
