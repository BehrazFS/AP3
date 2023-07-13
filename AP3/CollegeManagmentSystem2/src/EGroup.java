import java.util.ArrayList;

public class EGroup {
	private String name , headName , collegeName , universityName;
	private ArrayList<User> studentsList;
	private ArrayList<User> teachersList;
	public EGroup(String name , String universityName , String collegeName) {
		// TODO Auto-generated constructor stub
		this.name= name;
		this.collegeName = collegeName;
		this.universityName = universityName;
		try {
			studentsList = SQLight_DataBase.getStudentsList(name , collegeName , universityName);
			teachersList = SQLight_DataBase.getTeachersList(name , collegeName , universityName);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHeadName() {
		return headName;
	}
	public void setHeadName(String headName) {
		this.headName = headName;
	}
	public ArrayList<User> getStudentsList() {
		return studentsList;
	}
	public void refreshStudentsList() {
		try {
			studentsList = SQLight_DataBase.getStudentsList(name , collegeName , universityName);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public ArrayList<User> getTeachersList() {
		return teachersList;
	}
	public void refreshTeachersList() {
		try {
			teachersList = SQLight_DataBase.getTeachersList(name , collegeName , universityName);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public String getCollegeName() {
		return collegeName;
	}
	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}
	public String getUniversityName() {
		return universityName;
	}
	public void setUniversityName(String universityName) {
		this.universityName = universityName;
	}
	
}
