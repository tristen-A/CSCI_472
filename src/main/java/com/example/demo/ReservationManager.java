package com.example.demo;

import com.example.demo.Database.Account;
import com.example.demo.Database.DatabaseHandler;
import com.example.demo.Database.Reservation;

import java.util.HashMap;

public class ReservationManager extends DatabaseHandler {
    private static final String DB_FILE = "dbReservations";

    private TableManager TableManager;

    private HashMap<Integer, String[]> rawDB = new HashMap<>();
    private HashMap<Integer, Reservation> ReservDB = new HashMap<>();
    private int CUR_TOP_NUM = 0;

    @Override
    protected void castToObjectDB(HashMap<Integer, String[]> currentDB) {
        for (int item_num : currentDB.keySet()) {
            Reservation item = new Reservation(item_num, currentDB.get(item_num));
            ReservDB.put(item_num, item);
            CUR_TOP_NUM++;
        }
    }

    // not override, but required
    protected void castToRawDB(HashMap<Integer, Reservation> currentDB) {
        rawDB.clear();
        CUR_TOP_NUM = 0;

        for (Reservation cur_res : currentDB.values()) {
            String[] data = new String[4];
            data[0] = cur_res.getAccUsern();
            data[1] = String.valueOf(cur_res.getTableNum());
            data[2] = cur_res.getDate();
            data[3] = cur_res.getTime();

            rawDB.put(cur_res.getResNum(), data);
            CUR_TOP_NUM++;
        }
    }

    public ReservationManager(TableManager TB) {
        this.TableManager = TB;

        csvLoader(DB_FILE, rawDB);
        castToObjectDB(rawDB);
    }

    public void addReservation(String[] data) {
        Reservation res = new Reservation(CUR_TOP_NUM, data);
        ReservDB.put(CUR_TOP_NUM, res);
        CUR_TOP_NUM++;
    }
    public void editReservation(int res_num, String[] data) {
        Reservation cur_res = ReservDB.get(res_num);
        if (!data[0].isEmpty()) { cur_res.setAccUsern(data[0]); }
        if (!data[1].isEmpty()) { cur_res.setTableNum(Integer.parseInt(data[1])); }
        if (!data[2].isEmpty()) { cur_res.setDate(data[2]); }
        if (!data[3].isEmpty()) { cur_res.setTime(data[3]); }
        ReservDB.replace(res_num, cur_res);
    }
    public void deleteReservation(int res_num) {
        ReservDB.remove(res_num);
        CUR_TOP_NUM--;
    }

    public HashMap<Integer, Reservation> getReservations() {
        return ReservDB;
    }

    public void updateDB() {
        castToRawDB(ReservDB);
        csvWriter(DB_FILE, rawDB);
    }
}
