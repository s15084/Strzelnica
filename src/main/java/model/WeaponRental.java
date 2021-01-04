package model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class WeaponRental {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private long id;

    private LocalDate rentalDate;
    private LocalTime rentalTime;

    @ManyToOne
    private Client client;

    @ManyToOne
    private Weapon weapon;

    public WeaponRental(LocalDate rentalDate, LocalTime rentalTime, Client client, Weapon weapon) {
        this.rentalDate = rentalDate;
        this.rentalTime = rentalTime;
        this.client = client;
        this.weapon = weapon;
    }

    public WeaponRental() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(LocalDate rentalDate) {
        this.rentalDate = rentalDate;
    }

    public LocalTime getRentalTime() {
        return rentalTime;
    }

    public void setRentalTime(LocalTime rentalTime) {
        this.rentalTime = rentalTime;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
        client.addWeaponRental(this);
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
        client.addWeaponRental(this);
    }
}
