package Services;

import Comanda.*;
import Local.*;
import Produs.*;
import Persoana.*;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public interface IMainService {

    User creeazaUser(Scanner in);

    Local adaugaRestaurant(Scanner in);

    Produs adaugaProdusLaLocal(Scanner in);

    Sofer adaugaSofer(Scanner in);
    Comanda adaugaComandaRestaurant(Scanner in);

    Comanda adaugaComandaMagazin(Scanner in);

    Magazin adaugaMagazin(Scanner in);

    List<Produs> getMeniuFromRestaurant(Scanner in);

    List<Comanda> getComenziFromLocal();

    HashMap<User, List<Comanda>> getComenziFromUsers();

    List<Comanda> getComenziInOrdineCresc();



}
