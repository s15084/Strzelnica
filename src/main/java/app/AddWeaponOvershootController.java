package app;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Weapon;
import model.WeaponOvershoot;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AddWeaponOvershootController implements Initializable {

    private WeaponController weaponController = new WeaponController();
    private WeaponOvershootController weaponOvershootController = new WeaponOvershootController();
    private Weapon weapon;
    private ObservableList<String> resultsList;


    @FXML
    private TextArea overshootCommentTextArea;

    @FXML
    private DatePicker overshootDatePicker;

    @FXML
    private ComboBox<String> overshootResultComboBox;

    @FXML
    private Button overshootAddButton;


    public AddWeaponOvershootController() {
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        overshootCommentTextArea.setWrapText(true);
        resultsList = FXCollections.observableArrayList();
        overshootResultComboBox.setItems(resultsList);
        addListener();
    }



    private void addListener(){

        this.overshootAddButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                String comment = overshootCommentTextArea.getText().trim();
                LocalDate overshootDate = overshootDatePicker.getValue();

                if(overshootDate == null){
                    System.out.println("Wypełnij wszystkie dane");
                }else{

                    WeaponOvershoot weaponOvershoot = new WeaponOvershoot(overshootDate);
                    weaponOvershoot.setComment(comment);
                    weaponOvershoot.getOvershootResult(resultsList);
                    weaponOvershoot.setWeapon(weapon);

                    weaponController.updateWeapon(weapon);
                    Stage stage = (Stage)overshootAddButton.getScene().getWindow();
                    stage.close();
                }
            }
        });
    }
}
