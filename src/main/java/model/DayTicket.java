package model;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
public class DayTicket extends Ticket{

    private LocalDate issueDate;
    private LocalDate startDate;
    private LocalDate endDate;

    private static int dailyPrice = 30;

    public DayTicket() {
    }

    public DayTicket(LocalDate issueDate, LocalDate startDate, LocalDate endDate) {
        this.issueDate = issueDate;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    int calculateTicketPrice() {
        return 0;
    }
}
