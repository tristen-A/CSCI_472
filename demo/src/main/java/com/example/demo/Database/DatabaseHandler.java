package com.example.demo.Database;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.HashMap;

public abstract class DatabaseHandler {
    public static final String CURRENT_DIRECTORY = System.getProperty("user.dir");

    public static void csvLoader(String loadfile, HashMap<Integer, String[]> targetDB) {
        String filepath = CURRENT_DIRECTORY + "\\" + loadfile + ".csv";
        File file = new File(filepath);
        if (file.exists() != true) {
            try {file.createNewFile();} catch (IOException e) {e.printStackTrace();}
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(", ");
                String[] item_data = new String[data.length-1];
                for (int i=0; i<data.length-1; i++) {
                    item_data[i] = data[i+1];
                }
                targetDB.put(Integer.parseInt(data[0]), item_data);
            }
        } catch (IOException e) {
            System.out.println("Indicated filepath: " + filepath + " not found.");
            e.printStackTrace();
        }
    }

    public static void csvWriter(String targetfile, HashMap<Integer, String[]> targetDB) {
        String filepath = CURRENT_DIRECTORY + "\\" + targetfile + ".csv";

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

    // -- Intended for override
    protected abstract void castToObjectDB(HashMap<Integer, String[]> currentDB);
}
