package model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Client extends Person{
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private long id;

    private int licenceNumber;
    private String emailAddress;

    @ManyToMany
    private List<Weapon> weaponList;


    @OneToMany
    private List<WeaponRental> weaponRentalList;

    public List<WeaponRental> getWeaponRentalList(){
        return weaponRentalList;
    }

    @OneToMany
    private List<TicketPurchase> ticketPurchaseList;

    public List<TicketPurchase> getTicketPurchaseList(){
        return ticketPurchaseList;
    }

    public void addTicketPurchase(TicketPurchase ticketPurchase){
        if(!ticketPurchaseList.contains(ticketPurchase)){
            ticketPurchaseList.add(ticketPurchase);
            ticketPurchase.setClient(this);
        }
    }

    public void removeTicketPurchase(TicketPurchase ticketPurchase){
        if(ticketPurchaseList.contains(ticketPurchaseList)){
            this.ticketPurchaseList.remove(ticketPurchase);
            ticketPurchase.setClient(null);
        }
    }

    public void addWeaponRental(WeaponRental weaponRental){
        if(!weaponRentalList.contains(weaponRental)){
            weaponRentalList.add(weaponRental);
            weaponRental.setClient(this);
        }
    }

    public void removeWeaponRental(WeaponRental weaponRental){
        if(weaponRentalList.contains(weaponRental)){
            weaponRentalList.remove(weaponRental);
            weaponRental.setClient(null);
        }
    }

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
