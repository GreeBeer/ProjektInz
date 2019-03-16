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
    int iloscKupionych;

    @Override
    public String toString() {
        return "dataKupna:" + dataKupna +
                ", iloscKupionych: " + iloscKupionych +
                ", ksiazka: " + ksiazka.toString();
    }
}
