package model;

import org.hibernate.annotations.GenericGenerator;

import javax.annotation.processing.Generated;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Weapon")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Weapon {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private long id;

    private int serialNumber;
    private String name;
    private double caliber;
    private double barrelLength;
    private String ammoType;
    private weapon_Condition weaponCondition;

    public enum weapon_Condition {
        SPRAWNA, NIESPRAWNA;
    }


    //asocjacje
    @OneToMany(
            targetEntity = TechnicalReview.class,
            mappedBy = "weapon",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<TechnicalReview> technicalReviews = new ArrayList<>();

    @OneToMany(
            targetEntity = WeaponOvershoot.class,
            mappedBy = "weapon",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<WeaponOvershoot> weaponOvershoots = new ArrayList<>();

    @ManyToOne
    private ShootingTrack shootingTrack;


    public void addTechnicalReview(TechnicalReview newTechnicalReview){
        if(!technicalReviews.contains(newTechnicalReview)){
            technicalReviews.add(newTechnicalReview);
            newTechnicalReview.setWeapon(this);
        }
    }

    public void removeTechnicalReview(TechnicalReview technicalReview){
        if(technicalReviews.contains(technicalReview)){
            this.technicalReviews.remove(technicalReview);
            technicalReview.removeWeapon();
        }
    }

    public void addWeaponOvershoot(WeaponOvershoot newWeaponOvershoot){
        if(!weaponOvershoots.contains(newWeaponOvershoot)){
            weaponOvershoots.add(newWeaponOvershoot);
            newWeaponOvershoot.setWeapon(this);
        }
    }

    public void removeWeaponOvershoot(WeaponOvershoot weaponOvershoot){
        if(weaponOvershoots.contains(weaponOvershoot)){
            this.weaponOvershoots.remove(weaponOvershoot);
            weaponOvershoot.removeWeapon();
        }
    }

    public Weapon() {
    }

    public Weapon(String name, double caliber, double barrelLength, String ammoType, weapon_Condition weaponCondition) {
        this.name = name;
        this.caliber = caliber;
        this.barrelLength = barrelLength;
        this.ammoType = ammoType;
        this.weaponCondition = weapon_Condition.SPRAWNA;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCaliber() {
        return caliber;
    }

    public void setCaliber(double caliber) {
        this.caliber = caliber;
    }

    public double getBarrelLength() {
        return barrelLength;
    }

    public void setBarrelLength(double barrelLength) {
        this.barrelLength = barrelLength;
    }

    public String getAmmoType() {
        return ammoType;
    }

    public void setAmmoType(String ammoType) {
        this.ammoType = ammoType;
    }

    public List<TechnicalReview> getTechnicalReviews() {
        return technicalReviews;
    }

    public void setTechnicalReviews(List<TechnicalReview> technicalReviews) {
        this.technicalReviews = technicalReviews;
    }

    public List<WeaponOvershoot> getWeaponOvershoots() {
        return weaponOvershoots;
    }

    public void setWeaponOvershoots(List<WeaponOvershoot> weaponOvershoots) {
        this.weaponOvershoots = weaponOvershoots;
    }

    public ShootingTrack getShootingTrack() {
        return shootingTrack;
    }

    public void setShootingTrack(ShootingTrack shootingTrack) {
        this.shootingTrack = shootingTrack;
        shootingTrack.addWeapon(this);
    }

    @Enumerated
    public weapon_Condition getWeaponCondition() {
        return weaponCondition;
    }

    public void setWeaponCondition(weapon_Condition weaponCondition) {
        this.weaponCondition = weaponCondition;
    }

    @Override
    public String toString() {
        return "Weapon{" +
                "serialNumber=" + serialNumber +
                ", name='" + name + '\'' +
                ", caliber=" + caliber +
                ", barrelLength=" + barrelLength +
                ", ammoType='" + ammoType + '\'' +
                ", condition=" + weaponCondition +
                '}';
    }
}


//stany techniczne broni


