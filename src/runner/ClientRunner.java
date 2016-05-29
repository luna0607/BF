package runner;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import rmi.RemoteHelper;
import service.IOService;
import ui.MainFrame;

public class ClientRunner {
	private RemoteHelper remoteHelper;
	
	private ClientRunner() {
		linkToServer();
		initGUI();
	}
	
	private void linkToServer() {
		try {
			remoteHelper = RemoteHelper.getInstance();
			remoteHelper.setRemote(Naming.lookup("rmi://localhost:8888/DataRemoteObject"));
			System.out.println("linked");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}
	
	private void initGUI() {
		try {
			MainFrame mainFrame = new MainFrame();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	private void test(){
		try {

		System.out.println(remoteHelper.getUserService().login("admin", "123456"));
			System.out.println(remoteHelper.getIOService().writeFile("2", "admin", "testFile","time"));
				System.out.println(remoteHelper.getIOService().readFileFullName().size());

		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args){
		ClientRunner cr = new ClientRunner();
		cr.test();
	}
}
