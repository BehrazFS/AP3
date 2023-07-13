
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class LoginFrame extends JFrame implements MouseListener , Service {
	private JPanel mainPanel , loggedInPanel;
	private JTextField userText , passText ;
	private JLabel msg1,msg2 , infomsg;
	private static JLabel errormsg;
	private JButton loginButton , signUpButton , manageButton , refresh ,action , logout,fstudent,fteacher;
	private User nowUser = new User(0);
	private static ArrayList<University> UList = new ArrayList<>();
	public LoginFrame() {
		try {
			File file = new File("CMS2.db");
			if(!file.exists()) {
				SQLight_DataBase.createSQL();
			}
			else {
				SQLight_DataBase.construct();
			}
			
			try {
				UList = SQLight_DataBase.getUniversityList();
			}
			catch (Exception e) {
				// TODO: handle exception
			}
			
			//mainPanel
			userText = new JTextField();
			userText.setBounds(100,80,300,30);
			
			passText = new JTextField();
			passText.setBounds(150,150,200,30);
			
			msg1 = new JLabel("Enter your username : ");
			msg1.setHorizontalAlignment(SwingConstants.CENTER);
			msg1.setBounds(100,50,300,30);
			
			msg2 = new JLabel("Enter your password : ");
			msg2.setHorizontalAlignment(SwingConstants.CENTER);
			msg2.setBounds(150,120,200,30);
			
			loginButton = new JButton("Login");
			loginButton.setBounds(200,200 , 100,30);
			loginButton.setFocusable(false);
			loginButton.addMouseListener(this);
			
			signUpButton = new JButton("Sign Up");
			signUpButton.setBounds(210,240 , 80,20);
			signUpButton.setFocusable(false);
			signUpButton.addMouseListener(this);
			
			
			errormsg = new JLabel();
			errormsg.setHorizontalAlignment(SwingConstants.CENTER);
			errormsg.setBounds(50,270,400,150);
			errormsg.setOpaque(true);
			errormsg.setBackground(Color.white);
			
			mainPanel = new JPanel();
			mainPanel.setSize(500,500);
			mainPanel.setLayout(null);
			mainPanel.add(userText);
			mainPanel.add(passText);
			mainPanel.add(msg1);
			mainPanel.add(msg2);
			mainPanel.add(loginButton);
			mainPanel.add(errormsg);
			mainPanel.add(signUpButton);
		//loggedInPanel
			infomsg = new JLabel();
			infomsg.setVerticalAlignment(SwingConstants.TOP);
			infomsg.setOpaque(true);
			infomsg.setBackground(Color.white);
			infomsg.setFont(new Font("MV Boli", Font.PLAIN,20));
	
			JScrollPane scroll = new JScrollPane(infomsg);
			scroll.setBounds(20,20,440,200);
			
			manageButton = new JButton("Manage");
			manageButton.setBounds(210,240 , 80,20);
			manageButton.setFocusable(false);
			manageButton.addMouseListener(this);
			
			
			refresh = new JButton("Refresh");
			refresh.setBounds(210,270 , 80,20);
			refresh.setFocusable(false);
			refresh.addMouseListener(this);
			
			action = new JButton("Action");
			action.setBounds(210,300 , 80,20);
			action.setFocusable(false);
			action.addMouseListener(this);
			
			logout = new JButton("Logout");
			logout.setBounds(210,330 , 80,20);
			logout.setFocusable(false);
			logout.addMouseListener(this);
			
			fteacher = new JButton("find teacher");
			fteacher.setBounds(190,360 , 120,20);
			fteacher.setFocusable(false);
			fteacher.addMouseListener(this);
			fstudent = new JButton("find student");
			fstudent.setBounds(190,390 , 120,20);
			fstudent.setFocusable(false);
			fstudent.addMouseListener(this);
			
			loggedInPanel = new JPanel();
			loggedInPanel.setSize(500,500);
			loggedInPanel.setLayout(null);
			loggedInPanel.add(scroll);
			loggedInPanel.add(refresh);
			loggedInPanel.add(logout);
			loggedInPanel.add(action);
			loggedInPanel.add(manageButton);
			loggedInPanel.add(fstudent);
			loggedInPanel.add(fteacher);
		//--frame--
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setResizable(false);
			this.setSize(500,500);
			this.setLayout(null);
			this.add(mainPanel);
			this.setVisible(true);
		}
		catch (Exception e) {
			// TODO: handle exception
		}
	}
	public static void setErrorMessege(String msg , Color color) {
		try {
			errormsg.setForeground(color);
			errormsg.setText(msg);
		}
		catch (Exception e) {
			// TODO: handle exception
		}
	}
	public static void addToUniversityList(University uni) {
		try {
			boolean exists = false;
			for(University item : UList) {
				if(item.getName().equals(uni.getName())) {
					exists = true;
				}
			}
			if(!exists) {
				UList.add(uni);
				try {
					SQLight_DataBase.addUniversity(uni.getName());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					
				}
			}
			else {
				for(University item : UList) {
					if(item.getName().equals(uni.getName())) {
						item.setType(uni.getType());
						item.setAddress(uni.getAddress());
					}
				}
			}
		}
		catch (Exception e) {
			// TODO: handle exception
		}
	}
	public static void refreshUniversityList() {
		
		try {
			UList = SQLight_DataBase.getUniversityList();
		}
		catch (Exception e) {
			// TODO: handle exception
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		try {
			if(e.getSource() == loginButton) {
				if(CheckUsername(userText.getText())) {
					if(CheckPassword(userText.getText(), passText.getText())){
						nowUser = SQLight_DataBase.getUser(userText.getText() , passText.getText());
						if(nowUser.getId() == 0) {
							setErrorMessege("something went wrong", Color.red);
						}
						else {
							infomsg.setText(nowUser.getPrintableInfo());
							this.remove(mainPanel);
							this.setVisible(false);
							this.add(loggedInPanel);
							this.setVisible(true);
						}
					}
					else {
						setErrorMessege("invalid password", Color.red);
					}
				}
				else {
					setErrorMessege("invalid username", Color.red);
				}
			}
			if(e.getSource() == signUpButton) {
				SignUpDialog sign = new SignUpDialog();
			}
			if(e.getSource() == manageButton) {
				ManageDialog manage = new ManageDialog(nowUser);
			}
			if(e.getSource() == refresh) {
				if(nowUser.getId() != 0) {
					nowUser.construct();
					infomsg.setText(nowUser.getPrintableInfo());
				}
				this.remove(loggedInPanel);
				this.setVisible(false);
				this.add(loggedInPanel);
				this.setVisible(true);
			}
			if(e.getSource() == logout) {
				nowUser = new User(0);
				userText.setText("");
				passText.setText("");
				this.remove(loggedInPanel);
				this.setVisible(false);
				this.add(mainPanel);
				this.setVisible(true);
			}
			if(e.getSource() == action) {
				if(nowUser.getRole().equals("manager")) {
					Manager mn = new Manager(nowUser);
					ManagerActionDialog mad = new ManagerActionDialog(mn);
				}
				if(nowUser.getRole().equals("admin")) {
					AdminPanel ap = new AdminPanel();
				}
				if(nowUser.getRole().equals("teacher")) {
					Teacher t = new Teacher(nowUser);
					TeacherActionDialog tad = new TeacherActionDialog(t);
				}
				if(nowUser.getRole().equals("student")) {
					Student st = new Student(nowUser);
					st.showScore();
				}
			}
			if(e.getSource() == fstudent) {
				String name = JOptionPane.showInputDialog("name : ");
				String lastname = JOptionPane.showInputDialog("lastname : ");
				findStudent(name, lastname);
			}
			if(e.getSource() == fteacher) {
				String name = JOptionPane.showInputDialog("name : ");
				String lastname = JOptionPane.showInputDialog("lastname : ");
				findTeacher(name, lastname);
			}
		}
		catch (Exception ex) {
			// TODO: handle exception
		}
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean CheckUsername(String username) {
		// TODO Auto-generated method stub
		try {
			return SQLight_DataBase.CheckUsername(username);
		}catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
	@Override
	public boolean CheckPassword(String username, String password) {
		// TODO Auto-generated method stub
		try {
			return SQLight_DataBase.CheckPassword(username, password);
		}
		catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
	@Override
	public void findTeacher(String name, String lastname) {
		// TODO Auto-generated method stub
		User tempu;
		try {
			tempu = SQLight_DataBase.getUserByName(name,lastname,"teacher");
			JOptionPane.showMessageDialog(null, ("id - name - lastname - username\n"+tempu.getId() + " - " + tempu.getName() + " - " + tempu.getLastname()+ " - " + tempu.getUsername()) , "Teacher", JOptionPane.PLAIN_MESSAGE);

		} catch (Exception e) {
			
		}
		
	}
	@Override
	public void findStudent(String name, String lastname) {
		// TODO Auto-generated method stub
		User tempu;
		try {
			tempu = SQLight_DataBase.getUserByName(name,lastname,"student");
			JOptionPane.showMessageDialog(null, ("id - name - lastname - username\n"+tempu.getId() + " - " + tempu.getName() + " - " + tempu.getLastname() + " - " + tempu.getUsername()) , "Teacher", JOptionPane.PLAIN_MESSAGE);

		} catch (Exception e) {
			
		}
	}
}