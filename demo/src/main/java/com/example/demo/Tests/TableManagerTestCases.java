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
    /*@Test
    public void evaluateDBAddErrors() {
        TableManager tblmgr = new TableManager();

        String[] data = {"4", "100", "Gallery C", "false"};
        String error = tblmgr.addTable(3, data);
        String expected_msg = ("Account username '" + data[0] + "' already exists.");

        assertEquals(expected_msg, error);

        data = new String[] {"bobnt_new", "billybobjones", "Bobby", "3"};
        error = accmgr.addAccount(data);
        expected_msg = ("Account password '" + data[1] + "' is already in use.");

        assertEquals(expected_msg, error);
    }

    @Test
    public void evaluateDBEditUsername() {
        AccountManager accmgr = new AccountManager();

        String[] data = {"Username", "New_Password", "", ""};
        //accmgr.addAccount(data);
        HashMap<String, Account>  TEST_DB = accmgr.getAccounts();
        for (Account acc : TEST_DB.values()) {
            System.out.println(acc.getUsername());
            System.out.println(acc.getPassword());
            System.out.println(acc.getName());
            System.out.println(acc.getAuth());
        }

        Account prev_acc =  accmgr.getAccount(data[0]);
        accmgr.editAccount(data[0], data);
        Account new_acc = accmgr.getAccount(data[0]);

        System.out.println(prev_acc == null);
        System.out.println(new_acc == null);

        assertEquals(prev_acc.getUsername(), new_acc.getUsername());
        assertEquals(data[1], new_acc.getPassword());
        assertEquals(prev_acc.getName(), new_acc.getName());
        assertEquals(prev_acc.getAuth(), new_acc.getAuth());

        //accmgr.updateDB();
    }
    @Test
    public void evaluateDBEditName() {
        AccountManager accmgr = new AccountManager();

        String[] data = {"Username", "", "New_Name", ""};

        Account prev_acc =  accmgr.getAccount(data[0]);
        accmgr.editAccount(data[0], data);
        Account new_acc = accmgr.getAccount(data[0]);

        assertEquals(prev_acc.getUsername(), new_acc.getUsername());
        assertEquals(prev_acc.getPassword(), new_acc.getPassword());
        assertEquals(data[2], new_acc.getName());
        assertEquals(prev_acc.getAuth(), new_acc.getAuth());
    }
    @Test
    public void evaluateDBEditAuth() {
        AccountManager accmgr = new AccountManager();

        String[] data = {"Username", "", "", "0"};

        Account prev_acc =  accmgr.getAccount(data[0]);
        accmgr.editAccount(data[0], data);
        Account new_acc = accmgr.getAccount(data[0]);

        assertEquals(prev_acc.getUsername(), new_acc.getUsername());
        assertEquals(prev_acc.getPassword(), new_acc.getPassword());
        assertEquals(prev_acc.getName(), new_acc.getName());
        assertEquals(Integer.parseInt(data[3]), new_acc.getAuth());
    }

    @Test
    public void evaluateDBDelete() {
        AccountManager accmgr = new AccountManager();

        String user = "bobnt";
        accmgr.deleteAccount(user);

        assertEquals(null, accmgr.getAccount(user));
    }
    @Test
    public void evaluateDBDeleteErrors() {
        AccountManager accmgr = new AccountManager();

        String usern = "Nonexisting_Username";
        String error = accmgr.deleteAccount(usern);
        String expected_msg = ("No account found under username '" + usern + "'.");

        assertEquals(expected_msg, error);
    }

    @Test
    public void evaluateLogin() {
        AccountManager accmgr = new AccountManager();

        boolean valid_login = accmgr.validateLogin("Username", "Password");
        boolean invalid_login = accmgr.validateLogin("Username", "Not_Password");

        assertEquals(true, valid_login);
        assertEquals(false, invalid_login);
    }*/

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
