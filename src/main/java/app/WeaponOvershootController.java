package app;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.WeaponOvershoot;
import service.WeaponOvershootService;
import service.WeaponOvershootServiceImpl;

import java.util.List;

public class WeaponOvershootController {

    private WeaponOvershootService weaponOvershootService = new WeaponOvershootServiceImpl();
    private ObservableList<WeaponOvershoot> weaponOvershootObservableList = FXCollections.observableArrayList();
    private ObservableList<WeaponOvershoot> weaponOvershootForWeaponObservableList = FXCollections.observableArrayList();

    public void addWeaponOvershoot(WeaponOvershoot weaponOvershoot){
        weaponOvershootService.addOvershoot(weaponOvershoot);
    }

    public ObservableList<WeaponOvershoot> getAllWeaponOvershoots(){
        if(!weaponOvershootObservableList.isEmpty()){
            weaponOvershootObservableList.clear();
        }
        weaponOvershootObservableList = FXCollections.observableList((List<WeaponOvershoot>) weaponOvershootService.getAllWeaponOvershoots());
        return weaponOvershootObservableList;
    }

    public ObservableList<WeaponOvershoot> getAllWeaponOvershootsForWeapon(long id){
        if(!weaponOvershootForWeaponObservableList.isEmpty()){
            weaponOvershootForWeaponObservableList.clear();
        }
        weaponOvershootForWeaponObservableList = FXCollections.observableList((List<WeaponOvershoot>) weaponOvershootService.getAllWeaponOvershootsForWeapon(id));
        return weaponOvershootForWeaponObservableList;
    }

    public void updateWeaponOvershoot(WeaponOvershoot weaponOvershoot){
        weaponOvershootService.updateWeaponOvershoot(weaponOvershoot);
    }

    public void removeWeaponOvershoot(Long id){
        weaponOvershootService.removeWeaponOvershoot(id);
    }

}
