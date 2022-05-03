package Services;

import Persoana.User;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserCSV {

    private static UserCSV instance = null;
    private List<User> users = new ArrayList<>();

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public static UserCSV getInstance() {
        if (instance == null) {
            instance = new UserCSV();
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
    public List<User> getCSVData() {
        // gets all the data from the csv file
        List<String[]> data = instance.getUsersFromFile("data/users.csv");
        for(var objectData : data) {
            User newUser = new User(
                    objectData[0],
                    objectData[1]
            );
            this.users.add(newUser);
        }
        return this.users;
    }



    public void writeToCSV(User u) {
        // writes a user to the csv file
        try {
            FileWriter out = new FileWriter("data/users.csv", true);
//            for (var user : this.users) {
//                out.write(user.toCSV());
//                out.write("\n");
//            }
            out.write(u.toCSV());
            out.close();
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }
}