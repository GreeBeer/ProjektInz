package ksiagarnia.com.internal.ksiazka;

import java.util.Objects;

public class Ksiazka {
    public final int id;
    public final String tytul;
    public final int rokWydania;
    public final String isbn;
    public final GatunekKsiazki gatunekKsiazki;
    public final double cenaNowej;
    public final double kosztWyporzyczenia;

    public Ksiazka(int id,
                   String tytul,
                   int rokWydania,
                   String isbn,
                   GatunekKsiazki gatunekKsiazki,
                   double cenaNowej,
                   double kosztWyporzyczenia) {
        this.id = id;
        this.tytul = tytul;
        this.rokWydania = rokWydania;
        this.isbn = isbn;
        this.gatunekKsiazki = gatunekKsiazki;
        this.cenaNowej = cenaNowej;
        this.kosztWyporzyczenia = kosztWyporzyczenia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ksiazka)) return false;
        Ksiazka ksiazka = (Ksiazka) o;
        return id == ksiazka.id &&
                rokWydania == ksiazka.rokWydania &&
                Objects.equals(tytul, ksiazka.tytul) &&
                Objects.equals(isbn, ksiazka.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tytul, rokWydania, isbn);
    }

    @Override
    public String toString() {
        return "Ksiazka{" +
                "id=" + id +
                ", tytul='" + tytul + '\'' +
                ", rokWydania=" + rokWydania +
                ", isbn='" + isbn + '\'' +
                ", gatunekKsiazki=" + gatunekKsiazki +
                ", cenaNowej=" + cenaNowej +
                ", kosztWyporzyczenia=" + kosztWyporzyczenia +
                '}';
    }
}
