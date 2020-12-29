package model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Ticket {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private long ticketNumber;

    private static double deposit = 200.0;
    private static double ammoPieceCost = 0.1;
    private int ammoQuantity;


    public Ticket() {
    }




    public long getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(long ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public static double getDeposit() {
        return deposit;
    }

    public static void setDeposit(double deposit) {
        Ticket.deposit = deposit;
    }

    public static double getAmmoPieceCost() {
        return ammoPieceCost;
    }

    public static void setAmmoPieceCost(double ammoPieceCost) {
        Ticket.ammoPieceCost = ammoPieceCost;
    }

    public int getAmmoQuantity() {
        return ammoQuantity;
    }

    public void setAmmoQuantity(int ammoQuantity) {
        this.ammoQuantity = ammoQuantity;
    }

    abstract int calculateTicketPrice();
}
