package ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.RemoteException;
import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.text.SimpleDateFormat;
import  java.util.*;

import com.sun.org.apache.bcel.internal.generic.RETURN;
import rmi.RemoteHelper;

public class MainFrame extends JFrame {

	private static void InitGlobalFont(Font font) {
		FontUIResource fontRes = new FontUIResource(font);
		for (Enumeration<Object> keys = UIManager.getDefaults().keys();
			 keys.hasMoreElements(); ) {
			Object key = keys.nextElement();
			Object value = UIManager.get(key);
			if (value instanceof FontUIResource) {
				UIManager.put(key, fontRes);
			}
		}}
	private String userId;
private Date now=new Date();
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd/HH/mm/ss");
	private String time= dateFormat.format( now );
	private JTextArea textArea=new JTextArea();
	private JMenuBar menuBar = new JMenuBar();
 	private JLabel label1=new JLabel();
	private JLabel label2=new JLabel();
	private JTextField textField=new JTextField();
	private JTextField textField2=new JTextField();
	private JFrame frame = new JFrame("BF Client");
  //  private ArrayList<String> fileFullName=RemoteHelper.getInstance().getIOService().readFileFullName();

	public MainFrame() throws InterruptedException, RemoteException {
		System.out.println(time);
		InitGlobalFont(new Font("Tempus Sans ITC",Font.PLAIN,26));
		// 创建窗体
		frame.setSize(1000, 800);
		frame.setResizable(false);
		frame.setLayout(null);
        frame.addWindowStateListener(new WindowStateListener () {

			public void windowStateChanged(WindowEvent state) {
				if(state.getNewState()==6||state.getNewState()==0){
					textArea.setBounds(0, 0, frame.getWidth(), frame.getHeight()/2);
					label1.setBounds(0,frame.getHeight()/2,frame.getWidth()/2,frame.getHeight()/40);
					label2.setBounds(frame.getWidth()/2,frame.getHeight()/2,frame.getWidth()/2,frame.getHeight()/40);
					textField.setBounds(0, (21*frame.getHeight()/40), frame.getWidth()/2, 3*frame.getHeight()/10);
					textField2.setBounds(frame.getWidth()/2, (21*frame.getHeight()/40), frame.getWidth()/2, 3*frame.getHeight()/10);
				//	label1.setFont(new Font("Tempus Sans ITC",Font.PLAIN,26));
					//label2.setFont(new Font("Tempus Sans ITC",Font.PLAIN,26));
				}
			}


		});
//file的下拉菜单项

		JMenu fileMenu = new JMenu("File");
		menuBar.add(fileMenu);

		JMenuItem newMenuItem = new JMenuItem("New");
		fileMenu.add(newMenuItem);
		JMenuItem openMenuItem = new JMenuItem("Open");
        ArrayList<String > projectNameArraylist;
		projectNameArraylist = new ArrayList<>();
		for (int i = 0; i <projectNameArraylist.size(); i++) {
            openMenuItem.add(new JMenuItem(projectNameArraylist.get(i)));
        }
        fileMenu.add(openMenuItem);
		JMenuItem saveMenuItem = new JMenuItem("Save");
		fileMenu.add(saveMenuItem);
		JMenuItem exitMenuItem = new JMenuItem("Exit");
		fileMenu.add(exitMenuItem);
		frame.setJMenuBar(menuBar);

		newMenuItem.addActionListener(new MenuItemActionListener());
		openMenuItem.addActionListener(new MenuItemActionListener());
		saveMenuItem.addActionListener(new SaveActionListener());
		exitMenuItem.addActionListener(new MenuItemActionListener());
		
		JMenu runMenu=new JMenu("Run");
		menuBar.add(runMenu);
		JMenuItem runMenuItem=new JMenuItem("Run");
		runMenu.add(runMenuItem);
		runMenuItem.addActionListener(new MenuItemActionListener());
		
		JMenu versionMenu=new JMenu("Version");
		menuBar.add(versionMenu);
		JMenuItem versionOne=new JMenuItem("version1.0");
		versionMenu.add(versionOne);
		versionOne.addActionListener(new MenuItemActionListener());
		JMenuItem versionTwo=new JMenuItem("version2.0");
		versionMenu.add(versionTwo);
		versionTwo.addActionListener(new MenuItemActionListener());
		JMenuItem versionThree=new JMenuItem("version3.0");
		versionMenu.add(versionThree);
		versionThree.addActionListener(new MenuItemActionListener());
		
		JMenu accountMenu=new JMenu("Account");
		menuBar.add(accountMenu);
		JMenuItem login=new JMenuItem("Login");
		accountMenu.add(login);
		login.addActionListener(new MenuItemActionListener());
		JMenuItem logout=new JMenuItem("Logout");
		accountMenu.add(logout);
		logout.addActionListener(new MenuItemActionListener());				
		
	//	OptionDialog optionDialog=new OptionDialog(); 		
		textArea = new JTextArea();
		textArea.setMargin(new Insets(10, 10, 10, 10));
		textArea.setBackground(Color.white);

		label1.setText("Input your order");
		label1.setFont(new Font("Tempus Sans ITC",Font.PLAIN,26));
		label1.setBackground(Color.GRAY);
		label2.setText("output your order");
		label2.setBackground(Color.GRAY);
		label2.setFont(new Font("Tempus Sans ITC",Font.PLAIN,26));
		textField.setBackground(Color.white);
		textField2.setBackground(Color.white);
		textArea.setBounds(0, 0, frame.getWidth(), frame.getHeight()/2);
		label1.setBounds(0,frame.getHeight()/2,frame.getWidth()/2,frame.getHeight()/40);
		label2.setBounds(frame.getWidth()/2,frame.getHeight()/2,frame.getWidth()/2,frame.getHeight()/40);
		textField.setBounds(0, (21*frame.getHeight()/40), frame.getWidth()/2, 3*frame.getHeight()/10);
		textField2.setBounds(frame.getWidth()/2, (21*frame.getHeight()/40), frame.getWidth()/2, 3*frame.getHeight()/10);
		 
		frame.add(textArea);
		frame.add(label1);
		frame.add(label2);
		frame.add(textField);
		frame.add(textField2);
		
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setLocation(400, 200);
		//frame.pack();
		frame.setResizable(true);
		frame.setVisible(true);
		
		System.out.println(frame.getWidth()+" "+frame.getHeight());
	}

	private class MenuItemActionListener implements ActionListener {
		/**
		 * 子菜单响应事件
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			String cmd = e.getActionCommand();
			if (cmd.equals("Open")) {
				try {
					ArrayList<String> fileFullName=RemoteHelper.getInstance().getIOService().readFileFullName();
					int barNumbers=projectName(fileFullName).size();
					textArea.setText("Open");
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}

			}else if (cmd.equals("New")) {
				if(userId!= null &&userId.length()>0){
					String name= JOptionPane.showInputDialog("Please give a name");
					try {
						RemoteHelper.getInstance().getIOService().writeFile("", userId, name,time);
					} catch (RemoteException e1) {
						e1.printStackTrace();
					}}
				else{
loginWarning();
				}
				textField2.setText("New");
			} else if (cmd.equals("Run")) {
				textField2.setText("Hello, result");
			}else if(cmd.equals("Exit")){
				System.exit(0);
			}else if(cmd.equals("version1.0")){
				textField2.setText("1");
			}else if(cmd.equals("version2.0")){
				textField2.setText("2");
			}else if(cmd.equals("version3.0")){
				textField2.setText("3");
			}else if(cmd.equals("Login")){
				JFrame littleFrame=new JFrame();
				littleFrame.setVisible(true);
				littleFrame.setSize(500,400);
				littleFrame.setTitle("登录");
                JLabel label=new JLabel("Login here",JLabel.CENTER);
                littleFrame.add(label);
                label.setBounds(0,0,500,50);
                littleFrame.setResizable(false);
				JTextField user=new JTextField();
				JPasswordField password=new JPasswordField();
                JButton sureButton=new JButton();
                sureButton.setText("OK");
				sureButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						char[]pass=password.getPassword();
						String password=new String(pass);
						userId=user.getText();
						try {
							if(RemoteHelper.getInstance().getUserService().login(user.getText(),password)){
								JFrame thirdFrame=new JFrame();
								JLabel newLabel=new JLabel();
								newLabel.setText("Successfully login");
								thirdFrame.add(newLabel);
								thirdFrame.setVisible(true);
								thirdFrame.setSize(200,150);
                            }
							else{
								JFrame thirdFrame=new JFrame();
								JLabel newLabel=new JLabel();
								newLabel.setText("           Wrong input");
                                thirdFrame.setResizable(false);
                                thirdFrame.setLayout(new BorderLayout());
								thirdFrame.add(newLabel,BorderLayout.CENTER);
								thirdFrame.setVisible(true);
								thirdFrame.setSize(200,150);
							}
						} catch (RemoteException e1) {
							e1.printStackTrace();
						}


					}
				});
                JButton closeButton=new JButton();
                closeButton.setText("Cancel");
				closeButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						littleFrame.dispose();
					}
				});
                littleFrame.setLayout(null);
                littleFrame.add(user);
                JLabel userLabel=new JLabel("Username");
                littleFrame.add(userLabel);
                userLabel.setBounds(25,100,150,50);
                JLabel passwordLabel=new JLabel("Password");
                littleFrame.add(passwordLabel);
                passwordLabel.setBounds(25,200,150,50);
                user.setBounds(140,100,300,50);
                littleFrame.add(password);
                password.setBounds(140,200,300,50);
				password.setEchoChar('*');
                littleFrame.add(sureButton);
                sureButton.setBounds(120,270,120,45);
                littleFrame.add(closeButton);
                closeButton.setBounds(240,270,120,45);

			}else if(cmd.equals("Logout")){
				textArea.setText("Logout");				
			}
		}
	}

	class SaveActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String code = textArea.getText();
			if(userId!= null&&(userId.length()>0)){
			try {
				RemoteHelper.getInstance().getIOService().writeFile(code, userId, "code",time);
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}}
			else{
				loginWarning();
			}
		}
	}

	private void loginWarning(){
		JFrame thirdFrame=new JFrame();
		JLabel newLabel=new JLabel();
		newLabel.setText("            Please login first.");
		thirdFrame.setResizable(false);
		thirdFrame.setLayout(new BorderLayout());
		thirdFrame.add(newLabel,BorderLayout.CENTER);
		thirdFrame.setVisible(true);
		thirdFrame.setSize(200,150);
	}

	/**
	 * 参数是fileFullName
	 * @return projectName
     */
    private ArrayList<String> projectName(ArrayList<String> fileFullName){
	ArrayList<String> list=new ArrayList<>();
	boolean isHere=false;
	for(String a:fileFullName){
		String[] s1=a.split("_");
		for (String aList : list) {
			isHere = aList.equals(s1[1]);
		}
		if(!isHere){
			list.add(s1[1]);
		}
	}
	return  list;
}

	/**
	 * 参数是fileFullName
	 */
	private ArrayList<String> Versions(String projectName,ArrayList<String> fileFullName){
		ArrayList<String> list=new ArrayList<>();
		for(String a:fileFullName){
			if(a.contains(projectName)){
				list.add(a);
			}
		}
		return list;
	}

	private int newstVersion(ArrayList<String> versions){
		int length=versions.size();
		int[] month=new int[length];
		int[] day=new int[length];
		int[]hour=new int[length];
		int[] minute=new int[length];
		int[] second=new int[length];
		int count=0;
		for(int i=0;i<versions.size();i++){
			String[] temp=versions.get(i).split("/");
			month[i]=Integer.parseInt(temp[1]);
			day[i]=Integer.parseInt(temp[2]);
			hour[i]=Integer.parseInt(temp[3]);
			minute[i]=Integer.parseInt(temp[4]);
			second[i]=Integer.parseInt(temp[5]);
		}

		/**
		 * 判断谁的时间最先。
		 */
		int index;
		index=-1;
		for(int i:month){
			if(i==getMax(month)) {
				count++;
			if(count>1){
				count=0;
				index=0;
				for(int j:day){
					if(j==getMax(day)){
						count++;
						if(count>1){
							count=0;
							index=0;
							for(int k:hour){
								if(k==getMax(hour)){
									count++;
									if(count>1){
										count=0;
										index=0;
										for(int l:minute){
											if(l==getMax(minute)){
												count++;
												if(count>1){
													count=0;
													index=0;
													for(int z:second){
														if(z==getMax(second)){
															count++;
															if(count==1){
																return index;
															}
															else{
																return -1;
															}
														}
														index++;
													}
												}
												else{return index;}
											}
											index++;
										}
									}
									else{return index;}
								}
								index++;
							}
						}
						else {return index;}
					}
					index++;
				}
			}
			else {return index;}
			}
			index++;
		}
		return index;
	}

	private  int getMax(int[] numbers){
		int max=0;
		for(int a:numbers){
			if(a>max)
				max=a;
		}
		return max;
	}

}
