import app.WeaponController;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.*;

import java.time.LocalDate;


public class Main extends Application {

    //lista broni
    @FXML
    private ListView<Weapon> weaponsListView;

    //tabela przeglądów technicznych
    @FXML
    private TableView<TechnicalReview> weaponOverviewTableView;
    @FXML
    private TableColumn reviewDate;
    @FXML
    private TableColumn<TechnicalReview, LocalDate> nextReviewDate;
    @FXML
    private TableColumn<TechnicalReview, String> faults;
    @FXML
    private TableColumn<TechnicalReview, String> comment;


    //tabela przystrzeleń
            /*...*/

    //przyciski
            /*...*/

    //kontrolery
    private WeaponController weaponController = new WeaponController();
            /*...*/


    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("mainpage.fxml"));
        fxmlLoader.setController(this);
        Parent root = fxmlLoader.load();

        primaryStage.setTitle("Strona główna");
        primaryStage.setScene(new Scene(root));

        populateWeaponsListView();
        addListeners();

        primaryStage.show();
    }

    private void populateWeaponsListView(){
        weaponsListView.setItems(weaponController.getAllWeapons());
        weaponsListView.setCellFactory(param -> new ListCell<Weapon>(){
            @Override
            protected void updateItem(Weapon item, boolean empty){
                super.updateItem(item, empty);

                if(!empty || item!=null){
                    if(item instanceof ShortWeapon){
                        setText("Broń krótka " +item.getCaliber() + " mm | " +((ShortWeapon)item).getClipCapacity() + " " + item.getWeaponCondition());
                    }
                    else if(item instanceof MachineWeapon){
                        setText("Broń maszynowa " +item.getCaliber() + " mm | " +((MachineWeapon)item).getClipCapacity() + " " + item.getWeaponCondition());
                    }
                    else if(item instanceof FlatBarrelWeapon){
                        setText("Broń płasko-lufowa " +item.getCaliber() + " mm | " +((FlatBarrelWeapon)item).isDoubleBarreled() + " " + item.getWeaponCondition());
                    }
                }
            }
        });
    }

    private void addListeners(){

        weaponsListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getClickCount() == 2 && weaponsListView.getSelectionModel().getSelectedItem() != null){
                  Weapon selectedWeapon = weaponsListView
                        .getSelectionModel()
                        .getSelectedItem();
                  /* .... */
                }
            }
        });
    }

    public static void main(String [] args){

                Main main = new Main();
        main.addExamples();
    launch(args);
    }


    private void addExamples(){
        weaponController.addWeapon(new ShortWeapon("Glock", 9.0, 10.0,"gładka",Weapon.weapon_Condition.SPRAWNA,50));
    }
}
