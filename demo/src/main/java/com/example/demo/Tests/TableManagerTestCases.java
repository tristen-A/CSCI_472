/* Assignment: PA-04
 * 11/14/2025
 * Tristen Achterberg
 */

package com.example.demo.Tests;

import com.example.demo.Core.AccountManager;
import com.example.demo.Database.Account;
import org.junit.Test;
import org.junit.AfterClass;

import java.io.*;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class TableManagerTestCases {

    /*public HashMap<String, Account> createObjectDB() {
        HashMap<String, Account> objectDB = new HashMap<>();
        objectDB.put("Username", new Account("Username", "Password", "Name", 1));
        objectDB.put("usern", new Account("usern", "pass", "bill", 4));
        return objectDB;
    }

    public HashMap<Integer, String[]> createRawDB() {
        HashMap<Integer, String[]> rawDB = new HashMap<>();
        rawDB.put(0, new String[] {"Username", "Password", "Name", "1"});
        rawDB.put(1, new String[] {"usern", "pass", "bill", "4"});
        return rawDB;
    }

    public void createDBFile() {
        HashMap<Integer, String[]> rawDB = new HashMap<Integer, String[]>();
        rawDB.put(0, new String[] {"Username", "Password", "Name", "1"});
        rawDB.put(1, new String[] {"usern", "pass", "bill", "4"});

        String CURRENT_DIR = System.getProperty("user.dir");
        String TEST_DIR = CURRENT_DIR + "\\" + "dbAccounts.csv";
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
        String TEST_DIR = CURRENT_DIR + "\\" + "dbAccounts.csv";
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
        HashMap<String, Account> EXPECTED_DB = createObjectDB();
        //HashMap<String, Account> EXPECTED_DB = new HashMap<String, Account>();
        //HashMap<Integer, String[]> TEST_DB = new HashMap<Integer, String[]>();

        createDBFile();
        AccountManager accmgr = new AccountManager();

        HashMap<String, Account> TEST_DB = accmgr.getAccounts();
        Account acc1 = EXPECTED_DB.get("Username");
        Account acc2 = TEST_DB.get("Username");

        assertEquals(acc1.getUsername(), acc2.getUsername());
        assertEquals(acc1.getPassword(), acc2.getPassword());
        assertEquals(acc1.getName(), acc2.getName());
        assertEquals(acc1.getAuth(), acc2.getAuth());

        Account acc3 = EXPECTED_DB.get("usern");
        Account acc4 = TEST_DB.get("usern");

        assertEquals(acc3.getUsername(), acc4.getUsername());
    }

    @Test
    public void evaluateDBSaving() {
        HashMap<Integer, String[]> EXPECTED_DB = createRawDB();

        AccountManager accmgr = new AccountManager();
        accmgr.updateDB();

        HashMap<Integer, String[]> TEST_DB = loadRawDB();

        String[] acc1 = EXPECTED_DB.get(0);
        String[] acc2 = TEST_DB.get(0);

        assertEquals(acc1[0], acc2[0]);
        assertEquals(acc1[1], acc2[1]);
        assertEquals(acc1[2], acc2[2]);
        assertEquals(acc1[3], acc2[3]);

        String[] acc3 = EXPECTED_DB.get(1);
        String[] acc4 = TEST_DB.get(1);

        assertEquals(acc3[0], acc4[0]);
    }

    @Test
    public void evaluateDBAdd() {
        AccountManager accmgr = new AccountManager();

        String[] data = {"bobnt", "billybobjones", "Bobby", "3"};
        //String[] data2 = {"grbnut", "beowulf", "Grendel", "2"};
        accmgr.addAccount(data);
        //accmgr.addAccount(data2);

        Account acc = accmgr.getAccount(data[0]);
        //Account acc2 = accmgr.getAccount(data2[0]);

        assertEquals(data[0], acc.getUsername());
        assertEquals(data[1], acc.getPassword());
        assertEquals(data[2], acc.getName());
        assertEquals(Integer.parseInt(data[3]), acc.getAuth());
    }
    @Test
    public void evaluateDBAddErrors() {
        AccountManager accmgr = new AccountManager();

        String[] data = {"bobnt", "", "Bobby", "3"};
        String error = accmgr.addAccount(data);
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
    }

    @AfterClass
    public static void cleanDBFiles() {
        String CURRENT_DIR = System.getProperty("user.dir");
        String ACCOUNT_DB = CURRENT_DIR + "\\" + "dbAccounts.csv";

        File file = new File(ACCOUNT_DB);
        if (file.delete()) {
            System.out.println("Testing file cleaned!");
        } else {
            System.out.println("File directory '" + ACCOUNT_DB + "' not found.");
        }
    }*/
}
