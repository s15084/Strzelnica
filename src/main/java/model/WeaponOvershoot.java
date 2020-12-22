package model;

import javafx.collections.ObservableList;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class WeaponOvershoot {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private long id;

    private LocalDate overshootDate;
    private overshoot_Result overshootResult;
    private String comment;


    public enum overshoot_Result{
        POZYTYWNY, NEGATYWNY;
    }

    //asocjacja
    @ManyToOne
    private Weapon weapon;

    public WeaponOvershoot() {
    }

    public WeaponOvershoot(LocalDate overshootDate) {
        this.overshootDate = overshootDate;
    }

    public LocalDate getOvershootDate() {
        return overshootDate;
    }

    public void setOvershootDate(LocalDate overshootDate) {
        this.overshootDate = overshootDate;
    }

    public overshoot_Result getOvershootResult(ObservableList<String> overshootPickedResult) {
        return overshootResult;
    }

    public void setOvershootResult(overshoot_Result overshootResult) {
        this.overshootResult = overshootResult;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }



    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        if(this.weapon == null){
            this.weapon = weapon;
            this.weapon.addWeaponOvershoot(this);
        } else if(this.weapon != weapon){
            removeWeapon();
            setWeapon(weapon);
        }
    }

    public void removeWeapon(){
        this.weapon.removeWeaponOvershoot(this);
        this.weapon = null;
    }

    @Transient
    public LocalDate getNextOvershootDate(){
        return overshootDate.plusYears(1);
    }

    @Override
    public String toString() {
        return "WeaponOvershoot{" +
                "id=" + id +
                ", overshootDate=" + overshootDate +
                ", overshootResult=" + overshootResult +
                ", comment='" + comment + '\'' +
                ", weapon=" + this.weapon + '\'' +
                '}';
    }
}
