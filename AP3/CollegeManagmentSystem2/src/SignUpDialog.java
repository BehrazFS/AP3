import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class SignUpDialog extends JDialog implements ActionListener{
	private JLabel msg1,msg2 , msg3;
	private JLabel errormsg;
	private JTextField userText , passText ,confirmText;
	private JButton submit;
	public SignUpDialog() {
		try {
			msg1 = new JLabel("Enter your username : ");
			msg1.setHorizontalAlignment(SwingConstants.CENTER);
			msg1.setBounds(100,50,300,30);
			
			userText = new JTextField();
			userText.setBounds(100,80,300,30);
			
			msg2 = new JLabel("Enter your password : ");
			msg2.setHorizontalAlignment(SwingConstants.CENTER);
			msg2.setBounds(100,120,300,30);
			
			passText = new JTextField();
			passText.setBounds(100,150,300,30);
			
			msg3 = new JLabel("Confirm your password : ");
			msg3.setHorizontalAlignment(SwingConstants.CENTER);
			msg3.setBounds(100,190,300,30);
			
			confirmText = new JTextField();
			confirmText.setBounds(100,220,300,30);
			
			submit = new JButton("Submit");
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
			this.add(msg3);
			this.add(confirmText);
			this.add(passText);
			this.add(userText);
			this.add(submit);
			this.add(errormsg);
			this.setVisible(true);
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		try {
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
						boolean result =  SQLight_DataBase.signUp(userText.getText(),passText.getText());
						if(result) {
							LoginFrame.setErrorMessege("Submit done", Color.green);;
							this.dispose();
						}
						else {
							errormsg.setForeground(Color.red);
							errormsg.setText("incorrect username or connection error");
						}
					}
					catch (Exception ee) {
						// TODO: handle exception
						System.out.println(ee.getMessage());
					}
				}
			}
		}catch (Exception ex) {
			// TODO: handle exception
			System.out.println(ex.getMessage());
		}
	}

}
