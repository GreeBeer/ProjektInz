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
    Uzytkownik uzytkownik;
    @ManyToOne
    @JoinColumn(name = "idKsiazka")
    Ksiazka ksiazka;

    Timestamp dataWypozyczenia;
    Timestamp dataOddania;
}
