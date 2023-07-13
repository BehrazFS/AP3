import java.sql.SQLException;
import java.util.ArrayList;

public class University {
	private String name , type , address;
	private ArrayList<College> list;
	public University(String name) {
		this.name = name;
		// TODO Auto-generated constructor stub
		try {
			list = SQLight_DataBase.getCollegeList(name);
		}
		catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
		}
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public ArrayList<College> getCollegeList() {
		return list;
	}
	public void refreshCollegeList() {
		try {
			list = SQLight_DataBase.getCollegeList(name);
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
		}
	}
	
}
