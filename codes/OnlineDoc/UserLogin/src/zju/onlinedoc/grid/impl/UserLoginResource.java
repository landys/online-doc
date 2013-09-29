package zju.onlinedoc.grid.impl; 

import org.globus.wsrf.*;
import org.globus.wsrf.impl.*;

import zju.onlinedoc.grid.UserLogin;

public class UserLoginResource implements 
	Resource, ResourceIdentifier, ResourceProperties, UserLoginNamespaces
{
	private ResourcePropertySet propSet;
	private Object key;
	
	 
	/**
	 *
	 * @generated
	 */
	private ResourceProperty userIdRP;
	 
	 
	 
	
	

	/**
	 *
	 * @modifiable-generated
	 */
	public int getUserId() {
		// begin-user-code
		
		// end-user-code
		return (Integer)this.userIdRP.get(0);
	}
	/**
	 *
	 * @modifiable-generated
	 */
	public void setUserId(int userId) {
		// begin-user-code
		
		// end-user-code
		this.userIdRP.set(0, userId);
	}

	

	/**
	 *
	 * @modifiable-generated
	 */
	public ResourcePropertySet getResourcePropertySet() {
		// begin-user-code
		
		// end-user-code
		return this.propSet;
	}

	/**
	 *
	 * @modifiable-generated
	 */
	public Object getID() {
		// begin-user-code
		
		// end-user-code
		return this.key;
	}

	/**
	 *
	 * @modifiable-generated
	 */
	public Object create() throws Exception {
		this.key = new Integer(hashCode());
		initialize();
		// begin-user-code
		
		// end-user-code
		return key;
	}
	
	/**
	 *
	 * @modifiable-generated
	 */
	public void initialize()
	{
		this.propSet = new SimpleResourcePropertySet(RESOURCE_PROPERTIES);
		
		
	
		try {
	
	 
	 
	 
	 	userIdRP = new SimpleResourceProperty(RP_USERID);
	 	
	 	
		this.userIdRP.add(new Integer(0));
		
		 this.propSet.add(userIdRP);
	
	
			
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		// begin-user-code
		
		// end-user-code
	}
	
	
    
	
}
