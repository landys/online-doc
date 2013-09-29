/**
 * CommandHelp.java
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

package zju.onlinedoc.web.command;

import java.rmi.RemoteException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.rpc.ServiceException;

import org.apache.axis.types.URI.MalformedURIException;

import zju.onlinedoc.grid.client.UserLoginClient;
import zju.onlinedoc.meta.stubs.FileRecord;
import zju.onlinedoc.util.LoadProperties;
import zju.onlinedoc.util.OnlineDocException;
import zju.onlinedoc.web.vo.UserFileTableVO;

/**
 * @author tonywjd
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CommandHelp
{
    /**
     * Grid user url.
     */
    private static String instanceURIUserLogin = null;
    
    /**
     * useSecurity
     */
    private static boolean useSecurity = false;
    
    /**
     * Every user own a UserLoginClient and reuse it in every operation.
     * So the key is the session id.
     */
    private static Map<String, UserLoginClient> clients = Collections.synchronizedMap(new HashMap<String, UserLoginClient>()); 
    
    /**
     * Open file table, make sure only one user can edit the same file.
     */
    private static UserFileTableVO openTable = new UserFileTableVO();
    
    /**
     * @param request
     * @param action -- click button to invoke action
     * @param message -- message on the result page
     */
    public static void setFailAttribute(final HttpServletRequest request, final String action, final String message)
    {
        setFailAttribute(request, "Return", action, message);
    }
    
    /**
     * @param request
     * @param text -- button value
     * @param action -- click button to invoke action
     * @param message -- message on the result page
     */
    public static void setFailAttribute(final HttpServletRequest request, final String text, final String action, final String message)
    {
        request.setAttribute("executeResult", "Fail");
        String[][] actions = new String[1][2];
        actions[0][0] = text;
        actions[0][1] = action;
        setResultAttribute(request, actions, message);
    }
    
    public static void setSuccessAttribute(final HttpServletRequest request, final String text, final String action, final String message)
    {
        String[][] actions = new String[1][2];
        actions[0][0] = text;
        actions[0][1] = action;
        setResultAttribute(request, actions, message);
    }
    
    /**
     * @param request
     * @param actions -- buttons with value and click action
     * @param message -- message on the result page
     */
    public static void setResultAttribute(final HttpServletRequest request, final String[][] actions, final String message)
    {
        request.setAttribute("actions", actions);
        request.setAttribute("message", message);
    }
    
    public static void setEditResultAttribute(final HttpServletRequest request, final String text, final String action, final String message)
    {
        String[][] actions = new String[2][2];
        actions[0][0] = text;
        actions[0][1] = action;
        actions[1][0] = "Return Home";
        actions[1][1] = "home";
        setResultAttribute(request, actions, message);
    }
    
    /**
     * Make the word with red color
     * @param message
     * @return
     */
    public static String makeRed(final String message)
    {
        return "<font color=\"red\">" + message + "</font>";
    }
    
    public static int[] getFileIdsFromEditPage(final HttpServletRequest request)
    {
        String[] strFIds = request.getParameterValues("fileIds");
        if (strFIds == null || strFIds.length == 0)
        {
            return null;
        }
        
        int[] tempFIds = new int[strFIds.length];
        int t = 0;
        for (int i=0; i<strFIds.length; i++)
        {
            try
            {
                int fId = Integer.parseInt(strFIds[i]);
                if (fId >= 0)
                {
                    tempFIds[t++] = fId;
                }
            }
            catch (Exception e)
            {
            }
        }
        
        if (t == 0)
        {
            return null;
        }
        int[] fIds = tempFIds;
        if (t < tempFIds.length)
        {
            fIds = new int[t];
            for (int i=0; i<t; i++)
            {
                fIds[i] = tempFIds[i];
            }
        }
        return fIds;
    }
    
    public static void setLoginSession(final HttpSession session, final int userId, final String userName)
    {
        session.setAttribute("loginUserId", userId);
        session.setAttribute("loginUserName", userName);
    }
    
    public static void removeLoginSession(final HttpSession session)
    {
        session.removeAttribute("loginUserId");
        session.removeAttribute("loginUserName");
    }
    
    /**
     * @param key -- always be session id
     * @return
     * @throws OnlineDocException
     * @throws MalformedURIException
     * @throws RemoteException
     * @throws ServiceException
     */
    public static UserLoginClient getUserLoginClient(final String key) throws OnlineDocException, MalformedURIException, RemoteException, ServiceException
    {
        if (instanceURIUserLogin == null)
        {
            instanceURIUserLogin = LoadProperties.getInstance(
                    "config.properties").getProp("grid.url.UserLoginService");
        }
        UserLoginClient client = clients.get(key);
        if (client == null)
        {
            client = new UserLoginClient(instanceURIUserLogin, useSecurity);
            clients.put(key, client);
        }
        
        return client;
    }
    
    public static void getAndSetFileRecordArray(final HttpServletRequest request) throws MalformedURIException, RemoteException, OnlineDocException, ServiceException
    {
        UserLoginClient client = getUserLoginClient(request.getSession().getId());
        
        FileRecord[] fileRecords = client.getFileRecordArray();
        if (fileRecords == null)
        {
            fileRecords = new FileRecord[0];
        }
        request.setAttribute("fileRecords", fileRecords);
        
        System.out.println("Total " + fileRecords.length + "files.");
    }
    
    public static void removeSessionClient(final String key)
    {
        if (clients.containsKey(key))
        {
            clients.remove(key);
        }
    }

    public static UserFileTableVO getOpenTable()
    {
        return openTable;
    }

    public static void setOpenTable(UserFileTableVO openTable)
    {
        CommandHelp.openTable = openTable;
    }
    
    
}
