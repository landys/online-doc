/**
 * FileSupport.java
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

package zju.onlinedoc.application;

import java.util.List;

import zju.onlinedoc.dao.FileDbSupport;
import zju.onlinedoc.meta.FileRecord;
import zju.onlinedoc.meta.UserInfo;

public class FileSupport
{
    /**
     * 这个类将操作数据库中文件的信息
     */
    private FileDbSupport dbOperator;
    /**
     * 这个类将操作文件的真实内容
     */
    private FileApplication fileOperation;
    
    public FileSupport()
    {
    	dbOperator = new FileDbSupport();
    	fileOperation = new FileApplication();
    }
    
    public int createFile(int userId, String fileName)
    {
    	if(dbOperator.canCreateFile(userId, fileName))
    	{
    		if(dbOperator.createFile(userId, fileName))
    		{
    			int fileId = dbOperator.getFileId(userId, fileName);
    			fileOperation.createFile(fileId);
    			return fileId;
    		}
    		else
    		{// create file information in database error
    			return -1;
    		}
    	}
    	else
    	{// the file has already in the system
    		return -1;
    	}
	    
    }
    
    public int createFile(int userId, String fileName, String fileContent)
    {
    	int fileId = createFile(userId, fileName);
    	if(fileId > 0)
    	{
    		fileOperation.updateFile(fileId, fileContent);
    	}
        return fileId;
    }
    
    public boolean deleteFile(int userId, int fileId)
    {
    	if(dbOperator.canDeleteFile(userId, fileId))
    	{
    		dbOperator.deleteFile(fileId);
    		fileOperation.deleteFile(fileId);
    		return true;
    	}
        return false;
    }
    
    public FileRecord getFileRecord(int usrId, int fileId)
    {
        return dbOperator.getFileInformation(usrId, fileId);
    }
    
    public FileRecord[] getFileRecordArray(int userId)
    {
    	return dbOperator.getFileRecordArray(userId);        
    }
    
    public String getFileContent(int userId, int fileId)
    {
    	if(dbOperator.getFileType(userId, fileId) > 0)
    	{
    		return fileOperation.readFile(fileId);
    	}
        return null;
    }
    
    public boolean updateFileContent(int userId, int fileId, String fileContent)
    {
    	if(dbOperator.getFileType(userId, fileId) > 0)
    	{
    		return fileOperation.updateFile(fileId, fileContent);
    	}
        return false;
    }

    public boolean renameFile(int userId, int fileId, String fileName)
    {
    	if(dbOperator.getFileType(userId, fileId) > 0)
    	{
    		FileRecord[] fileList = getFileRecordArray(userId);
    		for(FileRecord file : fileList)
    		{
    			if(file.getFileName().equals(fileName) && file.getFileType() == 1
                        && file.getFileId() != fileId)
    				return false;
    		}
    		return dbOperator.renameFile(fileId, fileName);
    	}
        return false;
    }
    
    public boolean updateFile(int userId, int fileId, String fileName, String fileContent)
    {
    	if(renameFile(userId, fileId, fileName))
    	{
    		return fileOperation.updateFile(fileId, fileContent);
    	}
        return false;
    }
    
    public UserInfo[] getUserNameArray(int fileId)
    {
        return dbOperator.getUserNameArray(fileId);
    }
    
    public boolean createShareFile(int userId, int[] fileIds, String[] userNames, int type)
    {
        // 首先检查这个用户是否有创建这些共享文件的权限，主要看这些文件的type是否为1
        for(int fileId : fileIds)
        {
            if(dbOperator.getFileType(userId, fileId) != 1)
            {
                return false;
            }
        }
        
        // 然后得到所有的userNames相对应的userIds
        List<Integer> userIdList = dbOperator.getUserIds(userNames);
        
        // 然后对每个文件和每个用户插入一条对应关系在tbluserfile中, 复杂度（N * N）
        boolean isFault = true;
        for(int fileId : fileIds)
        {
            for(int shareUserId : userIdList)
            {
                boolean isSuccess = 
                        dbOperator.createShareFile(shareUserId, fileId, type);
                if(!isSuccess)
                {
                    isFault = false;
                }
            }
        }
        
        return isFault; 
    }
    
    public boolean deleteFileList(int[] fileIds)
    {
        return dbOperator.deleteFileList(fileIds);
    }
}
