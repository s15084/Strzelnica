package model;

import javax.persistence.Entity;

@Entity
public class ShortWeapon extends Weapon {

    private int clipCapacity;

    public ShortWeapon() {
    }

    public ShortWeapon(int serialNumber, String name, double caliber, double barrelLength, String ammoType, weapon_Condition condition, int clipCapacity) {
        super(serialNumber, name, caliber, barrelLength, ammoType);
        this.clipCapacity = clipCapacity;
    }


    public int getClipCapacity() {
        return clipCapacity;
    }

    public void setClipCapacity(int clipCapacity) {
        this.clipCapacity = clipCapacity;
    }
}
