package model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Person")
    @Inheritance(strategy = InheritanceType.SINGLE_TABLE)
    public abstract class Person {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private long id;

    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private int phoneNumber;


    //asocjacje
    @OneToMany(
            targetEntity = Client.class,
            mappedBy = "person",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Client> clients = new ArrayList<>();

    @OneToMany(
            targetEntity = Instructor.class,
            mappedBy = "person",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Instructor> instructors = new ArrayList<>();


    public Person() {
    }

    public Person(String firstName, String lastName, LocalDate birthDate, int phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }



    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                ", phoneNumber=" + phoneNumber +
                '}';
    }
}
