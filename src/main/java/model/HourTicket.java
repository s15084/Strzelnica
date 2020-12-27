package model;

import javax.persistence.Entity;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

@Entity
public class HourTicket  extends Ticket{

    private LocalDate issueDate;
    private LocalTime startTime;
    private LocalTime endTime;

    private static int hourPrice = 5;

    public HourTicket() {
    }

    public HourTicket(LocalDate issueDate, LocalTime startTime, LocalTime endTime) {
        this.issueDate = issueDate;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public static void changeHourPrice (int newHourPrice) {
        hourPrice = newHourPrice;
    }

    @Override
    int calculateTicketPrice(){
        return (int) ChronoUnit.HOURS.between(startTime, endTime)*hourPrice;
    }

    static Ticket addTicket(LocalDate issueDate, LocalTime startTime, LocalTime endTime){
        return new HourTicket(issueDate, startTime, endTime);
    }
}


