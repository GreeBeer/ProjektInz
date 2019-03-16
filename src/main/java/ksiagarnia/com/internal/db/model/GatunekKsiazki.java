package ksiagarnia.com.internal.db.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class GatunekKsiazki {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    public String nazwa;

    @Override
    public String toString() {
        return "id: " + id + ", " + "nazwa: " + nazwa;
    }

    // konstructor dla hibernate
    public GatunekKsiazki() {

    }

    public GatunekKsiazki(int id, String nazwa) {
        this.id = id;
        this.nazwa = nazwa;
    }
}
