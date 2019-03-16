package ksiagarnia.com.internal.db.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class KupionaKsiazka {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    @ManyToOne
    @JoinColumn(name = "idUzytkownik")
    Uzytkownik uzytkownik;
    @ManyToOne
    @JoinColumn(name = "idKsiazka")
    Ksiazka ksiazka;

    Timestamp dataKupna;

    @Override
    public String toString() {
        return "dataKupna:" + dataKupna +
                ", ksiazka: " + ksiazka.toString();
    }

    public KupionaKsiazka(int userId, int ksiazkaId, Timestamp dataKupna) {
        this.uzytkownik = new Uzytkownik();
        this.uzytkownik.id = userId;
        this.ksiazka = new Ksiazka();
        this.ksiazka.id = ksiazkaId;
        this.dataKupna = dataKupna;
    }

    public KupionaKsiazka() {
    }
}
