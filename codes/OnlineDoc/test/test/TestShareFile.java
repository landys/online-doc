/**
 * TestShareFile.java
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

import zju.onlinedoc.application.FileSupport;
import zju.onlinedoc.grid.client.UserLoginClient;
import zju.onlinedoc.meta.UserInfo;

public class TestShareFile
{
    public static void main(String[] argc)
    {
       int userIdOne = 3;
       int userIdTwo = 4;
       
       FileSupport fs = new FileSupport();
       /*
       fs.createFile(userIdOne, "file1");
       fs.createFile(userIdOne, "file2");
       fs.createFile(userIdOne, "file3");
       
       fs.createFile(userIdTwo, "file1");
       fs.createFile(userIdTwo, "file2");
       //*/
       
       /*
       int fileIds[] = {69, 70, 71};
       String userNames[] = {"szh", "wjd"};
       int type = 2;
       fs.createShareFile(userIdOne, fileIds, userNames, type);
       //*/
       
       /*
       System.out.println("get file 70 information!!");
       UserInfo[] userList = fs.getUserNameArray(70);
       for(int i = 0; i < userList.length; i++)
       {
           System.out.println(userList[i]);
       }
       
       System.out.println("get file 73 information!!");
       userList = fs.getUserNameArray(73);
       for(int i = 0; i < userList.length; i++)
       {
           System.out.println(userList[i]);
       }
       //*/
       int deleteFileID[] = {70, 71};
       fs.deleteFileList(deleteFileID);
    }
}
