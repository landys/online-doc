/**
 * UserRecord.java
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

public class UserRecord
{
    private int userId;
    private String userName;
    private String userPwd;
    
    public UserRecord(int userId, String userName, String userPwd)
    {
        this.userId = userId;
        this.userName = userName;
        this.userPwd = userPwd;
    }

    /**
     * @return Returns the userId.
     */
    public int getUserId()
    {
        return userId;
    }

    /**
     * @param userId The userId to set.
     */
    public void setUserId(int userId)
    {
        this.userId = userId;
    }

    /**
     * @return Returns the userName.
     */
    public String getUserName()
    {
        return userName;
    }

    /**
     * @param userName The userName to set.
     */
    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    /**
     * @return Returns the userPwd.
     */
    public String getUserPwd()
    {
        return userPwd;
    }

    /**
     * @param userPwd The userPwd to set.
     */
    public void setUserPwd(String userPwd)
    {
        this.userPwd = userPwd;
    }
    
}
