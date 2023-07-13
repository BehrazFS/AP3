import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class ManageDialog extends JDialog implements ActionListener{
	private JLabel label1,label2,label3,label4,label5,label6;
	private JTextField text1,text2,text3,text4,text5,text6;
	private JLabel errormsg;
	private JButton change ,cpass;
	private JComboBox combo;
	private User userToManage;
	private String[] roles = { "manager" , "teacher" , "student" , "admin"};
	public ManageDialog(User nowU) {
		// TODO Auto-generated constructor stub
		try {
			userToManage = new User(nowU);
			
			text1 = new JTextField();
			text1.setBounds(100,80,300,30);
			
			label1 = new JLabel("name :");
			label1.setHorizontalAlignment(SwingConstants.CENTER);
			label1.setBounds(100,50,300,30);
			
			
			text2 = new JTextField();
			text2.setBounds(100,150,300,30);
			
			label2 = new JLabel("lastname :");
			label2.setHorizontalAlignment(SwingConstants.CENTER);
			label2.setBounds(100,120,300,30);
			
			
			text3 = new JTextField();
			text3.setBounds(100,210,300,30);
			
			label3 = new JLabel("university : ");
			label3.setHorizontalAlignment(SwingConstants.CENTER);
			label3.setBounds(100,180,300,30);
			
			text4 = new JTextField();
			text4.setBounds(100,270,300,30);
			
			label4 = new JLabel("college : ");
			label4.setHorizontalAlignment(SwingConstants.CENTER);
			label4.setBounds(100,240,300,30);
			
			text5 = new JTextField();
			text5.setBounds(100,330,300,30);
			
			label5 = new JLabel("group : ");
			label5.setHorizontalAlignment(SwingConstants.CENTER);
			label5.setBounds(100,300,300,30);
			
			text6 = new JTextField();
			text6.setBounds(100,390,300,30);
			
			label6 = new JLabel("field : ");
			label6.setHorizontalAlignment(SwingConstants.CENTER);
			label6.setBounds(100,360,300,30);
			
			combo = new JComboBox(roles);
			combo.setBounds(100,430,300,30);
			
			change = new JButton("Change");
			change.setBounds(210,470 , 80,20);
			change.setFocusable(false);
			change.addActionListener(this);
			
			cpass = new JButton("Change password");
			cpass.setBounds(180,500 , 140,20);
			cpass.setFocusable(false);
			cpass.addActionListener(this);
			
			errormsg = new JLabel();
			errormsg.setHorizontalAlignment(SwingConstants.CENTER);
			errormsg.setBounds(50,530,400,120);
			errormsg.setOpaque(true);
			errormsg.setBackground(Color.white);
			//--
			this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			
			this.setResizable(false);
			this.setSize(500,700);
			this.setLayout(null);
			this.setModal(true);
			this.add(label1);
			this.add(label2);
			this.add(label3);
			this.add(label4);
			this.add(label5);
			this.add(label6);
			this.add(text6);
			this.add(text1);
			this.add(text2);
			this.add(text3);
			this.add(text4);
			this.add(text5);
			this.add(errormsg);
			this.add(combo);
			this.add(change);
			this.add(cpass);
			this.add(errormsg);
			this.setVisible(true);
		}
		catch (Exception e) {
			// TODO: handle exception
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		try {
			if(e.getSource() == change) {
				if(!text1.getText().equals("")) {
					userToManage.setName(text1.getText());
				}
				if(!text2.getText().equals("")) {
					userToManage.setLastname(text2.getText());
				}
				if(!text3.getText().equals("")) {
					userToManage.setUnivesityName(text3.getText());
				}
				if(!text4.getText().equals("")) {
					userToManage.setCollegeName(text4.getText());
				}
				if(!text5.getText().equals("")) {
					userToManage.seteGroupName(text5.getText());
				}
				if(!text6.getText().equals("")) {
					userToManage.setField(text6.getText());
				}
				userToManage.setRole(roles[combo.getSelectedIndex()]);
				try {
					SQLight_DataBase.updateUser(userToManage);
					errormsg.setForeground(Color.green);
					errormsg.setText("Data updated");
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					System.out.print(e1.getMessage());
					errormsg.setForeground(Color.red);
					errormsg.setText("Something went wrong");
				}
			}
			if(e.getSource() == cpass) {
				PasswordChangeDialog pcd = new PasswordChangeDialog(userToManage);
			}
		}catch (Exception ex) {
			// TODO: handle exception
		}
	}

}
