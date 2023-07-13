import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.KeyStore.PrivateKeyEntry;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class SetScoreDialog extends JDialog implements ActionListener{
	private JLabel label1,label2,label3,label4;
	private JTextField text1,text2,text3,text4;
	private JButton set;
	public SetScoreDialog() {
		// TODO Auto-generated constructor stub
		text1 = new JTextField();
		text1.setBounds(100,80,300,30);
		
		label1 = new JLabel("lesson ID :");
		label1.setHorizontalAlignment(SwingConstants.CENTER);
		label1.setBounds(100,50,300,30);
		
		
		text2 = new JTextField();
		text2.setBounds(100,150,300,30);
		
		label2 = new JLabel("user(student) ID :");
		label2.setHorizontalAlignment(SwingConstants.CENTER);
		label2.setBounds(100,120,300,30);
		
		
		text3 = new JTextField();
		text3.setBounds(100,210,300,30);
		
		label3 = new JLabel("term : ");
		label3.setHorizontalAlignment(SwingConstants.CENTER);
		label3.setBounds(100,180,300,30);
		
		text4 = new JTextField();
		text4.setBounds(100,270,300,30);
		
		label4 = new JLabel("score : ");
		label4.setHorizontalAlignment(SwingConstants.CENTER);
		label4.setBounds(100,240,300,30);
		
		set = new JButton("confirm");
		set.setBounds(210,370 , 80,20);
		set.setFocusable(false);
		set.addActionListener(this);
		
		this.setResizable(false);
		this.setSize(500,500);
		this.setLayout(null);
		this.setModal(true);
		
		this.add(label1);
		this.add(label2);
		this.add(label3);
		this.add(label4);
		
		this.add(text4);
		this.add(text1);
		this.add(text2);
		this.add(text3);
		
		this.add(set);
		this.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		try {
			if(e.getSource() == set) {
				SQLight_DataBase.setScore(Integer.parseInt(text1.getText()), Integer.parseInt(text2.getText()), Integer.parseInt(text3.getText()), Double.parseDouble(text4.getText()));
				this.dispose();
			}
		}
		catch (Exception ex) {
			// TODO: handle exception
		}
	}
}
