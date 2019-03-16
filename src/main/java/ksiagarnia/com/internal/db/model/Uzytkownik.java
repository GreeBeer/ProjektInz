package ksiagarnia.com.internal.db.model;

import ksiagarnia.com.Odpowiedz;
import ksiagarnia.com.internal.user.Konto;

import javax.persistence.*;

@Entity
public class Uzytkownik {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    public String imie;
    public String nazwisko;
    public String loginName;
    public String loginPassword;
    public boolean admin;
    private double saldo; // musi istniec tylko dla hibernate
    @Transient
    private boolean saldoZainiciowane;
    @Transient
    private Konto konto = new Konto(0);

    public Uzytkownik() {
    }

    public Uzytkownik(String imie, String nazwisko, String loginName, String loginPassword, boolean admin) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.loginName = loginName;
        this.loginPassword = loginPassword;
        this.admin = admin;
    }

    public void doladujSaldo(double saldo) {
        if (!saldoZainiciowane) {
            saldoZainiciowane = true;
            konto.ustawSaldo(saldo);
        }
        konto.doladujSaldo(saldo);
        this.saldo = konto.podajSaldo();
    }

    public double podajSaldo() {
        if (!saldoZainiciowane) {
            saldoZainiciowane = true;
            konto.ustawSaldo(saldo);
        }
        return konto.podajSaldo();
    }

    public Odpowiedz zaplac(double wartosc) {
        if (!saldoZainiciowane) {
            saldoZainiciowane = true;
            konto.ustawSaldo(saldo);
        }
        return konto.zaplac(wartosc);
    }

    @Override
    public String toString() {
        return "id: " + id +
                ", imie: " + imie +
                ", nazwisko: " + nazwisko +
                ", loginName: " + loginName +
                ", loginPassword: ***" +
                ", saldo: " + konto.podajSaldo();
    }
}
