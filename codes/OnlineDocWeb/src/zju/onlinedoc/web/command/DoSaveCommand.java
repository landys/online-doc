/**
 * DoSaveCommand.java
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
import zju.onlinedoc.util.CheckHelp;
import zju.onlinedoc.web.vo.UserFileVO;

/**
 * @author tonywjd
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DoSaveCommand implements ICommand
{

    /* (non-Javadoc)
     * @see zju.onlinedoc.web.command.ICommand#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        System.out.println("Enter DoSaveCommand.execute.");
        
        String strFId = request.getParameter("fId");
        String fName = request.getParameter("fName");
        String fContent = request.getParameter("fContent");
        String strType = request.getParameter("fType");
        
        boolean goodFName = CheckHelp.checkGoodString(fName);
        boolean goodFContent = CheckHelp.checkGoodString(fContent);
        
        if (!goodFName)
        {
            System.out.println("Invalid file name.");
            if (goodFContent)
            {
                request.getSession().setAttribute("fContent", fContent);
            }
            if (strFId != null)
            {
            	CommandHelp.setEditResultAttribute(request, "Retry Update", "update", 
	                    CommandHelp.makeRed("Invalid file name."));
            }
            else
            {
	            CommandHelp.setEditResultAttribute(request, "Retry Create", "create", 
	                    CommandHelp.makeRed("Invalid file name."));
            }
            return;
        }
        
        try
        {
            UserLoginClient client = CommandHelp.getUserLoginClient(request.getSession().getId());
            
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
            {   // update file
                request.getSession().setAttribute("fId", fId);
                String message = null;
                if (!client.updateFile(fId, fName, fContent))
                {
                	CommandHelp.setFailAttribute(request, "home", 
                            CommandHelp.makeRed("Fail to update file. Maybe the file is deleted by the owner just now."));
                	return;
                }
                else
                {
                    message = "Successfully update. Click return to continue editing.";
                }
                int fType = 2;
                try
                {
                    fType = Integer.parseInt(strType);
                }
                catch (Exception e)
                {
                    fType = 2;
                }
                CommandHelp.setEditResultAttribute(request, "Continue Update", "update&fId="+fId+"&fName="+fName+"&fType="+fType, message);
                
                CommandHelp.getOpenTable().remove(new UserFileVO((Integer)request.getSession().getAttribute("loginUserId"), fId));
            }
            else
            {   // create file
                if (goodFContent)
                {
                    fId = client.createFile(fName, fContent);
                }
                else
                {
                    fId = client.createEmptyFile(fName);
                }
                String message = null;
                if (fId >= 0)
                {
                    request.getSession().setAttribute("fId", fId);
                    message = "Successfully create. Click return to continue editing.";
                    CommandHelp.setEditResultAttribute(request, "Continue Update", "update&fId="+fId+"&fName="+fName+"&fType=1", message);
                }
                else
                {
                    if (goodFName)
                    {
                        request.getSession().setAttribute("fName", fName);
                    }
                    if (goodFContent)
                    {
                        request.getSession().setAttribute("fContent", fContent);
                    }
                    message = CommandHelp.makeRed("Fail to create file. You may already have the file with the same name.");
                    CommandHelp.setEditResultAttribute(request, "Retry Create", "create", message);
                }
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
