package ksiagarnia.com.internal.zbiory;

import java.util.HashMap;
import java.util.Map;

/**
 * Przechowywuje tylko id do ksiazek wraz z ich iloscia w zbiorze
 */
public abstract class ZbiorKsiazek {
    private final Map<Integer, Integer> ksiazkaDoIlosc = new HashMap<>(); // ksiazka id do ilosc

    public void dodajKsiazke(int ksiazkaId, int ilosc) {
        ksiazkaDoIlosc.put(ksiazkaId, ilosc);
    }

    public boolean czyJestDostepna(int ksiazkaId) {
        return ksiazkaDoIlosc.getOrDefault(ksiazkaId, 0) > 0;
    }

    protected Integer podajIloscDostepnych(int ksiazkaId) {
        return ksiazkaDoIlosc.get(ksiazkaId);
    }

    protected void uakutalnijIlosc(int ksiazkaId, int nowaIlosc) {
        ksiazkaDoIlosc.put(ksiazkaId, nowaIlosc);
    }
}
