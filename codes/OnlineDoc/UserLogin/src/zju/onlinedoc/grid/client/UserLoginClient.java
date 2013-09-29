 		 
package zju.onlinedoc.grid.client;
import org.apache.axis.message.addressing.Address;
import org.apache.axis.message.addressing.EndpointReferenceType;
import org.globus.axis.util.Util;
import java.rmi.RemoteException;
import zju.onlinedoc.grid.impl.*;
//import zju.onlinedoc.grid.stubs.*;
//import zju.onlinedoc.grid.stubs.bindings.*;
//import zju.onlinedoc.grid.stubs.service.*;
import zju.onlinedoc.grid.userLoginService.stubs.*;
import zju.onlinedoc.grid.userLoginService.stubs.bindings.*;
import zju.onlinedoc.grid.userLoginService.stubs.service.*;
import de.fb12.utils.beanutils.Beanutils;
import org.oasis.wsrf.lifetime.Destroy;
import org.oasis.wsrf.properties.GetResourcePropertyResponse;
import org.oasis.wsrf.properties.InvalidResourcePropertyQNameFaultType;
import org.oasis.wsrf.properties.ResourceUnknownFaultType;
import org.apache.axis.types.URI.MalformedURIException;
import javax.xml.rpc.ServiceException;
import org.oasis.wsrf.lifetime.ResourceNotDestroyedFaultType;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.List;


public class UserLoginClient implements UserLoginNamespaces
{

  static {
   Util.registerTransport();
  }

  private UserLoginPortType port =null;
  String CLIENT_DESC = "client-security-descriptor.xml";

  //private String instanceURI="http://127.0.0.1:8080/wsrf/services/UserLoginService";


  public UserLoginPortType getPort(String instanceURI, boolean useSecurity) throws MalformedURIException, ServiceException, RemoteException {
    EndpointReferenceType instanceEPR;
    UserLoginServiceAddressingLocator locator = new UserLoginServiceAddressingLocator();    
    instanceEPR = new EndpointReferenceType();
    instanceEPR.setAddress(new Address(instanceURI));
    UserLoginPortType port = locator.getUserLoginPortTypePort(instanceEPR);
    if (useSecurity){((javax.xml.rpc.Stub)port)._setProperty(org.globus.wsrf.security.Constants.CLIENT_DESCRIPTOR_FILE, CLIENT_DESC);}
    CreateResourceResponse createResponse = port.createResource(new CreateResource());
    // Create resource and get endpoint reference of WS-Resource
    instanceEPR = createResponse.getEndpointReference();
	// Get instance PortType
	 port = locator.getUserLoginPortTypePort(instanceEPR);
	 if (useSecurity){((javax.xml.rpc.Stub)port)._setProperty(org.globus.wsrf.security.Constants.CLIENT_DESCRIPTOR_FILE, CLIENT_DESC);}
    return port;
  }


  public UserLoginClient(String instanceURI, boolean useSecurity) throws MalformedURIException, RemoteException, ServiceException {
   this.port=getPort(instanceURI, useSecurity);
   //this.instanceURI=instanceURI;
  }




	/**
	 *
	 * @generated
	 */
	public int loginNewUser(java.lang.String userName, java.lang.String password) throws RemoteException  {
		
		int retVal=  this.port.loginNewUser(new LoginNewUser(password, userName)).getLoginNewUserReturn();
		return retVal;
	
	}
	
	
	/**
	 *
	 * @generated
	 */
	public int canLoginSystem(java.lang.String userName, java.lang.String password) throws RemoteException  {
		
		int retVal=  this.port.canLoginSystem(new CanLoginSystem(password, userName)).getCanLoginSystemReturn();
		return retVal;
	
	}
	
	
	/**
	 *
	 * @generated
	 */
	public int createEmptyFile(java.lang.String fileName) throws RemoteException  {
		
		int retVal=  this.port.createEmptyFile(new CreateEmptyFile(fileName)).getCreateEmptyFileReturn();
		return retVal;
	
	}
	
	
	/**
	 *
	 * @generated
	 */
	public int createFile(java.lang.String fileName, java.lang.String fileContent) throws RemoteException  {
		
		int retVal=  this.port.createFile(new CreateFile(fileContent, fileName)).getCreateFileReturn();
		return retVal;
	
	}
	
	
	/**
	 *
	 * @generated
	 */
	public boolean deleteFile(int fileId) throws RemoteException  {
		
		boolean retVal=  this.port.deleteFile(new DeleteFile(fileId)).isDeleteFileReturn();
		return retVal;
	
	}
	
	
	/**
	 *
	 * @generated
	 */
	public zju.onlinedoc.meta.FileRecord[] getFileRecordArray() throws RemoteException  {
		
		zju.onlinedoc.meta.FileRecord[] retVal; 
		retVal= (zju.onlinedoc.meta.FileRecord[]) Beanutils.copyProperties( this.port.getFileRecordArray(new GetFileRecordArray()).getGetFileRecordArrayReturn(), zju.onlinedoc.meta.FileRecord[].class);
		return retVal;
	
	}
	
	
	/**
	 *
	 * @generated
	 */
	public java.lang.String getFileContent(int fileId) throws RemoteException  {
		
		java.lang.String retVal=  this.port.getFileContent(new GetFileContent(fileId)).getGetFileContentReturn();
		return retVal;
	
	}
	
	
	/**
	 *
	 * @generated
	 */
	public boolean updateFileContent(int fileId, java.lang.String fileContent) throws RemoteException  {
		
		boolean retVal=  this.port.updateFileContent(new UpdateFileContent(fileContent, fileId)).isUpdateFileContentReturn();
		return retVal;
	
	}
	
	
	/**
	 *
	 * @generated
	 */
	public boolean renameFile(int fileId, java.lang.String fileName) throws RemoteException  {
		
		boolean retVal=  this.port.renameFile(new RenameFile(fileId, fileName)).isRenameFileReturn();
		return retVal;
	
	}
	
	
	/**
	 *
	 * @generated
	 */
	public boolean updateFile(int fileId, java.lang.String fileName, java.lang.String fileContent) throws RemoteException  {
		
		boolean retVal=  this.port.updateFile(new UpdateFile(fileContent, fileId, fileName)).isUpdateFileReturn();
		return retVal;
	
	}
	
	
	/**
	 *
	 * @generated
	 */
	public zju.onlinedoc.meta.UserInfo[] getUserNameArray(int fileId) throws RemoteException  {
		
		zju.onlinedoc.meta.UserInfo[] retVal; 
		retVal= (zju.onlinedoc.meta.UserInfo[]) Beanutils.copyProperties( this.port.getUserNameArray(new GetUserNameArray(fileId)).getGetUserNameArrayReturn(), zju.onlinedoc.meta.UserInfo[].class);
		return retVal;
	
	}
	
	
	/**
	 *
	 * @generated
	 */
	public boolean createShareFile(int[] fileIds, java.lang.String[] userNames, int type) throws RemoteException  {
		boolean retVal=  this.port.createShareFile(new CreateShareFile(fileIds, type, userNames)).isCreateShareFileReturn();
		return retVal;
	
	}
	
	
	/**
	 *
	 * @generated
	 */
	public boolean deleteFileList(int[] fileIds) throws RemoteException  {
		boolean retVal=  this.port.deleteFileList(new DeleteFileList(fileIds)).isDeleteFileListReturn();
		return retVal;
	
	}
	
	
	/**
	 *
	 * @generated
	 */
	public zju.onlinedoc.meta.stubs.FileRecord getFileRecord(int fileId) throws RemoteException  {
		
		zju.onlinedoc.meta.FileRecord retVal; 
            retVal = (zju.onlinedoc.meta.FileRecord) Beanutils.copyProperties( this.port.getFileRecord(new GetFileRecord(fileId)).getGetFileRecordReturn()); 
		return retVal;
	
	}
	
	


  

			 	/**
	             * @generated
	             */
			  public org.oasis.wsrf.properties.GetResourcePropertyResponse getResourceProperty(javax.xml.namespace.QName getResourcePropertyRequest) throws org.oasis.wsrf.properties.InvalidResourcePropertyQNameFaultType, org.oasis.wsrf.properties.ResourceUnknownFaultType, RemoteException
              {
                return this.port.getResourceProperty(getResourcePropertyRequest);
              }

	/**
	 * @generated
	 */
	public org.oasis.wsrf.properties.GetResourcePropertyResponse getUserIdAsResourceProperty() throws Exception, InvalidResourcePropertyQNameFaultType, ResourceUnknownFaultType, RemoteException {
    	return getResourceProperty(RP_USERID);
	}

  
  public static void main(String[] args) {
    String instanceURI="http://127.0.0.1:8080/wsrf/services/UserLoginService";
    boolean useSecurity = false;

    try {
      UserLoginClient client = new UserLoginClient(  instanceURI, useSecurity);
      
       
      
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
