package ksiagarnia.com.internal.ksiazka;

import ksiagarnia.com.internal.db.model.GatunekKsiazki;
import ksiagarnia.com.internal.db.service.KsiazkaService;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.TimeZone;

/**
 * Baza danych wszystkich istniejacych ksiazek
 */
public class RejestKsiazka {
    private final ArrayList<Ksiazka> ksiazkiList = new ArrayList<>(); // ksiazka id -> Ksiazka
    private final KsiazkaService ksiazkaService;

    public RejestKsiazka() {
        ksiazkaService = new KsiazkaService();
        ksiazkiList.addAll(transform(ksiazkaService.listaKsiazekZarejestrowanych()));
    }

    public void dodajKsiazke(Ksiazka ksiazka) {
        ksiazkaService.dodajKsiazke(
                nowaKsiazka(ksiazka, ksiazkaService.listaGatunkow().get(0))
        );
        ksiazkiList.add(ksiazka);
    }

    public Ksiazka znajdzKsiazke(int ksiazkaId) {
        for (Ksiazka ksiazka : ksiazkiList) {
            if (ksiazka.id == ksiazkaId) {
                return ksiazka;
            }
        }
        return null;
    }

    public ArrayList<Ksiazka> znajdzKsiazki(Collection<Integer> ids) {
        ArrayList<Ksiazka> list = new ArrayList<>();
        if (ids == null || ids.isEmpty()) return list;
        for (Ksiazka ksiazka : ksiazkiList) {
            if (ids.contains(ksiazka.id)) {
                list.add(ksiazka);
            }
        }
        return list;
    }

    public ArrayList<Ksiazka> podajKsiazki() {
        return ksiazkiList;
    }

    private static List<Ksiazka> transform(List<ksiagarnia.com.internal.db.model.Ksiazka> dbKsiazki) {
        List<Ksiazka> out = new ArrayList<>();
        for (ksiagarnia.com.internal.db.model.Ksiazka dbKsiazka : dbKsiazki) {
            out.add(new Ksiazka(
                    dbKsiazka.id,
                    dbKsiazka.tytul,
                    LocalDateTime.ofInstant(
                            Instant.ofEpochMilli(dbKsiazka.rokWydania.getTime()),
                            TimeZone.getDefault().toZoneId()
                    ).getYear(),
                    dbKsiazka.isbn,
                    dbKsiazka.idGatunek.nazwa,
                    0.0,
                    0.0
            ));
        }
        return out;
    }

    private static ksiagarnia.com.internal.db.model.Ksiazka nowaKsiazka(Ksiazka ksiazka, GatunekKsiazki gatunekKsiazki) {
        ksiagarnia.com.internal.db.model.Ksiazka db = new ksiagarnia.com.internal.db.model.Ksiazka();
        db.tytul = ksiazka.tytul;
        db.dateRejestracji = new Timestamp(System.currentTimeMillis());
        db.rokWydania = Timestamp.valueOf(LocalDateTime.of(ksiazka.rokWydania, 1, 1, 1, 1));
        db.isbn = ksiazka.isbn;
        db.idGatunek = gatunekKsiazki;
        return db;
    }
}
