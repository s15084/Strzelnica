package model;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Client  extends Person{

    private int licenceNumber;
    private String emailAddress;

    @ManyToMany
    private List<Weapon> weaponList;



    public Client() {
    }

    public Client(String firstName, String lastName, LocalDate birthDate, int phoneNumber, int licenceNumber, String emailAddress) {
        super(firstName, lastName, birthDate, phoneNumber);
        this.licenceNumber = licenceNumber;
        this.emailAddress = emailAddress;
    }

    public int getLicenceNumber() {
        return licenceNumber;
    }

    public void setLicenceNumber(int licenceNumber) {
        this.licenceNumber = licenceNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public List<Weapon> getWeaponList() {
        return weaponList;
    }

    public void setWeaponList(List<Weapon> weaponList) {
        this.weaponList = weaponList;
    }
}
