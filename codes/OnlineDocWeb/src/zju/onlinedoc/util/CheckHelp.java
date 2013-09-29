/**
 * CheckHelp.java
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

package zju.onlinedoc.util;

/**
 * @author tonywjd
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CheckHelp
{
    public static boolean checkNotNull(final Object obj)
    {
        return obj != null;
    }
    
    public static boolean checkGoodString(final String str)
    {
        return (str != null && str.length() > 0);
    }
}
