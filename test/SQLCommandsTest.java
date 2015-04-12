/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
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
public class SQLCommandsTest {
    
    public SQLCommandsTest() {
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
     * Test1 of verifyUserPass method, of class SQLCommands.
     */
    @Test
    public void testVerifyUserPass1() {
        System.out.println("verifyUserPass");
        String tfUserText = "Zoli";
        String pfPassText = "1234";
        SQLCommands instance = new SQLCommands();
        boolean expResult = true;
        boolean result = instance.verifyUserPass(tfUserText, pfPassText);
        assertEquals(expResult, result);
    }
    
     /**
     * Test2 of verifyUserPass method, of class SQLCommands.
     */
    @Test
    public void testVerifyUserPass2() {
        System.out.println("verifyUserPass");
        String tfUserText = "Zoli";
        String pfPassText = "0123";
        SQLCommands instance = new SQLCommands();
        boolean expResult = false;
        boolean result = instance.verifyUserPass(tfUserText, pfPassText);
        assertEquals(expResult, result);
    }

    /**
     * Test of insertUser method, of class SQLCommands.
     */
    @Test
    public void testInsertUser() {
        System.out.println("insertUser");
        String nume = "Muresan";
        String username = "Mury";
        String password = "mm.y";
        String email = "mury@yahoo.com";
        String tel = "2341";
        String str = "Calea Floresit";
        String city = "Cluj-Napoca";
        int nr = 123;
        SQLCommands instance = new SQLCommands();
        int expResult = 0;
        int result = instance.insertUser(nume, username, password, email, tel, str, city, nr);
        assertEquals(expResult, result);
    }

   
    /**
     * Test of insertProductInWarehouse method, of class SQLCommands.
     */
    @Test
    public void testInsertProductInWarehouse() {
        System.out.println("insertProductInWarehouse");
        String name = "Clatite";
        float price = 5;
        int location = 4;
        int pieces = 1;
        SQLCommands instance = new SQLCommands();
        int expResult = 1;
        int result = instance.insertProductInWarehouse(name, price, location, pieces);
        assertEquals(expResult, result);
    }

    /**
     * Test1 of verifyAdmin method, of class SQLCommands.
     */
    @Test
    public void testVerifyAdmin1() {
        System.out.println("verifyAdmin");
        String username = "Zoli";
        String pass = "1234";
        SQLCommands instance = new SQLCommands();
        boolean expResult = true;
        boolean result = instance.verifyAdmin(username, pass);
        assertEquals(expResult, result);
    }
    
    /**
     * Test2 of verifyAdmin method, of class SQLCommands. (false)
     */
    @Test
    public void testVerifyAdmin2() {
        System.out.println("verifyAdmin");
        String username = "Mury";
        String pass = "1234";
        SQLCommands instance = new SQLCommands();
        boolean expResult = false;
        boolean result = instance.verifyAdmin(username, pass);
        assertEquals(expResult, result);
    }
    
    /**
     * Test2 of findProductByID method, of class SQLCommands. in-stock 
     */
    @Test
    public void testfindProductByID1() {
        System.out.println("findProductByID");
        int id = 1;
        SQLCommands instance = new SQLCommands();
        String expResult = "Slanina prajita";
        String result = instance.findProductByID(id).getName();
        assertEquals(expResult, result);
    }
    
    /**
     * Test2 of findProductByID method, of class SQLCommands. out-of-stock
     */
    @Test
    public void testfindProductByID2() {
        System.out.println("findProductByID");
        int id = 3;
        SQLCommands instance = new SQLCommands();
        Product expResult = null;
        Product result = instance.findProductByID(id);
        assertEquals(expResult, result);
    }
    
    /**
     * Test2 of findProductByID method, of class SQLCommands. out-of-stock
     */
    @Test
    public void testfindTotalInWarehouse() {
        System.out.println("findTotalInWarehouse");
        SQLCommands instance = new SQLCommands();
        int expResult = 63;
        int result = instance.findTotalInWarehouse();
        assertEquals(expResult, result);
    }

}
