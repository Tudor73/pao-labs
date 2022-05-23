package Local;


import Produs.Produs;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Restaurant extends Local {

    private String tipRestaurant;
    private List<Produs> meniuRestaurant = new ArrayList<Produs>();

    public Restaurant(int id, String numeLocal, String program, String adresa) {
        super(id,numeLocal, program, adresa);
    }

    public Restaurant(String numeLocal, String program, String adresa, String tipRestaurant) {
        super(numeLocal, program, adresa);
        this.tipRestaurant = tipRestaurant;
        this.meniuRestaurant.add(new Produs("sare", "default", 1,1));
    }

    public Restaurant(Scanner in) {
        super(in);
        System.out.println("Tip restaurant: ");
        this.tipRestaurant = in.nextLine();
        this.meniuRestaurant.add(new Produs("sare", "default", 1,1));
    }

    @Override
    public void adaugaProdusInMeniu(Produs p) {
        this.meniuRestaurant.add(p);
    }
    @Override
    public void eliminaProdusDinMeniu(Produs p){
        this.meniuRestaurant.remove(p);
    }

    @Override
    public String toString() {
        return super.toString() + "tip:" + this.tipRestaurant + "\n";

    }
    public String toCSV() {
        return numeLocal + "," + program + "," + adresa + "," + tipRestaurant;
    }
    // GETTERS AND SETTERS
    public String getTipRestaurant() {
        return tipRestaurant;
    }

    public void setTipRestaurant(String tipRestaurant) {
        this.tipRestaurant = tipRestaurant;
    }

    public List<Produs> getMeniuRestaurant() {
        return meniuRestaurant;
    }

    public void setMeniuRestaurant(List<Produs> meniuRestaurant) {
        this.meniuRestaurant = meniuRestaurant;
    }
}
