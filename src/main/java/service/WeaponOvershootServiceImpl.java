package service;

import dao.TechnicalReviewDAO;
import dao.TechnicalReviewDAOImpl;
import dao.WeaponOvershootDAO;
import dao.WeaponOvershootDAOImpl;
import model.WeaponOvershoot;

import java.util.List;

public class WeaponOvershootServiceImpl implements  WeaponOvershootService{

    private WeaponOvershootDAO weaponOvershootDAO = new WeaponOvershootDAOImpl();

    @Override
    public void addOvershoot(WeaponOvershoot weaponOvershoot) {
        weaponOvershootDAO.addOvershoot(weaponOvershoot);
    }

    @Override
    public List<WeaponOvershoot> getAllWeaponOvershoots() {
        return weaponOvershootDAO.getAllWeaponOvershoots();
    }

    @Override
    public List<WeaponOvershoot> getAllWeaponOvershootsForWeapon(long id) {
        return weaponOvershootDAO.getAllWeaponOvershootsForWeapon(id);
    }

    @Override
    public void updateWeaponOvershoot(WeaponOvershoot weaponOvershoot) {
        weaponOvershootDAO.updateWeaponOvershoot(weaponOvershoot);
    }

    @Override
    public void removeWeaponOvershoot(Long id) {
        weaponOvershootDAO.removeWeaponOvershoot(id);
    }
}
