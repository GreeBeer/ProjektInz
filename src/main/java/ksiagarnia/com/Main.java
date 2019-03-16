package ksiagarnia.com;

import ksiagarnia.com.internal.db.HibernateUtils;
import ksiagarnia.com.internal.db.model.GatunekKsiazki;
import ksiagarnia.com.internal.db.model.Ksiazka;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

public class Main {

    private final static Ksiegarnia ksiegarnia = new Ksiegarnia();

    public static void main(String[] args) {
        // laczymy sie z baza danych
        HibernateUtils.getSessionFactory().openSession().close();

        Scanner scanner = new Scanner(System.in);
        mainMenu(scanner);
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
            System.out.println("(A) Dodaj Ksiazke");
            System.out.println("(B) Zarejestruj Ksiazke");
            System.out.println("(C) Wyrejestruj Ksiazke");
            System.out.println("(D) Wyswietl wszystkie ksiazki");
            System.out.println("(E) Dodaj Uzytkownika");
            System.out.println("(F) Wyswietl uzytkownikow");
            System.out.println("(G) Wyswietl wszystkie wypozyczone ksiazki uzytkownika");
            System.out.println("(H) Wyswietl wszystkie kupione ksiazki uzytkownika");

            System.out.println("(P) Powrot");
            System.out.println("(K) Koniec");

            char wybor = Character.toUpperCase(scanner.nextLine().charAt(0));
            switch (wybor) {
                case 'A':
                    List<GatunekKsiazki> gatunki = ksiegarnia.podajGatunki();
                    wypiszGatunki(gatunki);
                    System.out.print("Podaj gatunek id: ");
                    int idGatunek = Integer.valueOf(scanner.nextLine().trim());
                    System.out.print("Podaj gatunek tytul: ");
                    String tytul = scanner.nextLine();
                    System.out.print("Podaj gatunek autor: ");
                    String autor = scanner.nextLine();
                    System.out.print("Podaj gatunek rokWydania: ");
                    int rokWydania = Integer.valueOf(scanner.nextLine().trim());
                    System.out.print("Podaj gatunek isbn: ");
                    String isbn = scanner.nextLine();
                    ksiegarnia.dodajKsiazke(
                            idGatunek,
                            tytul,
                            autor,
                            rokWydania,
                            isbn
                    );
                    break;
                case 'B': {
                    Collection<Ksiazka> ksiazki = ksiegarnia.podajDostepneKsiazki();
                    wyswietlKsiazki(ksiazki, "REJESTR");
                    System.out.println("Ksiazka id do zarejestrowania");
                    int ksiazkaId = Integer.valueOf(scanner.nextLine().trim());
                    Odpowiedz odpowiedz = ksiegarnia.zarejestrujKsiazke(ksiazkaId);
                    System.out.println(odpowiedz);
                    break;
                }
                case 'C': {
                    Collection<Ksiazka> ksiazki = ksiegarnia.podajDostepneKsiazki();
                    wyswietlKsiazki(ksiazki, "REJESTR");
                    System.out.println("Ksiazka id do wyrejestrowania");
                    int ksiazkaId = Integer.valueOf(scanner.nextLine().trim());
                    Odpowiedz odpowiedz = ksiegarnia.wyrejestrujKsiazke(ksiazkaId);
                    System.out.println(odpowiedz);
                    break;
                }
                case 'D': {
                    Collection<Ksiazka> ksiazki = ksiegarnia.podajDostepneKsiazki();
                    wyswietlKsiazki(ksiazki, "REJESTR");
                    break;
                }
                case 'E':
                    break;
                case 'F':
                    break;
                case 'G':
                    break;
                case 'H':
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

    private static void wypiszGatunki(List<GatunekKsiazki> gatunki) {
        System.out.println("######## Gatunki ########");
        System.out.println();
        for (GatunekKsiazki gatunek : gatunki) {
            System.out.println(gatunek.toString());
        }
        System.out.println();
        System.out.println("###############");
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
//                    wyswietlKsiazki(ksiegarnia.podajKsiazkiKupione(idUzytkownik), "KUPIONE");
//                    wyswietlKsiazki(ksiegarnia.podajKsiazkiWypozyczone(idUzytkownik), "WYPOZYCZONE");
                    break;
                case 'B':
                    System.out.println("Podaj id ksiazki");
                    int idKsiazka = scanner.nextInt();
//                    Odpowiedz kup = ksiegarnia.kup(idKsiazka, idUzytkownik);
//                    System.out.println(kup);
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
