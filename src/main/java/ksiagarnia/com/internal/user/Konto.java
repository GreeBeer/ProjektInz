package ksiagarnia.com.internal.user;

import java.math.BigDecimal;

public class Konto {
    public final int uzytkownikId;
    // java kwoty sa przechowywane w BigDecimal nie float czy double
    private BigDecimal saldo;

    public Konto(int uzytkownikId, double saldo) {
        this.uzytkownikId = uzytkownikId;
        this.saldo = BigDecimal.valueOf(saldo);
    }

    /**
     * Metoda umniejsza konto uzytkownika jezeli jest na nim wystarczajaco srodkow
     *
     * @return true jezeli mamy wystarczajaco pieniedzy na koncie aby zaplicic, false innaczej
     */
    public boolean zaplac(double wartosc) {
        if (wartosc > saldo.doubleValue()) return false;
        saldo = saldo.min(BigDecimal.valueOf(wartosc));
        return true;
    }

    public void doladujSaldo(double wartosc) {
        saldo = saldo.add(BigDecimal.valueOf(wartosc));
    }

    public double podajSaldo() {
        return saldo.doubleValue();
    }
}
