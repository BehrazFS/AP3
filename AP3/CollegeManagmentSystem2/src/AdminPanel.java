import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;

public class AdminPanel extends JDialog implements ActionListener{
	private JButton addU,addC,addEG,showlist;
	public AdminPanel() {
		// TODO Auto-generated constructor stub
		try {
			addU = new JButton("add University");
			addU.setBounds(190,240 , 120,20);
			addU.setFocusable(false);
			addU.addActionListener(this);
			
			
			addC = new JButton("add College");
			addC.setBounds(190,270 , 120,20);
			addC.setFocusable(false);
			addC.addActionListener(this);
			
			addEG = new JButton("add EGroup");
			addEG.setBounds(190,300 , 120,20);
			addEG.setFocusable(false);
			addEG.addActionListener(this);
			
			showlist = new JButton("User list");
			showlist.setBounds(190,330 , 120,20);
			showlist.setFocusable(false);
			showlist.addActionListener(this);
			
			
			
			this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			this.setResizable(false);
			this.setSize(500,500);
			this.setLayout(null);
			this.setModal(true);
			this.add(addC);
			this.add(addEG);
			this.add(addU);
			this.add(showlist);
			this.setVisible(true);
		}catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		try {
			if(e.getSource() == showlist) {
				ArrayList<String> list;
				try {
					list = SQLight_DataBase.getUserAdminDataList();
					ListDialog ul = new ListDialog(list);
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
				}
				
			}
			if(e.getSource() == addU) {
				try {
					AddUniversityDialog aud = new AddUniversityDialog();
	//				for(University item : LoginFrame.UList) {
	//					System.out.println(item.getName());
	//				}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
				}
				
			}
			if(e.getSource() == addC) {
				try {
					AddCollegeDialog acd = new AddCollegeDialog();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
				}
				
			}
			if(e.getSource() == addEG) {
				try {
					AddEGroupDialog agd = new AddEGroupDialog();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
				}
				
			}
		}catch (Exception ex) {
			// TODO: handle exception
		}
	}
}
