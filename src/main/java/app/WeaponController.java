package app;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Weapon;
import service.WeaponService;
import service.WeaponServiceImpl;

import java.util.List;

public class WeaponController {

    private WeaponService weaponService = new WeaponServiceImpl();
    private ObservableList<Weapon> weaponObservableList = FXCollections.observableArrayList();

    public void addWeapon(Weapon weapon){
        weaponService.addWeapon(weapon);
    }

    public ObservableList<Weapon> getAllWeapons(){
        if(!weaponObservableList.isEmpty()){
            weaponObservableList.clear();
        }
        weaponObservableList = FXCollections.observableList((List<Weapon>) weaponService.getAllWeapons());
        return weaponObservableList;
    }

    public void updateWeapon(Weapon weapon){
        weaponService.updateWeapon(weapon);
    }
}
