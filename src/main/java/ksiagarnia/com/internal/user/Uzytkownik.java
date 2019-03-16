package ksiagarnia.com.internal.user;


public class Uzytkownik {
    public final int id;
    public final String imie;
    public final String nazwisko;

    public Uzytkownik(int id, String imie, String nazwisko) {
        this.id = id;
        this.imie = imie;
        this.nazwisko = nazwisko;
    }

    @Override
    public String toString() {
        return "Uzytkownik{" +
                "id=" + id +
                ", imie='" + imie + '\'' +
                ", nazwisko='" + nazwisko + '\'' +
                '}';
    }
}
