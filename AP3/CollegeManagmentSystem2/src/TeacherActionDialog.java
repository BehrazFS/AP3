import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;

public class TeacherActionDialog extends JDialog implements ActionListener{
	private JComboBox combo;
	private JButton run;
	private Teacher teacher;
	private String[] options = {"remove student from lesson" , "show scores" , "set or update score"};
	public TeacherActionDialog(Teacher teacher) {
		// TODO Auto-generated constructor stub
		this.teacher = teacher;
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
				teacher.removeSelectVahed();
				break;
			case 1:
				// TODO score paper
				teacher.showScore();
				break;
			case 2:
				teacher.setOrUpdateScore();
				break;
			default:
				break;
			}
		}
	}
}
