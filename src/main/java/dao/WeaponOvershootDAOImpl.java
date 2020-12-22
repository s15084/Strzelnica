package dao;

import app.HibernateUtil;
import model.WeaponOvershoot;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class WeaponOvershootDAOImpl implements WeaponOvershootDAO{

    @Override
    public void addOvershoot(WeaponOvershoot weaponOvershoot) {
        Session session = HibernateUtil.openSession();
        session.beginTransaction();
        System.out.println("Dodaję przegląd techniczny");
        session.save(weaponOvershoot);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<WeaponOvershoot> getAllWeaponOvershoots() {
        List<WeaponOvershoot> overshootList = new ArrayList<>();
        Session session = HibernateUtil.openSession();
        session.beginTransaction();
        overshootList = session.createQuery("from WeaponOvershoot ").list();
        session.getTransaction().commit();
        session.close();
        return overshootList;
    }

    @Override
    public List<WeaponOvershoot> getAllWeaponOvershootsForWeapon(Long idW) {
        List<WeaponOvershoot> overshootList = new ArrayList<>();
        Session session = HibernateUtil.openSession();
        session.beginTransaction();
        overshootList = session.createQuery("SELECT distinct w from WeaponOvershoot w left join fetch WeaponOvershoot.overshootResult where w.weapon.id = :idWeapon").setParameter("idWeapon", idW).list();
        overshootList.stream().distinct().collect((Collectors.toList()));
        System.out.println("Początek listy");
        overshootList.forEach(x -> System.out.println(x));
        session.getTransaction().commit();
        session.close();
        return overshootList;
    }

    @Override
    public void updateWeaponOvershoot(WeaponOvershoot weaponOvershoot) {
        Session session = HibernateUtil.openSession();
        session.beginTransaction();
        session.update(weaponOvershoot);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void removeWeaponOvershoot(Long id) {
        Session session = HibernateUtil.openSession();
        session.beginTransaction();
        WeaponOvershoot overshoot = (WeaponOvershoot) session.load(WeaponOvershoot.class, id);
        session.delete(overshoot);
        session.getTransaction().commit();
        session.close();
    }
}
