/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.ResultSet;
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
public class DBQueryTest {
    
    public DBQueryTest() {
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
     * Test1 of callStatement method, of class DBQuery.
     */
    @Test
    public void testCallStatement1() {
        System.out.println("callStatement");
        String query = "SELECT * FROM product";
        DBQuery instance = new DBQuery();
        int expResult = 1;
        int result = instance.callStatement(query);
        assertEquals(expResult, result);

    }
    
     /**
     * Test2 of callStatement method, of class DBQuery.
     */
    @Test
    public void testCallStatement2() {
        System.out.println("callStatement");
        String query = "SELECT * FROM prod";
        DBQuery instance = new DBQuery();
        int expResult = 0;
        int result = instance.callStatement(query);
        assertEquals(expResult, result);

    }
  
}
