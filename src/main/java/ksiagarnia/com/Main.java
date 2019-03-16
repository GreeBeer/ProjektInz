package ksiagarnia.com;

import ksiagarnia.com.internal.db.HibernateUtils;
import ksiagarnia.com.internal.db.model.*;

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
            System.out.println("(Q) Koniec");

            char wybor = Character.toUpperCase(scanner.nextLine().charAt(0));
            switch (wybor) {
                case 'A':
                    adminMenu(scanner);
                    break;
                case 'B':
                    userMenu(scanner);
                    break;
                case 'Q':
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
            System.out.println("(D) Dodaj ksiazke do sklepu");
            System.out.println("(E) Wyswietl dostepne ksazki(sklep)");
            System.out.println("(F) Dodaj ksiazke do wypozyczalni");
            System.out.println("(G) Wyswietl dostepne ksazki(wypozyczalnia)");
            System.out.println("(H) Wyswietl wszystkie ksiazki (Rejestr)");

            System.out.println("(I) Dodaj Uzytkownika");
            System.out.println("(J) Wyswietl uzytkownikow");
            System.out.println("(K) Wyswietl wszystkie wypozyczone ksiazki uzytkownika");
            System.out.println("(L) Wyswietl wszystkie kupione ksiazki uzytkownika");

            System.out.println("(P) Powrot");
            System.out.println("(Q) Koniec");

            char wybor = Character.toUpperCase(scanner.nextLine().charAt(0));
            switch (wybor) {
                case 'A': {
                    List<GatunekKsiazki> gatunki = ksiegarnia.podajGatunki();
                    wypiszGatunki(gatunki);
                    System.out.print("Podaj gatunek id: ");
                    int idGatunek = Integer.valueOf(scanner.nextLine().trim());
                    System.out.print("Podaj tytul: ");
                    String tytul = scanner.nextLine();
                    System.out.print("Podaj autor: ");
                    String autor = scanner.nextLine();
                    System.out.print("Podaj rokWydania: ");
                    int rokWydania = Integer.valueOf(scanner.nextLine().trim());
                    System.out.print("Podaj isbn: ");
                    String isbn = scanner.nextLine();
                    ksiegarnia.dodajKsiazke(
                            idGatunek,
                            tytul,
                            autor,
                            rokWydania,
                            isbn
                    );
                    break;
                }
                case 'B': {
                    Collection<Ksiazka> ksiazki = ksiegarnia.podajNieDostepneKsiazki();
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
                    System.out.print("Podaj ksiazkaId: ");
                    int ksiazkaId = Integer.parseInt(scanner.nextLine());
                    System.out.print("Podaj ilosc: ");
                    int ilosc = Integer.parseInt(scanner.nextLine());
                    System.out.print("Podaj cena: ");
                    int cena = Integer.parseInt(scanner.nextLine());

                    Odpowiedz odpowiedz = ksiegarnia.dodajKsiazkeDoSklepu(ksiazkaId, ilosc, cena);
                    System.out.println(odpowiedz);
                    break;
                }
                case 'E': {
                    wyswietlKsiazkaSklep(ksiegarnia.podajKsiazkiSklep(), "DOSTEPNE DO KUPNA");
                    break;
                }
                case 'F': {
                    System.out.print("Podaj ksiazkaId: ");
                    int ksiazkaId = Integer.parseInt(scanner.nextLine());
                    System.out.print("Podaj ilosc: ");
                    int ilosc = Integer.parseInt(scanner.nextLine());
                    System.out.print("Podaj kosztWypozyczenia: ");
                    int koszt = Integer.parseInt(scanner.nextLine());

                    Odpowiedz odpowiedz = ksiegarnia.dodajKsiazkeDoWypozyczalni(ksiazkaId, ilosc, koszt);
                    System.out.println(odpowiedz);
                    break;
                }
                case 'G': {
                    wyswietlKsiazkaWypozyczalnia(ksiegarnia.podajKsiazkiWypozyczalnia(), "DOSTEPNE DO WYPOZYCZENIA");
                    break;
                }
                case 'H': {
                    wyswietlKsiazki(ksiegarnia.podajDostepneKsiazki(), "REJESTR DOSTEPNE");
                    wyswietlKsiazki(ksiegarnia.podajNieDostepneKsiazki(), "REJESTR NIE DOSTEPNE");
                    break;
                }
                case 'I': {
                    System.out.print("Podaj Imie: ");
                    String imie = scanner.nextLine();
                    System.out.print("Podaj nazwisko: ");
                    String nazwisko = scanner.nextLine();
                    System.out.print("Podaj loginName: ");
                    String loginName = scanner.nextLine();
                    System.out.print("Podaj password: ");
                    String password = scanner.nextLine();

                    Odpowiedz odpowiedz = ksiegarnia.dodajUzytkownika(imie, nazwisko, loginName, password);
                    System.out.println(odpowiedz);
                    wyswietlUzytkownikow(ksiegarnia.podajUzytkownikow(), "WSZYSCY");
                    break;
                }
                case 'J': {
                    wyswietlUzytkownikow(ksiegarnia.podajUzytkownikow(), "WSZYSCY");
                    break;
                }
                case 'K': {
                    System.out.print("Podaj id uzytkownika: ");
                    int userId = Integer.parseInt(scanner.nextLine());
                    wyswietlWypozyczoneKsiazki(ksiegarnia.podajWypozyczoneKsiazki(userId), "KUPIONE");
                    break;
                }
                case 'L': {
                    System.out.print("Podaj id uzytkownika: ");
                    int userId = Integer.parseInt(scanner.nextLine());
                    wyswietlKupioneKsiazki(ksiegarnia.podajKupioneKsiazki(userId), "KUPIONE");
                    break;
                }
                case 'P':
                    ksiegarnia.wyloguj();
                    return;
                case 'Q':
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

    private static void wyswietlKsiazkaWypozyczalnia(Collection<KsiazkaWypozyczalnia> ksiazki, String title) {
        System.out.println("######## " + title + " ########");
        System.out.println();
        for (KsiazkaWypozyczalnia ksiazka : ksiazki) {
            System.out.println(ksiazka.toString());
        }
        System.out.println();
        System.out.println("###############");
    }

    private static void wyswietlKsiazkaSklep(Collection<KsiazkaSklep> ksiazki, String title) {
        System.out.println("######## " + title + " ########");
        System.out.println();
        for (KsiazkaSklep ksiazka : ksiazki) {
            System.out.println(ksiazka.toString());
        }
        System.out.println();
        System.out.println("###############");
    }

    private static void wyswietlWypozyczoneKsiazki(Collection<WypozyczonaKsiazka> ksiazki, String title) {
        System.out.println("######## " + title + " ########");
        System.out.println();
        for (WypozyczonaKsiazka ksiazka : ksiazki) {
            System.out.println(ksiazka.toString());
        }
        System.out.println();
        System.out.println("###############");
    }

    private static void wyswietlKupioneKsiazki(Collection<KupionaKsiazka> ksiazki, String title) {
        System.out.println("######## " + title + " ########");
        System.out.println();
        for (KupionaKsiazka ksiazka : ksiazki) {
            System.out.println(ksiazka.toString());
        }
        System.out.println();
        System.out.println("###############");
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

    private static void wyswietlUzytkownikow(Collection<Uzytkownik> uzytkownicy, String title) {
        System.out.println("######## " + title + " ########");
        System.out.println();
        for (Uzytkownik uzytkownik : uzytkownicy) {
            System.out.println(uzytkownik.toString());
        }
        System.out.println();
        System.out.println("###############");
    }

    private static void userMenu(Scanner scanner) {
        if (loginEkran(scanner) != Odpowiedz.LOGOWANIE_OK) {
            System.out.println("Blad logowanie nie jestes Admin lub zle haslo i login");
            return;
        }

        while (true) {
            System.out.println("Witaj w ksiegarni Uzytkownik");
            System.out.println("(A) Wyswietl moje kupione ksiazki");
            System.out.println("(B) Wyswietl moje wypozyczone ksiazki");
            System.out.println("(C) Kup ksiazke");
            System.out.println("(D) Wypozycz ksiazke");
            System.out.println("(E) Oddaj ksiazke");
            System.out.println("(F) Wyswietl dostepne ksazki(sklep)");
            System.out.println("(G) Wyswietl dostepne ksazki(wypozyczalnia)");
            System.out.println("(H) Sprawdz stan konta");
            System.out.println("(I) Doladuj saldo");
            System.out.println("(P) Powrot");
            System.out.println("(Q) Koniec");

            char wybor = Character.toUpperCase(scanner.nextLine().charAt(0));
            switch (wybor) {
                case 'A':
                    wyswietlKupioneKsiazki(ksiegarnia.podajMojeKupioneKsiazki(), "KUPIONE");
                    break;
                case 'B':
                    wyswietlWypozyczoneKsiazki(ksiegarnia.podajMojeWypozyczoneKsiazki(), "WYPOZYCZONE");
                    break;
                case 'C': {
                    System.out.println("Podaj id ksiazki");
                    int ksiazkaId = Integer.parseInt(scanner.nextLine());
                    Odpowiedz odpowiedz = ksiegarnia.kupKsiazke(ksiazkaId);
                    System.out.println(odpowiedz);
                    break;
                }
                case 'D': {
                    System.out.println("Podaj id ksiazki");
                    int ksiazkaId = Integer.parseInt(scanner.nextLine());
                    Odpowiedz odpowiedz = ksiegarnia.wypozyczKsiazke(ksiazkaId);
                    System.out.println(odpowiedz);
                    break;
                }
                case 'E': {
                    System.out.println("Podaj id ksiazki");
                    int ksiazkaId = Integer.parseInt(scanner.nextLine());
                    Odpowiedz odpowiedz = ksiegarnia.oddajKsiazke(ksiazkaId);
                    System.out.println(odpowiedz);
                    break;
                }
                case 'F': {
                    wyswietlKsiazkaSklep(ksiegarnia.podajKsiazkiSklep(), "DOSTEPNE DO KUPNA");
                    break;
                }
                case 'G': {
                    wyswietlKsiazkaWypozyczalnia(ksiegarnia.podajKsiazkiWypozyczalnia(), "DOSTEPNE DO WYPOZYCZENIA");
                    break;
                }
                case 'H': {
                    System.out.println("Saldo: " + ksiegarnia.podajSaldo());
                    break;
                }
                case 'I': {
                    System.out.println("Ile chcesz doladowac: ");
                    double topup = Double.parseDouble(scanner.nextLine());
                    ksiegarnia.doladujSaldo(topup);
                    break;
                }
                case 'P':
                    ksiegarnia.wyloguj();
                    return;
                case 'Q':
                    System.exit(0);
                    return;
            }
            promptEnterKey();
            clearScreen();
        }
    }
}
