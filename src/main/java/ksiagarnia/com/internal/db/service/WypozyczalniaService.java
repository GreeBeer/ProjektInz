package ksiagarnia.com.internal.db.service;

import ksiagarnia.com.internal.db.HibernateUtils;
import ksiagarnia.com.internal.db.model.KsiazkaWypozyczalnia;
import ksiagarnia.com.internal.db.model.WypozyczonaKsiazka;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.NoResultException;
import java.util.List;

public class WypozyczalniaService {

    @SuppressWarnings("unchecked")
    public List<KsiazkaWypozyczalnia> listaDostepnych() {
        Session session = HibernateUtils.getSessionFactory().openSession();

        List<KsiazkaWypozyczalnia> list = session
                .createSQLQuery("SELECT * FROM Wypozyczalnia WHERE ilosc > 0")
                .addEntity(KsiazkaWypozyczalnia.class)
                .list();

        session.close();

        return list;
    }

    @SuppressWarnings("unchecked")
    public List<KsiazkaWypozyczalnia> listaWszystkich() {
        Session session = HibernateUtils.getSessionFactory().openSession();

        List<KsiazkaWypozyczalnia> list = session
                .createSQLQuery("SELECT * FROM Wypozyczalnia")
                .addEntity(KsiazkaWypozyczalnia.class)
                .list();

        session.close();

        return list;
    }

    @SuppressWarnings("unchecked")
    public List<WypozyczonaKsiazka> listaWypozyczonych(int userId) {
        Session session = HibernateUtils.getSessionFactory().openSession();

        List<WypozyczonaKsiazka> list = session
                .createSQLQuery("SELECT * FROM WypozyczonaKsiazka WHERE idUzytkownik=:userId AND dataOddania IS NULL")
                .addEntity(WypozyczonaKsiazka.class)
                .setParameter("userId", userId)
                .list();

        session.close();

        return list;
    }

    @SuppressWarnings("unchecked")
    public WypozyczonaKsiazka znajdzWypozyczonaNieOddana(int userId, int idKsiazka) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        WypozyczonaKsiazka ksiazka = null;
        try {
            ksiazka = (WypozyczonaKsiazka) session
                    .createSQLQuery("SELECT * FROM WypozyczonaKsiazka WHERE idUzytkownik=:userId AND idKsiazka=:idKsiazka AND dataOddania IS NULL")
                    .addEntity(WypozyczonaKsiazka.class)
                    .setParameter("userId", userId)
                    .setParameter("idKsiazka", idKsiazka)
                    .getResultStream()
                    .findFirst()
                    .orElse(null);
        } catch (NoResultException ignore) {

        }
        session.close();

        return ksiazka;
    }

    @SuppressWarnings("unchecked")
    public void uaktualnijLubDodaj(WypozyczonaKsiazka wypozyczonaKsiazka) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(wypozyczonaKsiazka);
        transaction.commit();
        session.close();
    }

    @SuppressWarnings("unchecked")
    public void uaktualnijLubDodaj(KsiazkaWypozyczalnia ksiazkaWypozyczalnia) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(ksiazkaWypozyczalnia);
        transaction.commit();
        session.close();
    }

    public KsiazkaWypozyczalnia znajdzKsiazke(int ksiazkaId) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        KsiazkaWypozyczalnia ksiazka = null;
        try {
            ksiazka = (KsiazkaWypozyczalnia) session
                    .createSQLQuery("SELECT * FROM Wypozyczalnia WHERE idKsiazka=:ksiazkaId")
                    .addEntity(KsiazkaWypozyczalnia.class)
                    .setParameter("ksiazkaId", ksiazkaId)
                    .getSingleResult();
        } catch (NoResultException ignore) {
        }
        session.close();

        return ksiazka;
    }
}
