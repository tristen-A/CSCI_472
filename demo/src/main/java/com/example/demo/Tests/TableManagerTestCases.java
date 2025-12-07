/* Assignment: PA-04
 * 11/14/2025
 * Tristen Achterberg
 */

package com.example.demo.Tests;

import com.example.demo.Core.TableManager;
import com.example.demo.Database.Reservation;
import com.example.demo.Database.Table;
import org.junit.Test;
import org.junit.AfterClass;

import java.io.*;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class TableManagerTestCases {

    public HashMap<Integer, Table> createObjectDB() {
        HashMap<Integer, Table> objectDB = new HashMap<>();
        objectDB.put(1, new Table(1, 4, 100, "Gallery A", false));
        objectDB.put(2, new Table(2, 8, 200, "Gallery B", false));
        return objectDB;
    }

    public HashMap<Integer, String[]> createRawDB() {
        HashMap<Integer, String[]> rawDB = new HashMap<>();
        rawDB.put(1, new String[] {"4", "100", "Gallery A", "false"});
        rawDB.put(2, new String[] {"8", "200", "Gallery B", "false"});
        return rawDB;
    }

    public void createDBFile() {
        HashMap<Integer, String[]> rawDB = new HashMap<Integer, String[]>();
        rawDB.put(1, new String[] {"4", "100", "Gallery A", "false"});
        rawDB.put(2, new String[] {"8", "200", "Gallery B", "false"});

        String CURRENT_DIR = System.getProperty("user.dir");
        String TEST_DIR = CURRENT_DIR + "\\" + "dbTables.csv";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(TEST_DIR))) {
            for (int item_num : rawDB.keySet()) {
                String[] data = rawDB.get(item_num);
                String line = item_num + ", ";
                for (String txt : data) {line += txt + ", "; }
                line = line.substring(0, line.length()-2);
                writer.write(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public HashMap<Integer, String[]> loadRawDB() {
        HashMap<Integer, String[]> loadDB = new HashMap<Integer, String[]>();

        String CURRENT_DIR = System.getProperty("user.dir");
        String TEST_DIR = CURRENT_DIR + "\\" + "dbTables.csv";
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

        return loadDB;
    }
    
    // Test #1:
    @Test
    public void evaluateDBLoading() {
        HashMap<Integer, Table> EXPECTED_DB = createObjectDB();

        createDBFile();
        TableManager tblmgr = new TableManager();

        HashMap<Integer, Table> TEST_DB = tblmgr.getTables();
        Table tbl1 = EXPECTED_DB.get(1);
        Table tbl2 = TEST_DB.get(1);

        assertEquals(tbl1.getNumber(), tbl2.getNumber());
        assertEquals(tbl1.getCap(), tbl2.getCap());
        assertEquals(tbl1.getPrice(), tbl2.getPrice());
        assertEquals(tbl1.getLocation(), tbl2.getLocation());
        assertEquals(tbl1.checkReservation(), tbl2.checkReservation());

        Table tbl3 = EXPECTED_DB.get(2);
        Table tbl4 = TEST_DB.get(2);

        assertEquals(tbl3.getNumber(), tbl4.getNumber());
    }

    @Test
    public void evaluateDBSaving() {
        HashMap<Integer, String[]> EXPECTED_DB = createRawDB();

        TableManager tblmgr = new TableManager();
        tblmgr.updateDB();

        HashMap<Integer, String[]> TEST_DB = loadRawDB();

        String[] tbl1 = EXPECTED_DB.get(1);
        String[] tbl2 = TEST_DB.get(1);

        assertEquals(tbl1[0], tbl2[0]);
        assertEquals(tbl1[1], tbl2[1]);
        assertEquals(tbl1[2], tbl2[2]);
        assertEquals(tbl1[3], tbl2[3]);

        String[] tbl3 = EXPECTED_DB.get(2);
        String[] tbl4 = TEST_DB.get(2);

        assertEquals(tbl3[0], tbl4[0]);
    }

    @Test
    public void evaluateDBAdd() {
        TableManager tblmgr = new TableManager();

        String[] data = {"4", "100", "Gallery C", "false"};
        tblmgr.addTable(3, data);

        HashMap<Integer, Table> tbl_db = tblmgr.getTables();
        Table tbl = tbl_db.get(3);

        assertEquals(3, tbl.getNumber());
        assertEquals(Integer.parseInt(data[0]), tbl.getCap());
        assertEquals(Integer.parseInt(data[1]), tbl.getPrice());
        assertEquals(data[2], tbl.getLocation());
        assertEquals(Boolean.parseBoolean(data[3]), tbl.checkReservation());
    }
    @Test
    public void evaluateDBAddErrors() {
        TableManager tblmgr = new TableManager();

        String[] data = {"", "", "", ""};
        String error = tblmgr.addTable(3, data);
        String expected_msg = ("Cannot add a table with incomplete fields!");

        assertEquals(expected_msg, error);

        data = new String[] {"0", "100", "Gallery C", "false"};
        error = tblmgr.addTable(3, data);
        expected_msg = ("Given table #" + 3 + " already exists.");

        assertEquals(expected_msg, error);

        data = new String[] {"0", "100", "Gallery C", "false"};
        error = tblmgr.addTable(0, data);
        expected_msg = ("Table number must be above 0 and below 1000.");

        assertEquals(expected_msg, error);

        data = new String[] {"0", "100", "Gallery C", "false"};
        error = tblmgr.addTable(1001, data);
        expected_msg = ("Table number must be above 0 and below 1000.");

        assertEquals(expected_msg, error);

        data = new String[] {"0", "100", "Gallery C", "false"};
        error = tblmgr.addTable(10, data);
        expected_msg = ("Table capacity must be greater than 0.");

        assertEquals(expected_msg, error);
    }

    @Test
    public void evaluateDBEditCap() {
        TableManager tblmgr = new TableManager();

        int tbl_num = 4;
        String[] data = {"4", "100", "Gallery C", "false"};
        tblmgr.addTable(tbl_num, data);

        data = new String[] {"8", "", "", ""};
        HashMap<Integer, Table> tables =  tblmgr.getTables();
        Table prev_tbl =  tables.get(tbl_num);
        tblmgr.editTable(tbl_num, data);
        Table new_tbl = tables.get(tbl_num);

        assertEquals(Integer.parseInt(data[0]), new_tbl.getCap());
        assertEquals(prev_tbl.getPrice(), new_tbl.getPrice());
        assertEquals(prev_tbl.getLocation(), new_tbl.getLocation());
        assertEquals(prev_tbl.checkReservation(), new_tbl.checkReservation());
    }
    @Test
    public void evaluateDBEditPrice() {
        TableManager tblmgr = new TableManager();

        int tbl_num = 5;
        String[] data = {"4", "100", "Gallery C", "false"};
        tblmgr.addTable(tbl_num, data);

        data = new String[] {"", "200", "", ""};
        HashMap<Integer, Table> tables =  tblmgr.getTables();
        Table prev_tbl =  tables.get(tbl_num);
        tblmgr.editTable(tbl_num, data);
        Table new_tbl = tables.get(tbl_num);

        assertEquals(prev_tbl.getCap(), new_tbl.getCap());
        assertEquals(Integer.parseInt(data[1]), new_tbl.getPrice());
        assertEquals(prev_tbl.getLocation(), new_tbl.getLocation());
        assertEquals(prev_tbl.checkReservation(), new_tbl.checkReservation());
    }
    @Test
    public void evaluateDBEditLocation() {
        TableManager tblmgr = new TableManager();

        int tbl_num = 6;
        String[] data = {"4", "100", "Gallery C", "false"};
        tblmgr.addTable(tbl_num, data);

        data = new String[] {"", "", "Gallery Boggle", ""};
        HashMap<Integer, Table> tables =  tblmgr.getTables();
        Table prev_tbl =  tables.get(tbl_num);
        tblmgr.editTable(tbl_num, data);
        Table new_tbl = tables.get(tbl_num);

        assertEquals(prev_tbl.getCap(), new_tbl.getCap());
        assertEquals(prev_tbl.getPrice(), new_tbl.getPrice());
        assertEquals(data[2], new_tbl.getLocation());
        assertEquals(prev_tbl.checkReservation(), new_tbl.checkReservation());
    }
    @Test
    public void evaluateDBEditReserved() {
        TableManager tblmgr = new TableManager();

        int tbl_num = 7;
        String[] data = {"4", "100", "Gallery C", "false"};
        tblmgr.addTable(tbl_num, data);

        data = new String[] {"", "", "Gallery Boggle", ""};
        HashMap<Integer, Table> tables =  tblmgr.getTables();
        Table prev_tbl =  tables.get(tbl_num);
        tblmgr.editTable(tbl_num, data);
        Table new_tbl = tables.get(tbl_num);

        assertEquals(prev_tbl.getCap(), new_tbl.getCap());
        assertEquals(prev_tbl.getPrice(), new_tbl.getPrice());
        assertEquals(prev_tbl.getLocation(), new_tbl.getLocation());
        assertEquals(Boolean.parseBoolean(data[3]), new_tbl.checkReservation());
    }

    @Test
    public void evaluateDBEditErrors() {
        TableManager tblmgr = new TableManager();

        String[] data = {"4", "100", "Gallery C", "false"};
        int tbl_num = 811;

        String error_msg = tblmgr.editTable(tbl_num, data);
        assertEquals(error_msg, "Given table #" + tbl_num + " does not exist.");

        tbl_num = 8;
        tblmgr.addTable(tbl_num, data);
        data = new String[] {"0", "100", "Gallery C", "false"};

        error_msg = tblmgr.editTable(tbl_num, data);
        assertEquals(error_msg, "Table capacity must be greater than 0.");
    }

    @Test
    public void evaluateDBDelete() {
        TableManager tblmgr = new TableManager();

        String[] data = {"4", "100", "Gallery C", "false"};
        int tbl = 4;
        tblmgr.addTable(tbl, data);
        tblmgr.deleteTable(tbl);

        assertEquals(null, tblmgr.getTables().get(tbl));
    }
    @Test
    public void evaluateDBDeleteErrors() {
        TableManager tblmgr = new TableManager();

        int tbl = 10;
        String error = tblmgr.deleteTable(tbl);
        String expected_msg = ("Given table #" + tbl + " does not exist.");

        assertEquals(expected_msg, error);
    }

    @Test
    public void evaluateCheckReserved() {
        TableManager tblmgr = new TableManager();

        String[] data = {"4", "100", "Gallery C", "true"};
        int tbl = 11;
        tblmgr.addTable(tbl, data);

        boolean reserved = tblmgr.checkReserved(tbl);
        assertEquals(true, reserved);
    }

    @Test
    public void evaluateUpdateReserved() {
        TableManager tblmgr = new TableManager();

        String[] data = {"4", "100", "Gallery C", "true"};
        int tbl = 12;
        tblmgr.addTable(tbl, data);

        tblmgr.updateReserved(tbl, false);
        boolean reserved = tblmgr.checkReserved(tbl);
        assertEquals(false, reserved);
    }

    @AfterClass
    public static void cleanDBFiles() {
        String CURRENT_DIR = System.getProperty("user.dir");
        String TABLE_DB = CURRENT_DIR + "\\" + "dbTables.csv";

        File file = new File(TABLE_DB);
        if (file.delete()) {
            System.out.println("Testing file cleaned!");
        } else {
            System.out.println("File directory '" + TABLE_DB + "' not found.");
        }
    }
}
