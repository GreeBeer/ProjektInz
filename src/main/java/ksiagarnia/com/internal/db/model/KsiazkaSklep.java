package ksiagarnia.com.internal.db.model;

import javax.persistence.*;

@Entity
@Table(name = "Sklep")
public class KsiazkaSklep {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    @ManyToOne
    @JoinColumn(name = "idKsiazka")
    public Ksiazka ksiazka;
    public int ilosc;
    public double cenaKsiazki;

    public KsiazkaSklep() {
    }

    public KsiazkaSklep(int ksiazkaId, int ilosc, double cenaKsiazki) {
        this.ksiazka = new Ksiazka();
        ksiazka.id = ksiazkaId;
        this.ilosc = ilosc;
        this.cenaKsiazki = cenaKsiazki;
    }

    @Override
    public String toString() {
        return "ilosc: " + ilosc +
                ", cenaKsiazki: " + cenaKsiazki +
                ", ksiazka: " + ksiazka;
    }
}
