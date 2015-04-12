/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Zoli
 */
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class DBQuery 
{
	private DBConnect bdc = new DBConnect();
	private Statement selectStatement = null;
	private ResultSet rs = null;
        private CallableStatement callStatement = null;
	
	public int callStatement(String query)
	{
		try
		{
			bdc.connect();
			selectStatement = (bdc.getConexiune()).createStatement();
			selectStatement.execute(query);
			rs = selectStatement.getResultSet();
                        return 1;
		}
		catch(SQLException sqlex)
		{
			/**
			 * Mesaj de eroare.
			 */
			JOptionPane.showMessageDialog(null, "Data verification has failed...\n"+sqlex.getMessage(),
										  "Error", JOptionPane.ERROR_MESSAGE);
                        return 0;
		}
	}
       
	public void inchideStatement()
	{
		if (selectStatement != null) 
		{ 
            try 
            { 
            	bdc.disconnect();
                selectStatement.close();
                selectStatement = null;
            } 
            catch(SQLException e) 
            {
    			/**
    			 * Mesaj de eroare.
    			 */
    			JOptionPane.showMessageDialog(null, "Cannot close conection to your database!...",
    										  "Error", JOptionPane.ERROR_MESSAGE);
            }    
         }
		if (rs != null) 
		{ 
            try 
            { 
                rs.close();
                rs = null; 
            } 
            catch(SQLException e) 
            {
    			/**
    			 * Mesaj de eroare.
    			 */
            	JOptionPane.showMessageDialog(null, "Cannot close conection to your database!...",
						  "Error", JOptionPane.ERROR_MESSAGE);
            }   
         }
	}
	
	/**
	 * @return ResultSet de la baza de date.
	 */
	public ResultSet getResultSet()
	{
		return rs;
	}
        
	
	/**
	 * @return true daca exista date in rs fals daca nu exista.
	 */
	public boolean isResultSet()
	{
		try
		{
			return rs.next();
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "Data verification has failed!..." + e.getMessage(),
					  "Error", JOptionPane.ERROR_MESSAGE);
		}
		return false;
	}
}

