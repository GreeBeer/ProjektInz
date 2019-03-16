package ksiagarnia.com.internal.db.model;

import javax.persistence.*;

@Entity
@Table(name = "Wypozyczalnia")
public class KsiazkaWypozyczalnia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    @ManyToOne
    @JoinColumn(name = "idKsiazka")
    public Ksiazka ksiazka;
    public int ilosc;
    public double kosztWypozyczenia;

    public KsiazkaWypozyczalnia() {
    }

    public KsiazkaWypozyczalnia(int ksiazkaId, int ilosc, double kosztWypozyczenia) {
        this.ksiazka = new Ksiazka();
        ksiazka.id = ksiazkaId;
        this.ilosc = ilosc;
        this.kosztWypozyczenia = kosztWypozyczenia;
    }

    @Override
    public String toString() {
        return "ilosc: " + ilosc +
                ", kosztWypozyczenia: " + kosztWypozyczenia +
                ", ksiazka: " + ksiazka.toString();
    }
}
