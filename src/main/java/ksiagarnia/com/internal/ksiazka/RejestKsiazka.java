package ksiagarnia.com.internal.ksiazka;

import ksiagarnia.com.Odpowiedz;
import ksiagarnia.com.internal.db.model.GatunekKsiazki;
import ksiagarnia.com.internal.db.model.Ksiazka;
import ksiagarnia.com.internal.db.service.KsiazkaService;
import ksiagarnia.com.internal.user.Login;

import java.sql.Timestamp;
import java.util.List;

/**
 * Baza danych wszystkich istniejacych ksiazek
 */
public class RejestKsiazka {
    private final KsiazkaService ksiazkaService;
    private final Login login;

    public RejestKsiazka(Login login) {
        this.login = login;
        ksiazkaService = new KsiazkaService();
    }

    public List<GatunekKsiazki> podajGatunki() {
        return ksiazkaService.listaGatunkow();
    }

    public void dodajKsiazke(Ksiazka ksiazka) {
        ksiazkaService.zapiszKsiazke(ksiazka);
    }

    public Odpowiedz zarajestrujKsiazke(int id) {
        if (!login.isAdmin()) return Odpowiedz.ADMIN_LOGIN_POTRZEBNY;
        Ksiazka ksiazka = ksiazkaService.znajdzKsiazke(id);
        if (ksiazka == null) return Odpowiedz.REJESTRACJA_KSIAZKA_NIE_ISTNIEJE;
        ksiazka.dateRejestracji = new Timestamp(System.currentTimeMillis());
        ksiazkaService.zapiszKsiazke(ksiazka);
        return Odpowiedz.REJESTRACJA_OK;
    }

    public Odpowiedz wyrejestrujKsiazke(int id) {
        if (!login.isAdmin()) return Odpowiedz.ADMIN_LOGIN_POTRZEBNY;
        Ksiazka ksiazka = ksiazkaService.znajdzKsiazke(id);
        if (ksiazka == null) return Odpowiedz.REJESTRACJA_KSIAZKA_NIE_ISTNIEJE;
        ksiazka.dateRejestracji = null;
        ksiazkaService.zapiszKsiazke(ksiazka);
        return Odpowiedz.REJESTRACJA_OK;
    }

    public Ksiazka znajdzKsiazke(int ksiazkaId) {
        return ksiazkaService.znajdzKsiazke(ksiazkaId);
    }

    public List<Ksiazka> podajKsiazkiZarejestrowane() {
        return ksiazkaService.listaKsiazekZarejestrowanych();
    }
}
