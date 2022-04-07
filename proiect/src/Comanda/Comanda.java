package Comanda;

import Persoana.Sofer;
import Persoana.User;
import Produs.Produs;

import java.time.LocalDate;
import java.util.Scanner;

public abstract class Comanda implements Comparable<Comanda> {
    static int numarComenzi = 0 ;

    protected String tipPlata = "cash";
    protected LocalDate data;
    protected int pret;
    protected int numarComanda;
    protected User user;
    protected Sofer sofer;

    public Comanda() {
        this.data = LocalDate.now();
        numarComenzi += 1;
        this.numarComanda = numarComenzi;
    }
    public Comanda(User user, String tipPlata) {
        this.user = user;
        this.tipPlata = tipPlata;
        Comanda.numarComenzi += 1;
        this.numarComanda = Comanda.numarComenzi;
    }
    public Comanda(Scanner in) {
        Comanda.numarComenzi += 1;
        this.numarComanda = Comanda.numarComenzi;
        this.read(in);
    }
    public void read(Scanner in) {
        System.out.println("Tip plata: ");
        this.tipPlata = in.nextLine();

    }
    public abstract void calculeazaPret();
    public abstract void adaugaProdus(Produs p);

    @Override
    public int compareTo(Comanda c) {
        return this.pret - c.getPret();
    }
    @Override
    public String toString() {
        return "plata: " + this.tipPlata + " data: " + this.data.toString() + "User: " + this.user.toString() +
                "Sofer: " + this.sofer.toString();
    }


    // GETTERS AND SETTERS
    public String getTipPlata() {
        return tipPlata;
    }

    public void setTipPlata(String tipPlata) {
        this.tipPlata = tipPlata;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public int getPret() {
        return pret;
    }

    public void setPret(int pret) {
        this.pret = pret;
    }

    public int getNumarComanda() {
        return numarComanda;
    }

    public void setNumarComanda(int numarComanda) {
        this.numarComanda = numarComanda;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Sofer getSofer() {
        return sofer;
    }

    public void setSofer(Sofer sofer) {
        this.sofer = sofer;
    }
}
