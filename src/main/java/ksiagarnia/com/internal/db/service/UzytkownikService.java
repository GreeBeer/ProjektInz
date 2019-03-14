package ksiagarnia.com.internal.db.service;

import ksiagarnia.com.internal.db.HibernateUtils;
import ksiagarnia.com.internal.db.model.Uzytkownik;
import org.hibernate.Session;

import javax.persistence.NoResultException;
import java.util.List;

public class UzytkownikService {

    @SuppressWarnings("unchecked")
    public List<Uzytkownik> listaUzytkownikow() {
        Session session = HibernateUtils.getSessionFactory().openSession();

        List<Uzytkownik> list = session
                .createSQLQuery("SELECT * FROM Uzytkownik")
                .addEntity(Uzytkownik.class)
                .list();

        session.close();

        return list;
    }

    public Uzytkownik znajdzUzytkownika(String loginName) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Uzytkownik user;
        try {
            user = (Uzytkownik) session
                    .createSQLQuery("SELECT * FROM Uzytkownik WHERE loginName = :loginName")
                    .setParameter("loginName", loginName)
                    .addEntity(Uzytkownik.class)
                    .getSingleResult();
        }catch (NoResultException e) {
            user = null;
        }
        session.close();

        return user;
    }
}
