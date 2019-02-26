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

    public boolean wypozycz(int ksiazkaId, int uzytkownikId) {
        if (!czyJestDostepna(ksiazkaId)) return false;
        Set<Integer> wypozyczone = uzytkownikDoKsiazki.get(uzytkownikId);
        if (wypozyczone == null) {
            wypozyczone = new HashSet<>();
            uzytkownikDoKsiazki.put(uzytkownikId, wypozyczone);
        }
        return wypozyczone.add(ksiazkaId);
    }

    public void oddaj(int ksiazkaId, int uzytkownikId) {
        Set<Integer> wypozyczone = uzytkownikDoKsiazki.get(uzytkownikId);
        if (wypozyczone == null || !wypozyczone.remove(ksiazkaId))
            throw new IllegalStateException("Nigdy nie wypozyczyles tej ksiazki");
        Integer ilosc = podajIlosc(ksiazkaId);
        if (ilosc == null) throw new IllegalStateException("Ksiazka nie istnieje w wypozyczalni");
        uakutalnijIlosc(ksiazkaId, ilosc + 1);
    }
}
