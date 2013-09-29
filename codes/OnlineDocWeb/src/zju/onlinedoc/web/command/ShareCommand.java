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

/**
 * @author tonywjd
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ShareCommand implements ICommand
{

    /* (non-Javadoc)
     * @see zju.onlinedoc.web.command.ICommand#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        System.out.println("Enter DoShareCommand.execute.");
        
        int[] fIds = CommandHelp.getFileIdsFromEditPage(request);
        if (fIds == null || fIds.length == 0)
        {
            CommandHelp.setFailAttribute(request, "home", 
                    CommandHelp.makeRed("You should shoose the right files to share."));
            return;
        }
        request.getSession().setAttribute("shareFIds", fIds);
    }

}
