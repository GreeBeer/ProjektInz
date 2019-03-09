package ksiagarnia.com;

import ksiagarnia.com.internal.ksiazka.GatunekKsiazki;
import ksiagarnia.com.internal.ksiazka.Ksiazka;
import ksiagarnia.com.internal.user.Uzytkownik;

import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Scanner;

public class Main {

    private final static Ksiegarnia ksiegarnia = new Ksiegarnia();

    public static void main(String[] args) {
        initialData();
        Scanner scanner = new Scanner(System.in);
        mainMenu(scanner);
    }

    private static void initialData() {
        ksiegarnia.rejest(new Uzytkownik(
                        0,
                        "Marek",
                        "W",
                        new HashSet<>()
                ),
                100);
        ksiegarnia.rejestKsiazka(new Ksiazka(
                0,
                "X",
                1,
                "",
                GatunekKsiazki.DRAMAT,
                50,
                5)
        );
        ksiegarnia.rejestKsiazka(new Ksiazka(
                1,
                "X",
                1,
                "",
                GatunekKsiazki.KRYMINAL,
                5,
                5)
        );

        ksiegarnia.rejestKsiazkaDoSklep(0, 10);
        ksiegarnia.rejestKsiazkaDoWypozyczalni(1, 5);
    }

    private static void promptEnterKey() {
        System.out.println("Press \"ENTER\" to continue...");
        try {
            int read = System.in.read(new byte[2]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void clearScreen() {
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
    }

    private static void mainMenu(Scanner scanner) {
        while (true) {
            System.out.println("Witaj w ksiegarni");
            System.out.println("(A) zaloguj sie jako Admin");
            System.out.println("(B) zaloguj sie jako Uzytkownik");
            System.out.println("(K) Koniec");

            char wybor = Character.toUpperCase(scanner.next().charAt(0));
            switch (wybor) {
                case 'A':
                    adminMenu(scanner);
                    break;
                case 'B':
                    userMenu(scanner);
                    break;
                case 'K':
                    System.exit(0);
                    return;
            }
        }
    }

    private static void adminMenu(Scanner scanner) {
        while (true) {
            System.out.println("Witaj w ksiegarni Admin");
            System.out.println("(A) Wyswietl wszystkie ksiazki");
            System.out.println("(B) Wyswietl wszystkie ksiazki uzytkownika");
            System.out.println("(C) Wyswietl uzytkownikow");
            System.out.println("(D) Rejestracja Uzytkownika");
            System.out.println("(E) Dodaj Ksiazke");
            System.out.println("(P) Powrot");
            System.out.println("(K) Koniec");

            char wybor = Character.toUpperCase(scanner.next().charAt(0));
            switch (wybor) {
                case 'A':
                    Collection<Ksiazka> ksiazki = ksiegarnia.podajKsiazki();
                    wyswietlKsiazki(ksiazki, "REJESTR");
                    break;
                case 'B':
                    break;
                case 'C':
                    Collection<Uzytkownik> uzytkownicy = ksiegarnia.podajUzytkownikow();
                    System.out.println(uzytkownicy.toString());
                    break;
                case 'D':
                    break;
                case 'E':
                    break;
                case 'P':
                    return;
                case 'K':
                    System.exit(0);
                    return;
            }
            promptEnterKey();
            clearScreen();
        }
    }

    private static void wyswietlKsiazki(Collection<Ksiazka> ksiazki) {
        wyswietlKsiazki(ksiazki, "");
    }

    private static void wyswietlKsiazki(Collection<Ksiazka> ksiazki, String title) {
        System.out.println("######## " + title + " ########");
        System.out.println();
        for (Ksiazka ksiazka : ksiazki) {
            System.out.println(ksiazka.toString());
        }
        System.out.println();
        System.out.println("###############");
    }

    private static void userMenu(Scanner scanner) {
        System.out.println("Podaj id uzytkownika");
        int idUzytkownik = scanner.nextInt();

        while (true) {
            System.out.println("Witaj w ksiegarni Uzytkownik");
            System.out.println("(A) Wyswietl moje ksiazki");
            System.out.println("(B) Kup ksiazke");
            System.out.println("(C) Sprawdz stan konta");
            System.out.println("(P) Powrot");
            System.out.println("(K) Koniec");

            char wybor = Character.toUpperCase(scanner.next().charAt(0));
            switch (wybor) {
                case 'A':
                    wyswietlKsiazki(ksiegarnia.podajKsiazkiKupione(idUzytkownik), "KUPIONE");
                    wyswietlKsiazki(ksiegarnia.podajKsiazkiWypozyczone(idUzytkownik), "WYPOZYCZONE");
                    break;
                case 'B':
                    System.out.println("Podaj id ksiazki");
                    int idKsiazka = scanner.nextInt();
                    Odpowiedz kup = ksiegarnia.kup(idKsiazka, idUzytkownik);
                    System.out.println(kup);
                    break;
                case 'C':
                    break;
                case 'P':
                    return;
                case 'K':
                    System.exit(0);
                    return;
            }
            promptEnterKey();
            clearScreen();
        }
    }
}
