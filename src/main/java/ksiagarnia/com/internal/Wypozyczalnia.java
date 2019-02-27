package ksiagarnia.com.internal;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class Wypozyczalnia extends ZbiorKsiazek {
    private final Map<Integer, Set<Integer>> uzytkownikDoKsiazki = new HashMap<>(); // uzytkownikId do Set ksiazkaId

    public Set<Integer> podajListeWypozyczonych(int uzytkownikId) {
        return uzytkownikDoKsiazki.getOrDefault(uzytkownikId, null);
    }

    /**
     * Wypozycza ksiazke dla uzytkownika, zalozenie jest takie ze nie da sie wypozyczyc tej samej ksiazki wiecej niz raz
     */
    public boolean wypozycz(int ksiazkaId, int uzytkownikId) {
        if (!czyJestDostepna(ksiazkaId)) return false;
        Set<Integer> wypozyczone = uzytkownikDoKsiazki.get(uzytkownikId);
        if (wypozyczone == null) {
            // tworzymy nowa liste wyporzyczonych ksiazek dla uzytkownika
            wypozyczone = new HashSet<>();
            uzytkownikDoKsiazki.put(uzytkownikId, wypozyczone);
        }
        // jezeli add zwroci true to znaczy ze dana ksiakza nie znajdowala sie w kolekcji uzytkownika
        if(!wypozyczone.add(ksiazkaId)){
            return false;
        }
        // uaktualniamy stan naszych ksiazek w wypozyczalni
        Integer ilosc = podajIloscDostepnych(ksiazkaId);
        if (ilosc == null) throw new IllegalStateException("Ksiazka nie istnieje w wypozyczalni");
        uakutalnijIlosc(ksiazkaId, ilosc - 1);
        return true;
    }

    public void oddaj(int ksiazkaId, int uzytkownikId) {
        Set<Integer> wypozyczone = uzytkownikDoKsiazki.get(uzytkownikId);
        // gdy oddajemy ksiazke ktorej nie wypozyczalismy lub nie wypozyczylismy nigdy zadnej ksiazki (null)
        if (wypozyczone == null || !wypozyczone.remove(ksiazkaId))
            throw new IllegalStateException("Nigdy nie wypozyczyles tej ksiazki");
        // uaktualniamy stan naszych ksiazek w wypozyczalni
        Integer ilosc = podajIloscDostepnych(ksiazkaId);
        if (ilosc == null) throw new IllegalStateException("Ksiazka nie istnieje w wypozyczalni");
        uakutalnijIlosc(ksiazkaId, ilosc + 1);
    }
}
