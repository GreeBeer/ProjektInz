package ksiagarnia.com.internal.user;

import ksiagarnia.com.internal.db.model.Uzytkownik;
import ksiagarnia.com.internal.db.service.UzytkownikService;

public class Login {

    private boolean isAdmin;
    private int idZalogowanego;

    public boolean zaloguj(String userName, String haslo) {
        Uzytkownik uzytkownik = new UzytkownikService().znajdzUzytkownika(userName);
        boolean zalogowany = uzytkownik != null && uzytkownik.loginPassword.equals(haslo);
        if (zalogowany) {
            idZalogowanego = uzytkownik.id;
            isAdmin = uzytkownik.admin;
        } else idZalogowanego = -1;
        return zalogowany;
    }

    public int getIdZalogowanego() {
        return idZalogowanego;
    }

    public boolean isAdmin() {
        return isAdmin;
    }
}
