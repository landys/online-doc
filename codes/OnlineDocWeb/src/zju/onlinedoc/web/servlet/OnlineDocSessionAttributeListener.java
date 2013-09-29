/**
 * OnlineDocSessionAttributeListener.java
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

package zju.onlinedoc.web.servlet;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import zju.onlinedoc.web.command.CommandHelp;

/**
 * @author tonywjd
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class OnlineDocSessionAttributeListener implements
        HttpSessionAttributeListener
{

    /* (non-Javadoc)
     * @see javax.servlet.http.HttpSessionAttributeListener#attributeAdded(javax.servlet.http.HttpSessionBindingEvent)
     */
    public void attributeAdded(HttpSessionBindingEvent event)
    {
        if ("loginUserName".equals(event.getName()))
        {
            System.out.println(event.getValue() + " login.");
        }

    }

    /* (non-Javadoc)
     * @see javax.servlet.http.HttpSessionAttributeListener#attributeRemoved(javax.servlet.http.HttpSessionBindingEvent)
     */
    public void attributeRemoved(HttpSessionBindingEvent event)
    {
        if ("loginUserId".equals(event.getName()))
        {
            CommandHelp.getOpenTable().removeWithUserId((Integer)event.getValue());
        }
        
        if ("loginUserName".equals(event.getName()))
        {
            System.out.println(event.getValue() + " leaves.");
        }

    }

    /* (non-Javadoc)
     * @see javax.servlet.http.HttpSessionAttributeListener#attributeReplaced(javax.servlet.http.HttpSessionBindingEvent)
     */
    public void attributeReplaced(HttpSessionBindingEvent event)
    {
        if ("loginUserName".equals(event.getName()))
        {
            System.out.println(event.getValue() + " login, and the previous one leaves.");
        }
    }

}
