package model;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
public class Instructor  extends Person{

    private LocalDate hireDate;
    private int yearsOfExperience;

    public Instructor() {
    }

    public Instructor(String firstName, String lastName, LocalDate birthDate, int phoneNumber, LocalDate hireDate, int yearsOfExperience) {
        super(firstName, lastName, birthDate, phoneNumber);
        this.hireDate = hireDate;
        this.yearsOfExperience = yearsOfExperience;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }
}
