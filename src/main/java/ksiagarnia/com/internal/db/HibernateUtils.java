package ksiagarnia.com.internal.db;

import ksiagarnia.com.internal.db.model.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtils {
    private HibernateUtils() {
        throw new IllegalStateException("Utility class");
    }

    private static final SessionFactory sessionFactory;

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure("cfg.xml");
            configuration.addAnnotatedClass(GatunekKsiazki.class);
            configuration.addAnnotatedClass(Ksiazka.class);
            configuration.addAnnotatedClass(Uzytkownik.class);
            configuration.addAnnotatedClass(WypozyczonaKsiazka.class);
            configuration.addAnnotatedClass(KupionaKsiazka.class);

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties())
                    .build();

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (Exception e) {
            System.err.println("Session Factory could not be created.\n" + e);
            throw new ExceptionInInitializerError(e);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

}


