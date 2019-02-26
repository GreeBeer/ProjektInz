package ksiagarnia.com.internal;

import java.util.Set;

public class Uzytkownik {
    public final int id;
    public final String imie;
    public final String nazwisko;
    public final Set<Ksiazka> ksiazkiZakupione;

    public Uzytkownik(int id, String imie, String nazwisko, Set<Ksiazka> ksiazkiZakupione) {
        this.id = id;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.ksiazkiZakupione = ksiazkiZakupione;
    }

    @Override
    public String toString() {
        return "Uzytkownik{" +
                "id=" + id +
                ", imie='" + imie + '\'' +
                ", nazwisko='" + nazwisko + '\'' +
                ", ksiazkiZakupione=" + ksiazkiZakupione +
                '}';
    }
}
