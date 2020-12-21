package service;

import model.Weapon;

import java.util.List;

public interface WeaponService {

    void addWeapon(Weapon weapon);
    List<Weapon> getAllWeapons();
    void loadWeaponTechnicalReview(Weapon weapon);
    void updateWeapon(Weapon weapon);
    void removeWeapon(Long id);
}
