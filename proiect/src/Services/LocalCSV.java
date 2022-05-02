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
    private List<Local> users = new ArrayList<>();

    public List<Local> getUsers() {
        return users;
    }

    public void setUsers(List<Local> users) {
        this.users = users;
    }

    public static LocalCSV getInstance() {
        if (instance == null) {
            instance = new LocalCSV();
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
        List<String[]> data = instance.getUsersFromFile("data/locale.csv");
        for(var objectData : data) {
            Local newLocal = new Restaurant(
                    objectData[0],
                    objectData[1],
                    objectData[2],
                    objectData[3]

            );
            this.users.add(newLocal);
        }

    }

    public void writeToCSV() {

        try {
            FileWriter out = new FileWriter("data/locale.csv", true);
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