package Persoana;

import Comanda.Comanda;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class User {
    private static int nrUsers = 0;
    private int userId;
    protected String nume;
    protected String prenume;
    protected LocalDate dataInregistrarii;
    protected List<Comanda> comenzi = new ArrayList<Comanda>();

    public User(int userId, String nume, String prenume, LocalDate dataInregistrarii) {
        this.userId = userId;
        this.nume = nume;
        this.prenume = prenume;
        this.dataInregistrarii = dataInregistrarii;
    }

    public User(String nume, String prenume) {
        this.nume = nume;
        this.prenume = prenume;
        this.dataInregistrarii = LocalDate.now();
        User.nrUsers += 1;
        this.userId = User.nrUsers;
    }
    public User(Scanner in) {
        this.read(in);
        this.dataInregistrarii = LocalDate.now();
        User.nrUsers += 1;
        this.userId = User.nrUsers;
    }

    private void read(Scanner in) {
        System.out.println("Nume: ");
        this.nume = in.nextLine();
        System.out.println("Prenume");
        this.prenume = in.nextLine();
    }
    @Override
    public String toString() {
        return "id: " + this.userId + ", nume: " + this.nume + ", prenume: " + this.prenume +
                ", data inregistrarii: " + this.dataInregistrarii.toString()+ "\n";
    }
    public String toCSV() {
        return nume + "," + prenume;
    }

    // GETTERS AND SETTERS
    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public LocalDate getDataInregistrarii() {
        return dataInregistrarii;
    }

    public void setDataInregistrarii(LocalDate dataInregistrarii) {
        this.dataInregistrarii = dataInregistrarii;
    }

    public List<Comanda> getComenzi() {
        return comenzi;
    }

    public void setComenzi(List<Comanda> comenzi) {
        this.comenzi = comenzi;
    }

    public void adaugaComanda(Comanda c) {
        this.comenzi.add(c);
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
