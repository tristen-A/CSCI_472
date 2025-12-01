package com.example.demo.Core;

import com.example.demo.Database.DatabaseHandler;
import com.example.demo.Database.Reservation;
import com.example.demo.Database.Account;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import static java.time.temporal.ChronoUnit.HOURS;

import java.util.HashMap;
import java.util.Objects;

public class ReservationManager extends DatabaseHandler {
    private static final String DB_FILE = "dbReservations";

    private TableManager TableManager;

    private static HashMap<Integer, String[]> rawDB = new HashMap<>();
    private static HashMap<Integer, Reservation> ReservDB = new HashMap<>();
    private int CUR_TOP_NUM = 0;

    @Override
    protected void castToObjectDB(HashMap<Integer, String[]> currentDB) {
        for (int item_num : currentDB.keySet()) {
            Reservation item = new Reservation(item_num, currentDB.get(item_num));
            ReservDB.put(item_num, item);
            if (item_num > CUR_TOP_NUM) {
                CUR_TOP_NUM = item_num + 1;
            }
        }
    }

    // not override, but required
    protected void castToRawDB(HashMap<Integer, Reservation> currentDB) {
        rawDB.clear();
        CUR_TOP_NUM = 0;

        for (Reservation cur_res : currentDB.values()) {
            String[] data = new String[5];
            data[0] = cur_res.getAccUsern();
            data[1] = String.valueOf(cur_res.getTableNum());
            data[2] = cur_res.getDate();
            data[3] = cur_res.getTime();
            data[4] = String.valueOf(cur_res.getPartySize());

            rawDB.put(cur_res.getResNum(), data);
            CUR_TOP_NUM++;
        }
    }

    public ReservationManager(TableManager TB) {
        this.TableManager = TB;

        csvLoader(DB_FILE, rawDB);
        castToObjectDB(rawDB);
    }

    public String addReservation(String[] data) {
        // Individual case for there being no account currently logged in.
        if (!verifyValidUsername(data[0])) {
            return ("You must log in to create a reservation!");
        }

        int tbl_num = Integer.parseInt(data[1]);
        if (!TableManager.verifyTableNum(tbl_num)) {
            return ("Given table #" + tbl_num + " does not exist.");
        }
        if (TableManager.checkReserved(tbl_num)) {
            return ("Given table #" + tbl_num + " is already reserved.");
        }
        if (!verifyResDateTime(data[2], data[3])) {
            return ("Cannot select a date or time before current date/time.");
        }

        Reservation res = new Reservation(CUR_TOP_NUM, data);
        ReservDB.put(CUR_TOP_NUM, res);
        CUR_TOP_NUM++;

        return "";
    }
    public String editReservation(int res_num, String[] data) {
        int tbl_num = Integer.parseInt(data[1]);

        Reservation cur_res = ReservDB.get(res_num);
        if (!data[0].isEmpty()) { cur_res.setAccUsern(data[0]); }
        if (!data[1].isEmpty()) {
            if (!TableManager.verifyTableNum(tbl_num)) {
                return ("Given table #" + tbl_num + " does not exist.");
            }
            if (TableManager.checkReserved(tbl_num)) {
                return ("Given table #" + tbl_num + " is already reserved.");
            }

            TableManager.updateReserved(cur_res.getTableNum(), false);
            cur_res.setTableNum(tbl_num);
            TableManager.updateReserved(tbl_num, true);
        }
        if (!data[2].isEmpty()) {
            if (!verifyResDateTime(data[2], cur_res.getTime())) {
                return ("Cannot select a date or time before current date/time.");
            }
            cur_res.setDate(data[2]);
        }
        if (!data[3].isEmpty()) {
            if (!verifyResDateTime(cur_res.getDate(), data[3])) {
                return ("Cannot select a date or time before current date/time.");
            }
            cur_res.setTime(data[3]);
        }
        if (!data[4].isEmpty()) {
            if (!TableManager.verifyWithinCap(tbl_num, Integer.parseInt(data[4]))) {
                return ("Party size is not within table capacity.");
            }
            cur_res.setPartySize(Integer.parseInt(data[4]));
        }
        //ReservDB.replace(res_num, cur_res);

        return "";
    }
    public String deleteReservation(int res_num) {
        if (!verifyResNum(res_num)) {
            return ("Given reservtaion #" + res_num + " does not exist.");
        }

        Reservation cur_res = ReservDB.get(res_num);
        TableManager.updateReserved(cur_res.getTableNum(), false);
        ReservDB.remove(res_num);
        CUR_TOP_NUM--;

        return "";
    }

    public HashMap<Integer, Reservation> getReservations() {
        return ReservDB;
    }
    public HashMap<Integer, String[]> getRawDB() {
        return rawDB;
    }

    public HashMap<Integer, Reservation> getAccReservations(String usern) {
        HashMap<Integer, Reservation> return_db = new HashMap<>();

        for (Reservation cur_res : ReservDB.values()) {
            if ( cur_res.getAccUsern().equals(usern) ) {
                return_db.put(cur_res.getResNum(), cur_res);
            }
        }

        return return_db;
    }

    // --- Error checking methods ------------------------------------------------------
    public boolean verifyResNum(int res_num) {
        return (ReservDB.get(res_num) != null);
    }
    public boolean verifyValidUsername(String usern) {
        return (!usern.equals(""));
    }
    public boolean verifyResDateTime(String date_str, String time_str) {
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //SimpleDateFormat sdfTime = new SimpleDateFormat("hh.mm aa");
        try {
            Date date = sdfDate.parse(date_str + " " + time_str + ":00");
            Date cur_date = new Date();

            long diff = date.getTime() - cur_date.getTime();
            long hourDiff = (diff / (1000 * 60 * 60)) % 24;

            return date.after(cur_date) && (hourDiff > 1);
            //return (hourDiff);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /*public String[] verifyResInput(int res_num, String[] data) {
        boolean T1 = verifyResNum()
    }*/
    //----------------------------------------------------------------------------------

    public void updateDB() {
        castToRawDB(ReservDB);
        csvWriter(DB_FILE, rawDB);
    }
}
