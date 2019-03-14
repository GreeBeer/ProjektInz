package ksiagarnia.com;

import ksiagarnia.com.internal.db.HibernateUtils;
import ksiagarnia.com.internal.ksiazka.Ksiazka;
import ksiagarnia.com.internal.user.Uzytkownik;

import java.io.IOException;
import java.util.Collection;
import java.util.Scanner;

public class Main {

    private final static Ksiegarnia ksiegarnia = new Ksiegarnia();

    public static void main(String[] args) {
        HibernateUtils.getSessionFactory().openSession().close();
        initialData();
        Scanner scanner = new Scanner(System.in);
        mainMenu(scanner);
    }

    private static void initialData() {
        ksiegarnia.rejestrKsiazkaDoSklep(1, 10);
        ksiegarnia.rejestrKsiazkaDoWypozyczalni(2, 5);
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

            char wybor = Character.toUpperCase(scanner.nextLine().charAt(0));
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
        if (loginEkran(scanner) != Odpowiedz.LOGOWANIE_ADMIN_OK) {
            System.out.println("Blad logowanie nie jestes Admin lub zle haslo i login");
            return;
        }

        while (true) {
            System.out.println("Witaj w ksiegarni Admin");
            System.out.println("(A) Wyswietl wszystkie ksiazki");
            System.out.println("(B) Wyswietl wszystkie ksiazki uzytkownika");
            System.out.println("(C) Wyswietl uzytkownikow");
            System.out.println("(D) Rejestracja Uzytkownika");
            System.out.println("(E) Dodaj Ksiazke");
            System.out.println("(P) Powrot");
            System.out.println("(K) Koniec");

            char wybor = Character.toUpperCase(scanner.nextLine().charAt(0));
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

    private static Odpowiedz loginEkran(Scanner scanner) {
        System.out.println("Podaj userName");
        String userName = scanner.nextLine();
        System.out.println("Podaj haslo");
        String password = scanner.nextLine();
        return ksiegarnia.login(userName, password);
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
        if (loginEkran(scanner) != Odpowiedz.LOGOWANIE_OK) {
            System.out.println("Blad logowanie nie jestes Admin lub zle haslo i login");
            return;
        }
        int idUzytkownik = ksiegarnia.podajIdZalogowanegoUzytkownika();

        while (true) {
            System.out.println("Witaj w ksiegarni Uzytkownik");
            System.out.println("(A) Wyswietl moje ksiazki");
            System.out.println("(B) Kup ksiazke");
            System.out.println("(C) Sprawdz stan konta");
            System.out.println("(P) Powrot");
            System.out.println("(K) Koniec");

            char wybor = Character.toUpperCase(scanner.nextLine().charAt(0));
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
