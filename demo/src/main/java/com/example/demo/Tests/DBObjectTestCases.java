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
    /*@Test
    public void evaluateReservationObject() {
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
    public void evaluateTableObject() {
        Table tbl_1 = new Table(1, 4, 100, "Location", false);

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
    }*/
}
