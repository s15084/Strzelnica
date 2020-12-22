package model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
public class ShootingTrack {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private long id;

    private int trackNumber;
    @Enumerated
    private track_Condition trackCondition;
    private int availableSpots;
    private int targetDistance;
    private boolean isMovingTarget;



    public enum track_Condition{
        OTWARTY, ZAMKNIĘTY;
    }

    //asocjacje
    @ManyToOne
    private ShootingTrackDomain shootingTrackDomain;

    @OneToMany
    private List<Weapon> weapon;



    //konstruktor pusty
    public ShootingTrack() {
    }

    //konstruktor
    public ShootingTrack(int trackNumber, track_Condition trackCondition, int availableSpots, int targetDistance, boolean isMovingTarget) {
        this.trackNumber = trackNumber;
        this.trackCondition = trackCondition;
        this.availableSpots = availableSpots;
        this.targetDistance = targetDistance;
        this.isMovingTarget = isMovingTarget;
    }


    public void addWeapon(Weapon weapon1){
        if(!this.weapon.contains(weapon1)){
            weapon.add(weapon1);
            weapon1.setShootingTrack(this);
        }
    }

    public void removeWeapon(Weapon weapon1){
        if(weapon.contains(weapon1)){
            this.weapon.remove(weapon1);
            weapon1.setShootingTrack(null);
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public int getTrackNumber() {
        return trackNumber;
    }

    public void setTrackNumber(int trackNumber) {
        this.trackNumber = trackNumber;
    }

    public track_Condition getTrackCondition() {
        return trackCondition;
    }

    public void setTrackCondition(track_Condition trackCondition) {
        this.trackCondition = trackCondition;
    }

    public int getAvailableSpots() {
        return availableSpots;
    }

    public void setAvailableSpots(int availableSpots) {
        this.availableSpots = availableSpots;
    }

    public int getTargetDistance() {
        return targetDistance;
    }

    public void setTargetDistance(int targetDistance) {
        this.targetDistance = targetDistance;
    }

    public boolean isMovingTarget() {
        return isMovingTarget;
    }

    public void setMovingTarget(boolean movingTarget) {
        isMovingTarget = movingTarget;
    }

    public ShootingTrackDomain getShootingTrackDomain() {
        return shootingTrackDomain;
    }

    public void setShootingTrackDomain(ShootingTrackDomain shootingTrackDomain) {
        this.shootingTrackDomain = shootingTrackDomain;
    }
}
