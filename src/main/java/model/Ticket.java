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



}
