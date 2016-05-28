//服务器IOService的Stub，内容相同
package service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface IOService extends Remote{
	public ArrayList<String> readFileFullName() throws RemoteException;

	public boolean writeFile(String file, String userId, String fileName,String time)throws RemoteException;
	
	public String readFile(String userId, String fileName)throws RemoteException;
	
	public String readFileList(String userId)throws RemoteException;
}
