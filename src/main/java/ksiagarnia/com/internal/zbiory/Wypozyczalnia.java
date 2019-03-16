package ksiagarnia.com.internal.zbiory;

import ksiagarnia.com.Odpowiedz;
import ksiagarnia.com.internal.db.model.Ksiazka;
import ksiagarnia.com.internal.db.model.KsiazkaWypozyczalnia;
import ksiagarnia.com.internal.db.model.Uzytkownik;
import ksiagarnia.com.internal.db.model.WypozyczonaKsiazka;
import ksiagarnia.com.internal.db.service.WypozyczalniaService;
import ksiagarnia.com.internal.ksiazka.RejestKsiazka;
import ksiagarnia.com.internal.user.Login;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Collections;

public class Wypozyczalnia {
    private final Login login;
    private final WypozyczalniaService wypozyczalniaService;
    private final RejestKsiazka rejestKsiazka;

    public Wypozyczalnia(Login login, WypozyczalniaService wypozyczalniaService, RejestKsiazka rejestKsiazka) {
        this.login = login;
        this.wypozyczalniaService = wypozyczalniaService;
        this.rejestKsiazka = rejestKsiazka;
    }

    public Odpowiedz wypozycz(int ksiazkaId) {
        Uzytkownik uzytkownik = login.getUzytkownik();
        if (uzytkownik == null) return Odpowiedz.LOGOWANIE_BLAD;
        if (rejestKsiazka.znajdzKsiazke(ksiazkaId) == null) return Odpowiedz.KSIAZKA_NIE_ISTNIEJE;
        KsiazkaWypozyczalnia ksiazkaWypozyczalnia = wypozyczalniaService.znajdzKsiazke(ksiazkaId);
        if (ksiazkaWypozyczalnia == null || ksiazkaWypozyczalnia.ilosc <= 0)
            return Odpowiedz.SKLEP_KSIAZKA_NIE_JEST_DOSTEPNA;
        Odpowiedz odpowiedzZaplac = uzytkownik.zaplac(ksiazkaWypozyczalnia.kosztWypozyczenia);
        if (odpowiedzZaplac != Odpowiedz.OK) {
            return odpowiedzZaplac;
        }
        ksiazkaWypozyczalnia.ilosc -= 1;
        wypozyczalniaService.uaktualnijLubDodaj(ksiazkaWypozyczalnia);
        wypozyczalniaService.uaktualnijLubDodaj(new WypozyczonaKsiazka(uzytkownik.id, ksiazkaId, new Timestamp(System.currentTimeMillis()), null));
        return Odpowiedz.OK;
    }

    public Odpowiedz oddaj(int ksiazkaId) {
        Uzytkownik uzytkownik = login.getUzytkownik();
        if (uzytkownik == null) return Odpowiedz.LOGOWANIE_BLAD;
        if (rejestKsiazka.znajdzKsiazke(ksiazkaId) == null) return Odpowiedz.KSIAZKA_NIE_ISTNIEJE;
        WypozyczonaKsiazka ksiazkaWypozyczona = wypozyczalniaService.znajdzWypozyczonaNieOddana(uzytkownik.id, ksiazkaId);
        if (ksiazkaWypozyczona == null) return Odpowiedz.NIGDY_NIE_WYPOZYCZYL_KSIAZKI;
        if (ksiazkaWypozyczona.dataOddania != null) return Odpowiedz.KSIAZKA_JEST_JUZ_ODDANA;
        KsiazkaWypozyczalnia ksiazkaWypozyczalnia = wypozyczalniaService.znajdzKsiazke(ksiazkaId);
        if (ksiazkaWypozyczalnia == null) return Odpowiedz.WYPOZYCZALNIA_KSIAZKA_NIE_JEST_DOSTEPNA;
        ksiazkaWypozyczalnia.ilosc += 1;
        ksiazkaWypozyczona.dataOddania = new Timestamp(System.currentTimeMillis());
        wypozyczalniaService.uaktualnijLubDodaj(ksiazkaWypozyczalnia);
        wypozyczalniaService.uaktualnijLubDodaj(ksiazkaWypozyczona);
        return Odpowiedz.OK;
    }

    public Odpowiedz dodajKsiazke(int ksiazkaId, int ilosc, double kosztWypozyczenia) {
        if (!login.isAdmin()) return Odpowiedz.ADMIN_LOGIN_POTRZEBNY;
        Ksiazka ksiazka = rejestKsiazka.znajdzKsiazke(ksiazkaId);
        if (ksiazka == null) return Odpowiedz.KSIAZKA_NIE_ISTNIEJE;
        wypozyczalniaService.uaktualnijLubDodaj(new KsiazkaWypozyczalnia(ksiazkaId, ilosc, kosztWypozyczenia));
        return Odpowiedz.OK;
    }


    public Collection<WypozyczonaKsiazka> listaMoichWypozyczonych() {
        return listaWypozyczonych(login.getIdZalogowanego());
    }

    public Collection<WypozyczonaKsiazka> listaWypozyczonych(int id) {
        if (id != login.getIdZalogowanego() && !login.isAdmin()) return Collections.emptySet();
        return wypozyczalniaService.listaWypozyczonych(id);
    }

    /**
     * Inwentarz
     */
    public Collection<KsiazkaWypozyczalnia> listaKsiazek() {
        if (login.isAdmin()) return wypozyczalniaService.listaWszystkich();
        return wypozyczalniaService.listaDostepnych();
    }

}
