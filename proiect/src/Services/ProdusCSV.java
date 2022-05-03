package Services;

import Persoana.User;
import Produs.Produs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProdusCSV {

    private static ProdusCSV instance = null;
    private List<Produs> produse = new ArrayList<>();

    public List<Produs> getProduse() {
        return produse;
    }

    public void setProduse(List<Produs> produse) {
        this.produse = produse;
    }

    public static ProdusCSV getInstance() {
        if (instance == null) {
            instance = new ProdusCSV();
        }
        return instance;
    }
    private List<String[]> getUsersFromFile(String filename) {
        List<String[]> data = new ArrayList<>();
        try {
            BufferedReader in = new BufferedReader(new FileReader(filename));
            String line = in.readLine();
            while(line != null) {
                String[] objectData = line.replaceAll(" ", "").split(",");
                data.add(objectData);
                line = in.readLine();
            }

        } catch (IOException e) {
            System.out.println("No users to read");
        }
        return data;
    }
    public List<Produs> getCSVData() {
        List<String[]> data = instance.getUsersFromFile("data/produs.csv");
        for(var objectData : data) {
            Produs newProdus = new Produs(
                    objectData[0],
                    objectData[1],
                    Double.parseDouble(objectData[2]),
                    Double.parseDouble(objectData[3])
            );
            this.produse.add(newProdus);
        }
    return this.produse;
    }

    public void writeToCSV(Produs p) {

        try {
            FileWriter out = new FileWriter("data/produs.csv", true);
//            for (var produs : this.produse) {
//                out.write(produs.toCSV());
//                out.write("\n");
//            }
            out.write(p.toCSV());
            out.close();
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }
}