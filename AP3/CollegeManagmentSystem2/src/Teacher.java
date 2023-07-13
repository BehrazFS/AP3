import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Teacher extends User{
	private ArrayList<Lesson> list = new ArrayList<>();
	public Teacher(User user) {
		super(user);
		// TODO Auto-generated constructor stub
		try {
			list = SQLight_DataBase.getLessonListByTeacher(this.getId());
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public ArrayList<Lesson> getList() {
		return list;
	}
	public void refreshList(ArrayList<Lesson> list) {
		try {
			list = SQLight_DataBase.getLessonListByTeacher(this.getId());
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public void removeSelectVahed() {
		try {
			NewSelectVahedDialog nsvd = new NewSelectVahedDialog(false,SQLight_DataBase.getUser(this.getId()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		
	}
	public void setOrUpdateScore() {
		try {
			SetScoreDialog ssd = new SetScoreDialog();
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
	}
	public void showScore() {
		try {
			int idtm = Integer.parseInt(JOptionPane.showInputDialog("enter id :"));
			User usertm = SQLight_DataBase.getValidUser(this.geteGroupName(),this.getUnivesityName(),this.getCollegeName(),"student",idtm);
			if(usertm.getId() != 0) {
				SQLight_DataBase.showScoreTable(usertm.getId());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
	}
	
}
