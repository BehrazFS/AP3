import java.sql.SQLException;
import java.util.ArrayList;

public class Student extends User{
	private int passedLessonCount,passedTerm;
	private ArrayList<Lesson> list = new ArrayList<>();
	public Student(User user) {
		super(user);
		// TODO Auto-generated constructor stub
		try {
			list = SQLight_DataBase.getScoreListByStudentNow(this.getId());
			passedTerm = SQLight_DataBase.getPassedTerms(this.getId());
			passedLessonCount = SQLight_DataBase.getPassedLessonCount(this.getId());
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	public int getPassedLessonCount() {
		return passedLessonCount;
	}
	public void refreshPassedLessonCount() {
		try {
			
			passedLessonCount = SQLight_DataBase.getPassedLessonCount(this.getId());
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public int getPassedterm() {
		return passedTerm;
	}
	public void refreshPassedTerm() {
		try {
			
			passedTerm = SQLight_DataBase.getPassedTerms(this.getId());
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public ArrayList<Lesson> getList() {
		return list;
	}
	public void refreshLessonsList() {
		try {
			list = SQLight_DataBase.getScoreListByStudentNow(this.getId());
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public void showScore() {
		try {
			SQLight_DataBase.showScoreTable(this.getId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
	}

}
