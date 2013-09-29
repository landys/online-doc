package zju.onlinedoc.grid.impl;

import javax.xml.namespace.QName;

public interface UserLoginNamespaces {

	
	public static final QName RP_USERID = new QName("http://zju.edu/onlinedoc/grid/userlogin/UserLogin","UserId");

	
	public static final QName RESOURCE_PROPERTIES = new QName("http://zju.edu/onlinedoc/grid/userlogin/UserLogin","UserLoginResourceProperties");

	public static final QName RESOURCE_REFERENCE = new QName("http://zju.edu/onlinedoc/grid/userlogin/UserLogin","UserLoginResourceReference");

	public static final QName KEY = new QName("http://zju.edu/onlinedoc/grid/userlogin/UserLogin","UserLoginResourceKey");

	public static final QName RP_SET = new QName("http://zju.edu/onlinedoc/grid/userlogin/UserLogin","UserLogin");
}
