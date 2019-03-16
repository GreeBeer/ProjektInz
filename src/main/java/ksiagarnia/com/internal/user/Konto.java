package ksiagarnia.com.internal.user;

import ksiagarnia.com.Odpowiedz;

import java.math.BigDecimal;

public class Konto {
    // java kwoty sa przechowywane w BigDecimal nie float czy double
    private BigDecimal saldo;

    public Konto(double saldo) {
        this.saldo = BigDecimal.valueOf(saldo);
    }

    /**
     * Metoda umniejsza konto uzytkownika jezeli jest na nim wystarczajaco srodkow
     *
     * @return true jezeli mamy wystarczajaco pieniedzy na koncie aby zaplicic, false innaczej
     */
    public Odpowiedz zaplac(double wartosc) {
        if (wartosc > saldo.doubleValue()) return Odpowiedz.UZYTKOWNIK_NIE_MA_KASY;
        saldo = saldo.subtract(BigDecimal.valueOf(wartosc));
        return Odpowiedz.OK;
    }

    public void doladujSaldo(double wartosc) {
        saldo = saldo.add(BigDecimal.valueOf(wartosc));
    }

    public void ustawSaldo(double wartosc) {
        saldo = BigDecimal.valueOf(wartosc);
    }

    public double podajSaldo() {
        return saldo.doubleValue();
    }
}
