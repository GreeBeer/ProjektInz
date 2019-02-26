package ksiagarnia.com.internal;

import java.math.BigDecimal;

class Konto {
    public final int uzytkownikId;
    private BigDecimal saldo;

    public Konto(int uzytkownikId, double saldo) {
        this.uzytkownikId = uzytkownikId;
        this.saldo = BigDecimal.valueOf(saldo);
    }

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
