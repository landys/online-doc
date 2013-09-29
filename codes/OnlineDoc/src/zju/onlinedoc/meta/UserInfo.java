/**
 * UserInfo.java
 * 
 * Copyright (c) 2006 Tony
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

public class UserInfo
{
    private int userId;
    private String userName;
    private int type;
    
    public UserInfo()
    {
        
    }
    
    public int getType()
    {
        return type;
    }
    public void setType(int type)
    {
        this.type = type;
    }
    public int getUserId()
    {
        return userId;
    }
    public void setUserId(int userId)
    {
        this.userId = userId;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }
    
    public String toString()
    {
        String retStr = "";
        
        retStr += "userId=" + getUserId() + "\r\n";
        retStr += "userName=" + getUserName()+ "\r\n";
        retStr += "type=" + getType() + "\r\n";
        
        return retStr;
    }
}
