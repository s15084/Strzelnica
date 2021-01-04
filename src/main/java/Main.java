import app.*;
import javafx.application.Application;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Callback;
import model.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class Main extends Application {

    //lista broni
    @FXML
    private ListView<Weapon> weaponsListView;


    //tabela przeglądów technicznych
    @FXML
    private TableView<TechnicalReview> technicalReviewTableView;
    @FXML
    private TableColumn reviewDate;
    @FXML
    private TableColumn<TechnicalReview, LocalDate> nextReviewDate;
    @FXML
    private TableColumn<TechnicalReview, String> faults;
    @FXML
    private TableColumn<TechnicalReview, String> comment;



    //tabela przystrzeleń
    @FXML
    private TableView<WeaponOvershoot> weaponOvershootTableView;
    @FXML
    private TableColumn overshootDate;
    @FXML
    private TableColumn<WeaponOvershoot, LocalDate> nextOvershootDate;
    @FXML
    private TableColumn<WeaponOvershoot, String> overshootResult;
    @FXML
    private TableColumn<WeaponOvershoot, String> comment2;

    //przyciski
    @FXML
    private Button newWeaponOverviewButton;
    @FXML
    private Button newWeaponOvershootButton;
    @FXML
    private Button sellTicketButton;


    //kontrolery
    private WeaponController weaponController = new WeaponController();
    private TechnicalReviewController technicalReviewController = new TechnicalReviewController();
    private WeaponOvershootController weaponOvershootController = new WeaponOvershootController();

    public static void main(String [] args){

        Main main = new Main();
        main.addExamples();
        launch(args);
    }

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
                        setText("Broń krótka | " + item.getName() + " | SN: " + item.getSerialNumber());
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
                    populateTechnicalReviewsTable(selectedWeapon.getTechnicalReviews());
                    populateWeaponOvershootsTable(selectedWeapon.getWeaponOvershoots());
                }
            }
        });

        newWeaponOverviewButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    if(weaponsListView.getSelectionModel().getSelectedItem() == null){
                        newAlertMessage("Error","Wybierz broń z listy");
                    }else {
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("addReview.fxml"));
                        Parent root1 = (Parent) fxmlLoader.load();
                        Weapon chosenWeapon = weaponsListView.getSelectionModel().getSelectedItem();
                        AddTechnicalReviewController addTechnicalReviewController = fxmlLoader.getController();
                        addTechnicalReviewController.setWeapon(chosenWeapon);

                        Stage stage = new Stage();
                        stage.setTitle("Nowy przegląd techniczny");
                        stage.setScene(new Scene(root1));
                        stage.setResizable(false);
                        stage.show();

                        //Ustawienie odsiwezenia
                        stage.setOnHidden(new EventHandler<WindowEvent>() {
                            @Override
                            public void handle(WindowEvent event) {
                                populateTechnicalReviewsTable(chosenWeapon.getTechnicalReviews());
                            }
                        });

                    }
                }catch(Exception e){
                    System.out.println("Błąd");

                }

            }
        });

        //TODO dodać obsługę enum - wynik przystrzelenia

        newWeaponOvershootButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    if(weaponsListView.getSelectionModel().getSelectedItem() == null){
                        newAlertMessage("Error","Wybierz broń z listy");
                    }else {
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("addOvershoot.fxml"));
                        Parent root2 = (Parent) fxmlLoader.load();
                        Weapon chosenWeapon = weaponsListView.getSelectionModel().getSelectedItem();
                        AddWeaponOvershootController addWeaponOvershootController = fxmlLoader.getController();
                        addWeaponOvershootController.setWeapon(chosenWeapon);
                        Stage stage = new Stage();
                        stage.setTitle("Nowe przystrzelenie");
                        stage.setScene(new Scene(root2));
                        stage.setResizable(false);
                        stage.show();

                        //Ustawienie odsiwezenia
                        stage.setOnHidden(new EventHandler<WindowEvent>() {
                            @Override
                            public void handle(WindowEvent event) {
                                populateWeaponOvershootsTable(chosenWeapon.getWeaponOvershoots());
                            }
                        });

                    }
                }catch(Exception e){
                    System.out.println("Błąd");

                }

            }
        });
    }

    private void populateTechnicalReviewsTable(List<TechnicalReview> technicalReviewList){
        technicalReviewTableView.setItems(FXCollections.observableList(technicalReviewList));

        reviewDate = new TableColumn<>("Data przeglądu technicznego");
        reviewDate.setCellValueFactory(new PropertyValueFactory<TechnicalReview,LocalDate>("reviewDate"));

        nextReviewDate = new TableColumn<>("Data następnego przeglądu technicznego");
        nextReviewDate.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<TechnicalReview, LocalDate>, ObservableValue<LocalDate>>() {
            @Override
            public ObservableValue<LocalDate> call(TableColumn.CellDataFeatures<TechnicalReview, LocalDate> param) {
                return new SimpleObjectProperty<>(param.getValue().getNextReviewDate());
            }
        });

        faults = new TableColumn<>("Usterki");
        faults.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<TechnicalReview, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<TechnicalReview, String> param) {
                if(param.getValue().getFaults() == null || param.getValue().getFaults().size() == 0){
                    return new SimpleObjectProperty<>("Brak usterek");
                }

                String faults = "";
                boolean isFirstFault = true;
                for(String fault : param.getValue().getFaults()){
                    if(!isFirstFault){
                        faults += "\n";
                    }
                    isFirstFault = false;
                    faults += fault;
                }
                return new SimpleObjectProperty<>(faults);
            }
        });

        comment = new TableColumn<>("Komentarz");
        comment.setCellValueFactory(new PropertyValueFactory<TechnicalReview, String>("comment"));

        reviewDate.prefWidthProperty().bind(technicalReviewTableView.widthProperty().multiply(0.25));
        nextReviewDate.prefWidthProperty().bind(technicalReviewTableView.widthProperty().multiply(0.35));
        faults.prefWidthProperty().bind(technicalReviewTableView.widthProperty().multiply(0.2));
        comment.prefWidthProperty().bind(technicalReviewTableView.widthProperty().multiply(0.2));

        technicalReviewTableView.getColumns().setAll(reviewDate, nextReviewDate, faults, comment);

    }

    private void populateWeaponOvershootsTable(List<WeaponOvershoot> weaponOvershootList){
        weaponOvershootTableView.setItems(FXCollections.observableList(weaponOvershootList));

        overshootDate = new TableColumn<>("Data przystrzelenia");
        overshootDate.setCellValueFactory(new PropertyValueFactory<WeaponOvershoot,LocalDate>("overshootDate"));

        nextOvershootDate = new TableColumn<>("Data następnego przystrzelenia");
        nextOvershootDate.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<WeaponOvershoot, LocalDate>, ObservableValue<LocalDate>>() {
            @Override
            public ObservableValue<LocalDate> call(TableColumn.CellDataFeatures<WeaponOvershoot, LocalDate> param) {
                return new SimpleObjectProperty<>(param.getValue().getNextOvershootDate());
            }
        });

        overshootResult = new TableColumn<>("Wynik przystrzelenia");
//        overshootResult.setCellValueFactory(new PropertyValueFactory<WeaponOvershoot, WeaponOvershoot.overshoot_Result>("overshootResult"));
        overshootResult.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<WeaponOvershoot, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<WeaponOvershoot, String> param) {
                return new SimpleObjectProperty<>(param.getValue().getOvershootResult().toString());
            }
        });

        comment2 = new TableColumn<>("Komentarz");
        comment2.setCellValueFactory(new PropertyValueFactory<WeaponOvershoot, String>("comment"));

        overshootDate.prefWidthProperty().bind(weaponOvershootTableView.widthProperty().multiply(0.25));
        nextOvershootDate.prefWidthProperty().bind(weaponOvershootTableView.widthProperty().multiply(0.35));
        overshootResult.prefWidthProperty().bind(weaponOvershootTableView.widthProperty().multiply(0.2));
        comment2.prefWidthProperty().bind(weaponOvershootTableView.widthProperty().multiply(0.2));

        weaponOvershootTableView.getColumns().setAll(overshootDate, nextOvershootDate, overshootResult, comment2);

    }

    public TableColumn<WeaponOvershoot, String> getOvershootResult() {
        return overshootResult;
    }

    public void setOvershootResult(TableColumn<WeaponOvershoot, String> overshootResult) {
        this.overshootResult = overshootResult;
    }

    private void newAlertMessage(String header, String text){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(header);
        alert.setHeaderText(null);
        alert.setContentText(text);
        alert.showAndWait();
    }


    private void addExamples(){
        ShortWeapon shortWeapon = new ShortWeapon(58473847,"Glock", 8.0, 8.0,"gładka",Weapon.weapon_Condition.SPRAWNA,10);
        Client client = new Client("Walter", "White", LocalDate.parse("1965-01-01") ,123456789,6859586,"walter@white.com");
        weaponController.addWeapon(shortWeapon);
        System.out.println(client);
    }
}
