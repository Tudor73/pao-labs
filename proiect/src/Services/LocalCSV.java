package Services;

import Local.Local;
import Local.Restaurant;
import Persoana.User;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LocalCSV {

    private static LocalCSV instance = null;
    private List<Local> locals = new ArrayList<>();

    public List<Local> getUsers() {
        return locals;
    }

    public void setUsers(List<Local> users) {
        this.locals = users;
    }

    private LocalCSV(){

    }
    public static LocalCSV getInstance() {
        if (instance == null) {
            instance = new LocalCSV();
        }
        return instance;
    }
    private List<String[]> getLocalsFromFile(String filename) {
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
            System.out.println("No locals to read");
        }
        return data;
    }
    public List<Local> getCSVData() {
        List<String[]> data = instance.getLocalsFromFile("data/locale.csv");
        for(var objectData : data) {
            Local newLocal = new Restaurant(
                    objectData[0],
                    objectData[1],
                    objectData[2],
                    objectData[3]

            );
            this.locals.add(newLocal);
        }
        return this.locals;
    }

    public void writeToCSV(Local l) {

        try {
            FileWriter out = new FileWriter("data/locale.csv", true);
//            for (var local : this.locals) {
//                out.write(local.toCSV());
//                out.write("\n");
//            }
            out.write(l.toCSV());
            out.close();
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }
}