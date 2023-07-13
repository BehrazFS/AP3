import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class NewSelectVahedDialog extends JDialog implements ActionListener{
	private JLabel label1,label2,label3;
	private JTextField text1,text2,text3;
	private JButton add;
	private boolean removeOrSelect;
	private User manager;
	public NewSelectVahedDialog(boolean removeOrSelect, User manager) {//true -> select   and  false -> remove
		// TODO Auto-generated constructor stub
		this.manager = manager;
		this.removeOrSelect = removeOrSelect;
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
		
		add = new JButton("confirm");
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
		
		this.add(text1);
		this.add(text2);
		this.add(text3);
		
		this.add(add);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		try {
			if(e.getSource() == add) {
				boolean valid = true;
				if(manager.getRole().equals("manager")) {
					try {
						User temp = SQLight_DataBase.getValidUser(manager.geteGroupName(), manager.getUnivesityName(), manager.getCollegeName(), "student", Integer.parseInt(text2.getText()));
						Lesson lesson = SQLight_DataBase.getValidLesson(Integer.parseInt(text1.getText()),"manager",0);
						if(temp.getId() == 0 || lesson.getId() == 0) {
							valid = false;
						}
					} catch (NumberFormatException | ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
					}
				}
				if(manager.getRole().equals("teacher")) {
					try {
						User temp = SQLight_DataBase.getValidUser("student", Integer.parseInt(text2.getText()));
						Lesson lesson = SQLight_DataBase.getValidLesson(Integer.parseInt(text1.getText()),"teacher",manager.getId());
						if(temp.getId() == 0 || lesson.getId() == 0) {
							valid = false;
						}
					} catch (NumberFormatException | ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
					}
				}
				if(valid) {
					if(removeOrSelect) {
						try {
							if(Manager.vahedControl(SQLight_DataBase.getLesson(Integer.parseInt(text1.getText()))) == 1) {
								try {
									SQLight_DataBase.newSelectVahed(Integer.parseInt(text1.getText()), Integer.parseInt(text2.getText()),Integer.parseInt(text3.getText()));
									this.dispose();
								} catch (ClassNotFoundException | SQLException e1) {
									// TODO Auto-generated catch block
								}
							}
							else {
								JOptionPane.showMessageDialog(null, "this Vahed is full","error",JOptionPane.ERROR_MESSAGE);
								this.dispose();
							}
						} catch (NumberFormatException | HeadlessException | ClassNotFoundException | SQLException e1) {
							// TODO Auto-generated catch block
						}
					}
					else {
						try {
							SQLight_DataBase.removeSelectVahed(Integer.parseInt(text1.getText()), Integer.parseInt(text2.getText()),Integer.parseInt(text3.getText()));
							this.dispose();
						} catch (ClassNotFoundException | SQLException e1) {
							// TODO Auto-generated catch block
						}
					}
				}
			}
		}catch (Exception ex) {
			// TODO: handle exception
		}
	}
}
