package ksiagarnia.com.internal.db.model;

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

    @Transient
    Konto konto = new Konto(0);

    public Uzytkownik() {
    }

    public Uzytkownik(String imie, String nazwisko, String loginName, String loginPassword, boolean admin) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.loginName = loginName;
        this.loginPassword = loginPassword;
        this.admin = admin;
    }

    public void setSaldo(double saldo) {
        konto.ustawSaldo(saldo);
    }

    public double getSaldo() {
        return konto.podajSaldo();
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
