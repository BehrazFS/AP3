import java.sql.SQLException;

public class User {
	private final int id;
	private String username = "", password  = "", name = "", lastname = ""
			, eGroupName = "", collegeName = "", univesityName = "", role = "null" , field = "";
	public User(int id) {
		this.id = id;
		// TODO Auto-generated constructor stub
	}
	public User(User user) {
		this(user.getId());
		this.username = user.getUsername();
		this.password = user.getPassword();
		this.name = user.getName();
		this.lastname = user.getLastname();
		this.eGroupName = user.geteGroupName();
		this.collegeName = user.getCollegeName();
		this.univesityName = user.getUnivesityName();
		this.field = user.getField();
		this.role = user.getRole();
		
		// TODO Auto-generated constructor stub
	}
	//---construct from sql:
	public void construct() {
		try {
			User user = SQLight_DataBase.getUser(this.id);
			this.username = user.getUsername();
			this.password = user.getPassword();
			this.name = user.getName();
			this.lastname = user.getLastname();
			this.eGroupName = user.geteGroupName();
			this.collegeName = user.getCollegeName();
			this.univesityName = user.getUnivesityName();
			this.role = user.getRole();
			this.field = user.getField();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
		}
	}
	//-----
	public void update() {
		try {
			SQLight_DataBase.updateUser(id, username,password , name,lastname,eGroupName,collegeName,univesityName,role,field);
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public int getId() {
		return id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String geteGroupName() {
		return eGroupName;
	}
	public void seteGroupName(String eGroupName) {
		this.eGroupName = eGroupName;
	}
	public String getCollegeName() {
		return collegeName;
	}
	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}
	public String getUnivesityName() {
		return univesityName;
	}
	public void setUnivesityName(String univesityName) {
		this.univesityName = univesityName;
	}
	public String getPrintableInfo() {
		String ans =  "<html>" + (" id : " + id) + "<br/> name : " + name + "<br/> lastname : " + lastname + "<br/> username : "
				+ username + "<br/> password : " + password + "<br/> role : " + role + "<br/> university : " + univesityName+ "<br/> college : " 
				+collegeName + "<br/> group : " +eGroupName +"<br/> field : " +field +"</html>";
		return ans;
	}
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	
}
