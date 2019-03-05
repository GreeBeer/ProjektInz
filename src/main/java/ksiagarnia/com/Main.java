package ksiagarnia.com;

import ksiagarnia.com.internal.ksiazka.GatunekKsiazki;
import ksiagarnia.com.internal.ksiazka.Ksiazka;
import ksiagarnia.com.internal.user.Uzytkownik;

import java.util.Collection;
import java.util.HashSet;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Ksiegarnia ksiegarnia = new Ksiegarnia();
        ksiegarnia.rejest(new Uzytkownik(0, "Marek", "W", new HashSet<>()), 100);
        ksiegarnia.rejest(new Uzytkownik(002, "TOMEK", "Z", new HashSet<>()), 200);
        ksiegarnia.rejestKsiazka(new Ksiazka(0, "X", 1, "", GatunekKsiazki.DRAMAT, 50, 5));
        ksiegarnia.rejestKsiazka(new Ksiazka(1, "X", 1, "", GatunekKsiazki.KRYMINAL, 5, 5));

        ksiegarnia.rejestKsiazkaDoSklep(0, 10);
        ksiegarnia.rejestKsiazkaDoWypozyczalni(1, 5);

        Scanner scanner = new Scanner(System.in);

        System.out.println("Witaj w ksiegarni");
        while (true) {
            System.out.println("(A) wypisz uzytkownikow");
            System.out.println("(B) wypisz ksiazki");
            System.out.println("(C) kup ksiazke");
            System.out.println("(K) koniec");

            char wybor = Character.toUpperCase(scanner.next().charAt(0));

            switch (wybor) {
                case 'A':
                    Collection<Uzytkownik> uzytkownicy = ksiegarnia.podajUzytkownikow();
                    System.out.println(uzytkownicy.toString());
                    break;
                case 'B':
                    Collection<Ksiazka> ksiazki = ksiegarnia.podajKsiazki();
                    System.out.println(ksiazki.toString());
                    break;
                case 'C':
                    System.out.println("Podaj id ksiazki");
                    int idKsiazka = scanner.nextInt();
                    System.out.println("Podaj id uzytkownik");
                    int idUzytkownik = scanner.nextInt();

                    Odpowiedz kup = ksiegarnia.kup(idKsiazka, idUzytkownik);
                    System.out.println(kup);
                case 'K':
                    return;
            }
        }
    }
}
