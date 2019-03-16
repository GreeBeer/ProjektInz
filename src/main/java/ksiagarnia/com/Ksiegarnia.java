package ksiagarnia.com;

import ksiagarnia.com.internal.DateUtil;
import ksiagarnia.com.internal.db.model.*;
import ksiagarnia.com.internal.db.service.KsiazkaService;
import ksiagarnia.com.internal.db.service.SklepService;
import ksiagarnia.com.internal.db.service.UzytkownikService;
import ksiagarnia.com.internal.db.service.WypozyczalniaService;
import ksiagarnia.com.internal.ksiazka.RejestKsiazka;
import ksiagarnia.com.internal.user.Login;
import ksiagarnia.com.internal.user.Uzytkownicy;
import ksiagarnia.com.internal.zbiory.Sklep;
import ksiagarnia.com.internal.zbiory.Wypozyczalnia;

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
    private final Wypozyczalnia wypozyczalnia;

    public Ksiegarnia() {
        login = new Login();
        rejestKsiazka = new RejestKsiazka(login, new KsiazkaService());
        uzytkownicy = new Uzytkownicy(login, new UzytkownikService());
        sklep = new Sklep(login, new SklepService(), rejestKsiazka);
        wypozyczalnia = new Wypozyczalnia(login, new WypozyczalniaService(), rejestKsiazka);
    }

    public Odpowiedz login(String userName, String password) {
        return login.zaloguj(userName, password);
    }

    public void wyloguj() {
        login.wyloguj();
    }

    public double podajSaldo() {
        if (login.isLoggedIn()) return login.getUzytkownik().podajSaldo();
        return 0;
    }

    public void doladujSaldo(double topup) {
        uzytkownicy.doladuj(topup);
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
        return wypozyczalnia.dodajKsiazke(id, ilosc, kosztWypozyczenia);
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

    public Collection<KsiazkaSklep> podajKsiazkiSklep() {
        return sklep.listaKsiazek();
    }

    public Odpowiedz wypozyczKsiazke(int ksiazkaId) {
        return wypozyczalnia.wypozycz(ksiazkaId);
    }

    public Odpowiedz oddajKsiazke(int ksiazkaId) {
        return wypozyczalnia.oddaj(ksiazkaId);
    }

    public Collection<WypozyczonaKsiazka> podajWypozyczoneKsiazki(int userId) {
        return wypozyczalnia.listaWypozyczonych(userId);
    }

    public Collection<WypozyczonaKsiazka> podajMojeWypozyczoneKsiazki() {
        return wypozyczalnia.listaMoichWypozyczonych();
    }

    public Collection<KsiazkaWypozyczalnia> podajKsiazkiWypozyczalnia() {
        return wypozyczalnia.listaKsiazek();
    }
}
