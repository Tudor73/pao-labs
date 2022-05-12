package Services;

import Comanda.*;
import Comparators.PretComparator;
import Local.*;
import Produs.*;
import Persoana.*;


import java.util.*;

public class MainService implements IMainService{
    private List<User> useri = new ArrayList<>();
    private List<Local> localuri = new ArrayList<>();
    private List<Comanda> comenzi = new ArrayList<>();
    private List<Sofer> soferi = new ArrayList<>();
    private List<Produs> produse = new ArrayList<>();

    private UserCSV userCSV = UserCSV.getInstance();
    private LocalCSV localCSV = LocalCSV.getInstance();
    private SoferCSV soferCSV = SoferCSV.getInstance();
    private ProdusCSV produsCSV= ProdusCSV.getInstance();


    private static MainService instance = null;

    private MainService() {
//        useri.add(new User("Andrei", "Mihai"));
//        localuri.add(new Restaurant("Mcdonalds", "8.00-24.00", "123 King Street", "fast-food"));
//        soferi.add(new Sofer("Popescu", "Andi", "072137648"));
        this.useri = userCSV.getCSVData();
        this.produse = produsCSV.getCSVData();
        this.localuri = localCSV.getCSVData();
        this.soferi = soferCSV.getCSVData();

    }
    public static MainService getInstance() {
        if (instance == null) {
            instance = new MainService();
        }
        return instance;
    }

    @Override
    public User creeazaUser(Scanner in) {
        User user = new User(in);
        userCSV.writeToCSV(user);
        return user;
    }

    @Override
    public Local adaugaRestaurant(Scanner in) {
        Local restaurant= new Restaurant(in);
        localCSV.writeToCSV(restaurant);
        this.localuri.add(restaurant);
        return restaurant;
    }

    @Override
    public Produs adaugaProdusLaLocal(Scanner in) {
        Produs p = new Produs(in);
        produsCSV.writeToCSV(p);
        System.out.println("Id ul localului ( " + this.localuri.toString()  + "): ");
        int id = Integer.parseInt(in.nextLine());
        try {
            Local local = findLocalById(id);
            if (local == null)
                throw new NullPointerException();
            local.adaugaProdusInMeniu(p);
        } catch(NullPointerException exception) {
            System.out.println("Id ul nu exista");
        }

        return p;
    }

    @Override
    public Sofer adaugaSofer(Scanner in) {
        Sofer s = new Sofer(in);
        soferCSV.writeToCSV(s);
        this.soferi.add(s);
        return s;
    }


    @Override
    public Comanda adaugaComandaRestaurant(Scanner in) {

        ComandaLocal comandaNoua = new ComandaLocal();
        System.out.println("Id ul userului care face comanda( " + this.useri.toString() + "): " );
        int userId = Integer.parseInt(in.nextLine());
        try {
            User u = findUserById(userId);
            if(u == null)
                throw new NullPointerException();
            comandaNoua.setUser(u);
        } catch(NullPointerException exception) {
            System.out.println("Id ul nu exista");
            return null;
        }
        System.out.println("Id ul restaurantului de la care se face comanda( " + this.localuri.toString() + "): ");
        int localId = Integer.parseInt(in.nextLine());
        Local local;
        try {
            local = findLocalById(localId);
            if (local == null)
                throw new NullPointerException();
            comandaNoua.setLocal(local);
        } catch(NullPointerException exception) {
            System.out.println("Id ul nu exista");
            return null;
        }

        Restaurant r = (Restaurant) local;
        List<Produs> meniuRestaurant = r.getMeniuRestaurant();
        while(true) {
            System.out.println("Meniul restaurantului: " + meniuRestaurant.toString());
            System.out.println("Indicele produsului dorit (-1 pentru oprire): ");
            int produsId = Integer.parseInt(in.nextLine());
            if (produsId == -1)
                break;
            try {
                comandaNoua.adaugaProdus(meniuRestaurant.get(produsId));
            } catch(IndexOutOfBoundsException exception) {
                System.out.println("Id ul nu exista");
            }
        }
        comandaNoua.calculeazaPret();
        this.comenzi.add(comandaNoua);

        Sofer sofer = preiaComanda(); // alege soferul random
        System.out.println("Soferul cu id-ul " + sofer.getSoferId() + "a fost ales sa livreze comanda");
        comandaNoua.setSofer(sofer);
        return comandaNoua;
    }

    @Override
    public Comanda adaugaComandaMagazin(Scanner in) {
        ComandaMagazin comandaNoua = new ComandaMagazin();
        System.out.println("Id ul userului care face comanda( " + this.useri.toString() + "): " );
        int userId = Integer.parseInt(in.nextLine());
        try {
            User u = findUserById(userId);
            if(u == null)
                throw new NullPointerException();
            comandaNoua.setUser(u);
        } catch(NullPointerException exception) {
            System.out.println("Id ul nu exista");
        }
        System.out.println("Id ul restaurantului de la care se face comanda( " + this.localuri.toString() + "): ");
        int magazinId = Integer.parseInt(in.nextLine());

        try {
            comandaNoua.setMagazin(localuri.get(magazinId));
        } catch(IndexOutOfBoundsException exception) {
            System.out.println("Id ul nu exista");
        }

        Magazin m =  (Magazin) localuri.get(magazinId);
        List<Produs> listaProduse = m.getProduse();
        while(true) {
            System.out.println("Produsele magazinului: " + listaProduse.toString());
            System.out.println("Id ul produsului dorit (-1 pentru oprire): ");
            int produsId = Integer.parseInt(in.nextLine());
            if (produsId == -1)
                break;
            try {
                comandaNoua.adaugaProdus(listaProduse.get(produsId));
            } catch(IndexOutOfBoundsException exception) {
                System.out.println("Id ul nu exista");
            }
        }
        System.out.println("Fragila(1/0): ");
        int fragila = Integer.parseInt(in.nextLine());
        comandaNoua.setEsteFragila(fragila == 1);

        comandaNoua.calculeazaPret();
        this.comenzi.add(comandaNoua);

        Sofer sofer = preiaComanda(); // alege soferul random
        System.out.println("Soferul cu id-ul " + sofer.getSoferId() + "a fost ales sa livreze comanda");
        comandaNoua.setSofer(sofer);
        return comandaNoua;
    }

    private User findUserById(int userId ) {
        for (User u : this.useri) {
            if (u.getUserId() == userId)
                return u;
        }
        return null;
    }

    private Local findLocalById(int localId) {
        for (Local l : this.localuri) {
            if (l.getLocalId() == localId)
                return l;
        }
        return null;
    }

    private Sofer preiaComanda() {
        Random rand = new Random();
        return this.soferi.get(rand.nextInt(this.soferi.size()));
    }
    @Override
    public Magazin adaugaMagazin(Scanner in) {
        Magazin magazin = new Magazin(in);
        this.localuri.add(magazin);
        return magazin;
    }

    @Override
    public List<Produs> getMeniuFromRestaurant(Scanner in) {
        System.out.println("Id ul restaurantului ");
        int id = Integer.parseInt(in.nextLine());
        try {
            Restaurant restaurant = (Restaurant) findLocalById(id);
            if (restaurant == null)
                throw new NullPointerException();
            return restaurant.getMeniuRestaurant();
        } catch(NullPointerException exception) {
            System.out.println("Id ul nu exista");
            return null;
        }
    }

    @Override
    public List<Comanda> getComenziFromLocal() {
        return null;
    }

    @Override
    public HashMap<User, List<Comanda>> getComenziFromUsers() {
        HashMap<User, List<Comanda>> map = new HashMap<User, List<Comanda>>();
        for(Comanda c :this.comenzi) {
            User newUser = c.getUser();
            if (!map.containsKey(newUser)) {
                map.put(newUser, new ArrayList<Comanda>());
                map.get(newUser).add(c);
            }
            else {
                map.get(newUser).add(c);
            }
        }
        return map;
    }

    @Override
    public List<Comanda> getComenziInOrdineCresc() {
        try {
            PretComparator comp = new PretComparator();
            this.comenzi.sort(comp);
            return this.comenzi;
        } catch (NullPointerException exception) {
            System.out.println("Nu exista comenzi");
            return null;
        }
    }

    //    @Override
//    public Produs adaugaProdusLaMagazin(Scanner in) {
//        Produs p = new Produs(in);
//        System.out.println("Id ul magazinului");
//        int id = Integer.parseInt(in.nextLine());
//        try {
//            Magazin m =  (Magazin) localuri.get(id);
//            m.adaugaProdusInMeniu(p);
//        } catch (IndexOutOfBoundsException exception) {
//            System.out.println("Nu exista magazin cu id-ul respectiv");
//        }
//        return p;
//    }
}
