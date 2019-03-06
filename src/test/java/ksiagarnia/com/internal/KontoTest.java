package ksiagarnia.com.internal;

import ksiagarnia.com.internal.user.Konto;
import org.junit.Assert;
import org.junit.Test;

public class KontoTest {

    @Test
    public void testSaldoRosnie() {
        Konto konto = new Konto(1, 0);
        konto.doladujSaldo(10);
        Assert.assertEquals(2, konto.podajSaldo(), 0);
    }
}