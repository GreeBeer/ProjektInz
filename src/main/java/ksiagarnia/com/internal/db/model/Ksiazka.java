package ksiagarnia.com.internal.db.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class Ksiazka {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    public String tytul;

    public Timestamp rokWydania;
    public Timestamp dateRejestracji;
    public String isbn;

    @ManyToOne
    @JoinColumn(name = "idGatunek")
    public GatunekKsiazki idGatunek;
}
