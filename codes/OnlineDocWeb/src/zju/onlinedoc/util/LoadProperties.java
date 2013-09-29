/**
 * 
 */
package zju.onlinedoc.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

/**
 * @author Administrator
 *
 */
public class LoadProperties
{
    /**
     * All properties in the hashmap, a property file is corresponding to hashmap entry
     */
    private static final HashMap<String, LoadProperties> propsMap = new HashMap<String, LoadProperties>();
    
	/**
	 * Properties of on property file
	 */
	private Properties props = new Properties();
    
	private LoadProperties()
	{
	}
	
	public static LoadProperties getInstance(final String propsName) throws OnlineDocException
	{
		if (!propsMap.containsKey(propsName))
		{
            LoadProperties lp = new LoadProperties();
			
			try
			{
				InputStream in = LoadProperties.class.getResourceAsStream(propsName);
				lp.props.load(in);
			}
			catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new OnlineDocException("Error: IO exception in reading sql property.");
			}
            catch (Exception e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
                throw new OnlineDocException("Error: Unknown exception, maybe file not found.");
            }
            propsMap.put(propsName, lp);
		}

		return propsMap.get(propsName);
	}
	
	public String getProp(final String key)
	{
		return props.getProperty(key);
	}
}
