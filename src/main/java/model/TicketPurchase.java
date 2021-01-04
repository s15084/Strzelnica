package model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class TicketPurchase {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private long id;

    private LocalDate purchaseDate;
    private LocalTime purchaseTime;


    @ManyToOne
    private Client client;

    @ManyToOne
    private Ticket ticket;

    public TicketPurchase(LocalDate purchaseDate, LocalTime purchaseTime, Client client, Ticket ticket) {
        this.purchaseDate = purchaseDate;
        this.purchaseTime = purchaseTime;
        this.client = client;
        this.ticket = ticket;
    }

    public TicketPurchase() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public LocalTime getPurchaseTime() {
        return purchaseTime;
    }

    public void setPurchaseTime(LocalTime purchaseTime) {
        this.purchaseTime = purchaseTime;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
        client.addTicketPurchase(this);

    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
        ticket.addTicketPurchase(this);

    }
}
