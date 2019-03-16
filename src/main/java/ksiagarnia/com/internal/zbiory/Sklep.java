package ksiagarnia.com.internal.zbiory;

import ksiagarnia.com.Odpowiedz;
import ksiagarnia.com.internal.db.model.Ksiazka;
import ksiagarnia.com.internal.db.model.KsiazkaSklep;
import ksiagarnia.com.internal.db.model.KupionaKsiazka;
import ksiagarnia.com.internal.db.model.Uzytkownik;
import ksiagarnia.com.internal.db.service.SklepService;
import ksiagarnia.com.internal.ksiazka.RejestKsiazka;
import ksiagarnia.com.internal.user.Login;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Collections;

public class Sklep {
    private final Login login;
    private final SklepService sklepService;
    private final RejestKsiazka rejestKsiazka;

    public Sklep(Login login, SklepService sklepService, RejestKsiazka rejestKsiazka) {
        this.login = login;
        this.sklepService = sklepService;
        this.rejestKsiazka = rejestKsiazka;
    }

    public Odpowiedz dodajKsiazke(int ksiazkaId, int ilosc, double cena) {
        if (!login.isAdmin()) return Odpowiedz.ADMIN_LOGIN_POTRZEBNY;
        Ksiazka ksiazka = rejestKsiazka.znajdzKsiazke(ksiazkaId);
        if (ksiazka == null) return Odpowiedz.KSIAZKA_NIE_ISTNIEJE;
        sklepService.uaktualnijLubDodaj(new KsiazkaSklep(ksiazkaId, ilosc, cena));
        return Odpowiedz.OK;
    }

    public Odpowiedz kupKsiazke(int ksiazkaId) {
        Uzytkownik uzytkownik = login.getUzytkownik();
        if (uzytkownik == null) return Odpowiedz.LOGOWANIE_BLAD;
        if (rejestKsiazka.znajdzKsiazke(ksiazkaId) == null) return Odpowiedz.KSIAZKA_NIE_ISTNIEJE;
        KsiazkaSklep ksiazkaSklep = sklepService.znajdzKsiazke(ksiazkaId);
        if (ksiazkaSklep == null || ksiazkaSklep.ilosc <= 0) return Odpowiedz.SKLEP_KSIAZKA_NIE_JEST_DOSTEPNA;
        Odpowiedz odpowiedzZaplac = uzytkownik.zaplac(ksiazkaSklep.cenaKsiazki);
        if (odpowiedzZaplac != Odpowiedz.OK) {
            return odpowiedzZaplac;
        }
        ksiazkaSklep.ilosc -= 1;
        sklepService.uaktualnijLubDodaj(ksiazkaSklep);
        sklepService.dodajZakukpionaKsiazke(new KupionaKsiazka(uzytkownik.id, ksiazkaId, new Timestamp(System.currentTimeMillis())));

        return Odpowiedz.OK;
    }

    public Collection<KupionaKsiazka> listaMoichKupionych() {
        return listaKupionych(login.getIdZalogowanego());
    }

    public Collection<KupionaKsiazka> listaKupionych(int id) {
        if (id != login.getIdZalogowanego() && !login.isAdmin()) return Collections.emptySet();
        return sklepService.listaKupionych(id);
    }

    /**
     * Inwentarz
     */
    public Collection<KsiazkaSklep> listaKsiazek() {
        if (login.isAdmin()) return sklepService.listaWszystkich();
        return sklepService.listaDostepnych();
    }
}
