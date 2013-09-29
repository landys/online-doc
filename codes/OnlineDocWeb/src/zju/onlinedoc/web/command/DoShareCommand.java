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

/**
 * @author tonywjd
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DoShareCommand implements ICommand
{

    /* (non-Javadoc)
     * @see zju.onlinedoc.web.command.ICommand#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        System.out.println("Enter DoShareCommand.execute.");
        
        int[] fIds = (int[])request.getSession().getAttribute("shareFIds");
        if (fIds == null || fIds.length == 0)
        {
            CommandHelp.setFailAttribute(request, "home", 
                    CommandHelp.makeRed("You should shoose the right files to share."));
            return;
        }
        String strUserNames = request.getParameter("userNames");
        String[] userNames = null;
        try
        {
            userNames = strUserNames.split(",");
        }
        catch (Exception e)
        {
        }
        if (userNames == null || userNames.length == 0)
        {
            CommandHelp.setFailAttribute(request, "home", 
                    CommandHelp.makeRed("You should enter the user names to share with."));
            return;
        }
        String strType = request.getParameter("authorization");
        int type = 3;
        if ("share".equalsIgnoreCase(strType))
        {
            type = 2;
        }
        else
        {
            type = 3;
        }
        
        try
        {
            UserLoginClient client = CommandHelp.getUserLoginClient(request.getSession().getId());
            if (!client.createShareFile(fIds, userNames, type))
            {
                CommandHelp.setFailAttribute(request, "home", 
                        CommandHelp.makeRed("Unsuccessfully. Something wrong with the system, sorry."));
            }
            else
            {
                CommandHelp.getAndSetFileRecordArray(request);
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
