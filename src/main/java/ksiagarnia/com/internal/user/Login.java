package ksiagarnia.com.internal.user;

import ksiagarnia.com.Odpowiedz;
import ksiagarnia.com.internal.db.model.Uzytkownik;
import ksiagarnia.com.internal.db.service.UzytkownikService;

public class Login {

    private boolean isAdmin;
    private int idZalogowanego;

    public Odpowiedz zaloguj(String userName, String haslo) {
        Uzytkownik uzytkownik = new UzytkownikService().znajdzUzytkownika(userName);
        boolean zalogowany = uzytkownik != null && uzytkownik.loginPassword.equals(haslo);
        if (zalogowany) {
            idZalogowanego = uzytkownik.id;
            isAdmin = uzytkownik.admin;
            return isAdmin ? Odpowiedz.LOGOWANIE_ADMIN_OK : Odpowiedz.LOGOWANIE_OK;
        }
        idZalogowanego = -1;
        return Odpowiedz.LOGOWANIE_BLAD;
    }

    public int getIdZalogowanego() {
        return idZalogowanego;
    }

    public boolean isAdmin() {
        return isAdmin;
    }
}
