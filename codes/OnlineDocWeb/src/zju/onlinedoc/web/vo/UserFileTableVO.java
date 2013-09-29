/**
 * UserFileListVO.java
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

package zju.onlinedoc.web.vo;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author tonywjd
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class UserFileTableVO
{
    private Map<Integer, Integer> table = Collections.synchronizedMap(new HashMap<Integer, Integer>());
    
    public void add(UserFileVO vo)
    {
//        System.out.println("Enter add..............");
//        printTable();
        table.put(vo.getFileId(), vo.getUserId());
//        printTable();
    }
    
    public boolean contains(Object obj)
    {
//        System.out.println("Enter contains..............");
        return table.containsKey(((UserFileVO)obj).getFileId());
    }
    
    public boolean containsWithFileId(int fileId)
    {
//        System.out.println("Enter containsWithFileId..............");
        return table.containsKey(fileId);
    }
    
    public int getUserId(int fileId)
    {
//        System.out.println("Enter getUserId..............");
        if (!containsWithFileId(fileId))
        {
            return -1;
        }
        return table.get(fileId);
    }
    
    public void removeWithUserId(int userId)
    {
//        System.out.println("Enter removeWithUserId..............");
//        printTable();
        for (Entry<Integer, Integer> entry : table.entrySet())
        {
            if (entry.getValue() == userId)

            {
                table.remove(entry.getKey());
            }
        }
//        printTable();
    }
    
    public void removeWithFileId(int fileId)
    {
//        System.out.println("Enter removeWithFileId..............");
//        printTable();
        table.remove(fileId);
//        printTable();
    }
    
    public void remove(Object obj)
    {
//        System.out.println("Enter remove..............");
//        printTable();
        table.remove(((UserFileVO)obj).getFileId());
//        printTable();
    }
    
    public void printTable()
    {
        System.out.println("*********************************");
        System.out.println("FileId\tUserId");
        for (Entry entry : table.entrySet())
        {
            System.out.println(entry.getKey() + "\t" + entry.getValue());
        }
    }
}
