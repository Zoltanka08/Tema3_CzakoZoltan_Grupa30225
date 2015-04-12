/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Zoli
 */
public class SQLCommands {
    
	private DBQuery dbq = new DBQuery();
        private ResultSet rs = null;
        private ResultSet rs_aux = null;
        public static boolean SearchDiscVisibel = true;
        
        
	
	/**
	 * Metoda pt. verificarea username`ului si a parolei daca exista in baza de date.
         * @return true - utilizator existent, false - utilizator
	 */
	public boolean verifyUserPass(String tfUserText, String pfPassText)
	{
		boolean aux;
		/**
		 * Se realizeaza interogarea dorita.
		 */
		dbq.callStatement("SELECT username, pass FROM customer WHERE Username = '" + tfUserText + "'"
					+ " and Pass = '" + pfPassText + "'");
		
		/**
		 * Returneaza true daca sa returnat ceva de la baza de date, false daca nu.
		 */
		aux = dbq.isResultSet();
		dbq.inchideStatement();
		return aux;
	}
       
        /**  
         * Insereaza un utilizator
	 * @return int - 1 success, 0 - fail
    */
        
        public int insertUser(String nume, String username, String password, String email, String tel, String str, String city, int nr ) 
	{		
            int succes;
            succes = dbq.callStatement("INSERT INTO customer(email, tel, Username, Pass, city, str, nr, nume) VALUES('" + email + "' ,'" + tel + "', '" + username + "', '" +
                    password + "', '" + city + "', '" + str + "', '" + nr + "', '" + nume + "')");
            dbq.inchideStatement(); 
            
            return succes;
        }
        
        /** 
	 * @return Returneaza o lista cu produsele din Warehouse
    */
        
        public DefaultListModel getWarehouseContent()
        {
            DefaultListModel list = new DefaultListModel();
            String nume;
            float price;
            String  content;
            final DBQuery dbq = new DBQuery();
            
            dbq.callStatement("SELECT p.nume AS Name, p.price AS Price, w.pieces AS Pieces FROM product p, warehouse w WHERE p.id = w.id_Product");
		
            this.rs = dbq.getResultSet();

            try {
                while(rs.next())
                {
                    nume = rs.getString("Name");
                    price = rs.getFloat("Price");
                    content = nume + " - " + Float.toString(price) + " RON";
                    list.addElement(content);
                }  
            } catch (SQLException ex) {
                Logger.getLogger(SQLCommands.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            return list;
        }
        
     /** 
      * Sterge un produs din Warehouse
         * @param name numele  produsului 
	 * @return void
    */    
    public void removeProductFromWarehouse(String name)
    {
        dbq.callStatement("DELETE FROM  product WHERE nume = '" + name + "'");
    }
    
    /**
     * Insereaza o lista de produse in OPDept si in Orders
         * @param old_name String - numele veche  
         * @param new_name String - nume nou
         * @param price float - pretul nou 
	 * @return void
    */
    
    public void updateProductFromWarehouse(String old_name, String new_name, float price)
    {
        dbq.callStatement("UPDATE Product SET nume = '" + new_name +  "'" + ", price = " + price + ""
                + "WHERE nume LIKE '" + old_name + "%'");
    }
    
    
    /**
     * Insereaza un produs in Warehouse.
	 * @return void
    */
    public int insertProductInWarehouse(String name, float price, int location, int pieces)
    {
  
        dbq.callStatement("INSERT INTO Product(nume,price) VALUES('" + name + "',"  + price + ")");
        dbq.callStatement("SELECT id FROM Product WHERE nume LIKE '" + name + "%'" );
        rs = dbq.getResultSet();
        int succes = 0;
        try
        {
            rs.next();
            int index = rs.getInt("id");
            succes = dbq.callStatement("INSERT INTO Warehouse(id_Product,location,pieces) VALUES(" + index + ","  + location + "," + pieces + ")");
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, ex.getMessage(),
					  "Error", JOptionPane.ERROR_MESSAGE);
        }
        return succes;
    }
    
/**
 * Verifica daca numele utilizatorului si parola corespunde cu admin
	 * @return true - admin existent, false - admin inexistent
    */    
    
    public boolean verifyAdmin(String username, String pass)
    {
        dbq.callStatement("SELECT id FROM Admin WHERE username = '" + username + "' AND pass = '" + pass + "'");
        if(dbq.isResultSet()) return true;
        else return false;
    }
    
/**
 * Decrementeaza numarul produsului identificat prin numele produsului
	 * @return void
    */     
    
    public void decrementProductFromWarehouse(String name)
    {
        dbq.callStatement("SELECT id FROM product WHERE nume = '" + name  + "'");
        try
        {   
            rs = dbq.getResultSet();
            rs.next();
            int id = rs.getInt("id");
            dbq.callStatement("UPDATE Warehouse SET pieces = pieces-1 WHERE id_Product = " + id);
        }
        catch(SQLException ex )
        {
            JOptionPane.showMessageDialog(null, ex.getMessage(),
					  "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Incrementeaza numarul produsului identificat prin numele produsului
	 * @return void
    */ 
    
    public void incrementProductFromWarehouse(String name)
    {
        int nr;
        dbq.callStatement("SELECT id FROM product WHERE nume = '" + name  + "'");
        try
        {   
            rs = dbq.getResultSet();
            rs.next();
            int id = rs.getInt("id");
            dbq.callStatement("UPDATE Warehouse SET pieces = pieces+1 WHERE id_Product = " + id);
        }
        catch(SQLException ex )
        {
            JOptionPane.showMessageDialog(null, ex.getMessage(),
					  "Error", JOptionPane.ERROR_MESSAGE);
        }
 
    }
    
    /**
     * Insereaza o lista de produse in OPDept si in Orders
         * @param productlist un vector de produse 
	 * @return void
    */ 
    
    public void orderProducts(ArrayList<Product>  productlist)
    {
        int p_id=-1, c_id=-1, o_id=-1;
        for(Product product : productlist)
        {
            dbq.callStatement("SELECT id FROM product WHERE nume = '" + product.getName() + "'");
            try
            {
                rs = dbq.getResultSet();
                if(rs.next())
                {
                    p_id = rs.getInt("id");
                }
            }
            catch(SQLException ex)
            {
                JOptionPane.showMessageDialog(null, ex.getMessage(),
					  "Error", JOptionPane.ERROR_MESSAGE);
            }
            
            dbq.callStatement("SELECT id FROM customer WHERE username = '" + LogIn.username + "'");
            try
            {
                rs = dbq.getResultSet();
                if(rs.next())
                {
                    c_id = rs.getInt("id");
                }
            }
            catch(SQLException ex)
            {
                JOptionPane.showMessageDialog(null, ex.getMessage(),
					  "Error", JOptionPane.ERROR_MESSAGE);
            }
            
            if(c_id!=-1 && p_id!=-1)
            {    
                dbq.callStatement("INSERT INTO orders (id_Product,id_Customer,pieces) VALUES(" + p_id + "," + c_id + "," + 1 + ")");
            }
            
            dbq.callStatement("SELECT id FROM orders WHERE id_Product = " + p_id + " AND id_Customer = " + c_id);
            try
            {
                rs = dbq.getResultSet();
                if(rs.next())
                {
                    o_id = rs.getInt("id");
                }
            }
            catch(SQLException ex)
            {
                JOptionPane.showMessageDialog(null, ex.getMessage(),
					  "Error", JOptionPane.ERROR_MESSAGE);
            }
            
            Date now = new Date();
            Date then = new Date();
            then.setDate(now.getDay()+7);
            String now_s = new String(now.getYear() + "/" + now.getMonth() + "/" + now.getDay());
            String then_s = new String(then.getYear() + "/" + then.getMonth() + "/" + then.getDay());
            
            dbq.callStatement("INSERT INTO opdept (id_Order,start_date,end_date) VALUES (" + o_id + ",'" + now_s + "','" + then_s + "')");
        }
    }
    
    /**
     * Selecteaza un  produs in functie de ID
         * @param id int - ID-ul produsului cautat
	 * @return Product - produsul gasit;
    */ 
    public Product findProductByID(int id)
    {
        dbq.callStatement("SELECT nume, price FROM product WHERE id = " + id);
        String name = "123";
        float price=-1;
            try
            {
                rs = dbq.getResultSet();
                if(rs.next())
                {
                    name = rs.getString("nume");
                    price = rs.getFloat("price");
                }
            }
            catch(SQLException ex)
            {
                JOptionPane.showMessageDialog(null, ex.getMessage(),
					  "Error", JOptionPane.ERROR_MESSAGE);
            }
            Product p = new Product(name,price);
            if(name.equals("123"))
                    return null;
            else
                    return p;
    }
    
    /**
     * Calculeaza numarul total  de produse in Warehouse
     * @return int - numar total de produse
     */
    
    public int findTotalInWarehouse()
    {
        dbq.callStatement("SELECT SUM(pieces) AS total FROM warehouse");
        int total = 0;
            try
            {
                rs = dbq.getResultSet();
                if(rs.next())
                {
                    total = rs.getInt("total");
                }
            }
            catch(SQLException ex)
            {
                JOptionPane.showMessageDialog(null, ex.getMessage(),
					  "Error", JOptionPane.ERROR_MESSAGE);
            }
            
            return total;
    }
        
    public ResultSet getResult()
    {
        return this.rs;
    }
}
