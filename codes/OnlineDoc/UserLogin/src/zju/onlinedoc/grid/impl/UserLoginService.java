         
package zju.onlinedoc.grid.impl;

import java.rmi.RemoteException;
import org.globus.wsrf.*;
import org.apache.axis.message.addressing.EndpointReferenceType;
import org.globus.wsrf.utils.AddressingUtils;

import zju.onlinedoc.application.FileSupport;
import zju.onlinedoc.application.LoginSupport;
import zju.onlinedoc.grid.userLoginService.stubs.*;
import zju.onlinedoc.grid.impl.*;
import zju.onlinedoc.grid.UserLogin;
import zju.onlinedoc.meta.FileRecord;
import zju.onlinedoc.meta.UserInfo;
import de.fb12.utils.beanutils.Beanutils;
import org.globus.wsrf.impl.ResourceContextImpl;
import java.lang.reflect.InvocationTargetException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.globus.wsrf.Constants;
import org.globus.wsrf.ResourceContext;
import org.globus.wsrf.ResourceContextException;


public class UserLoginService {
 
    /**
     *
     * @generated
     */
    public CreateResourceResponse createResource(CreateResource request) throws RemoteException {
		ResourceContext ctx = null;
		UserLoginResourceHome home = null;
		ResourceKey key = null;

		try {
			ctx = ResourceContext.getResourceContext();

		
		
		

		home = (UserLoginResourceHome)ctx.getResourceHome();
		key = home.create();

		} catch (Exception e) {
			e.printStackTrace();
			throw new RemoteException("", e);
		}
		EndpointReferenceType epr = null;
		try {
			epr = AddressingUtils.createEndpointReference(ctx, key);
		} catch (Exception e) {
			throw new RemoteException("", e);
		}
		CreateResourceResponse response = new CreateResourceResponse();
		response.setEndpointReference(epr);
		return response;
	}

    /**
     *
     * @generated
     */
    private UserLoginResource getResource() throws RemoteException {
		Object resource = null;
		try {
			resource = ResourceContext.getResourceContext().getResource();
		} catch (Exception e) {
			throw new RemoteException("", e);
		}
		UserLoginResource res = (UserLoginResource)resource;
		return res;
	}
    /**
     * @generated
     */
    private DataSource getJNDIUserLoginDataSource()
	{
		DataSource source=null;
		try 
		{
			ResourceContext ctx = ResourceContext.getResourceContext();
        	Context initialContext = new InitialContext();
          
			String lookupString = Constants.JNDI_SERVICES_BASE_NAME + ctx.getService() +"/UserLogin";
        	source = (DataSource) initialContext.lookup(lookupString);
		} 
		catch (NamingException e) 
		{
			e.printStackTrace();
		} 
		catch (ResourceContextException e)
		{
			e.printStackTrace();
		}
		return source;
	}

    /**
     *
     * @modifiable-generated
     */
    public LoginNewUserResponse loginNewUser(LoginNewUser complexType) throws RemoteException  {		
		LoginNewUserResponse response = new LoginNewUserResponse();

        // begin-user-code
        String userName = complexType.getUserName();
        String userPwd = complexType.getPassword();
        LoginSupport ls = new LoginSupport();
        response.setLoginNewUserReturn(ls.loginNewUser(userName, userPwd));
        // end-user-code

		return response;		
	}

    /**
     *
     * @modifiable-generated
     */
    public CanLoginSystemResponse canLoginSystem(CanLoginSystem complexType) throws RemoteException  {		
		CanLoginSystemResponse response = new CanLoginSystemResponse();

        // begin-user-code
        UserLoginResource resource = getResource();
        String userName = complexType.getUserName();
        String userPwd = complexType.getPassword();
        LoginSupport ls = new LoginSupport();
        int userId = ls.canLoginSystem(userName, userPwd);
        resource.setUserId(userId);
        response.setCanLoginSystemReturn(userId);
        // end-user-code

		return response;		
	}

    /**
     *
     * @modifiable-generated
     */
    public CreateEmptyFileResponse createEmptyFile(CreateEmptyFile complexType) throws RemoteException  {		
		CreateEmptyFileResponse response = new CreateEmptyFileResponse();

        // begin-user-code
        UserLoginResource resource = getResource();
        int userId = resource.getUserId();
        String fileName = complexType.getFileName();
        FileSupport fs = new FileSupport();
        
        int fileId = fs.createFile(userId, fileName);
        response.setCreateEmptyFileReturn(fileId);
        // end-user-code

		return response;		
	}

    /**
     *
     * @modifiable-generated
     */
    public CreateFileResponse createFile(CreateFile complexType) throws RemoteException  {		
		CreateFileResponse response = new CreateFileResponse();

        // begin-user-code
        UserLoginResource resource = getResource();
        int userId = resource.getUserId();
        String fileName = complexType.getFileName();
        String fileContent = complexType.getFileContent();
        FileSupport fs = new FileSupport();
        
        int fileId = fs.createFile(userId, fileName, fileContent);
        response.setCreateFileReturn(fileId);
        // end-user-code

		return response;		
	}

    /**
     *
     * @modifiable-generated
     */
    public DeleteFileResponse deleteFile(DeleteFile complexType) throws RemoteException  {		
		DeleteFileResponse response = new DeleteFileResponse();

        // begin-user-code
        UserLoginResource resource = getResource();
        int userId = resource.getUserId();
        int fileId = complexType.getFileId();
        FileSupport fs = new FileSupport();
        
        boolean isSuccess = fs.deleteFile(userId, fileId);
        response.setDeleteFileReturn(isSuccess);
        // end-user-code

		return response;		
	}

    /**
     *
     * @modifiable-generated
     */
    public GetFileRecordArrayResponse getFileRecordArray(GetFileRecordArray complexType) throws RemoteException  {		
		GetFileRecordArrayResponse response = new GetFileRecordArrayResponse();

        // begin-user-code
        UserLoginResource resource = getResource();
        int userId = resource.getUserId();
        FileSupport fs = new FileSupport();
        
        zju.onlinedoc.meta.FileRecord[] fileList = fs.getFileRecordArray(userId);
        zju.onlinedoc.meta.stubs.FileRecord[] list = new zju.onlinedoc.meta.stubs.FileRecord[fileList.length];
        for(int i = 0; i < fileList.length; i++)
        {
            list[i] = new zju.onlinedoc.meta.stubs.FileRecord();
            list[i].setFileCreateDate(fileList[i].getFileCreateDate());
            list[i].setFileName(fileList[i].getFileName());
            list[i].setFileId(fileList[i].getFileId());
            list[i].setFileType(fileList[i].getFileType());
        }
        
        response.setGetFileRecordArrayReturn(list);
        // end-user-code

		return response;		
	}

    /**
     *
     * @modifiable-generated
     */
    public GetFileContentResponse getFileContent(GetFileContent complexType) throws RemoteException  {		
		GetFileContentResponse response = new GetFileContentResponse();

        // begin-user-code
        UserLoginResource resource = getResource();
        int userId = resource.getUserId();
        int fileId = complexType.getFileId();
        FileSupport fs = new FileSupport();
        
        String content = fs.getFileContent(userId, fileId);
        response.setGetFileContentReturn(content);
        // end-user-code

		return response;		
	}

    /**
     *
     * @modifiable-generated
     */
    public UpdateFileContentResponse updateFileContent(UpdateFileContent complexType) throws RemoteException  {		
		UpdateFileContentResponse response = new UpdateFileContentResponse();

        // begin-user-code
        UserLoginResource resource = getResource();
        int userId = resource.getUserId();
        int fileId = complexType.getFileId();  
        String content = complexType.getFileContent();
        
        FileSupport fs = new FileSupport();
        System.out.println("in server:userId=" + userId + " fileId=" + fileId);
        boolean isSuccess = fs.updateFileContent(userId, fileId, content);
        response.setUpdateFileContentReturn(isSuccess);
        // end-user-code

		return response;		
	}

    /**
     *
     * @modifiable-generated
     */
    public RenameFileResponse renameFile(RenameFile complexType) throws RemoteException  {		
		RenameFileResponse response = new RenameFileResponse();

        // begin-user-code
        UserLoginResource resource = getResource();
        int userId = resource.getUserId();
        int fileId = complexType.getFileId(); 
        String fileName = complexType.getFileName();
        
        FileSupport fs = new FileSupport();
        boolean isSuccess = fs.renameFile(userId, fileId, fileName);
        response.setRenameFileReturn(isSuccess);
        // end-user-code

		return response;		
	}

    /**
     *
     * @modifiable-generated
     */
    public UpdateFileResponse updateFile(UpdateFile complexType) throws RemoteException  {		
		UpdateFileResponse response = new UpdateFileResponse();

        // begin-user-code
        UserLoginResource resource = getResource();
        int userId = resource.getUserId();
        int fileId = complexType.getFileId();
        String fileName = complexType.getFileName();
        String fileContent = complexType.getFileContent();
        
        FileSupport fs = new FileSupport();
        boolean isSuccess = fs.updateFile(userId, fileId, fileName, fileContent);
        response.setUpdateFileReturn(isSuccess);
        // end-user-code

		return response;		
	}

    /**
     *
     * @modifiable-generated
     */
    public GetUserNameArrayResponse getUserNameArray(GetUserNameArray complexType) throws RemoteException  {		
		GetUserNameArrayResponse response = new GetUserNameArrayResponse();

        // begin-user-code
        int fileId = complexType.getFileId();
        
        FileSupport fs = new FileSupport();
        UserInfo[] usrList = fs.getUserNameArray(fileId);
        
        zju.onlinedoc.meta.stubs.UserInfo[] list = new zju.onlinedoc.meta.stubs.UserInfo[usrList.length];
        for(int i = 0; i < usrList.length; i++)
        {
            list[i] = new zju.onlinedoc.meta.stubs.UserInfo();
            list[i].setType(usrList[i].getType());
            list[i].setUserId(usrList[i].getUserId());
            list[i].setUserName(usrList[i].getUserName());
        }
        response.setGetUserNameArrayReturn(list);
        // end-user-code

		return response;		
	}

    /**
     *
     * @modifiable-generated
     */
    public CreateShareFileResponse createShareFile(CreateShareFile complexType) throws RemoteException  {		
		CreateShareFileResponse response = new CreateShareFileResponse();

        // begin-user-code
        UserLoginResource resource = getResource();
        int userId = resource.getUserId();
        int[] fileIds = complexType.getFileIds();
        String[] userNames = complexType.getUserNames();
        int type = complexType.getType();
        
        FileSupport fs = new FileSupport();
        boolean isSuccess = fs.createShareFile(userId, fileIds, userNames, type);
        response.setCreateShareFileReturn(isSuccess);
        // end-user-code

		return response;		
	}

    /**
     *
     * @modifiable-generated
     */
    public DeleteFileListResponse deleteFileList(DeleteFileList complexType) throws RemoteException  {		
		DeleteFileListResponse response = new DeleteFileListResponse();

        // begin-user-code
        int[] fileIds = complexType.getFileIds();
        FileSupport fs = new FileSupport();
        boolean isSuccess = fs.deleteFileList(fileIds);
        response.setDeleteFileListReturn(isSuccess);
        // end-user-code

		return response;		
	}

    /**
     *
     * @modifiable-generated
     */
    public GetFileRecordResponse getFileRecord(GetFileRecord complexType) throws RemoteException  {		
		GetFileRecordResponse response = new GetFileRecordResponse();

        // begin-user-code
        UserLoginResource resource = getResource();
        int userId = resource.getUserId();
        int fileId = complexType.getFileId();
        FileSupport fs = new FileSupport();
        zju.onlinedoc.meta.FileRecord fr = fs.getFileRecord(userId, fileId);
        zju.onlinedoc.meta.stubs.FileRecord record = new zju.onlinedoc.meta.stubs.FileRecord();
        
        record.setFileCreateDate(fr.getFileCreateDate());
        record.setFileId(fr.getFileId());
        record.setFileName(fr.getFileName());
        record.setFileType(fr.getFileType());
        response.setGetFileRecordReturn(record);
        // end-user-code

		return response;		
	}

}
 