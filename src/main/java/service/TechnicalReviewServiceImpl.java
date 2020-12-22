package service;

import model.TechnicalReview;
import dao.TechnicalReviewDAO;
import dao.TechnicalReviewDAOImpl;
import java.util.List;

public class TechnicalReviewServiceImpl implements TechnicalReviewService {

    private TechnicalReviewDAO technicalReviewDAO = new TechnicalReviewDAOImpl();

    @Override
    public void addReview(TechnicalReview technicalReview) {
        technicalReviewDAO.addReview(technicalReview);
    }

    @Override
    public List<TechnicalReview> getAllTechnicalReviews() {
        return technicalReviewDAO.getAllTechnicalReviews();
    }

    @Override
    public List<TechnicalReview> getAllTechnicalReviewsForWeapon(long id) {
        return technicalReviewDAO.getAllTechnicalReviewsForWeapon(id);
    }

    @Override
    public void updateTechnicalReview(TechnicalReview technicalReview) {
        technicalReviewDAO.updateTechnicalReview(technicalReview);
    }

    @Override
    public void removeTechnicalReview(Long id) {
        technicalReviewDAO.removeTechnicalReview(id);
    }
}
