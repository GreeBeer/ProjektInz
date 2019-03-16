package ksiagarnia.com.internal.user;

import ksiagarnia.com.Odpowiedz;
import ksiagarnia.com.internal.db.model.Uzytkownik;
import ksiagarnia.com.internal.db.service.UzytkownikService;

import java.util.Collection;

public class Uzytkownicy {
    private final Login login;
    private final UzytkownikService uzytkownikService;

    public Uzytkownicy(Login login, UzytkownikService uzytkownikService) {
        this.login = login;
        this.uzytkownikService = uzytkownikService;
    }

    public Collection<Uzytkownik> podajUzytkownikow() {
        return uzytkownikService.listaUzytkownikow();
    }

    public Odpowiedz dodajUzytkownika(Uzytkownik uzytkownik) {
        if (!login.isAdmin()) return Odpowiedz.ADMIN_LOGIN_POTRZEBNY;
        uzytkownikService.dodajUzytkownika(uzytkownik);
        return Odpowiedz.OK;
    }

    public void doladuj(double saldo) {
        if (!login.isLoggedIn()) return;
        login.getUzytkownik().doladujSaldo(saldo);
        uzytkownikService.uaktualnijSaldo(login.getUzytkownik());
    }
}
