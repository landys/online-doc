/**
 * FileDbSupport.java
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

import zju.onlinedoc.meta.FileRecord;
import zju.onlinedoc.meta.UserInfo;

public class FileDbSupport
{
    // the property about the database
    private static Properties daoPro = null;

    /**
     *  ��ʼ���������õ����ݿ������ļ����������ݿ������
     */
    public FileDbSupport()
    {
        if (daoPro == null)
        {
            // create the property
            daoPro = new Properties();
            InputStream in = FileDbSupport.class
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
    
    /**
     * ���ĳ���û��Ƿ���Դ�����fileNameΪ�����ļ�
     * @param userId
     *          �û������ݿ��id
     * @param fileName
     *          �ļ�������
     * @return
     *          true�����Դ���
     */
    public boolean canCreateFile(int userId, String fileName)
    {
        DatabaseManager dao = new DatabaseManager();
        String checkSql = daoPro.getProperty("CanCreateFile");
        
        List<Object> paras = new ArrayList<Object>();
        paras.add(userId);
        paras.add(fileName);
        
        ResultSet rs = dao.querySql(checkSql, paras);        
        try
        {
            if(rs == null)
            {// something bad happens in executing the sql
                return false;
            }
            
            if(rs.next())
            {// already has create the same file
                return false;
            }
            else
            {
                return true;
            }
        }
        catch (SQLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally
        {
            if(rs != null)
            {
                try
                {
                    rs.close();
                }
                catch (SQLException e)
                {
                    System.out.println("ResultSet close error!");
                    e.printStackTrace();
                }
            }            
            dao.close();
        }        
        return false;
    }
    
    /**
     * ����������������ݿ��д���һ���µ��û��ļ�
     * @param userId
     *          �����ļ����û�id
     * @param fileName
     *          �������µ��ļ�������
     * @return
     *          true�� �����ļ��ɹ�
     */
    public boolean createFile(int userId, String fileName)
    {
        DatabaseManager dao = new DatabaseManager();
        
        // get the time of the file creation 
        Calendar cl = Calendar.getInstance();
        String date = cl.get(Calendar.YEAR) + "-" + cl.get(Calendar.MONTH)
            + "-" + cl.get(Calendar.DAY_OF_MONTH) + " " + cl.get(Calendar.HOUR_OF_DAY )
            + ":" + cl.get(Calendar.MINUTE) + ":" + cl.get(Calendar.SECOND);
       
        // insert the new file to table tblfile
        String createFileSql = daoPro.getProperty("CreateFile");
        List<Object> paras1 = new ArrayList<Object>();
        paras1.add(fileName);
        paras1.add(date);
        
        dao.updateSql(createFileSql, paras1);
        
        // get the new fileId
        int fileId = dao.getLastID();
        
        // insert the usr-file relation record to tbluserfile
        String createRelationSql = daoPro.getProperty("CreateUsr_FileRelation");
        List<Object> paras2 = new ArrayList<Object>();
        paras2.add(userId);
        paras2.add(fileId);
        paras2.add(1);
           
        dao.updateSql(createRelationSql, paras2);
        dao.close();
        return true;
    }
    
    /**
     * ���������Ҫ���ĳ���û��Ƿ����ɾ��ĳ���ļ�
     * @param userId
     * 			�û���id
     * @param fileId
     * 			�ļ���id
     * @return
     * 			true���û�����ɾ������ļ�
     */
    public boolean canDeleteFile(int userId, int fileId)
    {
        DatabaseManager dao = new DatabaseManager();
        
        String checkDelSql = daoPro.getProperty("GetFileType");
        List<Object> paras = new ArrayList<Object>();
        paras.add(userId);
        paras.add(fileId);
        
        ResultSet rs = dao.querySql(checkDelSql, paras);
        try
        {
            if(rs.next())
            {
                int type = rs.getInt("type");
                if(type == 1)
                {
                    return true;
                }
            }
        }
        catch (SQLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally
        {
            if(rs != null)
            {
                try
                {
                    rs.close();
                }
                catch (SQLException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            dao.close();
        }
        
        return false;
    }
    
    /**
     * �������ɾ��һ���ļ������ݿ����Ϣ
     * @param fileId
     * 			�ļ���id
     * @return
     * 			true:ɾ���ɹ�
     */
    public boolean deleteFile(int fileId)
    {
        DatabaseManager dao = new DatabaseManager();
        
        String delSql = daoPro.getProperty("DeleteFile");
        List<Object> paras = new ArrayList<Object>();
        paras.add(fileId);
        
        dao.updateSql(delSql, paras);
        dao.close();
        return true;
    }
    
    /**
     * ������������û�id���ļ����õ�����ļ�id
     * @param usrId
     * 			�û���id
     * @param fileName
     * 			�ļ���
     * @return
     * 			�ļ������ݿ��id
     */
    public int getFileId(int usrId, String fileName)
    {
        DatabaseManager dao = new DatabaseManager();
        
        String selectSql = daoPro.getProperty("GetFileId");
        List<Object> paras = new ArrayList<Object>();
        paras.add(fileName);
        paras.add(usrId);
        
        ResultSet rs = dao.querySql(selectSql, paras);
        try
        {
            while (rs.next())
            {
                return rs.getInt("fileId");
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
    public FileRecord getFileInformation(int usrId, int fileId)
    {
        DatabaseManager dao = new DatabaseManager();
        
        List<Object> paras = new ArrayList<Object>();
        paras.add(usrId);
        paras.add(fileId);
        String getFileSql = daoPro.getProperty("GetFileInformation");
        
        ResultSet rs = dao.querySql(getFileSql, paras);
        FileRecord fr = new FileRecord();
        try
        {
            if(rs.next())
            {
                fr.setFileCreateDate(rs.getString("createDate"));
                fr.setFileId(fileId);
                fr.setFileName(rs.getString("fileName"));
                fr.setFileType(rs.getInt("type"));
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
        return fr;
    }
    /**
     * ��������õ�һ���û��������ļ��б�
     * @param usrId
     * 		�û���id
     * @return
     * 		�ļ����б�
     */
    public FileRecord[] getFileRecordArray(int usrId)
    {
        DatabaseManager dao = new DatabaseManager();
        
        String selectArraySql = daoPro.getProperty("GetFileArray");
        List<Object> paras = new ArrayList<Object>();
        paras.add(usrId);

        List<FileRecord> fileList = new ArrayList<FileRecord>();
        ResultSet rs = dao.querySql(selectArraySql, paras);
        try
        {
            while(rs.next())
            {
                FileRecord record = new FileRecord();
                record.setFileId(rs.getInt("fileId"));
                record.setFileName(rs.getString("fileName"));
                record.setFileType(rs.getInt("type"));
                record.setFileCreateDate(rs.getString("createDate"));
                fileList.add(record);
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
        
        FileRecord[] fileRecords = fileList.toArray(new FileRecord[0]);
        
        return fileRecords==null ? new FileRecord[0] : fileRecords;
    }
    
    public int getFileType(int usrId, int fileId)
    {
        DatabaseManager dao = new DatabaseManager();
        
    	String getTypeSql = daoPro.getProperty("GetFileType");
        List<Object> paras = new ArrayList<Object>();
        paras.add(usrId);
        paras.add(fileId);
        
        ResultSet rs = dao.querySql(getTypeSql, paras);
        
        try 
        {
			if(rs.next())
			{
				return rs.getInt("type");
			}
		} 
        catch (SQLException e) {
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
    
    public boolean renameFile(int fileId, String name)
    {
        DatabaseManager dao = new DatabaseManager();
        
    	String updateNameSql = daoPro.getProperty("RenameFile");
    	List<Object> paras = new ArrayList<Object>();
        paras.add(name);
        paras.add(fileId);
        
        boolean isSuccess = dao.updateSql(updateNameSql, paras);
        dao.close();
        return isSuccess;
    }
    
    public UserInfo[] getUserNameArray(int fileId)
    {
        DatabaseManager dao = new DatabaseManager();
        
        String getFileUserInfoSql = daoPro.getProperty("GetFileUserInfo");
        List<Object> paras = new ArrayList<Object>();
        paras.add(fileId);
        
        ResultSet rs = dao.querySql(getFileUserInfoSql, paras);
        List<UserInfo> userList = new ArrayList<UserInfo>();
        
        try
        {
            while(rs.next())
            {
                UserInfo userInfo = new UserInfo();
                userInfo.setType(rs.getInt("type"));
                userInfo.setUserId(rs.getInt("userId"));
                userInfo.setUserName(rs.getString("userName"));
                userList.add(userInfo);
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
        
        return userList.size() == 0 ? new UserInfo[0] : 
            userList.toArray(new UserInfo[0]);
    }
    
    public List<Integer> getUserIds(String userNames[])
    {
        DatabaseManager dao = new DatabaseManager();
    
        String namePlace = "?";
        for(int i = 1; i < userNames.length; i++)
        {
            namePlace += ", ?";
        }
        
        String getUserIdsSql = daoPro.getProperty("GetUserIds");
        getUserIdsSql = getUserIdsSql.replaceFirst("\\?", namePlace);
        List<Object> paras = new ArrayList<Object>();
        for(String userName : userNames)
        {
            paras.add(userName);
        }
        
        ResultSet rs = dao.querySql(getUserIdsSql, paras);
        List<Integer> userIdList = new ArrayList<Integer>();
        
        try
        {
            while(rs.next())
            {
                userIdList.add(rs.getInt("userId"));
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
        
        return userIdList;
    }
    
    public boolean createShareFile(int userId, int fileId, int type)
    {
        DatabaseManager dao = new DatabaseManager();
        String createRelationSql = daoPro.getProperty("CreateUsr_FileRelation");
        List<Object> paras = new ArrayList<Object>();
        paras.add(userId);
        paras.add(fileId);
        paras.add(type);
        
        boolean isSuccess = dao.updateSql(createRelationSql, paras);
        dao.close();
        return isSuccess;
    }
    
    public boolean deleteFileList(int fileIds[])
    {
        DatabaseManager dao = new DatabaseManager();
        
        String filePlace = "?";
        for(int i = 1; i < fileIds.length; i++)
        {
            filePlace += ", ?";
        }
        
        String deleteFileListSql = daoPro.getProperty("DeleteFileList");
        deleteFileListSql = deleteFileListSql.replaceFirst("\\?", filePlace);
        List<Object> paras = new ArrayList<Object>();
        for(int fileId : fileIds)
        {
            paras.add(fileId);
        }
        
        boolean isSuccess = dao.updateSql(deleteFileListSql, paras);
        dao.close();
        return isSuccess;
    }
}
