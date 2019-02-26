package ksiagarnia.com.internal;

import java.util.HashMap;
import java.util.Map;

abstract class ZbiorKsiazek {
    private final Map<Integer, Integer> ksiazkaDoIlosc = new HashMap<>(); // ksiazka id do ilosc

    public void dodajKsiazke(int ksiazkaId, int ilosc) {
        ksiazkaDoIlosc.put(ksiazkaId, ilosc);
    }

    public boolean czyJestDostepna(int ksiazkaId) {
        return ksiazkaDoIlosc.getOrDefault(ksiazkaId, 0) > 0;
    }

    protected Integer podajIlosc(int ksiazkaId) {
        return ksiazkaDoIlosc.get(ksiazkaId);
    }

    protected void uakutalnijIlosc(int ksiazkaId, int nowaIlosc) {
        ksiazkaDoIlosc.put(ksiazkaId, nowaIlosc);
    }
}
