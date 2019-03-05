package ksiagarnia.com.internal;

import ksiagarnia.com.internal.ksiazka.Ksiazka;
import ksiagarnia.com.internal.ksiazka.RejestKsiazka;
import ksiagarnia.com.internal.user.Konto;
import ksiagarnia.com.internal.user.Uzytkownik;
import ksiagarnia.com.internal.zbiory.Sklep;
import ksiagarnia.com.internal.zbiory.Wypozyczalnia;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Glowna logika naszej apki, Ksiegarnia jest odpowiedzialna za laczenie wszystkich innych klas.
 * <p>
 * Czyli ze swiata zewnetrznego zawsze komunikujemy sie poprzez ta klase.
 */
public class Ksiegarnia {
    private final RejestKsiazka rejestKsiazka;
    private final Map<Integer, Uzytkownik> uzytkownikMap;
    private final Map<Integer, Konto> kontaUzytkownik;
    private final Sklep sklep;
    private final Wypozyczalnia wypozyczalnia;

    public Ksiegarnia() {
        this.rejestKsiazka = new RejestKsiazka();
        this.uzytkownikMap = new HashMap<>();
        this.kontaUzytkownik = new HashMap<>();
        this.sklep = new Sklep();
        this.wypozyczalnia = new Wypozyczalnia();
    }

    public void rejest(Uzytkownik uzytkownik, double saldo) {
        uzytkownikMap.put(uzytkownik.id, uzytkownik);
        kontaUzytkownik.put(uzytkownik.id, new Konto(uzytkownik.id, saldo));
    }

    public void rejestKsiazka(Ksiazka ksiazka) {
        rejestKsiazka.dodajKsiazke(ksiazka);
    }

    public void rejestKsiazkaDoWypozyczalni(int id, int ilosc) {
        wypozyczalnia.dodajKsiazke(id, ilosc);
    }

    public void rejestKsiazkaDoSklep(int id, int ilosc) {
        sklep.dodajKsiazke(id, ilosc);
    }

    public Odpowiedz kup(int ksiazkaId, int uzytkowniId) {
        Uzytkownik uzytkownik = uzytkownikMap.get(uzytkowniId);
        if (uzytkownik == null) {
            return Odpowiedz.UZYTKOWNIK_NIE_ISTNIEJE;
        }
        Ksiazka ksiazka = rejestKsiazka.znajdzKsiazke(ksiazkaId);
        if (ksiazka == null) {
            return Odpowiedz.KSIAZKA_NIE_ISTNIEJE;
        }
        if (!sklep.czyJestDostepna(ksiazkaId)) {
            return Odpowiedz.SKLEP_KSIAZKA_NIE_JEST_DOSTEPNA;
        }
        Konto konto = kontaUzytkownik.get(uzytkowniId);
        if (!konto.zaplac(ksiazka.cenaNowej)) {
            return Odpowiedz.UZYTKOWNIK_NIE_MA_KASY;
        }
        sklep.kup(ksiazkaId);
        uzytkownik.ksiazkiZakupione.add(ksiazka);
        return Odpowiedz.OK;
    }

    public Odpowiedz wypozycz(int ksiazkaId, int uzytkowniId) {
        Uzytkownik uzytkownik = uzytkownikMap.get(uzytkowniId);
        if (uzytkownik == null) {
            return Odpowiedz.UZYTKOWNIK_NIE_ISTNIEJE;
        }
        Ksiazka ksiazka = rejestKsiazka.znajdzKsiazke(ksiazkaId);
        if (ksiazka == null) {
            return Odpowiedz.KSIAZKA_NIE_ISTNIEJE;
        }
        if (wypozyczalnia.czyJestDostepna(ksiazkaId)) {
            return Odpowiedz.WYPOZYCZALNIA_KSIAZKA_NIE_JEST_DOSTEPNA;
        }
        Konto konto = kontaUzytkownik.get(uzytkowniId);
        if (!konto.zaplac(ksiazka.kosztWyporzyczenia)) {
            return Odpowiedz.UZYTKOWNIK_NIE_MA_KASY;
        }
        wypozyczalnia.wypozycz(ksiazkaId, uzytkowniId);
        return Odpowiedz.OK;
    }

    public void oddaj(int ksiazkaId, int uzytkowniId) {

    }

    public Collection<Ksiazka> podajKsiazkiWypozyczone(int uzytkownikId) {
        Set<Integer> ksiazkiIdSet = wypozyczalnia.podajListeWypozyczonych(uzytkownikId);
        return rejestKsiazka.znajdzKsiazki(ksiazkiIdSet);
    }

    public Collection<Ksiazka> podajKsiazkiKupione(int uzytkownikId) {
        return uzytkownikMap.get(uzytkownikId).ksiazkiZakupione;
    }

    public Collection<Uzytkownik> podajUzytkownikow() {
        return uzytkownikMap.values();
    }

    public Collection<Ksiazka> podajKsiazki() {
        return rejestKsiazka.podajKsiazki();
    }
}
