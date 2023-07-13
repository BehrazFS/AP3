import java.awt.HeadlessException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Manager extends User{
	
	public Manager(User us) {
		super(us);
		// TODO Auto-generated constructor stub
	}
	public void newTeacher(User user) {
		user.setRole("teacher");
		user.setUnivesityName(this.getUnivesityName());
		user.setCollegeName(this.getCollegeName());
		user.seteGroupName(this.geteGroupName());
		try {
			SQLight_DataBase.updateUser(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
			
	}
	public void newStudent(User user) {
		user.setRole("student");
		user.setUnivesityName(this.getUnivesityName());
		user.setCollegeName(this.getCollegeName());
		user.seteGroupName(this.geteGroupName());
		try {
			SQLight_DataBase.updateUser(user);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
		}
	}
	public ArrayList<String> showTeacherList() {
		try {
			ArrayList<User> list1 = SQLight_DataBase.getTeachersList(this.geteGroupName(),this.getUnivesityName(),this.getCollegeName());
			//System.out.print(list);
			ArrayList<String> list = new ArrayList<>();
			list.add("id - username - name - lastname");
			for(User item : list1) {
				list.add("" + item.getId()+ " - " + item.getUsername() + " - " + item.getName() + " - " + item.getLastname());
			}
			return list;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return new ArrayList<>();
		}
	}
	public ArrayList<String> showStudentList() {
		try {
			ArrayList<User> list1 = SQLight_DataBase.getStudentsList(this.geteGroupName(),this.getUnivesityName(),this.getCollegeName());
			//System.out.print(list);
			ArrayList<String> list = new ArrayList<>();
			list.add("id - username - name - lastname");
			for(User item : list1) {
				list.add("" + item.getId()+ " - " + item.getUsername() + " - " + item.getName() + " - " + item.getLastname());
			}
			return list;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return new ArrayList<>();
		}
	}
	public void editTeacher() {

		try {
			int idtm = Integer.parseInt(JOptionPane.showInputDialog("enter id :"));
			User usertm = SQLight_DataBase.getValidUser(this.geteGroupName(),this.getUnivesityName(),this.getCollegeName(),"teacher",idtm);
			if(usertm.getId() != 0) {
				ManageDialog md =new ManageDialog(usertm);
				usertm.construct();
				usertm.setRole("teacher");
				usertm.setUnivesityName(this.getUnivesityName());
				usertm.setCollegeName(this.getCollegeName());
				usertm.seteGroupName(this.geteGroupName());
				SQLight_DataBase.updateUser(usertm);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
	}
	public void editStudent() {

		try {
			int idtm = Integer.parseInt(JOptionPane.showInputDialog("enter id :"));
			User usertm = SQLight_DataBase.getValidUser(this.geteGroupName(),this.getUnivesityName(),this.getCollegeName(),"student",idtm);
			if(usertm.getId() != 0) {
				ManageDialog md =new ManageDialog(usertm);
				usertm.construct();
				usertm.setRole("student");
				usertm.setUnivesityName(this.getUnivesityName());
				usertm.setCollegeName(this.getCollegeName());
				usertm.seteGroupName(this.geteGroupName());
				SQLight_DataBase.updateUser(usertm);
			}
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
	public void deleteTeacher() {
		try {
			int idtm = Integer.parseInt(JOptionPane.showInputDialog("enter id :"));
			User usertm = SQLight_DataBase.getValidUser(this.geteGroupName(),this.getUnivesityName(),this.getCollegeName(),"teacher",idtm);
			if(usertm.getId() != 0) {
				SQLight_DataBase.removeUser(usertm.getId());
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
	}
	public void deleteStudent() {
		try {
			int idtm = Integer.parseInt(JOptionPane.showInputDialog("enter id :"));
			User usertm = SQLight_DataBase.getValidUser(this.geteGroupName(),this.getUnivesityName(),this.getCollegeName(),"student",idtm);
			if(usertm.getId() != 0) {
				SQLight_DataBase.removeUser(usertm.getId());
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
	}
	public void newLesson() {
			NewLessonDialog nld = new NewLessonDialog();
		
	}
	public ArrayList<String> showLessonList() {
		try {
			ArrayList<String> list = SQLight_DataBase.getLessonList();
			list.add(0 , "id - name - code - teacherid");
			//System.out.print(list);
			return list;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return new ArrayList<>();
		}
	}
	public void editLesson() {

		try {
			Lesson less = SQLight_DataBase.getLesson(Integer.parseInt(JOptionPane.showInputDialog("enter id :")));
			if(less.getId() != 0) {
				EditLessonDialog edd = new EditLessonDialog(less);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
	}
	public void deleteLesson() {
		try {
			SQLight_DataBase.removeLesson(Integer.parseInt(JOptionPane.showInputDialog("enter id :")));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
	}
	public void selectVahed() {
		try {
			NewSelectVahedDialog nsvd = new NewSelectVahedDialog(true,SQLight_DataBase.getUser(this.getId()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
		}
		
	}
	public void removeSelectVahed() {
		try {
			NewSelectVahedDialog nsvd = new NewSelectVahedDialog(false,SQLight_DataBase.getUser(this.getId()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
		}
		
	}
	public static int vahedControl(Lesson lesson) {//-1 : wrong 1:not full 0:full
		// TODO Auto-generated method stub
		//System.out.print(lesson.getRemained());
		if(lesson.getRemained() < 0) {
			return -1;
		}
		else if (lesson.getRemained() > 0) {
			return 1;
		}
		else {
			return 0;
		}
		
	}
	
}
