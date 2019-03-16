package ksiagarnia.com.internal.db.service;

import ksiagarnia.com.internal.db.HibernateUtils;
import ksiagarnia.com.internal.db.model.KsiazkaSklep;
import ksiagarnia.com.internal.db.model.KupionaKsiazka;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.NoResultException;
import java.util.List;

public class SklepService {

    @SuppressWarnings("unchecked")
    public List<KsiazkaSklep> listaDostepnych() {
        Session session = HibernateUtils.getSessionFactory().openSession();

        List<KsiazkaSklep> list = session
                .createSQLQuery("SELECT * FROM Sklep WHERE ilosc > 0")
                .addEntity(KsiazkaSklep.class)
                .list();

        session.close();

        return list;
    }

    @SuppressWarnings("unchecked")
    public List<KsiazkaSklep> listaWszystkich() {
        Session session = HibernateUtils.getSessionFactory().openSession();

        List<KsiazkaSklep> list = session
                .createSQLQuery("SELECT * FROM Sklep")
                .addEntity(KsiazkaSklep.class)
                .list();

        session.close();

        return list;
    }

    @SuppressWarnings("unchecked")
    public List<KupionaKsiazka> listaKupionych(int userId) {
        Session session = HibernateUtils.getSessionFactory().openSession();

        List<KupionaKsiazka> list = session
                .createSQLQuery("SELECT * FROM KupionaKsiazka WHERE idUzytkownik=:userId")
                .addEntity(KupionaKsiazka.class)
                .setParameter("userId", userId)
                .list();

        session.close();

        return list;
    }

    @SuppressWarnings("unchecked")
    public void dodajZakukpionaKsiazke(KupionaKsiazka kupionaKsiazka) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(kupionaKsiazka);
        transaction.commit();
        session.close();
    }

    @SuppressWarnings("unchecked")
    public void uaktualnijLubDodaj(KsiazkaSklep ksiazkaSklep) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(ksiazkaSklep);
        transaction.commit();
        session.close();
    }

    public KsiazkaSklep znajdzKsiazke(int ksiazkaId) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        KsiazkaSklep ksiazka = null;
        try {
            ksiazka = (KsiazkaSklep) session
                    .createSQLQuery("SELECT * FROM Sklep WHERE idKsiazka=:ksiazkaId")
                    .addEntity(KsiazkaSklep.class)
                    .setParameter("ksiazkaId", ksiazkaId)
                    .getSingleResult();
        } catch (NoResultException ignore) {
        }
        session.close();

        return ksiazka;
    }
}
