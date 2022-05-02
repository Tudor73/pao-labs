package Comanda;

import Persoana.User;
import Local.Local;
import Produs.Produs;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ComandaLocal extends Comanda {
    private Local local;
    private List<Produs> produseComandate = new ArrayList<Produs>();

    public ComandaLocal() {
        super();
    }
    public ComandaLocal(User user, String tipPlata, Local local) {
        super(user, tipPlata);
        this.local = local;
    }
    public ComandaLocal(Scanner in){
        super(in);
    }
    public void read(Scanner in) {

    }
    @Override
    public void calculeazaPret() {
        for(Produs p: this.produseComandate) {
            this.pret += p.getPret();
        }
    }
    @Override
    public void adaugaProdus(Produs p)
    {
        this.produseComandate.add(p);
    }

    public Local getLocal() {
        return local;
    }

    public void setLocal(Local local) {
        this.local = local;
    }

    public List<Produs> getProduseComandate() {
        return produseComandate;
    }

    public void setProduseComandate(List<Produs> produseComandate) {
        this.produseComandate = produseComandate;
    }

    @Override
    public String toString() {
        return super.toString() + " Restaurant: " + this.local.toString() + " pret: " + this.pret +
                " produse: " + this.produseComandate.toString();
    }
}
