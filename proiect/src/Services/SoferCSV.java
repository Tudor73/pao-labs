package Services;

import Persoana.Sofer;
import Persoana.User;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SoferCSV {

    private static SoferCSV instance = null;
    private List<Sofer> soferi = new ArrayList<>();

    public List<Sofer> getSoferi() {
        return soferi;
    }

    public void setSoferi(List<Sofer> users) {
        this.soferi = users;
    }

    private SoferCSV() {

    }

    public static SoferCSV getInstance() {
        if (instance == null) {
            instance = new SoferCSV();
        }
        return instance;
    }
    private List<String[]> getDriversFromFile(String filename) {
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
            System.out.println("No drivers to read");
        }
        return data;
    }
    public List<Sofer> getCSVData() {
        List<String[]> data = instance.getDriversFromFile("data/sofer.csv");
        for(var objectData : data) {
            Sofer newSofer = new Sofer(
                    objectData[0],
                    objectData[1],
                    objectData[2]
            );
            this.soferi.add(newSofer);
        }
        return this.soferi;
    }

    public void writeToCSV(Sofer s) {

        try {
            FileWriter out = new FileWriter("data/sofer.csv", true);
//            for (var sofer : this.soferi) {
//                out.write(sofer.toCSV());
//                out.write("\n");
//            }
            out.write(s.toCSV());
            out.close();
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }
}