package com.example.demo.Database;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

import java.util.HashMap;
import java.net.URL;

public abstract class DatabaseHandler {
    public static final String CURRENT_DIRECTORY = System.getProperty("user.dir");
    //public static final String CURRENT_DIRECTORY = DatabaseHandler.class.getResource("/com/example/demo/Database/dbAccounts.csv").toString();
    //public static final String CURRENT_DIRECTORY = Paths.get(".").toAbsolutePath().toString();

    public static void csvLoader(String loadfile, HashMap<Integer, String[]> targetDB) {
        String filepath = CURRENT_DIRECTORY + "\\" + loadfile + ".csv";
        //String filepath = CURRENT_DIRECTORY;
        File file = new File(filepath);
        if (file.exists() != true) {
            try {file.createNewFile();} catch (IOException e) {e.printStackTrace();}
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
            String line;
            int cur_num = 0;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(", ");
                String[] item_data = new String[data.length-1];
                for (int i=0; i<data.length-1; i++) {
                    item_data[i] = data[i+1];
                }
                targetDB.put(cur_num, item_data);

                cur_num++;
            }
        } catch (IOException e) {
            //System.out.println("Indicated filepath: " + filepath + " not found.");
            e.printStackTrace();
        }
    }

    public static void csvWriter(String targetfile, HashMap<Integer, String[]> targetDB) {
        String filepath = CURRENT_DIRECTORY + "\\" + targetfile + ".csv";
        //String filepath = CURRENT_DIRECTORY;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filepath))) {
            for (int item_num : targetDB.keySet()) {
                String[] data = targetDB.get(item_num);
                String line = item_num + ", ";
                for (String txt : data) {line += txt + ", "; }
                line = line.substring(0, line.length()-2);
                writer.write(line + "\n");
            }
        } catch (IOException e) {
            System.out.println("Indicated filepath: " + filepath + " not found.");
            e.printStackTrace();
        }
    }

    public static void appendDB(HashMap<Integer, String[]> targetDB, int item_num, String[] data) {
        targetDB.put(item_num, data);
    }
    public static void editDB(HashMap<Integer, String[]> targetDB, int item_num, String[] data) {
        targetDB.replace(item_num, data);
    }
    public static void popDB(HashMap<Integer, String[]> targetDB, int item_num) {
        targetDB.remove(item_num);
    }

    public static void printDB(HashMap<Integer, String[]> targetDB) {
        for (String[] data : targetDB.values()) {
            for (String line : data) {
                System.out.print(line + ", ");
            }
            System.out.println();
        }
    }

    // -- Intended for override
    protected abstract void castToObjectDB(HashMap<Integer, String[]> currentDB);

    protected abstract void castToRawDB(HashMap<String, Account> currentDB);
}
