import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class AddUniversityDialog extends JDialog implements ActionListener{
	private University uni;
	private JLabel label1,label2,label3;
	private JTextField text1,text2,text3;
	private JButton button;
	public AddUniversityDialog() {
		// TODO Auto-generated constructor stub
		try {
			text1 = new JTextField();
			text1.setBounds(100,80,300,30);
			
			label1 = new JLabel("name :");
			label1.setHorizontalAlignment(SwingConstants.CENTER);
			label1.setBounds(100,50,300,30);
			
			
			text2 = new JTextField();
			text2.setBounds(100,150,300,30);
			
			label2 = new JLabel("address :");
			label2.setHorizontalAlignment(SwingConstants.CENTER);
			label2.setBounds(100,120,300,30);
			
			
			text3 = new JTextField();
			text3.setBounds(100,210,300,30);
			
			label3 = new JLabel("type : ");
			label3.setHorizontalAlignment(SwingConstants.CENTER);
			label3.setBounds(100,180,300,30);
			
			button = new JButton("add");
			button.setBounds(210,250 , 80,20);
			button.setFocusable(false);
			button.addActionListener(this);
			
			this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			this.setResizable(false);
			this.setSize(500,500);
			this.setLayout(null);
			this.setModal(true);
			this.add(label1);
			this.add(label2);
			this.add(label3);
			this.add(text1);
			this.add(text2);
			this.add(text3);
			this.add(button);
			this.setVisible(true);
		}catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		try {
			if(e.getSource() == button) {
				if(!text1.getText().equals("")) {
					uni = new University(text1.getText());
					uni.setAddress(text2.getText());
					uni.setType(text3.getText());
					LoginFrame.addToUniversityList(uni);
					this.dispose();
				}
			}
		}catch (Exception ex) {
			// TODO: handle exception
		}
		
	}
}
