package ksiagarnia.com.internal;

import org.junit.Assert;
import org.junit.Test;

public class KontoTest {

    @Test
    public void testSaldoRosnie() {
        Konto konto = new Konto(1, 0);
        konto.doladujSaldo(10);
        Assert.assertEquals(10, konto.podajSaldo(), 0);
    }
}