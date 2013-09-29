/**
 * TestGridShare.java
 * 
 * Copyright (c) 2006 Tony
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

import zju.onlinedoc.grid.client.UserLoginClient;
import zju.onlinedoc.meta.stubs.UserInfo;

public class TestGridShare
{
    public static void main(String[] argc)
    {
        String instanceUserLoginURI="http://127.0.0.1:8080/wsrf/services/UserLoginService";
        
        try
        {
            UserLoginClient login = new UserLoginClient(instanceUserLoginURI, false);
            int userIdOne = login.canLoginSystem("tony", "tony");
            
            /*
            login.createEmptyFile("file1");
            login.createEmptyFile("file2");
            login.createEmptyFile("file3");
            
            int userIdTwo = login.canLoginSystem("wjd", "wjd");
            login.createEmptyFile("file1");
            login.createEmptyFile("file2");
            //*/
            
            /*
            int fileIds[] = {74, 75, 76};
            String userNames[] = {"szh", "wjd"};
            int type = 2;
            login.createShareFile(fileIds, userNames, type);
            //*/
            
            /*
            System.out.println("get file 75 information!!");
            UserInfo[] userList = login.getUserNameArray(75);
            for(int i = 0; i < userList.length; i++)
            {
                System.out.println(userList[i]);
            }
            
            System.out.println("get file 78 information!!");
            userList = login.getUserNameArray(78);
            for(int i = 0; i < userList.length; i++)
            {
                System.out.println(userList[i]);
            }
            //*/
            
            //*
            int deleteFileID[] = {75, 78};
            login.deleteFileList(deleteFileID);
            //*/
            
            
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }  
}
