package ksiagarnia.com.internal.zbiory;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.fail;

public class SklepTest {

    @Test
    public void kupKsiazkeKtorejNieMa() {
        Sklep sklep = new Sklep();
        try {
            sklep.kup(1);
            fail("Powinien byc blad");
        } catch (Exception ignore) {

        }
    }

    @Test
    public void kupKsiazkeKtorejOK() {
        Sklep sklep = new Sklep();
        sklep.dodajKsiazke(0, 10);
        sklep.kup(0);
    }

    @Test
    public void kupKsiazkeKtorejIlosc9() {
        Sklep sklep = new Sklep();
        sklep.dodajKsiazke(0, 10);
        sklep.kup(0);
        Assert.assertEquals(9, sklep.podajIlosc(0).intValue());
    }
}