import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class ManagerActionDialog extends JDialog implements ActionListener{
	private Manager nowM;
	private JComboBox combo;
	private JButton run;
	private String[] options = {"new teacher","teacher list" , "manage teacher" , "remove teacher"
			,"new lesson" , "lesson list" , "edit lesson" , "remove lesson" , "new student","student list","edit student"
			,"remove student" , "select vahed" ,"remove student from lesson" , "show scores"};
	public ManagerActionDialog(Manager man){
		nowM = man;
		run = new JButton("run");
		run.setBounds(210,260 , 80,20);
		run.setFocusable(false);
		run.addActionListener(this);
		
		combo = new JComboBox(options);
		combo.setBounds(100,220,300,30);
		
		this.setResizable(false);
		this.setSize(500,500);
		this.setLayout(null);
		this.setModal(true);
		
		this.add(combo);
		this.add(run);
		this.setVisible(true);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == run) {
			switch (combo.getSelectedIndex()) {
			case 0:
				SignUpDialog sud = new SignUpDialog();
				try {
					nowM.newTeacher(SQLight_DataBase.getLastUser());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
				}
				break;
			case 1:
				ListDialog ls = new ListDialog(nowM.showTeacherList());
				break;
			case 2:
				try {
					nowM.editTeacher();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					
				}
				break;
			case 3:
				nowM.deleteTeacher();
				break;
			case 4:
				nowM.newLesson();
				break;
			case 5:
				ListDialog lss = new ListDialog(nowM.showLessonList());
				break;
			case 6:
				nowM.editLesson();
				break;
			case 7:
				nowM.deleteLesson();
				break;
			case 8:
				SignUpDialog sud2 = new SignUpDialog();
				try {
					nowM.newStudent(SQLight_DataBase.getLastUser());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
				}
				break;
			case 9:
				ListDialog ls2 = new ListDialog(nowM.showStudentList());
				break;
			case 10:
				nowM.editStudent();;
				break;
			case 11:
				nowM.deleteStudent();
				break;
			case 12:
				nowM.selectVahed();
				break;
			case 13:
				nowM.removeSelectVahed();
				break;
			case 14:
				nowM.showScore();
				break;
			default:
				break;
			}
		}
	}

}
