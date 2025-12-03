/* Assignment: PA-04
 * 11/14/2025
 * Tristen Achterberg
 */

package com.example.demo.Tests;

import com.example.demo.Core.*;
import com.example.demo.Database.*;
import org.junit.After;
import org.junit.Test;
import org.junit.AfterClass;

import java.io.*;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;

public class DBHandlerTestCases {
    
    // Test #1:
    public HashMap<Integer, String[]> createTestDB() {
        HashMap<Integer, String[]> TEST_DB = new HashMap<Integer, String[]>();
        TEST_DB.put(1, new String[] {"Data1", "Data2", "Data3", "Data4"});
        TEST_DB.put(2, new String[] {"Item1", "Item1", "Item1", "Item1"});
        return TEST_DB;
    }

    @Test
    public void evaluateCSVLoader() {
        String CURRENT_DIR = System.getProperty("user.dir");
        String TEST_DIR = CURRENT_DIR + "\\" + "TestDB.csv";
        HashMap<Integer, String[]> TEST_DB = createTestDB();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(TEST_DIR))) {
            for (int item_num : TEST_DB.keySet()) {
                String[] data = TEST_DB.get(item_num);
                String line = item_num + ", ";
                for (String txt : data) {line += txt + ", "; }
                line = line.substring(0, line.length()-2);
                writer.write(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        HashMap<Integer, String[]> loadDB = new HashMap<Integer, String[]>();
        DatabaseHandler.csvLoader("TestDB", loadDB);

        String[] l1 = TEST_DB.get(1);
        String[] l2 = loadDB.get(1);
        /*System.out.println(l1[0] + l1[1] + l1[2] + l1[3]);
        System.out.println(l2[0] + l2[1] + l2[2] + l2[3]);*/

        assertEquals(l1[0], l2[0]);
        assertEquals(l1[1], l2[1]);
        assertEquals(l1[2], l2[2]);
        assertEquals(l1[3], l2[3]);

        String[] l3 = TEST_DB.get(2);
        String[] l4 = loadDB.get(2);
        assertEquals(l3[0], l4[0]);

        File TEST_FILE = new File(TEST_DIR);
    }

    @Test
    public void evaluateCSVWriter() {
        String CURRENT_DIR = System.getProperty("user.dir");
        String TEST_DIR = CURRENT_DIR + "\\" + "TestDB.csv";
        HashMap<Integer, String[]> TEST_DB = createTestDB();

        DatabaseHandler.csvWriter("TestDB", TEST_DB);
        HashMap<Integer, String[]> loadDB = new HashMap<Integer, String[]>();

        try (BufferedReader reader = new BufferedReader(new FileReader(TEST_DIR))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(", ");
                String[] item_data = new String[data.length-1];
                for (int i=0; i<data.length-1; i++) {
                    item_data[i] = data[i+1];
                }
                loadDB.put(Integer.parseInt(data[0]), item_data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] l1 = TEST_DB.get(1);
        String[] l2 = loadDB.get(1);

        assertEquals(l1[0], l2[0]);
        assertEquals(l1[1], l2[1]);
        assertEquals(l1[2], l2[2]);
        assertEquals(l1[3], l2[3]);

        String[] l3 = TEST_DB.get(2);
        String[] l4 = loadDB.get(2);
        assertEquals(l3[0], l4[0]);
    }

    @AfterClass
    public static void cleanDBFiles() {
        String CURRENT_DIR = System.getProperty("user.dir");
        String TEST_DB = CURRENT_DIR + "\\" + "TestDB.csv";

        File file = new File(TEST_DB);
        if (file.delete()) {
            System.out.println("Testing file cleaned!");
        } else {
            System.out.println("File directory '" + TEST_DB + "' not found.");
        }
    }
}
