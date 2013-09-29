/**
 * LoginDbSupport.java
 * 
 * Copyright (c) 2006 Szh
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

import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;

import zju.onlinedoc.meta.UserRecord;

public class LoginDbSupport
{
    // the property about the database
    private static Properties daoPro = null;

    public LoginDbSupport()
    {
        if (daoPro == null)
        {
            // create the property
            daoPro = new Properties();
            InputStream in = LoginDbSupport.class
                    .getResourceAsStream("database1.properties");
            try
            {
                if (in == null)
                {
                    System.out.println("in == null");
                }
                daoPro.load(in);
            }
            catch (IOException e)
            {
                // TODO Auto-generated catch block
                System.out
                        .println("Error: IO exception in reading database property.");
                e.printStackTrace();
            }
        }
    }

    public int getUsrId(String usrName)
    {
        DatabaseManager dao = new DatabaseManager();
        
        String selectSql = daoPro.getProperty("TableOneSelectRecord");
        List<Object> paras = new ArrayList<Object>();
        paras.add(usrName);
        
        ResultSet rs = dao.querySql(selectSql, paras);
        try
        {
            while (rs.next())
            {
                return rs.getInt("userId");
            }
        }
        catch (SQLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally
        {
            // release the resource
            try
            {
                rs.close();
            }
            catch (SQLException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            dao.close();
        }
        
        return -1;
    }
    
    public void insertNewUsr(String userName, String userPwd)
    {
        DatabaseManager dao = new DatabaseManager();
        
        // get the time of the usr creation 
        Calendar cl = Calendar.getInstance();
        String date = cl.get(Calendar.YEAR) + "-" + cl.get(Calendar.MONTH)
            + "-" + cl.get(Calendar.DAY_OF_MONTH) + " " + cl.get(Calendar.HOUR_OF_DAY )
            + ":" + cl.get(Calendar.MINUTE) + ":" + cl.get(Calendar.SECOND);
        
        String insertSql = daoPro.getProperty("TableOneInsertNewUser");       
        List<Object> paras = new ArrayList<Object>();
        paras.add(userName);
        paras.add(userPwd);
        paras.add(date);

        dao.updateSql(insertSql, paras);
        dao.close();
    }

    /**
     * @param usrName
     * @return
     */
    public List<UserRecord> getUserRecord(String usrName)
    {
        DatabaseManager dao = new DatabaseManager();
        
        List<UserRecord> userList = new ArrayList<UserRecord>();

        String selectSql = daoPro.getProperty("TableOneSelectRecord");
        List<Object> paras = new ArrayList<Object>();
        paras.add(usrName);
        // debug information
        // for (Object key : daoPro.keySet())
        // {
        // System.out.println((String)key + "=" +
        // daoPro.getProperty((String)key));
        // }
        // System.out.println(daoPro.getProperty("TableName"));
        // System.out.println(daoPro.getProperty("TableOneColumn1"));
        // System.out.println(daoPro.getProperty("TableOneColumn2"));
        // System.out.println(daoPro.getProperty("TableOneColumn3"));
        // System.out.println(daoPro.getProperty("TableOneSelectRecord"));
        // System.out.println(daoPro.getProperty("TableOneInsertNewUser"));

        ResultSet rs = dao.querySql(selectSql, paras);
        try
        {
            while (rs.next())
            {
                userList.add(new UserRecord(rs.getInt(daoPro
                        .getProperty("TableOneColumn1")), rs.getString(daoPro
                        .getProperty("TableOneColumn2")), rs.getString(daoPro
                        .getProperty("TableOneColumn3"))));
            }
        }
        catch (SQLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally
        {
            // release the resource
            try
            {
                rs.close();
            }
            catch (SQLException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            dao.close();
        }

        return userList;
    }
}
