package com.example.demo.Core;

import com.example.demo.Database.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class AccountManager extends DatabaseHandler {
    private static final String DB_FILE = "dbAccounts";

    private static HashMap<Integer, String[]> rawDB = new HashMap<>();
    private static HashMap<String, Account> AccountsDB = new HashMap<>();
    private int CUR_TOP_NUM = 0;

    @Override
    protected void castToObjectDB(HashMap<Integer, String[]> currentDB) {
        for (String[] data : currentDB.values()) {
            Account item = new Account(data);
            AccountsDB.put(item.getUsername(), item);
            CUR_TOP_NUM++;
        }
    }

    // not override, but required
    protected void castToRawDB(HashMap<String, Account> currentDB) {
        rawDB.clear();
        CUR_TOP_NUM = 0;

        for (Account cur_acc : currentDB.values()) {
            String[] data = new String[4];
            data[0] = cur_acc.getUsername();
            data[1] = cur_acc.getPassword();
            data[2] = cur_acc.getName();
            data[3] = String.valueOf(cur_acc.getAuth());

            rawDB.put(CUR_TOP_NUM, data);
            CUR_TOP_NUM++;
        }
    }

    public AccountManager() {
        csvLoader(DB_FILE, rawDB);
        castToObjectDB(rawDB);
    }

    public String addAccount(String[] data) {
        if (!verifyUniqueUsername(data[0])) {
            return ("Account username '" + data[0] + "' already exists.");
        }
        if (!verifyUniquePassword(data[1])) {
            return ("Account password '" + data[1] + "' is already in use.");
        }

        Account acc = new Account(data);
        AccountsDB.put(data[0], acc);
        //rawDB.put(CUR_TOP_NUM, data);
        //csvWriter(DB_FILE, rawDB);
        CUR_TOP_NUM++;

        return "";
    }
    public String editAccount(String usern, String[] data) {
        if (verifyUniqueUsername(usern)) {
            return ("No account found under username '" + usern + "'.");
        }
        if (!verifyUniquePassword(data[1])) {
            return ("Account password '" + data[1] + "' is already in use.");
        }

        Account cur_acc = AccountsDB.get(usern);
        //if (!data[0].isEmpty()) { cur_acc.setUsername(data[0]); }
        if (!data[1].isEmpty()) { cur_acc.setPassword(data[1]); }
        if (!data[2].isEmpty()) { cur_acc.setName(data[2]); }
        if (!data[3].isEmpty()) { cur_acc.setAuth(Integer.parseInt(data[3])); }
        //AccountsDB.replace(usern, cur_acc);

        return "";
    }
    public String deleteAccount(String usern) {
        if (verifyUniqueUsername(usern)) {
            return ("No account found under username '" + usern + "'.");
        }

        //rawDB.remove(acc_num);
        //csvWriter(DB_FILE, rawDB);
        AccountsDB.remove(usern);
        CUR_TOP_NUM--;

        return "";
    }

    public Account getAccount(String usern) {
        for (Account acc : AccountsDB.values()) {
            if (acc.getUsername().equals(usern)) {
                return acc;
            }
        }
        return null;
    }

    public boolean validateLogin(String username, String password) {
        //String[] userData = getAccountData(username);
        Account cur_acc = AccountsDB.get(username);
        if (cur_acc != null) {
            return cur_acc.getPassword().equals(password);
        }
        return false;
    }

    // --- Error checking methods ------------------------------------------------------
    public boolean verifyUniqueUsername(String username) {
        return (AccountsDB.get(username) == null);
    }

    public boolean verifyUniquePassword(String password) {
        for (Account cur_acc : AccountsDB.values()) {
            if (cur_acc.getPassword().equals(password)) {
                return false;
            }
        }
        return true;
    }
    //----------------------------------------------------------------------------------

    public HashMap<String, Account> getAccounts() {
        return AccountsDB;
    }

    public void updateDB() {
        castToRawDB(AccountsDB);
        csvWriter(DB_FILE, rawDB);
    }
}
