/**
 * DatabaseManager.java
 * 
 * Copyright (c) 2006 szh
 * All rights free.
 *
 * 
 * Revision History
 *
 * Date				Programmer			Notes
 * -------------	-----------------	---------------------------
 * Nor, 2007		Developer Name		initial
*/

package zju.onlinedoc.dao;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.globus.wsrf.Constants;
import org.globus.wsrf.ResourceContext;
import org.globus.wsrf.ResourceContextException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DatabaseManager
{
    // every new instance has a connection to database
    private Connection dataCn;
    // the statement
    private PreparedStatement preState;
    // wether the last operation is updating the database
    private boolean isLastUpdata;
    
    // the debug construct function, native connect to database
    public DatabaseManager(int debug)
    {
        String driveName = "org.gjt.mm.mysql.Driver";
        String url = "jdbc:mysql://10.214.20.212:3306/onlinedoc";
        String username = "root";
        String password = "123456";
        try
        {
            Class.forName(driveName);
            dataCn = DriverManager.getConnection(url, username, password);
            dataCn.setAutoCommit(true);
        }
        catch (ClassNotFoundException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("Error: Mysql Driver not found.");
        }
        catch (SQLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("Error: Fail to get Mysql connection.");
        }
    }
    
    public DatabaseManager()
    {
        DataSource source=null;
        try 
        {
            // get the connection to the database
            ResourceContext ctx = ResourceContext.getResourceContext();
            Context initialContext = new InitialContext();
          
            String lookupString = Constants.JNDI_SERVICES_BASE_NAME + ctx.getService() + "/UserLogin";
            source = (DataSource) initialContext.lookup(lookupString);
            
            dataCn = source.getConnection();
            isLastUpdata = false;
            preState = null;
        } 
        catch (NamingException e) 
        {
            e.printStackTrace();
        } 
        catch (ResourceContextException e)
        {
            e.printStackTrace();
        }
        catch (SQLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public ResultSet querySql(final String sql, final List paras)
    {
        ResultSet rs = null;
        isLastUpdata = false;
        prepareStatement(sql, paras);
        try
        {
            rs = preState.executeQuery();
        }
        catch (SQLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return rs;
    }
    
    public boolean updateSql(final String sql, final List paras)
    {       
        isLastUpdata = true;
        prepareStatement(sql, paras);
        try
        {
            preState.executeUpdate();
            return true;
        }
        catch (SQLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return false;
    }
    
    public int getLastID()
    {
        if(preState != null && isLastUpdata == true)
        {
            try
            {
                ResultSet rs = preState.getGeneratedKeys();
                if(rs.next())
                {
                    return (int)rs.getInt(1);
                }
            }
            catch (SQLException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return -1;
    }
    
    private void prepareStatement(final String sql, final List paras)
    {
        try
        {
            preState = dataCn.prepareStatement(sql);
            if(paras != null)
            {
                for(int i = 0; i < paras.size(); i++)
                {
                    Object para = paras.get(i);
                    int index = i + 1;
                    if (para instanceof String)
                    {
                        preState.setString(index, (String) para);
                    }
                    else if (para instanceof Integer)
                    {
                        preState.setInt(index, ((Integer) para).intValue());
                    }
                    else if (para instanceof Double)
                    {
                        preState.setDouble(index, ((Double) para).doubleValue());
                    }
                    else if (para instanceof Float)
                    {
                        preState.setFloat(index, ((Float) para).floatValue());
                    }
                    else
                    {
                        preState.setObject(index, para);
                    }
                }
            }
        }
        catch (SQLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public void close()
    {
        if(preState != null)
        {
            try
            {
                preState.close();
            }
            catch (SQLException e)
            {
                System.out.println("SQL preparestatement close error!");
                e.printStackTrace();
            }
        }
        
        if(dataCn != null)
        {
            try
            {
                dataCn.close();
            }
            catch (SQLException e)
            {
                System.out.println("SQL connection close error!!!");
                e.printStackTrace();
            }
        }
        
       
    }
}
