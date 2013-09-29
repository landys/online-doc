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
public class DoDeleteCommand implements ICommand
{

    /* (non-Javadoc)
     * @see zju.onlinedoc.web.command.ICommand#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        System.out.println("Enter DoDeleteCommand.execute.");
        
        int[] fIds = CommandHelp.getFileIdsFromEditPage(request);
        
        try
        {
            if (fIds == null || fIds.length == 0)
            {
                CommandHelp.getAndSetFileRecordArray(request);
                return;
            }
            
            UserLoginClient client = CommandHelp.getUserLoginClient(request.getSession().getId());
            if (!client.deleteFileList(fIds))
            {
                CommandHelp.setFailAttribute(request, "home", 
                        CommandHelp.makeRed("You should shoose the right files to delete."));
            }
            else
            {
            	for (int i=0; i<fIds.length; i++)
            	{
            		if (CommandHelp.getOpenTable().containsWithFileId(fIds[i]))
            		{
            			CommandHelp.getOpenTable().removeWithFileId(fIds[i]);
            		}
            	}
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
