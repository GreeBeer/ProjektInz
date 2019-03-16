package ksiagarnia.com.internal.db.service;

import ksiagarnia.com.internal.db.HibernateUtils;
import ksiagarnia.com.internal.db.model.KupionaKsiazka;
import org.hibernate.Session;

import java.util.List;

public class SklepService {

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
}
