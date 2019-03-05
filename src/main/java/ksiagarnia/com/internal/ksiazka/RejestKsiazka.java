package ksiagarnia.com.internal.ksiazka;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Baza danych wszystkich istniejacych ksiazek
 */
public class RejestKsiazka {
    private final ArrayList<Ksiazka> ksiazkiList = new ArrayList<>(); // ksiazka id -> Ksiazka

    public void dodajKsiazke(Ksiazka ksiazka) {
        ksiazkiList.add(ksiazka);
    }

    public Ksiazka znajdzKsiazke(int ksiazkaId) {
        for (Ksiazka ksiazka : ksiazkiList) {
            if (ksiazka.id == ksiazkaId) {
                return ksiazka;
            }
        }
        return null;
    }

    public ArrayList<Ksiazka> znajdzKsiazki(Collection<Integer> ids) {
        ArrayList<Ksiazka> ksiazkaSet = new ArrayList<>();
        for (Ksiazka ksiazka : ksiazkiList) {
            if (ids.contains(ksiazka.id)) {
                ksiazkaSet.add(ksiazka);
            }
        }
        return ksiazkaSet;
    }

    public ArrayList<Ksiazka> podajKsiazki() {
        return ksiazkiList;
    }
}