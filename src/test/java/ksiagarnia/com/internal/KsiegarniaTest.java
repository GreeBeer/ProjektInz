package ksiagarnia.com.internal;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;

public class KsiegarniaTest {

    @Test
    public void kupKsiazkeUzytkownikNieIstnieje() {
        Ksiegarnia ksiegarnia = new Ksiegarnia();
        Assert.assertEquals(Odpowiedz.UZYTKOWNIK_NIE_ISTNIEJE, ksiegarnia.kup(0, 0));
    }

    @Test
    public void kupKsiazkeKsiazkaNieIstnieje() {
        Ksiegarnia ksiegarnia = new Ksiegarnia();
        ksiegarnia.rejest(new Uzytkownik(0, "", "", new HashSet<>()), 0);
        Assert.assertEquals(Odpowiedz.KSIAZKA_NIE_ISTNIEJE, ksiegarnia.kup(0, 0));
    }

}