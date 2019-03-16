package ksiagarnia.com;

import ksiagarnia.com.internal.DateUtil;
import ksiagarnia.com.internal.db.model.GatunekKsiazki;
import ksiagarnia.com.internal.db.model.Ksiazka;
import ksiagarnia.com.internal.db.model.Uzytkownik;
import ksiagarnia.com.internal.db.service.KsiazkaService;
import ksiagarnia.com.internal.db.service.UzytkownikService;
import ksiagarnia.com.internal.ksiazka.RejestKsiazka;
import ksiagarnia.com.internal.user.Login;
import ksiagarnia.com.internal.user.Uzytkownicy;

import java.util.Collection;
import java.util.List;

/**
 * Fasada do innych czesci naszej aplikacji
 * <p>
 * Czyli ze swiata zewnetrznego zawsze komunikujemy sie poprzez ta klase.
 */
public class Ksiegarnia {
    private final Login login;
    private final RejestKsiazka rejestKsiazka;
    private final Uzytkownicy uzytkownicy;

    public Ksiegarnia() {
        login = new Login();
        rejestKsiazka = new RejestKsiazka(login, new KsiazkaService());
        uzytkownicy = new Uzytkownicy(new UzytkownikService());
    }

    public Odpowiedz login(String userName, String password) {
        return login.zaloguj(userName, password);
    }

    public int podajIdZalogowanegoUzytkownika() {
        return login.getIdZalogowanego();
    }

    public Collection<Ksiazka> podajDostepneKsiazki() {
        return rejestKsiazka.podajKsiazkiZarejestrowane();
    }

    public List<GatunekKsiazki> podajGatunki() {
        return rejestKsiazka.podajGatunki();
    }

    public void dodajKsiazke(int idGatunek, String tytul, String autor, int rokWydania, String isbn) {
        rejestKsiazka.dodajKsiazke(new Ksiazka(tytul, autor, DateUtil.rok(rokWydania), null, isbn, idGatunek));
    }

    public Odpowiedz zarejestrujKsiazke(int id) {
        return rejestKsiazka.zarajestrujKsiazke(id);
    }

    public Odpowiedz wyrejestrujKsiazke(int id) {
        return rejestKsiazka.wyrejestrujKsiazke(id);
    }

    public void dodajUzytkownika(String imie, String nazwisko, String loginName, String loginPassword) {
        uzytkownicy.dodajUzytkownika(new Uzytkownik(imie, nazwisko, loginName, loginPassword, false));
    }

    public Collection<Uzytkownik> podajUzytkownikow() {
        return uzytkownicy.podajUzytkownikow();
    }

//    public void rejestr(Uzytkownik uzytkownik, double saldo) {
//        uzytkownikMap.put(uzytkownik.id, uzytkownik);
//        kontaUzytkownik.put(uzytkownik.id, new Konto(uzytkownik.id, saldo));
//    }


//    public void rejestrKsiazkaDoWypozyczalni(int id, int ilosc) {
//        wypozyczalnia.dodajKsiazke(id, ilosc);
//    }
//
//    public void rejestrKsiazkaDoSklep(int id, int ilosc) {
//        sklep.dodajKsiazke(id, ilosc);
//    }
//
//    public Odpowiedz kup(int ksiazkaId, int uzytkowniId) {
////        Uzytkownik uzytkownik = uzytkownikMap.get(uzytkowniId);
////        if (uzytkownik == null) {
////            return Odpowiedz.UZYTKOWNIK_NIE_ISTNIEJE;
////        }
////        Ksiazka ksiazka = rejestKsiazka.znajdzKsiazke(ksiazkaId);
////        if (ksiazka == null) {
////            return Odpowiedz.KSIAZKA_NIE_ISTNIEJE;
////        }
////        if (!sklep.czyJestDostepna(ksiazkaId)) {
////            return Odpowiedz.SKLEP_KSIAZKA_NIE_JEST_DOSTEPNA;
////        }
////        Konto konto = kontaUzytkownik.get(uzytkowniId);
////        if (!konto.zaplac(ksiazka.cenaNowej)) {
////            return Odpowiedz.UZYTKOWNIK_NIE_MA_KASY;
////        }
////        sklep.kup(ksiazkaId);
////        uzytkownik.ksiazkiZakupione.add(ksiazka);
//        return Odpowiedz.OK;
//    }
//
//    public Odpowiedz wypozycz(int ksiazkaId, int uzytkowniId) {
////        Uzytkownik uzytkownik = uzytkownikMap.get(uzytkowniId);
////        if (uzytkownik == null) {
////            return Odpowiedz.UZYTKOWNIK_NIE_ISTNIEJE;
////        }
////        Ksiazka ksiazka = rejestKsiazka.znajdzKsiazke(ksiazkaId);
////        if (ksiazka == null) {
////            return Odpowiedz.KSIAZKA_NIE_ISTNIEJE;
////        }
////        if (wypozyczalnia.czyJestDostepna(ksiazkaId)) {
////            return Odpowiedz.WYPOZYCZALNIA_KSIAZKA_NIE_JEST_DOSTEPNA;
////        }
////        Konto konto = kontaUzytkownik.get(uzytkowniId);
////        if (!konto.zaplac(ksiazka.kosztWyporzyczenia)) {
////            return Odpowiedz.UZYTKOWNIK_NIE_MA_KASY;
////        }
////        wypozyczalnia.wypozycz(ksiazkaId, uzytkowniId);
//        return Odpowiedz.OK;
//    }
//
//    public void oddaj(int ksiazkaId, int uzytkowniId) {
//
//    }
//
//    public Collection<Ksiazka> podajKsiazkiWypozyczone(int uzytkownikId) {
//        Set<Integer> ksiazkiIdSet = wypozyczalnia.podajListeWypozyczonych(uzytkownikId);
//        return rejestKsiazka.znajdzKsiazki(ksiazkiIdSet);
//    }
//
//    public Collection<Ksiazka> podajKsiazkiKupione(int uzytkownikId) {
////        return uzytkownikMap.get(uzytkownikId).ksiazkiZakupione;
//        return Collections.emptySet();
//    }
//
//    public Collection<Uzytkownik> podajUzytkownikow() {
//        return uzytkownikMap.values();
//    }
//
}
