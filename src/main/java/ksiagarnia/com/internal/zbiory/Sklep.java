package ksiagarnia.com.internal.zbiory;

public class Sklep extends ZbiorKsiazek {

    public void kup(int ksiazkaId) {
        Integer ilosc = podajIloscDostepnych(ksiazkaId);
        if (ilosc == null || ilosc == 0) throw new IllegalStateException("Ksiazka nie istnieje lub nie jest dostepna");
        uakutalnijIlosc(ksiazkaId, ilosc - 1);
    }
}
