/* Assignment: PA-04
 * 11/14/2025
 * Tristen Achterberg
 */

package com.example.demo.Tests;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.example.demo.Core.*;
import com.example.demo.Database.*;

import org.junit.Test;

public class DBObjectTestCases {
    
    // Test #1:
    @Test
    public void evaluateAccountObject() {
        Account acc_1 = new Account("Username", "Password", "Name", 1);

        String[] data = {"Username", "Password", "Name", "1"};
        Account acc_2 = new Account(data);

        assertEquals("Username", acc_1.getUsername());
        assertEquals("Password", acc_1.getPassword());
        assertEquals("Name", acc_1.getName());
        assertEquals(1, acc_1.getAuth());

        assertEquals("Username", acc_2.getUsername());
        assertEquals("Password", acc_2.getPassword());
        assertEquals("Name", acc_2.getName());
        assertEquals(1, acc_2.getAuth());
    }

    // Test #1:
    @Test
    public void evaluateReservationObject() {
        Reservation res_1 = new Reservation(0, "Username", 1, "2030-01-10", "14:00", 8);

        String[] data = {"Username", "2", "2030-01-10", "14:00", "8"};;
        Reservation res_2 = new Reservation(1, data);

        assertEquals(0, res_1.getResNum());
        assertEquals("Username", res_1.getAccUsern());
        assertEquals(1, res_1.getTableNum());
        assertEquals("2030-01-10", res_1.getDate());
        assertEquals("14:00", res_1.getTime());
        assertEquals(8, res_1.getPartySize());

        assertEquals(1, res_2.getResNum());
        assertEquals("Username", res_2.getAccUsern());
        assertEquals(2, res_2.getTableNum());
        assertEquals("2030-01-10", res_2.getDate());
        assertEquals("14:00", res_2.getTime());
        assertEquals(8, res_2.getPartySize());
    }

    // Test #1:
    @Test
    public void evaluateTableObject() {
        Table tbl_1 = new Table(1, 4, 100, "Location", false);

        String[] data = {"4", "100", "Location", "true"};
        Table tbl_2 = new Table(2, data);

        assertEquals(1, tbl_1.getNumber());
        assertEquals(4, tbl_1.getCap());
        assertEquals(100, tbl_1.getPrice());
        assertEquals("Location", tbl_1.getLocation());
        assertEquals(false, tbl_1.checkReservation());

        assertEquals(2, tbl_2.getNumber());
        assertEquals(4, tbl_2.getCap());
        assertEquals(100, tbl_2.getPrice());
        assertEquals("Location", tbl_2.getLocation());
        assertEquals(true, tbl_2.checkReservation());
    }
}
