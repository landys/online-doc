/**
 * LoginSupport.java
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

import zju.onlinedoc.dao.LoginDbSupport;
import zju.onlinedoc.meta.UserRecord;

public class LoginSupport
{
    private LoginDbSupport dbOperator;
    
    public LoginSupport()
    {
        dbOperator = new LoginDbSupport();
    }

    public int loginNewUser(String userName, String userPwd)
    {
        List<UserRecord> userList = dbOperator.getUserRecord(userName);
        
        if (userList.size() == 0)
        {
            dbOperator.insertNewUsr(userName, userPwd);
            return dbOperator.getUsrId(userName);
        }

        return -1;
    }

    public int canLoginSystem(String userName, String userPwd)
    {
        List<UserRecord> userList = dbOperator.getUserRecord(userName);
        if (userList.size() == 1)
        {
            String pwdInTable = userList.get(0).getUserPwd();
            if (pwdInTable.equals(userPwd))
            {
                // ����û����Ե�¼
                return userList.get(0).getUserId();
            }
            else
            {
                // ���벻��
                return -1;
            }
        }
        
        return -1;
    }
}
