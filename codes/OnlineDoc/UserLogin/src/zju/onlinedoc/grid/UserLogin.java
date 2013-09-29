package zju.onlinedoc.grid;

import zju.onlinedoc.meta.FileRecord;
import zju.onlinedoc.meta.UserInfo;
import de.fb12.gdt.GridService;
import de.fb12.gdt.GridAttribute;
import de.fb12.gdt.GridMethod;

@GridService (name = "UserLogin", namespace = "http://zju.edu/onlinedoc/grid/userlogin", targetPackage = "zju.onlinedoc.grid", serviceStyle = "SSTYLE_SIMPLE", resourceStyle = "RSTYLE_GLOBUS", operationProvider = "GetRPProvider", loadOnStartup = false, hotLoadable = false, securityDesc = "[]", dataSources = "DATASOURCE[SourceName:UserLogin, FactoryName:org.apache.commons.dbcp.BasicDataSourceFactory, SourceType:javax.sql.DataSource, SourceAuth:Container, DriverClass:org.gjt.mm.mysql.Driver, URL:jdbc:mysql://10.214.20.212:3306/onlinedoc, Username:root, Password:123456, MaxActive:20, MaxIdle:10, MaxWait:6000]")
public class UserLogin {
    
    @GridAttribute
    public int userId;
    
    @GridMethod
    public int loginNewUser(String userName, String password)
    {
        return 0;
    }

    @GridMethod
    public int canLoginSystem(String userName, String password)
    {
        return 0;
    }
   
    @GridMethod
    public int createEmptyFile(String fileName)
    {
        return 0;
    }

    @GridMethod
    public int createFile(String fileName, String fileContent)
    {
        return 0;
    }

    @GridMethod
    public boolean deleteFile(int fileId)
    {
        return true;
    }

    @GridMethod
    public FileRecord[] getFileRecordArray()
    {
        return null;
    }

    @GridMethod
    public String getFileContent(int fileId)
    {
        return null;
    }

    @GridMethod
    public boolean updateFileContent(int fileId, String fileContent)
    {
        return true;
    }

    @GridMethod
    public boolean renameFile(int fileId, String fileName)
    {
        return true;
    }

    @GridMethod
    public boolean updateFile(int fileId, String fileName, String fileContent)
    {
        return true;
    }
    
    @GridMethod
    public UserInfo[] getUserNameArray(int fileId)
    {
        return null;
    }

	@GridMethod public boolean createShareFile(int[] fileIds, String[] userNames, int type) {
	
		return true; 
	}


	@GridMethod public boolean deleteFileList(int[] fileIds) {
	
		return true; 
	}

    @GridMethod
    public FileRecord getFileRecord(int fileId)
    {
        return null;
    }
    
    public int getUserId()
    {
        return userId;
    }

    public void setUserId(int userId)
    {
        this.userId = userId;
    }
}