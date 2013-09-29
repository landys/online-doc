/**
 * TestGrid.java
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

import zju.onlinedoc.grid.client.UserLoginClient;
import zju.onlinedoc.meta.FileRecord;

public class TestGrid
{
    public static void main(String[] args)
    {
        String instanceFileEditURI="http://127.0.0.1:8080/wsrf/services/FileEditorService";
        String instanceUserLoginURI="http://127.0.0.1:8080/wsrf/services/UserLoginService";
        
        try {
            UserLoginClient login = new UserLoginClient(instanceUserLoginURI, false);
            
            //int userId = login.loginNewUser("szh", "123456");
            
            int userId = login.canLoginSystem("szh", "123456");
            System.out.println("userId = " + userId);
            
            System.out.println("create file1");
            int file1 = login.createEmptyFile("hello");
            System.out.println("file1=" + file1);
            System.out.println("create file2");
            int file2 = login.createFile("hasContent", "hello world, this is a content");
            System.out.println("file2=" + file2);
            
            System.out.println("can create \"hello\" file=" + login.createEmptyFile("hello"));
            System.out.println("can create \"hasContent\" file=" + login.createFile("hasContent", "must failed"));
            
            System.out.println("delete file hello");
            System.out.println("successful? " + login.deleteFile(file1));
            System.out.println("delete twice");
            System.out.println("successful? " + login.deleteFile(file1));
            
            System.out.println("create file1->hello again!");
            file1 = login.createEmptyFile("hello");
            System.out.println("can create \"hello\" fileId=" + file1);
            
            login.updateFileContent(file1, "aaaaaaaaaaaaaa");
            System.out.println("file1 content=" + login.getFileContent(file1));
            
            login.updateFileContent(file2, "haha you are  a sb");
            System.out.println("file2 content=" + login.getFileContent(file2));
            
            login.renameFile(file2, "anewname");
            System.out.println("rename file successful? " + login.renameFile(file1, "content"));
            
            login.createEmptyFile("file2");
            login.createEmptyFile("file2");
            login.createEmptyFile("file3");
            login.createEmptyFile("file4");
            login.createEmptyFile("file5");
            
            FileRecord[] fileRecord = login.getFileRecordArray();
            for(FileRecord temp : fileRecord)
            {
                System.out.println(temp);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
