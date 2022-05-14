package Local;

import Produs.Produs;

import java.util.Scanner;

abstract public  class Local {
    private static int nrLocaluri = 0;
    protected int localId;
    protected String numeLocal;
    protected String program;
    protected String adresa;
    protected double rating = 0;


    public Local(String numeLocal, String program, String adresa) {
        this.numeLocal = numeLocal;
        this.program = program;
        this.adresa = adresa;
        Local.nrLocaluri += 1;
        this.localId = nrLocaluri;
    }

    public Local(Scanner in) {
        this.read(in);
        Local.nrLocaluri += 1;
        this.localId = nrLocaluri;
    }
    public void read(Scanner in) {
        System.out.println("Nume local: ");
        this.numeLocal = in.nextLine();
        System.out.println("Adresa: ");
        this.adresa = in.nextLine();
        System.out.println("Program: ");
        this.program= in.nextLine();
    }
    public abstract void adaugaProdusInMeniu(Produs p);
    public abstract void eliminaProdusDinMeniu(Produs p);

    @Override
    public String toString(){
        return  "id: "+this.localId + ", nume local: " + this.numeLocal + " program: " + this.program +
                " adresa: " + this.adresa + '\n';
    }

    public String toCSV() {
        return numeLocal + "," + program + "," + adresa;
    }

    // GETTERS AND SETTERS
    public String getNumeLocal() {
        return numeLocal;
    }

    public void setNumeLocal(String numeLocal) {
        this.numeLocal = numeLocal;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getLocalId() {
        return localId;
    }

    public void setLocalId(int localId) {
        this.localId = localId;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }
}
