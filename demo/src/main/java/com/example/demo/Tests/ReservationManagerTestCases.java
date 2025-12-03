/* Assignment: PA-04
 * 11/14/2025
 * Tristen Achterberg
 */

package com.example.demo.Tests;

import com.example.demo.Core.*;
import com.example.demo.Database.*;
import org.junit.Test;
import org.junit.AfterClass;

import java.io.*;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class ReservationManagerTestCases {

    public HashMap<Integer, Reservation> createObjectDB() {
        HashMap<Integer, Reservation> objectDB = new HashMap<>();
        objectDB.put(0, new Reservation(0, "Username", 1, "2025-12-01", "12:00", 3));
        objectDB.put(1, new Reservation(1, "usern", 2, "2026-01-05", "06:00", 6));
        return objectDB;
    }

    public HashMap<Integer, String[]> createRawDB() {
        HashMap<Integer, String[]> rawDB = new HashMap<>();
        rawDB.put(0, new String[] {"Username", "1", "2025-12-01", "12:00", "3"});
        rawDB.put(1, new String[] {"usern", "2", "2026-01-05", "06:00", "6"});
        return rawDB;
    }

    public void createDBFile() {
        HashMap<Integer, String[]> rawDB = new HashMap<Integer, String[]>();
        rawDB.put(0, new String[] {"Username", "1", "2025-12-01", "12:00", "3"});
        rawDB.put(1, new String[] {"usern", "2", "2026-01-05", "06:00", "6"});

        String CURRENT_DIR = System.getProperty("user.dir");
        String TEST_DIR = CURRENT_DIR + "\\" + "dbReservations.csv";
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
        String TEST_DIR = CURRENT_DIR + "\\" + "dbReservations.csv";
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
        HashMap<Integer, Reservation> EXPECTED_DB = createObjectDB();

        createDBFile();
        ReservationManager resmgr = new ReservationManager(new TableManager());

        HashMap<Integer, Reservation> TEST_DB = resmgr.getReservations();
        Reservation res1 = EXPECTED_DB.get(0);
        Reservation res2 = TEST_DB.get(0);

        assertEquals(res1.getResNum(), res2.getResNum());
        assertEquals(res1.getAccUsern(), res2.getAccUsern());
        assertEquals(res1.getTableNum(), res2.getTableNum());
        assertEquals(res1.getDate(), res2.getDate());
        assertEquals(res1.getTime(), res2.getTime());
        assertEquals(res1.getPartySize(), res2.getPartySize());

        Reservation res3 = EXPECTED_DB.get(1);
        Reservation res4 = TEST_DB.get(1);

        assertEquals(res3.getResNum(), res4.getResNum());
    }

    @Test
    public void evaluateDBSaving() {
        HashMap<Integer, String[]> EXPECTED_DB = createRawDB();

        ReservationManager resmgr = new ReservationManager(new TableManager());
        resmgr.updateDB();

        HashMap<Integer, String[]> TEST_DB = loadRawDB();

        String[] res1 = EXPECTED_DB.get(0);
        String[] res2 = TEST_DB.get(0);

        assertEquals(res1[0], res2[0]);
        assertEquals(res1[1], res2[1]);
        assertEquals(res1[2], res2[2]);
        assertEquals(res1[3], res2[3]);
        assertEquals(res1[4], res2[4]);

        String[] res3 = EXPECTED_DB.get(1);
        String[] res4 = TEST_DB.get(1);

        assertEquals(res3[0], res4[0]);
    }

    @Test
    public void evaluateDBAdd() {
        TableManager tblmgr = new TableManager();
        tblmgr.addTable(1, new String[] {"4", "100", "Gallery A", "true"});
        tblmgr.addTable(2, new String[] {"8", "200", "Gallery B", "true"});
        tblmgr.addTable(3, new String[] {"8", "200", "Gallery C", "false"});

        ReservationManager resmgr = new ReservationManager(tblmgr);

        String[] data = {"New_Username", "3", "2026-01-10", "14:00", "8"};
        resmgr.addReservation(data);

        /*HashMap<Integer, Reservation>  TEST_DB = resmgr.getReservations();
        for (Reservation res : TEST_DB.values()) {
            System.out.println(res.getResNum());
            System.out.println(res.getAccUsern());
            System.out.println(res.getTableNum());
            System.out.println(res.getDate());
            System.out.println(res.getTime());
            System.out.println(res.getTableNum());
        }*/

        HashMap<Integer, Reservation> res_db = resmgr.getReservations();
        Reservation res = res_db.get(2);

        assertEquals(2, res.getResNum());
        assertEquals(data[0], res.getAccUsern());
        assertEquals(Integer.parseInt(data[1]), res.getTableNum());
        assertEquals(data[2], res.getDate());
        assertEquals(data[3], res.getTime());
        assertEquals(Integer.parseInt(data[4]), res.getPartySize());

        tblmgr.deleteTable(1);
        tblmgr.deleteTable(2);
        tblmgr.deleteTable(3);
    }
    @Test
    public void evaluateDBAddErrors() {
        TableManager tblmgr = new TableManager();
        tblmgr.addTable(1, new String[] {"4", "100", "Gallery A", "true"});
        tblmgr.addTable(2, new String[] {"8", "200", "Gallery B", "true"});
        tblmgr.addTable(3, new String[] {"8", "200", "Gallery C", "false"});
        tblmgr.updateDB();

        ReservationManager resmgr = new ReservationManager(tblmgr);

        String[] data = {"", "6", "2026-01-10", "14:00", "8"};
        String error = resmgr.addReservation(data);
        String expected_msg = ("You must log in to create a reservation!");;

        assertEquals(expected_msg, error);

        //AccountManager accmgr = new AccountManager();
        //accmgr.addAccount(new String[] {"Valid_Username", "Valid_Password", "Valid_Name", "4"});

        data = new String[] {"Valid_Username", "6", "2026-01-10", "14:00", "8"};
        error = resmgr.addReservation(data);
        expected_msg = ("Given table #" + Integer.parseInt(data[1]) + " does not exist.");

        assertEquals(expected_msg, error);

        data = new String[] {"Valid_Username", "2", "2026-01-10", "14:00", "8"};
        error = resmgr.addReservation(data);
        expected_msg = ("Given table #" + Integer.parseInt(data[1]) + " is already reserved.");

        assertEquals(expected_msg, error);

        data = new String[] {"Valid_Username", "3", "2023-01-10", "14:00", "8"};
        error = resmgr.addReservation(data);
        expected_msg = ("Cannot select a date or time before current date/time.");

        assertEquals(expected_msg, error);

        //accmgr.deleteAccount("Valid_Username");
        tblmgr.deleteTable(1);
        tblmgr.deleteTable(2);
        tblmgr.deleteTable(3);
    }

    /*@Test
    public void evaluateDBEditUsername() {
        AccountManager accmgr = new AccountManager();
        String[] data1 = {"Res_Account_A", "Res_Account_PassA", "Res_Account_PersonA", "10"};
        accmgr.addAccount(data1);
        String[] data2 = {"Res_Account_B", "Res_Account_PassB", "Res_Account_PersonB", "10"};
        accmgr.addAccount(data2);

        TableManager tblmgr = new TableManager();
        tblmgr.addTable(10, new String[] {"8", "200", "Gallery D", "false"});
        ReservationManager resmgr = new ReservationManager(tblmgr);
        resmgr.addReservation(new String[] {"Res_Account_A", "3", "2030-01-10", "14:00", "8"});

        HashMap<Integer, Reservation> reservations =  resmgr.getAccReservations(data1[0]);
        for (Reservation cur_res : reservations.values()) {
            assertEquals(data[0], cur_res.getAccUsern());
        }

        Reservation prev_res =  resmgr.getReservation(data[0]);
        accmgr.editAccount(data[0], data);
        Reservation new_res = accmgr.getAccount(data[0]);

        /*System.out.println(prev_acc == null);
        System.out.println(new_acc == null);

        assertEquals(prev_acc.getUsername(), new_acc.getUsername());
        assertEquals(data[1], new_acc.getPassword());
        assertEquals(prev_acc.getName(), new_acc.getName());
        assertEquals(prev_acc.getAuth(), new_acc.getAuth());

        //accmgr.updateDB();
    }*/

    @Test
    public void evaluateDBDelete() {
        ReservationManager resmgr = new ReservationManager(new  TableManager());

        HashMap<Integer, Reservation> reservations =  resmgr.getReservations();
        int del_num = 0;
        for (int res_num : reservations.keySet()) {
            del_num = res_num;
            resmgr.deleteReservation(res_num);
            break;
        }

        assertEquals(null, reservations.get(del_num));
    }
    @Test
    public void evaluateDBDeleteErrors() {
        ReservationManager resmgr = new ReservationManager(new  TableManager());

        int res_num = 640064;
        String error = resmgr.deleteReservation(res_num);
        String expected_msg = ("Given reservtaion #" + res_num + " does not exist.");

        assertEquals(expected_msg, error);
    }

    @Test
    public void evaluateGetAccReservations() {
        AccountManager accmgr = new AccountManager();
        String[] data = {"Res_Account_G", "Res_Account_PassG", "Res_Account_PersonG", "10"};
        accmgr.addAccount(data);

        TableManager tblmgr = new TableManager();
        tblmgr.addTable(16, new String[] {"8", "200", "Gallery G", "false"});
        ReservationManager resmgr = new ReservationManager(tblmgr);
        resmgr.addReservation(new String[] {"Res_Account_G", "3", "2030-01-10", "14:00", "8"});

        HashMap<Integer, Reservation> acc_reservations =  resmgr.getAccReservations(data[0]);
        for (Reservation cur_res : acc_reservations.values()) {
            assertEquals(data[0], cur_res.getAccUsern());
        }
    }

    @AfterClass
    public static void cleanDBFiles() {
        String CURRENT_DIR = System.getProperty("user.dir");
        String RESERVATION_DB = CURRENT_DIR + "\\" + "dbReservations.csv";
        String ACCOUNT_DB = CURRENT_DIR + "\\" + "dbAccounts.csv";
        String TABLE_DB = CURRENT_DIR + "\\" + "dbTables.csv";

        AccountManager accmgr = new AccountManager();
        TableManager tblmgr = new TableManager();
        HashMap<String, Account> account_db = accmgr.getAccounts();
        HashMap<Integer, Table> table_db = tblmgr.getTables();

        account_db.clear();
        table_db.clear();

        File file = new File(RESERVATION_DB);
        if (file.delete()) {
            System.out.println("Testing file cleaned!");
        } else {
            System.out.println("File directory '" + RESERVATION_DB + "' not found.");
        }
        file = new File(ACCOUNT_DB);
        if (file.delete()) {
            System.out.println("Testing file cleaned!");
        } else {
            System.out.println("File directory '" + ACCOUNT_DB + "' not found.");
        }
        file = new File(TABLE_DB);
        if (file.delete()) {
            System.out.println("Testing file cleaned!");
        } else {
            System.out.println("File directory '" + TABLE_DB + "' not found.");
        }
    }
}
