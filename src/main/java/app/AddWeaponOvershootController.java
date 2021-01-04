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

    private final String RESULT_POSITIVE = "Pozytywny";
    private final String RESULT_NEGATIVE = "Negatywny";

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

        overshootResultComboBox.getItems().add(RESULT_POSITIVE);
        overshootResultComboBox.getItems().add(RESULT_NEGATIVE);

        addListener();
    }



    private void addListener(){

        this.overshootAddButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                String comment = overshootCommentTextArea.getText().trim();
                LocalDate overshootDate = overshootDatePicker.getValue();

                String selectedResult = overshootResultComboBox.getSelectionModel().getSelectedItem();

                if(overshootDate == null || selectedResult == null){
                    newAlertMessage("Error","Wypełnij wszystkie dane");

                }else{

                    WeaponOvershoot weaponOvershoot = new WeaponOvershoot(overshootDate);
                    weaponOvershoot.setComment(comment);
                    weaponOvershoot.getOvershootResult();
                    weaponOvershoot.setWeapon(weapon);

                    if(selectedResult.equals(RESULT_POSITIVE)){
                        weaponOvershoot.setOvershootResult(WeaponOvershoot.overshoot_Result.POZYTYWNY);
                    }
                    else if(weaponOvershoot.equals(RESULT_NEGATIVE)){
                        weaponOvershoot.setOvershootResult(WeaponOvershoot.overshoot_Result.NEGATYWNY);
                    }


                    weaponController.updateWeapon(weapon);
                    Stage stage = (Stage)overshootAddButton.getScene().getWindow();
                    stage.close();
                }
            }
        });
    }

    private void newAlertMessage(String header,String text){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(header);
        alert.setHeaderText(null);
        alert.setContentText(text);
        alert.showAndWait();
    }
}
