package ksiagarnia.com.internal.user;

import ksiagarnia.com.internal.db.model.Uzytkownik;
import ksiagarnia.com.internal.db.service.UzytkownikService;

import java.util.Collection;

public class Uzytkownicy {
    private final UzytkownikService uzytkownikService;

    public Uzytkownicy(UzytkownikService uzytkownikService) {
        this.uzytkownikService = uzytkownikService;
    }

    public Collection<Uzytkownik> podajUzytkownikow() {
        return uzytkownikService.listaUzytkownikow();
    }

    public void dodajUzytkownika(Uzytkownik uzytkownik) {
        uzytkownikService.dodajUzytkownika(uzytkownik);
    }
}
