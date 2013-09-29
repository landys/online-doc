package zju.onlinedoc.grid.impl; 

import org.globus.wsrf.ResourceKey;
import org.globus.wsrf.impl.ResourceHomeImpl;
import org.globus.wsrf.impl.SimpleResourceKey;


public class UserLoginResourceHome extends ResourceHomeImpl {

	public ResourceKey create() throws Exception {
		UserLoginResource resource = (UserLoginResource)createNewInstance();
		resource.create();
		ResourceKey key = new SimpleResourceKey(keyTypeName, resource.getID());
		add(key, resource);
		return key;
	}
	
	

}
