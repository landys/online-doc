/**
 * PropertyTest.java
 * 
 * Copyright (c) 2006 Szh
 * All rights free.
 *
 * 
 * Revision History
 *
 * Date				Programmer			Notes
 * -------------	-----------------	---------------------------
 * Feb 03, 2007		Developer Name		initial
 */

package test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import zju.onlinedoc.application.LoginSupport;

public class PropertyTest
{
    public Properties pro;

    public PropertyTest()
    {
        pro = new Properties();
        InputStream in = LoginSupport.class
                .getResourceAsStream(".\\database.properties");
        try
        {
            pro.load(in);
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            System.out
                    .println("Error: IO exception in reading database property.");
            e.printStackTrace();
        }
    }
    
    public static void main(String[] argc)
    {
        PropertyTest p= new PropertyTest();
        
        System.out.println(p.pro.getProperty("TableOneName"));
        System.out.println(p.pro.getProperty("TableOneColumn1"));
        System.out.println(p.pro.getProperty("TableOneColumn2"));
        System.out.println(p.pro.getProperty("TableOneColumn3"));
        System.out.println(p.pro.getProperty("TableOneSelectRecord"));
        System.out.println(p.pro.getProperty("TableOneInsertNewUser"));
    }
}
