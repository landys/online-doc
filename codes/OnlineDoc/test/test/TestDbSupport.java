/**
 * TestDbSupport.java
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

import java.util.Calendar;

import zju.onlinedoc.dao.FileDbSupport;
import zju.onlinedoc.dao.LoginDbSupport;

public class TestDbSupport
{
    public static void main(String[] argc)
    {
        Calendar cl = Calendar.getInstance();

        System.out.println(cl.get(Calendar.YEAR) + "-" + cl.get(Calendar.MONTH)
                + "-" + cl.get(Calendar.DAY_OF_MONTH) + " " + cl.get(Calendar.HOUR_OF_DAY )
                + ":" + cl.get(Calendar.MINUTE) + ":" + cl.get(Calendar.SECOND));
        LoginDbSupport ls = new LoginDbSupport();
        FileDbSupport fs = new FileDbSupport();
        
        ls.insertNewUsr("szh", "123456");
        ls.insertNewUsr("tony", "I am a SB!!");
        int usrIdSzh = ls.getUsrId("szh");
        int usrIdTony = ls.getUsrId("tony");
        System.out.println("szh's userId = " + usrIdSzh);
        System.out.println("tony's userId = " + usrIdTony);
        System.out.println("sb's userId = " + ls.getUsrId("sb"));
        System.out.println("canCreateFile szh = " + fs.canCreateFile(usrIdSzh, "szh first file!"));
        fs.createFile(usrIdSzh, "szh first file!");
        System.out.println("canCreateFile szh = " + fs.canCreateFile(usrIdSzh, "szh first file!"));
        System.out.println("canCreateFile tony = " + fs.canCreateFile(usrIdTony, "szh first file!"));
        fs.createFile(usrIdTony, "szh first file!");
        int tonyfileId = fs.getFileId(usrIdTony, "szh first file!");
        fs.canDeleteFile(usrIdTony, tonyfileId);
        fs.deleteFile(tonyfileId);        
    }
}
