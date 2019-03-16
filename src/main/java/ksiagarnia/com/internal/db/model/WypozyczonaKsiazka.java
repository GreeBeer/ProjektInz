package ksiagarnia.com.internal.db.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class WypozyczonaKsiazka {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    @ManyToOne
    @JoinColumn(name = "idUzytkownik")
    public Uzytkownik uzytkownik;
    @ManyToOne
    @JoinColumn(name = "idKsiazka")
    public Ksiazka ksiazka;

    public Timestamp dataWypozyczenia;
    public Timestamp dataOddania;

    public WypozyczonaKsiazka() {

    }

    public WypozyczonaKsiazka(int userId, int ksiazkaId, Timestamp dataWypozyczenia, Timestamp dataOddania) {
        this.uzytkownik = new Uzytkownik();
        this.uzytkownik.id = userId;
        this.ksiazka = new Ksiazka();
        this.ksiazka.id = ksiazkaId;
        this.dataWypozyczenia = dataWypozyczenia;
        this.dataOddania = dataOddania;
    }

    @Override
    public String toString() {
        return "dataWypozyczenia: " + dataWypozyczenia +
                ", dataOddania: " + dataOddania +
                ", ksiazka: " + ksiazka.toString();
    }
}
