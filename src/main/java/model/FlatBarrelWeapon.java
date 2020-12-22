package model;

import javax.persistence.Entity;

@Entity
public class FlatBarrelWeapon extends Weapon {

    private boolean isDoubleBarreled;


    public FlatBarrelWeapon() {
    }

    public FlatBarrelWeapon(String name, double caliber, double barrelLength, String ammoType, weapon_Condition condition, boolean isDoubleBarreled) {
        super(name, caliber, barrelLength, ammoType);
        this.isDoubleBarreled = isDoubleBarreled();
    }

    public boolean isDoubleBarreled() {
        return isDoubleBarreled;
    }

    public void setDoubleBarreled(boolean doubleBarreled) {
        isDoubleBarreled = doubleBarreled;
    }
}
