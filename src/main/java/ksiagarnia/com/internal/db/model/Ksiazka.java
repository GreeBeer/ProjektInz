package ksiagarnia.com.internal.db.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class Ksiazka {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    public String tytul;
    public String autor;
    public Timestamp rokWydania;
    public Timestamp dateRejestracji;
    public String isbn;

    @ManyToOne
    @JoinColumn(name = "idGatunek")
    public GatunekKsiazki idGatunek;

    public Ksiazka() {

    }

    public Ksiazka(String tytul,
                   String autor,
                   Timestamp rokWydania,
                   Timestamp dateRejestracji,
                   String isbn,
                   int idGatunek) {
        this.tytul = tytul;
        this.autor = autor;
        this.rokWydania = rokWydania;
        this.dateRejestracji = dateRejestracji;
        this.isbn = isbn;
        this.idGatunek = new GatunekKsiazki(idGatunek, null);
    }

    @Override
    public String toString() {
        return "id: " + id + ", " +
                "tytul: " + tytul + ", " +
                "autor: " + autor + ", " +
                "isbn: " + isbn + ", " +
                "rokWydania: " + rokWydania + ", " +
                "gatunek: " + idGatunek.nazwa;
    }
}
