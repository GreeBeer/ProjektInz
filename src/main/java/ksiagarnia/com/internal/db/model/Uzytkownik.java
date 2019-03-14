package ksiagarnia.com.internal.db.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Uzytkownik {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    public String imie;
    public String nazwisko;
    public String loginName;
    public String loginPassword;
    public double saldo;
    public boolean admin;
}
