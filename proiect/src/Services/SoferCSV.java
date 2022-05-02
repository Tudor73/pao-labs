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
    private List<Sofer> users = new ArrayList<>();

    public List<Sofer> getUsers() {
        return users;
    }

    public void setUsers(List<Sofer> users) {
        this.users = users;
    }

    public static SoferCSV getInstance() {
        if (instance == null) {
            instance = new SoferCSV();
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
    public void getCSVData() {
        List<String[]> data = instance.getUsersFromFile("data/sofer.csv");
        for(var objectData : data) {
            Sofer newSofer = new Sofer(
                    objectData[0],
                    objectData[1],
                    objectData[2]
            );
            this.users.add(newSofer);
        }

    }

    public void writeToCSV() {

        try {
            FileWriter out = new FileWriter("data/sofer.csv", true);
            for (var user : this.users) {
                out.write(user.toCSV());
                out.write("\n");
            }
            out.close();
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }
}