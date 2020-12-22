package service;

import model.TechnicalReview;

import java.util.List;

public interface TechnicalReviewService {

    void addReview(TechnicalReview technicalReview);
    List<TechnicalReview> getAllTechnicalReviews();
    List<TechnicalReview> getAllTechnicalReviewsForWeapon(long id);
    void updateTechnicalReview(TechnicalReview technicalReview);
    void removeTechnicalReview(Long id);
}
