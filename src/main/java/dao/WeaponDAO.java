package dao;

import model.Weapon;

import java.util.List;

public interface WeaponDAO {

    void addWeapon(Weapon weapon);
    List<Weapon> getAllWeapons();
    void loadWeaponsTechnicalReviews(Weapon weapon);
    void loadWeaponOvershoots(Weapon weapon);
    void updateWeapon(Weapon weapon);
    void removeWeapon(Long id);
}
