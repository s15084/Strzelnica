package dao;

import app.HibernateUtil;
import model.Person;
import org.hibernate.Hibernate;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class PersonDAOImpl implements PersonDAO{

    @Override
    public void addPerson(Person person) {
        Session session = HibernateUtil.openSession();
        session.beginTransaction();
        session.save(person);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<Person> getAllPersons() {
        List<Person> personList = new ArrayList<>();
        Session session = HibernateUtil.openSession();
        session.beginTransaction();
        personList = session.createQuery("from Person").list();
        personList.forEach(x -> System.out.println(x));
        Hibernate.initialize(personList);
        session.getTransaction().commit();
        session.close();
        return personList;
    }


    @Override
    public void updatePerson(Person person) {
        Session session = HibernateUtil.openSession();
        session.beginTransaction();
        session.update(person);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void removePerson(Long id) {
        Session session = HibernateUtil.openSession();
        session.beginTransaction();
        Person person = (Person) session.load(Person.class, id);
        session.delete(person);
        session.getTransaction().commit();
        session.close();
    }
}
