import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class PasswordChangeDialog extends JDialog implements ActionListener{
	private JLabel msg1,msg2  ;
	private JLabel errormsg;
	private JTextField passText , confirmText ;
	private JButton submit;
	private User toManage;
	public PasswordChangeDialog(User nowU) {
		try {
			toManage = new User(nowU);
			// TODO Auto-generated constructor stub
			msg1 = new JLabel("Enter your password : ");
			msg1.setHorizontalAlignment(SwingConstants.CENTER);
			msg1.setBounds(100,50,300,30);
			
			passText = new JTextField();
			passText.setBounds(100,80,300,30);
			
			msg2 = new JLabel("Confirm your password : ");
			msg2.setHorizontalAlignment(SwingConstants.CENTER);
			msg2.setBounds(100,120,300,30);
			
			confirmText = new JTextField();
			confirmText.setBounds(100,150,300,30);
			
			
			submit = new JButton("Change");
			submit.setBounds(210,260 , 80,20);
			submit.setFocusable(false);
			submit.addActionListener(this);
			
			errormsg = new JLabel();
			errormsg.setHorizontalAlignment(SwingConstants.CENTER);
			errormsg.setBounds(50,290,400,150);
			errormsg.setOpaque(true);
			errormsg.setBackground(Color.white);
			//--
			this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			
			this.setResizable(false);
			this.setSize(500,500);
			this.setLayout(null);
			this.setModal(true);
			this.add(msg1);
			this.add(msg2);
			this.add(confirmText);
			this.add(passText);
			this.add(submit);
			this.add(errormsg);
			this.setVisible(true);
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
		// TODO Auto-generated method stub
			if(e.getSource() == submit) {
				if(!passText.getText().equals(confirmText.getText())) {
					errormsg.setForeground(Color.red);
					errormsg.setText(" password and confirm mismatch");
					
				}
				else if(passText.getText().length() < 8) {
					errormsg.setForeground(Color.red);
					errormsg.setText("password is too short");
					
				}
				else {
					
					try {
						toManage.setPassword(passText.getText());
						SQLight_DataBase.updateUser(toManage);
						this.dispose();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
					}
				}
			}
		}catch (Exception ex) {
			// TODO: handle exception
		}
	}

}
