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
import zju.onlinedoc.util.CheckHelp;

/**
 * @author tonywjd
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class DoLoginCommand implements ICommand
{

    /*
     * (non-Javadoc)
     * 
     * @see zju.onlinedoc.web.command.ICommand#execute(javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse)
     */
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        System.out.println("Enter DoLoginCommand.execute.");
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        if (!CheckHelp.checkGoodString(name) || !CheckHelp.checkGoodString(password))
        {
            System.out.println("Invalid name or password.");
            CommandHelp.setFailAttribute(request, "login", 
                    CommandHelp.makeRed("Invalid name or password."));
            return;
        }
        
        try
        {
            UserLoginClient client = CommandHelp.getUserLoginClient(request.getSession().getId());
            
            int userId = client.canLoginSystem(name, password);
            if (userId >= 0)
            {
                CommandHelp.setLoginSession(request.getSession(), userId, name);
                CommandHelp.getAndSetFileRecordArray(request);
            }
            else
            {
                CommandHelp.setFailAttribute(request, "login", 
                        CommandHelp.makeRed("Invalid name or password."));
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            CommandHelp.setFailAttribute(request, "login", 
                    CommandHelp.makeRed("Session timeout, you should relogin. Or Contact us..."));
        }
        
    }

}
