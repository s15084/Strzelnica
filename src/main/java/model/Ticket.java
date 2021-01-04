package model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

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

    @ManyToMany
    private List<TicketPurchase> ticketPurchaseList;

    public List<TicketPurchase> getTicketPurchaseList(){
        return ticketPurchaseList;
    }

    public void setTicketPurchaseList(List<TicketPurchase> ticketPurchaseList){
        this.ticketPurchaseList = ticketPurchaseList;
    }

    public void addTicketPurchase(TicketPurchase ticketPurchase){
        if(!ticketPurchaseList.contains(ticketPurchase)){
            ticketPurchaseList.add(ticketPurchase);
            ticketPurchase.setTicket(this);
        }
    }

    public void removeTicketPurchase(TicketPurchase ticketPurchase){
        if(ticketPurchaseList.contains(ticketPurchase)){
            ticketPurchaseList.remove(ticketPurchase);
            ticketPurchase.setTicket(null);
        }
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
