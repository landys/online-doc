/**
 * UserFileVO.java
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

package zju.onlinedoc.web.vo;

/**
 * @author tonywjd
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class UserFileVO
{
    private int userId;

    private int fileId;

    public UserFileVO()
    {
    }

    public UserFileVO(int userId, int fileId)
    {
        this.userId = userId;
        this.fileId = fileId;
    }

    public int getFileId()
    {
        return fileId;
    }

    public void setFileId(int fileId)
    {
        this.fileId = fileId;
    }

    public int getUserId()
    {
        return userId;
    }

    public void setUserId(int userId)
    {
        this.userId = userId;
    }

    @Override
    public int hashCode()
    {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + fileId;
        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final UserFileVO other = (UserFileVO) obj;
        if (fileId != other.fileId)
            return false;
        return true;
    }
}
