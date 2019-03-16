package ksiagarnia.com.internal.db.service;

import ksiagarnia.com.internal.db.HibernateUtils;
import ksiagarnia.com.internal.db.model.WypozyczonaKsiazka;
import org.hibernate.Session;

import java.util.List;

public class WypozyczalniaService {

    @SuppressWarnings("unchecked")
    public List<WypozyczonaKsiazka> listaWypozyczonych(int userId) {
        Session session = HibernateUtils.getSessionFactory().openSession();

        List<WypozyczonaKsiazka> list = session
                .createSQLQuery("SELECT * FROM WypozyczonaKsiazka WHERE idUzytkownik=:userId")
                .addEntity(WypozyczonaKsiazka.class)
                .setParameter("userId", userId)
                .list();

        session.close();

        return list;
    }
}
