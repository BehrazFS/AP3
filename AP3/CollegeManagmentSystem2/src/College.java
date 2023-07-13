import java.sql.SQLException;
import java.util.ArrayList;

public class College {
	private String name , headName , universityName;
	private ArrayList<String> fields;
	private ArrayList<EGroup> list;
	public College(String name , String universityName) {
		// TODO Auto-generated constructor stub
		this.name= name;
		this.universityName = universityName ;
		try {
			list = SQLight_DataBase.getEGroupList(universityName , name);
			fields = SQLight_DataBase.getFieldsList(universityName , name);
			//System.out.print(fields);
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
	public ArrayList<String> getFields() {
		return fields;
	}
	public void refreshFields(ArrayList<String> fields) {
		try {
			this.fields = fields = SQLight_DataBase.getFieldsList(universityName , name);
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
	}
	public ArrayList<EGroup> getEGroupList() {
		return list;
	}
	public void refreshEGroupList() {
		try {
			list = SQLight_DataBase.getEGroupList(universityName , name);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public String getUniversityName() {
		return universityName;
	}
	public void setUniversityName(String universityName) {
		this.universityName = universityName;
	}
	
}
