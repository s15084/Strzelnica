package dao;

import app.HibernateUtil;
import model.TechnicalReview;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TechnicalReviewDAOImpl implements TechnicalReviewDAO {

    @Override
    public void addReview(TechnicalReview technicalReview) {
        Session session = HibernateUtil.openSession();
        session.beginTransaction();
        System.out.println("Dodaję przegląd techniczny");
        session.save(technicalReview);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<TechnicalReview> getAllTechnicalReviews() {
        List<TechnicalReview> reviewList = new ArrayList<>();
        Session session = HibernateUtil.openSession();
        session.beginTransaction();
        reviewList = session.createQuery("from TechnicalReview ").list();
        session.getTransaction().commit();
        session.close();
        return reviewList;
    }

    @Override
    public List<TechnicalReview> getAllTechnicalReviewsForWeapon(Long idW) {
        List<TechnicalReview> reviewList = new ArrayList<>();
        Session session = HibernateUtil.openSession();
        session.beginTransaction();
        reviewList = session.createQuery("SELECT distinct r from TechnicalReview r left join fetch r.faults where r.weapon.id = :idWeapon").setParameter("idWeapon", idW).list();
        reviewList.stream().distinct().collect((Collectors.toList()));
        System.out.println("Początek listy");
        reviewList.forEach(x -> System.out.println(x));
        session.getTransaction().commit();
        session.close();
        return reviewList;
    }

    @Override
    public void updateTechnicalReview(TechnicalReview technicalReview) {
        Session session = HibernateUtil.openSession();
        session.beginTransaction();
        session.update(technicalReview);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void removeTechnicalReview(Long id) {
        Session session = HibernateUtil.openSession();
        session.beginTransaction();
        TechnicalReview review = (TechnicalReview) session.load(TechnicalReview.class, id);
        session.delete(review);
        session.getTransaction().commit();
        session.close();
    }
}
