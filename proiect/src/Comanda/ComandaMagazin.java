package Comanda;

import Persoana.User;
import Local.*;
import Produs.Produs;

import java.util.ArrayList;
import java.util.List;

public class ComandaMagazin extends Comanda {

    private Local magazin;
    private List<Produs> produseCumparate = new ArrayList<Produs>();
    private double greutateComanda;
    private boolean esteFragila;

    public ComandaMagazin() {
        super();
    }
    public ComandaMagazin(User user, String tipPlata, Magazin magazin,
                          double greutateComanda, boolean esteFragila) {
        super(user, tipPlata);
        this.magazin = magazin;
        this.greutateComanda = greutateComanda;
        this.esteFragila = esteFragila;
    }
    @Override
    public void calculeazaPret() {
        for(Produs p : this.produseCumparate) {
            this.greutateComanda += p.getGreutate();
            this.pret += p.getPret();
        }
        double pretAdaugat = greutateComanda / 2;  // la fiecare 2 kg adaug 2 lei la pret
        this.pret += pretAdaugat * 2;
    }
    @Override
    public void adaugaProdus(Produs p) {
        this.produseCumparate.add(p);
    }

    @Override
    public String toString() {
        return super.toString() + " Magazin: " + this.magazin.toString() + " Greutate: " + this.greutateComanda +
                " pret: " + this.pret + " Produse Comandate: " + this.produseCumparate.toString();
    }

    // GETTERS AND SETTERS
    public Local getMagazin() {
        return magazin;
    }

    public void setMagazin(Local magazin) {
        this.magazin = magazin;
    }

    public List<Produs> getProduseCumparate() {
        return produseCumparate;
    }

    public void setProduseCumparate(List<Produs> produseCumparate) {
        this.produseCumparate = produseCumparate;
    }

    public double getGreutateComanda() {
        return greutateComanda;
    }

    public void setGreutateComanda(double greutateComanda) {
        this.greutateComanda = greutateComanda;
    }

    public boolean isEsteFragila() {
        return esteFragila;
    }

    public void setEsteFragila(boolean esteFragila) {
        this.esteFragila = esteFragila;
    }


}
