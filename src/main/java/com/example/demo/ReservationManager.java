package com.example.demo;

import com.example.demo.Database.DatabaseHandler;
import com.example.demo.Database.Reservation;

import java.util.HashMap;

public class ReservationManager {
    private static final String DB_FILE = "dbReservations";

    private HashMap<Integer, String[]> rawDB = new HashMap<>();
    private HashMap<Integer, Reservation> ReservDB = new HashMap<>();
    private int CUR_TOP_NUM = 0;

    private void castToObjectDB(HashMap<Integer, String[]> currentDB) {
        for (int item_num : currentDB.keySet()) {
            Reservation item = new Reservation(item_num, currentDB.get(item_num));
            ReservDB.put(item_num, item);
            CUR_TOP_NUM++;
        }
    }

    public ReservationManager() {
        DatabaseHandler.csvLoader(DB_FILE, rawDB);
        castToObjectDB(rawDB);
    }

    public void addAccount(String[] data) {
        //Account acc = new Account(CUR_TOP_NUM, data);
        //AccountsDB.put(CUR_TOP_NUM, acc);
        rawDB.put(CUR_TOP_NUM, data);
        DatabaseHandler.csvWriter(DB_FILE, rawDB);
    }
    public void editAccount(int acc_num, String[] data) {
        String[] new_data = rawDB.get(acc_num);
        for (int i = 0; i < data.length; i++) {
            if (!data[i].isEmpty()) {
                new_data[i] = data[i];
            }
        }
        rawDB.put(acc_num, new_data);
        DatabaseHandler.csvWriter(DB_FILE, rawDB);
        /*
        int cur_ID = 0;
        String usern = data[0];
        String[] old_data;
        for (int i : rawDB.keySet()) {
            if (rawDB.get(i)[0].equals(usern)) {
                old_data = rawDB.get(i);
                cur_ID = i;
            }
        }
        String[] new_data = data.clone();
        for (int i = 0; i < data.length; i++) {
            if (!data[i].equals("")) {
                new_data[i] = data[i+1];
            }
        }
        rawDB.replace(cur_ID, new_data);
         */
    }
    public void deleteAccount(int acc_num) {
        rawDB.remove(acc_num);
        DatabaseHandler.csvWriter(DB_FILE, rawDB);
    }

    public HashMap<Integer, String[]> getTables() {
        return rawDB;
    }
}
