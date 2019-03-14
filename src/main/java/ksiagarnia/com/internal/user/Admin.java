package ksiagarnia.com.internal.user;

import ksiagarnia.com.internal.ksiazka.Ksiazka;

import java.util.Set;

public class Admin extends Uzytkownik {

    public Admin(int id, String imie, String nazwisko, Set<Ksiazka> ksiazkiZakupione) {
        super(id, imie, nazwisko, ksiazkiZakupione);
    }
}
