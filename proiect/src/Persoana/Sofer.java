package Persoana;

import java.util.Scanner;

public class Sofer {
    private static int numarSoferi = 0;
    private int soferId;
    private String nume;
    private String prenume;
    private String numarTelefon;
    private int rating ;

    public Sofer(String nume, String prenume, String numarTelefon) {
        this.nume = nume;
        this.prenume = prenume;
        this.numarTelefon = numarTelefon;
        Sofer.numarSoferi += 1;
        this.soferId = numarSoferi;
    }
    public Sofer(Scanner in) {
        this.read(in);
        Sofer.numarSoferi += 1;
        this.soferId = numarSoferi;
    }
    public void read(Scanner in) {
        System.out.println("Nume: ");
        this.nume = in.nextLine();
        System.out.println("Prenume: ");
        this.prenume = in.nextLine();
        System.out.println("Numar telefon: ");
        this.numarTelefon = in.nextLine();
    }
    @Override
    public String toString() {
        return "id: " + this.soferId + ", numeSofer: " + this.nume + ", prenumeSofer: " + this.prenume + "\n";
    }

    // GETTERS AND SETTERS
    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getNumarTelefon() {
        return numarTelefon;
    }

    public void setNumarTelefon(String numarTelefon) {
        this.numarTelefon = numarTelefon;
    }

    public int getSoferId() {
        return soferId;
    }

    public void setSoferId(int soferId) {
        this.soferId = soferId;
    }
}
