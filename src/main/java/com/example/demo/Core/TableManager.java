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

    public void addTable(int tb_num, String[] data) {
        Table tb = new Table(tb_num, data);
        TablesDB.put(tb_num, tb);
        CUR_TOP_NUM++;
    }
    public void editTable(int tb_num, String[] data) {
        Table cur_tb = TablesDB.get(tb_num);
        if (!data[0].isEmpty()) { cur_tb.setCap(Integer.parseInt(data[0])); }
        if (!data[1].isEmpty()) { cur_tb.setPrice(Integer.parseInt(data[1])); }
        if (!data[2].isEmpty()) { cur_tb.setLocation(data[2]); }
        if (!data[3].isEmpty()) { cur_tb.setReservation(Boolean.parseBoolean(data[3])); }
        TablesDB.replace(tb_num, cur_tb);
    }
    public void deleteTable(int tb_num) {
        TablesDB.remove(tb_num);
        CUR_TOP_NUM--;
    }

    public HashMap<Integer, Table> getTables() {
        return TablesDB;
    }

    public boolean checkReserved(int table_num) {
        return TablesDB.get(table_num).checkReservation();
    }
    public void updateReserved(int table_num, boolean reserved) {
        TablesDB.get(table_num).setReservation(reserved);
    }

    public void updateDB() {
        castToRawDB(TablesDB);
        csvWriter(DB_FILE, rawDB);
    }
}
