import java.sql.SQLException;
import java.util.ArrayList;

public class Lesson {
	private final int id;
	private String name , code ;
	private int vahed , limit, tpid;
	private ArrayList<User> list = new ArrayList<>();
	public Lesson(int id , int vahed , int limit , String name , String code , int tpid) {
		// TODO Auto-generated constructor stub
		this.id = id;
		this.name = name;
		this.code = code;
		this.tpid = tpid;
		this.vahed = vahed;
		this.limit = limit;
	}
	public Lesson(int id) {
		// TODO Auto-generated constructor stub
		this(id,0,0,"","",0);
	}
	public Lesson(Lesson us) {
		this(us.getId(),us.getVahed(),us.getLimit(),us.getName(),us.getCode(),us.getTeacherID());
	}
	
	public String getName() {
		return name;
	}
	public String getCode() {
		return code;
	}
	public int getTeacherID() {
		return tpid;
	}
	public int getVahed() {
		return vahed;
	}
	public int getLimit() {
		return limit;
	}
	public int getId() {
		return id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public void setTeacherID(int tpid) {
		this.tpid = tpid;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public void setVahed(int vahed) {
		this.vahed = vahed;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public void constructList() {
		try {
			list = SQLight_DataBase.getScoreListByLessonNow(id);
			//System.out.print(list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
		}
	}
	public ArrayList<User> GetList() {
		constructList();
		return list;
	}
	public int  getRemained() {
		constructList();
		return limit - list.size();
	}
}
