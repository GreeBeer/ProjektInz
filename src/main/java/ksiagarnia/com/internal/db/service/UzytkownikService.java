package ksiagarnia.com.internal.db.service;

import ksiagarnia.com.internal.db.HibernateUtils;
import ksiagarnia.com.internal.db.model.Uzytkownik;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.NoResultException;
import java.util.List;

public class UzytkownikService {

    @SuppressWarnings("unchecked")
    public List<Uzytkownik> listaUzytkownikow() {
        Session session = HibernateUtils.getSessionFactory().openSession();

        List<Uzytkownik> list = session
                .createSQLQuery("SELECT * FROM Uzytkownik WHERE admin = 0")
                .addEntity(Uzytkownik.class)
                .list();

        session.close();

        return list;
    }

    public Uzytkownik znajdzUzytkownika(String loginName) {
        Session session = HibernateUtils.getSessionFactory().openSession();

        Uzytkownik user = null;
        try {
            user = (Uzytkownik) session
                    .createSQLQuery("SELECT * FROM Uzytkownik WHERE loginName = :loginName")
                    .setParameter("loginName", loginName)
                    .addEntity(Uzytkownik.class)
                    .getSingleResult();
        } catch (NoResultException ignore) {

        }
        session.close();

        return user;
    }

    public void dodajUzytkownika(Uzytkownik uzytkownik) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(uzytkownik);
        transaction.commit();
        session.close();
    }

    public void uaktualnijSaldo(Uzytkownik uzytkownik) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.createSQLQuery("UPDATE Uzytkownik SET saldo=:saldo WHERE id=:userId")
                .setParameter("saldo", uzytkownik.podajSaldo())
                .setParameter("userId", uzytkownik.id)
                .executeUpdate();
        transaction.commit();
        session.close();
    }
}
