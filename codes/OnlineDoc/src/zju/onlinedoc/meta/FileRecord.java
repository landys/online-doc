/**
 * FileRecord.java
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

package zju.onlinedoc.meta;

public class FileRecord
{
    private int fileId;
    private String fileName;
    /**
     * 1: the file is created
     * 2: the file is shared
     * 3: the file is viewed
     */ 
    private int fileType;
    private String fileCreateDate;
    
    /**
     * @return Returns the fileCreateDate.
     */
    public String getFileCreateDate()
    {
        return fileCreateDate;
    }

    /**
     * @param fileCreateDate The fileCreateDate to set.
     */
    public void setFileCreateDate(String fileCreateDate)
    {
        this.fileCreateDate = fileCreateDate;
    }

    /**
     * @return Returns the fileName.
     */
    public String getFileName()
    {
        return fileName;
    }

    /**
     * @param fileName The fileName to set.
     */
    public void setFileName(String fileName)
    {
        this.fileName = fileName;
    }

    /**
     * @return Returns the fileId.
     */
    public int getFileId()
    {
        return fileId;
    }
    
    /**
     * @param fileId The fileId to set.
     */
    public void setFileId(int fileId)
    {
        this.fileId = fileId;
    }
    
    /**
     * @return Returns the fileType.
     */
    public int getFileType()
    {
        return fileType;
    }
    
    /**
     * @param fileType The fileType to set.
     */
    public void setFileType(int fileType)
    {
        this.fileType = fileType;
    }
    
    public String toString()
    {
    	String retStr = "";
    	
    	retStr += "FileId=" + getFileId() + "\r\n";
    	retStr += "FileName=" + getFileName()+ "\r\n";
    	retStr += "FileType=" + getFileType() + "\r\n";
    	retStr += "FileCreateDate=" + getFileCreateDate() + "\r\n";
    	
    	return retStr;
    }
}
