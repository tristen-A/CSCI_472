package com.example.demo.Core;

import com.example.demo.Database.Table;
import com.example.demo.Database.DatabaseHandler;

import java.util.HashMap;

public class TableManager extends DatabaseHandler {
    private static final String DB_FILE = "dbTables";

    private static HashMap<Integer, String[]> rawDB = new HashMap<>();
    private static HashMap<Integer, Table> TablesDB = new HashMap<>();
    private int CUR_TOP_NUM = 0;

    @Override
    protected void castToObjectDB(HashMap<Integer, String[]> currentDB) {
        for (int item_num : currentDB.keySet()) {
            Table item = new Table(item_num, currentDB.get(item_num));
            TablesDB.put(item_num, item);
            CUR_TOP_NUM++;
        }
    }

    // not override, but required
    protected void castToRawDB(HashMap<Integer, Table> currentDB) {
        rawDB.clear();
        CUR_TOP_NUM = 0;

        for (Table cur_tb : currentDB.values()) {
            String[] data = new String[4];
            data[0] = String.valueOf(cur_tb.getCap());
            data[1] = String.valueOf(cur_tb.getPrice());
            data[2] = cur_tb.getLocation();
            data[3] = String.valueOf(cur_tb.checkReservation());

            rawDB.put(cur_tb.getNumber(), data);
            CUR_TOP_NUM++;
        }
    }

    public TableManager() {
        csvLoader(DB_FILE, rawDB);
        castToObjectDB(rawDB);
    }

    public String addTable(int tbl_num, String[] data) {
        if (verifyTableNum(tbl_num)) {
            return ("Given table #" + tbl_num + " already exists.");
        }
        if (!verifyTableNumRange(tbl_num)) {
            return ("Table number must be above 0 and below 1000.");
        }

        Table tb = new Table(tbl_num, data);
        TablesDB.put(tbl_num, tb);
        CUR_TOP_NUM++;

        return "";
    }
    public String editTable(int tbl_num, String[] data) {
        if (!verifyTableNum(tbl_num)) {
            return ("Given table #" + tbl_num + " does not exist.");
        }

        Table cur_tb = TablesDB.get(tbl_num);
        if (!data[0].isEmpty()) { cur_tb.setCap(Integer.parseInt(data[0])); }
        if (!data[1].isEmpty()) { cur_tb.setPrice(Integer.parseInt(data[1])); }
        if (!data[2].isEmpty()) { cur_tb.setLocation(data[2]); }
        if (!data[3].isEmpty()) { cur_tb.setReservation(Boolean.parseBoolean(data[3])); }
        //TablesDB.replace(tbl_num, cur_tb);

        return "";
    }
    public String deleteTable(int tbl_num) {
        if (!verifyTableNum(tbl_num)) {
            return ("Given table #" + tbl_num + " does not exist.");
        }

        TablesDB.remove(tbl_num);
        CUR_TOP_NUM--;

        return "";
    }

    public HashMap<Integer, Table> getTables() {
        return TablesDB;
    }

    // --- Error checking methods ------------------------------------------------------
    public boolean verifyTableNum(int tbl_num) {
        return (TablesDB.get(tbl_num) != null);
    }
    public boolean verifyTableNumRange(int tbl_num) {
        return (tbl_num > 0 && tbl_num < 1000);
    }
    public boolean verifyWithinCap(int tbl_num, int size) {
        int tbl_cap = TablesDB.get(tbl_num).getCap();
        return (tbl_cap >= size) && (size > 0);
    }
    //----------------------------------------------------------------------------------

    public boolean checkReserved(int table_num) {
        return TablesDB.get(table_num).checkReservation();
    }
    public void updateReserved(int table_num, boolean reserved) {
        Table tbl = TablesDB.get(table_num);
        if (tbl != null) {
            TablesDB.get(table_num).setReservation(reserved);
        }
    }

    public void updateDB() {
        castToRawDB(TablesDB);
        csvWriter(DB_FILE, rawDB);
    }
}
