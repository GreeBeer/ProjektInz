package ksiagarnia.com.internal.db.service;

import ksiagarnia.com.internal.db.HibernateUtils;
import ksiagarnia.com.internal.db.model.GatunekKsiazki;
import ksiagarnia.com.internal.db.model.Ksiazka;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.NoResultException;
import java.util.List;

public class KsiazkaService {

    @SuppressWarnings("unchecked")
    public List<Ksiazka> listaKsiazekZarejestrowanych() {
        Session session = HibernateUtils.getSessionFactory().openSession();

        List<Ksiazka> list = session
                .createSQLQuery("SELECT * FROM Ksiazka WHERE dataRejestracji IS NOT NULL")
                .addEntity(Ksiazka.class)
                .list();

        session.close();

        return list;
    }

    @SuppressWarnings("unchecked")
    public List<Ksiazka> listaKsiazekNieZarejestrowanych() {
        Session session = HibernateUtils.getSessionFactory().openSession();

        List<Ksiazka> list = session
                .createSQLQuery("SELECT * FROM Ksiazka WHERE dataRejestracji IS NULL")
                .addEntity(Ksiazka.class)
                .list();

        session.close();

        return list;
    }

    @SuppressWarnings("unchecked")
    public Ksiazka znajdzKsiazke(int id) {
        Session session = HibernateUtils.getSessionFactory().openSession();

        Ksiazka ksiazka = null;
        try {
            ksiazka = (Ksiazka) session
                    .createSQLQuery("SELECT * FROM Ksiazka WHERE id = :id")
                    .addEntity(Ksiazka.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (NoResultException ignore) {

        }
        session.close();

        return ksiazka;
    }

    public void zapiszKsiazke(Ksiazka ksiazka) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(ksiazka);
        transaction.commit();
        session.close();
    }

    @SuppressWarnings("unchecked")
    public List<GatunekKsiazki> listaGatunkow() {
        Session session = HibernateUtils.getSessionFactory().openSession();

        List<GatunekKsiazki> list = session
                .createSQLQuery("SELECT * FROM GatunekKsiazki")
                .addEntity(GatunekKsiazki.class)
                .list();

        session.close();

        return list;
    }
}
