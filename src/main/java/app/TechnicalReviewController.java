package app;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.TechnicalReview;
import service.TechnicalReviewService;
import service.TechnicalReviewServiceImpl;

import java.util.List;

public class TechnicalReviewController {

    private TechnicalReviewService technicalReviewService = new TechnicalReviewServiceImpl();
    private ObservableList<TechnicalReview> technicalReviewObservableList = FXCollections.observableArrayList();
    private ObservableList<TechnicalReview> technicalReviewForWeaponObservableList = FXCollections.observableArrayList();


    public void addTechnicalReview(TechnicalReview technicalReview){
        technicalReviewService.addReview(technicalReview);
    }

    public ObservableList<TechnicalReview> getAllTechnicalReviews(){
        if(!technicalReviewObservableList.isEmpty()){
            technicalReviewObservableList.clear();
        }
        technicalReviewObservableList = FXCollections.observableList((List<TechnicalReview>) technicalReviewService.getAllTechnicalReviews());
        return technicalReviewObservableList;
    }

    public ObservableList<TechnicalReview> getAllTechnicalReviewsForWeapon(long id){
        if(!technicalReviewForWeaponObservableList.isEmpty()){
            technicalReviewForWeaponObservableList.clear();
        }
        technicalReviewForWeaponObservableList = FXCollections.observableList((List<TechnicalReview>) technicalReviewService.getAllTechnicalReviewsForWeapon(id));
        return technicalReviewForWeaponObservableList;
    }

    public void updateTechnicalReview(TechnicalReview technicalReview){
        technicalReviewService.updateTechnicalReview(technicalReview);
    }

    public void removeTechnicalReview(Long id){
        technicalReviewService.removeTechnicalReview(id);
    }
}
