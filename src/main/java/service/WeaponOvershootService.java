package service;

import model.TechnicalReview;
import model.WeaponOvershoot;

import java.util.List;

public interface WeaponOvershootService {

    void addOvershoot(WeaponOvershoot weaponOvershoot);
    List<WeaponOvershoot> getAllWeaponOvershoots();
    List<WeaponOvershoot> getAllWeaponOvershootsForWeapon(long id);
    void updateWeaponOvershoot(WeaponOvershoot weaponOvershoot);
    void removeWeaponOvershoot(Long id);

}
