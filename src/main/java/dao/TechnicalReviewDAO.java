package dao;

import model.TechnicalReview;

import java.util.List;

public interface TechnicalReviewDAO {

    void addReview(TechnicalReview technicalReview);
    List<TechnicalReview> getAllTechnicalReviews();
    List<TechnicalReview> getAllTechnicalReviewsForWeapon(Long id);
    void updateTechnicalReview(TechnicalReview technicalReview);
    void removeTechnicalReview(Long id);
}
