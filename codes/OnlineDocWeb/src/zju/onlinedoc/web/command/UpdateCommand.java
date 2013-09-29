/**
 * HomeCommand.java
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

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import zju.onlinedoc.grid.client.UserLoginClient;
import zju.onlinedoc.meta.stubs.UserInfo;
import zju.onlinedoc.util.CheckHelp;
import zju.onlinedoc.web.vo.UserFileVO;
import zju.onlinedoc.meta.stubs.FileRecord;

/**
 * @author tonywjd
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class UpdateCommand implements ICommand
{

    /* (non-Javadoc)
     * @see zju.onlinedoc.web.command.ICommand#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        System.out.println("Enter UpdateCommand.execute.");
        
        String strFId = request.getParameter("fId");
        
        int fId = -1;
        try
        {
            fId = Integer.parseInt(strFId);
        }
        catch (Exception e)
        {
            fId = -1;
        }
        
        if (fId >= 0)
        {
            if (CommandHelp.getOpenTable().containsWithFileId(fId) 
                    && CommandHelp.getOpenTable().getUserId(fId) != (Integer)request.getSession().getAttribute("loginUserId"))
            {
                request.setAttribute("hasRight", "no");
            }
            request.getSession().setAttribute("fId", fId);
        }
        else
        {
            CommandHelp.setFailAttribute(request, "home", 
                    CommandHelp.makeRed("Cannot open file."));
            return;
        }
        
        try
        {
            UserLoginClient client = CommandHelp.getUserLoginClient(request.getSession().getId());
            FileRecord record = client.getFileRecord(fId);
            
            if (record == null)
            {
            	CommandHelp.setFailAttribute(request, "home", 
                        CommandHelp.makeRed("Session timeout, you should relogin."));
                return;
            }
            if (record.getFileId() != fId)
            {
            	CommandHelp.setFailAttribute(request, "home", 
                        CommandHelp.makeRed("Fail to refresh file. Maybe the file is deleted by the owner just now."));
                return;
            }
            String fName = record.getFileName();
            int fType = record.getFileType();
            request.getSession().setAttribute("fName", fName);
            request.getSession().setAttribute("fType", fType);
            
            String fContent = client.getFileContent(fId);
            if (CheckHelp.checkGoodString(fContent))
            {
                request.getSession().setAttribute("fContent", fContent);
            }
            UserInfo[] users = client.getUserNameArray(fId);
            StringBuffer[] bufs = new StringBuffer[3];
            for (int i=0; i<bufs.length; i++)
            {
                bufs[i] = new StringBuffer("");
            }
            for (int i=0; i<users.length; i++)
            {
                int t = users[i].getType();
                if (t > 0 && t < 4)
                {
                    bufs[t-1].append(users[i].getUserName()).append(',');
                }
            }
            String[] infos = new String[bufs.length];
            for (int i=0; i<bufs.length; i++)
            {
                infos[i] = bufs[i].substring(0, bufs[i].length()==0 ? 0 : bufs[i].length()-1);
            }
            request.getSession().setAttribute("userInfos", infos);
            
            // update
            if ((fType == 1 || fType == 2) && !"no".equalsIgnoreCase((String) request.getAttribute("hasRight")))
            {
                CommandHelp.getOpenTable().add(new UserFileVO((Integer)request.getSession().getAttribute("loginUserId"), fId));
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            CommandHelp.setFailAttribute(request, "home", 
                    CommandHelp.makeRed("Session timeout, you should relogin. Or Contact us..."));
        }
    }

}
