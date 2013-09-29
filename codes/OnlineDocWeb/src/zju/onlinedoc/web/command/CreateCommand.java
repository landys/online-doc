/**
 * CreateCommand.java
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
public class CreateCommand implements ICommand
{

    /* (non-Javadoc)
     * @see zju.onlinedoc.web.command.ICommand#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        System.out.println("Enter CreateCommand.execute.");
        
        Integer fId = (Integer)request.getSession().getAttribute("fId");
    	String fName = (String)request.getSession().getAttribute("fName");
    	String fContent = (String)request.getSession().getAttribute("fContent");
    	String[] infos = (String[])request.getSession().getAttribute("userInfos");
    	Integer fType = (Integer)request.getSession().getAttribute("fType");

    	if (fId != null)
    	{
    		request.getSession().removeAttribute("fId");
    	}
    	if (fName != null)
    	{
    		request.getSession().removeAttribute("fName");
    	}
    	if (fContent != null)
    	{
    		request.getSession().removeAttribute("fContent");
    	}
    	if (fType != null)
    	{
    		request.getSession().removeAttribute("fType");
    	}
    	if (infos != null)
    	{
    		request.getSession().removeAttribute("userInfos");
    	}
    }

}
