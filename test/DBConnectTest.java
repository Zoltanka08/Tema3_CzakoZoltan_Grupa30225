/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.Connection;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Zoli
 */
public class DBConnectTest {
    
    public DBConnectTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getConexiune method, of class DBConnect.
     */
    @Test
    public void testGetConexiune() {
        System.out.println("getConexiune");
        DBConnect instance = new DBConnect();
        Connection expResult = null;
        Connection result = instance.getConexiune();
        assertEquals(expResult, result);
    }
    
}
