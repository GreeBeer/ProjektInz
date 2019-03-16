package ksiagarnia.com.internal.user;

import ksiagarnia.com.Odpowiedz;
import ksiagarnia.com.internal.db.model.Uzytkownik;
import ksiagarnia.com.internal.db.service.UzytkownikService;

public class Login {

    private Uzytkownik uzytkownik;

    public Odpowiedz zaloguj(String userName, String haslo) {
        uzytkownik = new UzytkownikService().znajdzUzytkownika(userName);
        boolean zalogowany = uzytkownik != null && uzytkownik.loginPassword.equals(haslo);
        if (zalogowany) {
            return uzytkownik.admin ? Odpowiedz.LOGOWANIE_ADMIN_OK : Odpowiedz.LOGOWANIE_OK;
        }
        return Odpowiedz.LOGOWANIE_BLAD;
    }

    public int getIdZalogowanego() {
        return uzytkownik == null ? -1 : uzytkownik.id;
    }

    public boolean isAdmin() {
        return uzytkownik != null && uzytkownik.admin;
    }

    public boolean isLoggedIn() {
        return uzytkownik != null;
    }

    public void wyloguj() {
        uzytkownik = null;
    }

    public Uzytkownik getUzytkownik() {
        return uzytkownik;
    }
}
