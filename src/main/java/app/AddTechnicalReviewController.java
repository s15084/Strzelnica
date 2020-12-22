package app;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.TechnicalReview;
import model.Weapon;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AddTechnicalReviewController implements Initializable {

    private WeaponController weaponController = new WeaponController();
    private TechnicalReviewController technicalReviewController = new TechnicalReviewController();
    private Weapon weapon;
    private ObservableList<String> faultsList;


    @FXML
    private TextArea reviewCommentTextArea;

    @FXML
    private DatePicker reviewDatePicker;

    @FXML
    private TextField reviewFaultTextField;

    @FXML
    private ListView faultsListView;

    @FXML
    private Button reviewFaultCommitButton;

    @FXML
    private Button reviewAddButton;



    public AddTechnicalReviewController() {
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

        @Override
        public void initialize(URL location, ResourceBundle resources) {
        reviewCommentTextArea.setWrapText(true);
        faultsList = FXCollections.observableArrayList();
        faultsListView.setItems(faultsList);
        addListener();
    }



    private void addListener(){

        this.reviewAddButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                String comment = reviewCommentTextArea.getText().trim();
                LocalDate reviewDate = reviewDatePicker.getValue();

                if(reviewDate == null){
                    System.out.println("Wypełnij wszystkie dane");
                }else{

                    TechnicalReview technicalReview = new TechnicalReview(reviewDate);
                    technicalReview.setComment(comment);
                    technicalReview.setFaults(faultsList);
                    technicalReview.setWeapon(weapon);

                    weaponController.updateWeapon(weapon);
                    Stage stage = (Stage)reviewAddButton.getScene().getWindow();
                    stage.close();
                }
            }
        });

        this.reviewFaultCommitButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(!reviewFaultTextField.getText().trim().isEmpty()){
                    faultsList.add(reviewFaultTextField.getText().trim());
                    reviewFaultTextField.clear();
                }
            }
        });
    }





}
