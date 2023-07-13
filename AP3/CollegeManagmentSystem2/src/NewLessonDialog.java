import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class NewLessonDialog extends JDialog implements ActionListener{
	private JLabel label1,label2,label3,label4,label5;
	private JTextField text1,text2,text3,text4,text5;
	private JButton add;
	public NewLessonDialog() {
		// TODO Auto-generated constructor stub
		
		text1 = new JTextField();
		text1.setBounds(100,80,300,30);
		
		label1 = new JLabel("name :");
		label1.setHorizontalAlignment(SwingConstants.CENTER);
		label1.setBounds(100,50,300,30);
		
		
		text2 = new JTextField();
		text2.setBounds(100,150,300,30);
		
		label2 = new JLabel("code :");
		label2.setHorizontalAlignment(SwingConstants.CENTER);
		label2.setBounds(100,120,300,30);
		
		
		text3 = new JTextField();
		text3.setBounds(100,210,300,30);
		
		label3 = new JLabel("teacher id: ");
		label3.setHorizontalAlignment(SwingConstants.CENTER);
		label3.setBounds(100,180,300,30);
		
		text4 = new JTextField();
		text4.setBounds(100,270,300,30);
		
		label4 = new JLabel("vahed : ");
		label4.setHorizontalAlignment(SwingConstants.CENTER);
		label4.setBounds(100,240,300,30);
		
		text5 = new JTextField();
		text5.setBounds(100,330,300,30);
		
		label5 = new JLabel("limit : ");
		label5.setHorizontalAlignment(SwingConstants.CENTER);
		label5.setBounds(100,300,300,30);
		
		add = new JButton("add");
		add.setBounds(210,370 , 80,20);
		add.setFocusable(false);
		add.addActionListener(this);
		
		this.setResizable(false);
		this.setSize(500,500);
		this.setLayout(null);
		this.setModal(true);
		
		this.add(label1);
		this.add(label2);
		this.add(label3);
		this.add(label4);
		this.add(label5);
		this.add(text1);
		this.add(text2);
		this.add(text3);
		this.add(text4);
		this.add(text5);
		this.add(add);
		this.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == add) {
			if(!text1.getText().equals("") && !text2.getText().equals("") && !text3.getText().equals("") && !text4.getText().equals("") && !text5.getText().equals("")) {
				try {
					SQLight_DataBase.newLesson(Integer.parseInt(text4.getText()),Integer.parseInt(text5.getText()),text1.getText() ,text2.getText(),Integer.parseInt(text3.getText()));
					this.dispose();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					
				}
			}
		}
	}
}
