/**
 * FileApplication.java
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

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.sun.xml.internal.ws.util.ByteArrayBuffer;

/**
 * @author szh
 *这个类主要操作真实文件，如修改文件的内容，真实的创建删除一个文件
 */
public class FileApplication
{
    private final String filePath = "./FileCluster/";
    private static File fileDirectory = null;
    
    public FileApplication()
    {
        if(fileDirectory == null)
        {
            fileDirectory = new File(filePath);
        }
    }
    
    public boolean createFile(int fileId)
    {
        if(!fileDirectory.isDirectory())
        {
            fileDirectory.mkdir();
        }
        
        File newFile = new File(filePath + fileId);
        
        try
        {
            return newFile.createNewFile();
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean deleteFile(int fileId)
    {
        File file = new File(filePath + fileId);
        
        if(file.isFile())
        {
            return file.delete();
        }
        else
        {
            return false;
        }
    }
    
    public boolean updateFile(int fileId, String content)
    {
        if(!fileDirectory.isDirectory())
        {
            fileDirectory.mkdir();
        }        
        
        try
        {
            File file = new File(filePath + fileId);
            DataOutputStream outs = new DataOutputStream(
                                        new BufferedOutputStream(
                                                new FileOutputStream(file)));
            
            outs.write(content.getBytes("UTF-8"));
            outs.close();
            return true;
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return false;
    }
    
    public String readFile(int fileId)
    {
        File file = new File(filePath + fileId);
        StringBuffer fileContext = new StringBuffer();
        
        if(!fileDirectory.isDirectory() || !file.isFile())
        {
            return null;
        } 
        
        DataInputStream ins = null;
        try
        {
            ins = new DataInputStream(new BufferedInputStream(
                                         new FileInputStream(file)));
            ByteArrayOutputStream outs = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len;
            
            while((len = ins.read(buffer)) >= 0)
            {
                outs.write(buffer, 0, len);
            }
            
            fileContext.append(new String(outs.toByteArray(), "UTF-8"));
        }
        catch (FileNotFoundException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally
        {
            if(ins != null)
            {
                try
                {
                    ins.close();
                }
                catch (IOException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }            
        }
        
        return fileContext.toString();
    }
}
