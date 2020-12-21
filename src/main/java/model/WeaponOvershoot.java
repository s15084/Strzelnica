package model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.GenericGenerators;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    public WeaponOvershoot(LocalDate overshootDate, overshoot_Result overshootResult, String comment) {
        this.overshootDate = overshootDate;
        this.overshootResult = overshootResult;
        this.comment = comment;

    }

    public LocalDate getOvershootDate() {
        return overshootDate;
    }

    public void setOvershootDate(LocalDate overshootDate) {
        this.overshootDate = overshootDate;
    }

    public overshoot_Result getOvershootResult() {
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
        weapon.addWeaponOvershoot(this);
        this.weapon = weapon;
    }

    public void removeWeapon(){
        this.weapon.removeWeaponOvershoot(this);
        this.weapon = null;
    }
}
