/**
 * TestFileApplication.java
 * 
 * Copyright (c) 2006 Szh
 * All rights free.
 *
 * 
 * Revision History
 *
 * Date				Programmer			Notes
 * -------------	-----------------	---------------------------
 * Feb 03, 2007		Developer Name		initial
*/

package test;

import zju.onlinedoc.application.FileApplication;

public class TestFileApplication
{
    public static void main(String[] argc)
    {
        FileApplication fa = new FileApplication();
        String first = new String("adfasdfasdfasdf");
        String second = new String("szh hellofasdfasdffasdfdfdfasdfdfdf\nadfadsfasdfasdfasfdfasf\rfasdfasdfasdfasfsdfasdddddddddddddddddddddddddddd" +
                "ddddddddddddddddddddddddddddddddddddddddddddfadfasdfadsfds\ndasfasfdasdfdfafasdfasfdafadfasdfasdfasdfasdfafadsssssssssssssssssssssfdsafdfadfasdfasf\r\nsafdasdfasdfafasdfasdfd" +
                "ddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddadfadfd" +
                "dsafddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "ddddddddddddddddddfffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffgggggggggggggggggggggg" +
                "asaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\nÊ¢Õñ»ª");
        fa.createFile(1);
        fa.updateFile(1, first);
        fa.updateFile(1, second);
        String str = fa.readFile(1).toString();
        System.out.println(str);
        fa.deleteFile(1);
        fa.createFile(2);
        fa.createFile(3);
        fa.createFile(4);       
    }
}
