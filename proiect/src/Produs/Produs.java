package Produs;

import java.util.Scanner;

public class Produs {
    private String nume;
    private String tip;
    private double pret;
    private double greutate;

    public Produs(String nume, String tip, double pret, double greutate) {
        this.nume = nume;
        this.tip = tip;
        this.pret = pret;
        this.greutate = greutate;
    }
    public Produs(Scanner in) {
        this.read(in);
    }
    public void read(Scanner in) {
        System.out.println("Nume: ");
        this.nume = in.nextLine();
        System.out.println("Tip: ");
        this.tip = in.nextLine();
        System.out.println("Pret: ");
        this.pret = Integer.parseInt(in.nextLine());
        System.out.println("Greutate(g): ");
        this.greutate = Integer.parseInt(in.nextLine());
    }
    @Override
    public String toString() {
        return "{nume produs: " + this.nume + ", tip: " + this.tip + ", pret: " + this.pret + "greutate: " + this.greutate + "}\n";
    }
    public String toCSV() {
        return nume + "," + tip + "," + pret + ","+greutate;
    }

    //GETTERS AND SETTERS
    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public double getPret() {
        return pret;
    }

    public void setPret(int pret) {
        this.pret = pret;
    }

    public double getGreutate() {
        return greutate;
    }

    public void setGreutate(double greutate) {
        this.greutate = greutate;
    }

}
