/**
 * UserLoginPortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2RC2 Nov 14, 2006 (10:23:53 EST) WSDL2Java emitter.
 */

package zju.onlinedoc.grid.userLoginService.stubs;

public interface UserLoginPortType extends java.rmi.Remote {
    public zju.onlinedoc.grid.userLoginService.stubs.DeleteFileResponse deleteFile(zju.onlinedoc.grid.userLoginService.stubs.DeleteFile parameters) throws java.rmi.RemoteException;
    public zju.onlinedoc.grid.userLoginService.stubs.CreateFileResponse createFile(zju.onlinedoc.grid.userLoginService.stubs.CreateFile parameters) throws java.rmi.RemoteException;
    public zju.onlinedoc.grid.userLoginService.stubs.LoginNewUserResponse loginNewUser(zju.onlinedoc.grid.userLoginService.stubs.LoginNewUser parameters) throws java.rmi.RemoteException;
    public zju.onlinedoc.grid.userLoginService.stubs.CanLoginSystemResponse canLoginSystem(zju.onlinedoc.grid.userLoginService.stubs.CanLoginSystem parameters) throws java.rmi.RemoteException;
    public zju.onlinedoc.grid.userLoginService.stubs.CreateEmptyFileResponse createEmptyFile(zju.onlinedoc.grid.userLoginService.stubs.CreateEmptyFile parameters) throws java.rmi.RemoteException;
    public zju.onlinedoc.grid.userLoginService.stubs.GetFileRecordArrayResponse getFileRecordArray(zju.onlinedoc.grid.userLoginService.stubs.GetFileRecordArray parameters) throws java.rmi.RemoteException;
    public zju.onlinedoc.grid.userLoginService.stubs.GetFileContentResponse getFileContent(zju.onlinedoc.grid.userLoginService.stubs.GetFileContent parameters) throws java.rmi.RemoteException;
    public zju.onlinedoc.grid.userLoginService.stubs.UpdateFileContentResponse updateFileContent(zju.onlinedoc.grid.userLoginService.stubs.UpdateFileContent parameters) throws java.rmi.RemoteException;
    public zju.onlinedoc.grid.userLoginService.stubs.RenameFileResponse renameFile(zju.onlinedoc.grid.userLoginService.stubs.RenameFile parameters) throws java.rmi.RemoteException;
    public zju.onlinedoc.grid.userLoginService.stubs.UpdateFileResponse updateFile(zju.onlinedoc.grid.userLoginService.stubs.UpdateFile parameters) throws java.rmi.RemoteException;
    public zju.onlinedoc.grid.userLoginService.stubs.GetUserNameArrayResponse getUserNameArray(zju.onlinedoc.grid.userLoginService.stubs.GetUserNameArray parameters) throws java.rmi.RemoteException;
    public zju.onlinedoc.grid.userLoginService.stubs.CreateShareFileResponse createShareFile(zju.onlinedoc.grid.userLoginService.stubs.CreateShareFile parameters) throws java.rmi.RemoteException;
    public zju.onlinedoc.grid.userLoginService.stubs.DeleteFileListResponse deleteFileList(zju.onlinedoc.grid.userLoginService.stubs.DeleteFileList parameters) throws java.rmi.RemoteException;
    public zju.onlinedoc.grid.userLoginService.stubs.GetFileRecordResponse getFileRecord(zju.onlinedoc.grid.userLoginService.stubs.GetFileRecord parameters) throws java.rmi.RemoteException;
    public zju.onlinedoc.grid.userLoginService.stubs.CreateResourceResponse createResource(zju.onlinedoc.grid.userLoginService.stubs.CreateResource parameter) throws java.rmi.RemoteException;
    public org.oasis.wsrf.properties.GetResourcePropertyResponse getResourceProperty(javax.xml.namespace.QName getResourcePropertyRequest) throws java.rmi.RemoteException, org.oasis.wsrf.properties.InvalidResourcePropertyQNameFaultType, org.oasis.wsrf.properties.ResourceUnknownFaultType;
}
