package model;

import org.hibernate.annotations.GenericGenerator;

import javax.annotation.processing.Generated;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class TechnicalReview {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private long id;

    private LocalDate reviewDate;
    private String comment;

    @ElementCollection
    private List<String> faults = new ArrayList<>();

    //asocjacja
    @ManyToOne
    private Weapon weapon;

    public TechnicalReview() {
    }

    public TechnicalReview(LocalDate reviewDate) {
        this.reviewDate = reviewDate;
    }

    public LocalDate getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(LocalDate reviewDate) {
        this.reviewDate = reviewDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }


    public List<String> getFaults() {
        return faults;
    }

    public void setFaults(List<String> faults) {
        this.faults = faults;
    }


    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        if(this.weapon == null){
            this.weapon = weapon;
            this.weapon.addTechnicalReview(this);
        } else if(this.weapon != weapon){
            removeWeapon();
            setWeapon(weapon);
        }
    }

    public void removeWeapon(){
        this.weapon.removeTechnicalReview(this);
        this.weapon = null;
    }

    @Transient
    public LocalDate getNextReviewDate(){
        return reviewDate.plusYears(1);
    }


    @Override
    public String toString() {
        return "TechnicalReview{" +
                "id=" + id +
                ", reviewDate=" + reviewDate +
                ", comment='" + comment + '\'' +
                ", weapon=" + this.weapon + '\'' +
                '}';
    }
}
