package dao;

import model.WeaponOvershoot;

import java.util.List;

public interface WeaponOvershootDAO {

    void addOvershoot(WeaponOvershoot weaponOvershoot);
    List<WeaponOvershoot> getAllWeaponOvershoots();
    List<WeaponOvershoot> getAllWeaponOvershootsForWeapon(Long id);
    void updateWeaponOvershoot(WeaponOvershoot weaponOvershoot);
    void removeWeaponOvershoot(Long id);
}
