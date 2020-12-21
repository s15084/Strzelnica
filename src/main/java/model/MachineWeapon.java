package model;

import javax.persistence.Entity;

@Entity
public class MachineWeapon extends Weapon {

    private int clipCapacity;

    public MachineWeapon() {
    }

    public MachineWeapon(String name, double caliber, double barrelLength, String ammoType, weapon_Condition condition, int clipCapacity) {
        super(name, caliber, barrelLength, ammoType, condition);
        this.clipCapacity = clipCapacity;
    }

    public int getClipCapacity() {
        return clipCapacity;
    }

    public void setClipCapacity(int clipCapacity) {
        this.clipCapacity = clipCapacity;
    }
}
