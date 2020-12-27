package dao;

import app.HibernateUtil;
import model.Weapon;
import org.hibernate.Hibernate;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class WeaponDAOImpl implements WeaponDAO{

    @Override
    public void addWeapon(Weapon weapon) {
        Session session = HibernateUtil.openSession();
        session.beginTransaction();
        session.save(weapon);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<Weapon> getAllWeapons() {
        List<Weapon> weaponList = new ArrayList<>();
        Session session = HibernateUtil.openSession();
        session.beginTransaction();
        weaponList = session.createQuery("from Weapon ").list();
        weaponList.forEach(w -> {
            Hibernate.initialize(w.getTechnicalReviews());
            w.getTechnicalReviews().forEach(p ->{
                Hibernate.initialize(p.getFaults());
            });
        });
        Hibernate.initialize(weaponList);
        session.getTransaction().commit();
        session.close();
        return weaponList;
    }

    @Override
    public void loadWeaponsTechnicalReviews(Weapon weapon) {
        Session session = HibernateUtil.openSession();
        session.beginTransaction();
        Hibernate.initialize(weapon.getTechnicalReviews());
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void loadWeaponOvershoots(Weapon weapon) {
        Session session = HibernateUtil.openSession();
        session.beginTransaction();
        Hibernate.initialize(weapon.getWeaponOvershoots());
        session.getTransaction().commit();
        session.close();
    }


    @Override
    public void updateWeapon(Weapon weapon) {
        Session session = HibernateUtil.openSession();
        session.beginTransaction();
        session.update(weapon);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void removeWeapon(Long id) {
        Session session = HibernateUtil.openSession();
        session.beginTransaction();
        Weapon weapon = (Weapon) session.load(Weapon.class, id);
        session.delete(weapon);
        session.getTransaction().commit();
        session.close();
    }
}
