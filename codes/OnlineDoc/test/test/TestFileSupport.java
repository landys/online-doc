package test;

import zju.onlinedoc.application.FileSupport;
import zju.onlinedoc.application.LoginSupport;
import zju.onlinedoc.meta.FileRecord;

public class TestFileSupport 
{
	public static void main(String[] argc)
	{
		LoginSupport ls = new LoginSupport();
		FileSupport fs = new FileSupport();
		
		int userId = ls.loginNewUser("szh", "123456");
		
		System.out.println("create file1");
		int file1 = fs.createFile(userId, "hello");
		System.out.println("file1=" + file1);
		System.out.println("create file2");
		int file2 = fs.createFile(userId, "hasContent", "hello world");
		System.out.println("file2=" + file2);
		
		System.out.println("can create \"hello\" file=" + fs.createFile(userId, "hello"));
		System.out.println("can create \"hasContent\" file=" + fs.createFile(userId, "hasContent", "must failed"));
		
		System.out.println("delete file hello");
		System.out.println("successful? " + fs.deleteFile(userId, file1));
		System.out.println("delete twice");
		System.out.println("successful? " + fs.deleteFile(userId, file1));
		
		System.out.println("create file1->hello again!");
		file1 = fs.createFile(userId, "hello");
		System.out.println("can create \"hello\" fileId=" + file1);
		
		fs.updateFileContent(userId, file1, "aaaaaaaaaaaaaa");
		System.out.println("file1 content=" + fs.getFileContent(userId, file1));
		
		fs.updateFile(userId, file2, "content", "haha you are  a sb");
		System.out.println("file2 content=" + fs.getFileContent(userId, file2));
		
		fs.renameFile(userId, file1, "anewname");
		System.out.println("rename file successful? " + fs.renameFile(userId, file1, "content"));
		
		fs.createFile(userId, "file3");
		fs.createFile(userId, "file4");
		fs.createFile(userId, "file5");
		fs.createFile(userId, "file6");
		fs.createFile(userId, "file7");
		
		FileRecord[] fileRecord = fs.getFileRecordArray(userId);
		for(FileRecord temp : fileRecord)
		{
			System.out.println(temp);
		}
	}
}
