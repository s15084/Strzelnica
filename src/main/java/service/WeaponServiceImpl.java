package service;

import dao.WeaponDAO;
import dao.WeaponDAOImpl;
import model.Weapon;

import java.util.List;

public class WeaponServiceImpl implements WeaponService {

    private WeaponDAO weaponDAO = new WeaponDAOImpl();

    @Override
    public void addWeapon(Weapon weapon) {
        weaponDAO.addWeapon(weapon);
    }

    @Override
    public List<Weapon> getAllWeapons() {
        return weaponDAO.getAllWeapons();
    }

    @Override
    public void loadWeaponTechnicalReview(Weapon weapon) {
        weaponDAO.loadWeaponsTechnicalReviews(weapon);
    }

    @Override
    public void updateWeapon(Weapon weapon) {
        weaponDAO.updateWeapon(weapon);
    }

    @Override
    public void removeWeapon(Long id) {
       weaponDAO.removeWeapon(id);
    }
}
