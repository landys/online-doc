/**
 * 
 */
package zju.onlinedoc.util;

/**
 * @author Administrator
 * 
 */
public class OnlineDocException extends Exception
{
    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 4635502111173878755L;

    public OnlineDocException()
    {
        super();
    }

    public OnlineDocException(String message)
    {
        super(message);
    }

    public OnlineDocException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public OnlineDocException(Throwable cause)
    {
        super(cause);
    }
}
