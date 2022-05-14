import Comanda.*;
import Local.Local;
import Local.Magazin;
import Persoana.Sofer;
import Produs.Produs;
import Persoana.User;
import Services.AuditService;
import Services.MainService;
import Services.AuditService;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        AuditService audit = new AuditService("data/audit.csv");

        MainService service = MainService.getInstance();

        Scanner in = new Scanner(System.in);
        boolean ok = true;
        while(ok) {
            System.out.println("1.Creeaza user ");
            System.out.println("2.Adauga restaurant ");
            System.out.println("3.Adauga produs la local ");
            System.out.println("4.Afiseaza meniu restaurant ");
            System.out.println("5.Adauga magazin ");
            System.out.println("6.Adaduga comanda restaurant ");
            System.out.println("7.Adauga sofer ");
            System.out.println("8.Adauga comanda magazin ");
            System.out.println("9.Afiseaza comenziile fiecarui user ");
            System.out.println("10.Afiseaza comenziile in ordine cresc dupa pret");
            System.out.println("11. Opreste");
            System.out.println("Alege comanda: " );
            String optiune = in.nextLine();
            switch(optiune) {
                case "1" -> {
                    User user = service.creeazaUser(in);
                    System.out.println(user);
                    audit.logAction("add_user");}
                case "2" -> {
                    Local restaurant = service.adaugaRestaurant(in);
                    System.out.println(restaurant);
                    audit.logAction("add_restaurant");
                }
                case "3" -> {
                    Produs p = service.adaugaProdusLaLocal(in);
                    System.out.println(p);
                    audit.logAction("add_produs");}
                case "4" -> {
                    List<Produs> lista = service.getMeniuFromRestaurant(in);
                    System.out.println(lista.toString());
                    audit.logAction("get_meniu");
                }
                case "5" -> {
                    Magazin magazin = service.adaugaMagazin(in);
                    System.out.println(magazin);
                    audit.logAction("add_magazin");}
                case "6" -> {
                    ComandaLocal comanda = (ComandaLocal)service.adaugaComandaRestaurant(in);
                    System.out.println(comanda.getProduseComandate().toString());
                    audit.logAction("add_comanda_retaurant");}
                case "7" -> {
                    Sofer s = service.adaugaSofer(in);
                    System.out.println(s);
                    audit.logAction("add_sofer");}
                case "8" -> {
                    ComandaMagazin comanda = (ComandaMagazin) service.adaugaComandaMagazin(in);
                    System.out.println(comanda.getProduseCumparate().toString());
                    audit.logAction("add_comanda_magazin");}
                case "9" -> {
                    HashMap<User, List<Comanda>> map = service.getComenziFromUsers();
                    System.out.println(map.toString());
                    audit.logAction("get_comenzi_from_user");}
                case "10" -> {
                    List<Comanda> lista = service.getComenziInOrdineCresc();
                    System.out.println(lista.toString());
                    audit.logAction("get_comenzi_in_order");}
                case "11" -> ok = false;
            }
        }
    }
}
