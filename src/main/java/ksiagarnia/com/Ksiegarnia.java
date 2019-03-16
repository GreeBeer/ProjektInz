package ksiagarnia.com;

import ksiagarnia.com.internal.DateUtil;
import ksiagarnia.com.internal.db.model.*;
import ksiagarnia.com.internal.db.service.KsiazkaService;
import ksiagarnia.com.internal.db.service.SklepService;
import ksiagarnia.com.internal.db.service.UzytkownikService;
import ksiagarnia.com.internal.ksiazka.RejestKsiazka;
import ksiagarnia.com.internal.user.Login;
import ksiagarnia.com.internal.user.Uzytkownicy;
import ksiagarnia.com.internal.zbiory2.Sklep;

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
    private final Sklep sklep;

    public Ksiegarnia() {
        login = new Login();
        rejestKsiazka = new RejestKsiazka(login, new KsiazkaService());
        uzytkownicy = new Uzytkownicy(login, new UzytkownikService());
        sklep = new Sklep(login, new SklepService(), rejestKsiazka);
    }

    public Odpowiedz login(String userName, String password) {
        return login.zaloguj(userName, password);
    }

    public void wyloguj() {
        login.wyloguj();
    }

    public double podajSaldo() {
        if (login.isLoggedIn()) return login.getUzytkownik().konto.podajSaldo();
        return 0;
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

    public Odpowiedz dodajKsiazkeDoSklepu(int id, int ilosc, double cena) {
        return sklep.dodajKsiazke(id, ilosc, cena);
    }

    public Odpowiedz dodajKsiazkeDoWypozyczalni(int id, int ilosc, double kosztWypozyczenia) {
//        return .dodajKsiazke(id, ilosc, kosztWypozyczenia);
        throw new IllegalStateException();
    }

    public Odpowiedz dodajUzytkownika(String imie, String nazwisko, String loginName, String loginPassword) {
        return uzytkownicy.dodajUzytkownika(new Uzytkownik(imie, nazwisko, loginName, loginPassword, false));
    }

    public Collection<Uzytkownik> podajUzytkownikow() {
        return uzytkownicy.podajUzytkownikow();
    }

    public Odpowiedz kupKsiazke(int ksiazkaId) {
        return sklep.kupKsiazke(ksiazkaId);
    }

    public Collection<KupionaKsiazka> podajKupioneKsiazki(int userId) {
        return sklep.listaKupionych(userId);
    }

    public Collection<KupionaKsiazka> podajMojeKupioneKsiazki() {
        return sklep.listaMoichKupionych();
    }

    public Collection<Ksiazka> podajMojeWypozyczoneKsiazki() {
        return null;
    }

    public Collection<KsiazkaSklep> wysieltKsiazkiSklep() {
        return sklep.listaKsiazek();
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
