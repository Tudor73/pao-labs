package Local;

import Produs.Produs;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Magazin extends Local {

    private List<Produs> produse = new ArrayList<Produs>();

    public Magazin(String numeMagazin, String program, String adresa) {
        super(numeMagazin, program, adresa);
        this.produse.add(new Produs("sare", "default", 1,1));

    }
    public Magazin(Scanner in) {
        super(in);
        this.produse.add(new Produs("sare", "default", 1,1));
    }

    @Override
    public void eliminaProdusDinMeniu(Produs p) {
        this.produse.add(p);
    }

    @Override
    public void adaugaProdusInMeniu(Produs p) {
        this.produse.remove(p);
    }

    //GETTERS AND SETTERS

    public List<Produs> getProduse() {
        return produse;
    }

    public void setProduse(List<Produs> produse) {
        this.produse = produse;
    }


}
