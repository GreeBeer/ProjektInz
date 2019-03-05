package ksiagarnia.com.internal.ksiazka;

import java.util.*;

public class RejestKsiazka {
    private final Map<Integer, Ksiazka> ksiazki = new HashMap<>();
    private final Map<GatunekKsiazki, Set<Ksiazka>> ksiazkiDoGatunek = new HashMap<>();

    public Ksiazka znajdzKsiazke(int ksiazkaId) {
        return ksiazki.get(ksiazkaId);
    }

    public void dodajKsiazke(Ksiazka ksiazka) {
        ksiazki.put(ksiazka.id, ksiazka);
        Set<Ksiazka> ksiazkiDlaGatunku = ksiazkiDoGatunek.get(ksiazka.gatunekKsiazki);
        if (ksiazkiDlaGatunku == null) {
            ksiazkiDlaGatunku = new HashSet<>();
            ksiazkiDoGatunek.put(ksiazka.gatunekKsiazki, ksiazkiDlaGatunku);
        }
        ksiazkiDlaGatunku.add(ksiazka);
    }

    public Set<Ksiazka> podajKsiazkiPoGatunku(GatunekKsiazki gatunekKsiazki) {
        return ksiazkiDoGatunek.getOrDefault(gatunekKsiazki, Collections.emptySet());
    }

    public Collection<Ksiazka> podajKsiazki() {
        return ksiazki.values();
    }

    public Collection<Ksiazka> podajKsiazki(Collection<Integer> ids) {
        Set<Ksiazka> ksiazkaSet = new HashSet<>();
        for (Ksiazka ksiazka : ksiazki.values()) {
            if (ids.contains(ksiazka.id)) {
                ksiazkaSet.add(ksiazka);
            }
        }
        return ksiazkaSet;
    }
}
