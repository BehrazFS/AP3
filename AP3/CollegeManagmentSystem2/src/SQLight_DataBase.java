import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SQLight_DataBase{
	public static Connection handle;
	public static void construct() throws ClassNotFoundException,SQLException {
		// TODO Auto-generated constructor stub

		Class.forName("org.sqlite.JDBC");
		handle = DriverManager.getConnection("jdbc:sqlite:CMS2.db");

	}
	public static void createSQL() throws ClassNotFoundException,SQLException {
		Class.forName("org.sqlite.JDBC");
		handle = DriverManager.getConnection("jdbc:sqlite:CMS2.db");
		String query = "";
		PreparedStatement prep;
		//--user table
		query = "CREATE TABLE IF NOT EXISTS users"
			+ "(id INTEGER PRIMARY KEY NOT NULL,username TEXT,password TEXT,name TEXT , lastname TEXT , egroupname TEXT , collegename TEXT , universityname TEXT ,role TEXT , field TEXT)";
		prep = handle.prepareStatement(query);
		prep.executeUpdate();
		//---admin setup
		query = "INSERT INTO users (username , password , role) VALUES ('Admin' , '12345678' , 'admin')";
		prep = handle.prepareStatement(query);
		prep.executeUpdate();
		//---
		query = "CREATE TABLE IF NOT EXISTS lessons"
				+ "(id INTEGER PRIMARY KEY NOT NULL,name TEXT,code TEXT,vahed INT , vahedlimit INT,teacherid INT)";
		prep = handle.prepareStatement(query);
		prep.executeUpdate();
		//---
		query = "CREATE TABLE IF NOT EXISTS scores"
				+ "(id INTEGER PRIMARY KEY NOT NULL,lessonid INT,userid INT,term INT , score TEXT)";
		prep = handle.prepareStatement(query);
		prep.executeUpdate();
	}
	public static User getUser(String username , String password) throws ClassNotFoundException,SQLException {
		String query = "";
		PreparedStatement prep;
		query = "SELECT * FROM users WHERE username = ? and password = ?";
		prep = handle.prepareStatement(query);
		prep.setString(1, username);
		prep.setString(2, password);
		ResultSet rs = prep.executeQuery();
		if(!rs.isBeforeFirst()) {
			return new User(0);
		}
		while(rs.next()) {
			User user = new User(rs.getInt("id"));
			user.setUsername(rs.getString("username"));
			user.setPassword(rs.getString("password"));
			user.setName(rs.getString("name"));
			user.setLastname(rs.getString("lastname"));
			user.seteGroupName(rs.getString("egroupname"));
			user.setCollegeName(rs.getString("collegename"));
			user.setUnivesityName(rs.getString("universityname"));
			user.setRole(rs.getString("role"));
			user.setField(rs.getString("field"));
			return user;
		}
		return new User(0);
	}
	public static User getUserByName(String name , String lastName , String role) throws ClassNotFoundException,SQLException {
		String query = "";
		PreparedStatement prep;
		query = "SELECT * FROM users WHERE name = ? and lastname = ? and role = ?";
		prep = handle.prepareStatement(query);
		prep.setString(1, name);
		prep.setString(2, lastName);
		prep.setString(3, role);
		ResultSet rs = prep.executeQuery();
		if(!rs.isBeforeFirst()) {
			return new User(0);
		}
		while(rs.next()) {
			User user = new User(rs.getInt("id"));
			user.setUsername(rs.getString("username"));
			user.setPassword(rs.getString("password"));
			user.setName(rs.getString("name"));
			user.setLastname(rs.getString("lastname"));
			user.seteGroupName(rs.getString("egroupname"));
			user.setCollegeName(rs.getString("collegename"));
			user.setUnivesityName(rs.getString("universityname"));
			user.setRole(rs.getString("role"));
			user.setField(rs.getString("field"));
			return user;
		}
		return new User(0);
	}
	public static User getUser(int id) throws ClassNotFoundException,SQLException {
		String query = "";
		PreparedStatement prep;
		query = "SELECT * FROM users WHERE id = ?";
		prep = handle.prepareStatement(query);
		prep.setInt(1, id);
		ResultSet rs = prep.executeQuery();
		if(!rs.isBeforeFirst()) {
			return new User(0);
		}
		while(rs.next()) {
			User user = new User(rs.getInt("id"));
			user.setUsername(rs.getString("username"));
			user.setPassword(rs.getString("password"));
			user.setName(rs.getString("name"));
			user.setLastname(rs.getString("lastname"));
			user.seteGroupName(rs.getString("egroupname"));
			user.setCollegeName(rs.getString("collegename"));
			user.setUnivesityName(rs.getString("universityname"));
			user.setRole(rs.getString("role"));
			user.setField(rs.getString("field"));
			return user;
		}
		return new User(0);
	}
	public static boolean CheckUsername(String username) {
		// TODO Auto-generated method stub
		try {
			String query = "";
			PreparedStatement prep;
			query = "SELECT * FROM users WHERE username = ?";
			prep = handle.prepareStatement(query);
			prep.setString(1, username);
			ResultSet rs = prep.executeQuery();
			if(!rs.isBeforeFirst()) {
				return false;
			}
			else {
				return true;
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
	public static boolean CheckPassword(String username, String password) {
		// TODO Auto-generated method stub
		try {
			String query = "";
			PreparedStatement prep;
			query = "SELECT * FROM users WHERE username = ? and password = ?";
			prep = handle.prepareStatement(query);
			prep.setString(1, username);
			prep.setString(2, password);
			ResultSet rs = prep.executeQuery();
			if(!rs.isBeforeFirst()) {
				return false;
			}
			else {
				return true;
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
	public static boolean signUp(String user, String pass) throws ClassNotFoundException,SQLException {
		String query = "";
		PreparedStatement prep;
		query = "SELECT * FROM users WHERE username = ?";
		prep = handle.prepareStatement(query);
		prep.setString(1, user);
		ResultSet res = prep.executeQuery();
		if(!res.isBeforeFirst()) {
			query = "INSERT INTO users (username,password) Values(?,?)";
			prep = handle.prepareStatement(query);
			prep.setString(1, user);
			prep.setString(2, pass);
			prep.execute();
			return true;
		}
		else {
			return false;
		}
	}
	public static void updateUser(int id,String username,String password ,String name, String lastname,String eGroupName,String collegeName,String univesityName,String role , String field) throws ClassNotFoundException,SQLException {
		String query = "";
		PreparedStatement prep;
		query = "UPDATE users SET username = ? , password = ? ,name = ? , lastname = ? , egroupname = ? , collegename = ? , universityname= ? , role = ? , field = ? WHERE id = ?";
		prep = handle.prepareStatement(query);
		prep.setString(1, username);
		prep.setString(2, password);
		prep.setString(3, name);
		prep.setString(4, lastname);
		prep.setString(5, eGroupName);
		prep.setString(6, collegeName);
		prep.setString(7, univesityName);
		prep.setString(8, role);
		prep.setString(9, field);
		prep.setInt(10, id);
		prep.executeUpdate();
	}
	public static void updateUser(User user) throws ClassNotFoundException,SQLException {
		String query = "";
		PreparedStatement prep;
		query = "UPDATE users SET username = ? , password = ? ,name = ? , lastname = ? , egroupname = ? , collegename = ? , universityname= ? , role = ?  , field = ? WHERE id = ?";
		prep = handle.prepareStatement(query);
		prep.setString(1, user.getUsername());
		prep.setString(2, user.getPassword());
		prep.setString(3, user.getName());
		prep.setString(4, user.getLastname());
		prep.setString(5, user.geteGroupName());
		prep.setString(6, user.getCollegeName());
		prep.setString(7, user.getUnivesityName());
		prep.setString(8, user.getRole());
		prep.setString(9, user.getField());
		prep.setInt(10, user.getId());
		prep.executeUpdate();
	}
	public static ArrayList<College> getCollegeList(String universityName) throws SQLException,ClassNotFoundException{
	// TODO Auto-generated method stub
		ArrayList<String> list = new ArrayList<>();
		ArrayList<College> ans = new ArrayList<>();
		String query = "";
		PreparedStatement prep;
		query = "SELECT collegename FROM users WHERE universityname = ?";
		prep = handle.prepareStatement(query);
		prep.setString(1, universityName);
		ResultSet res = prep.executeQuery();
		
		while (res.next()) {
			String now = res.getString("collegename");
			if(!list.contains(now)) {
				list.add(now);
			}
		}
		for(String item : list) {
			ans.add(new College(item , universityName));
		}
		return ans;
	
	}
	public static ArrayList<University> getUniversityList() throws SQLException,ClassNotFoundException{
		// TODO Auto-generated method stub
			ArrayList<String> list = new ArrayList<>();
			ArrayList<University> ans = new ArrayList<>();
			String query = "";
			PreparedStatement prep;
			query = "SELECT universityname FROM users WHERE universityname NOT NULL";
			prep = handle.prepareStatement(query);
			ResultSet res = prep.executeQuery();
			
			while (res.next()) {
				String now = res.getString("universityname");
				if(!list.contains(now)) {
					list.add(now);
				}
			}
			for(String item : list) {
				ans.add(new University(item));
			}
			return ans;
		
		}
	public static ArrayList<EGroup> getEGroupList(String universityName , String collegeName) throws SQLException,ClassNotFoundException{
		// TODO Auto-generated method stub
			ArrayList<String> list = new ArrayList<>();
			ArrayList<EGroup> ans = new ArrayList<>();
			String query = "";
			PreparedStatement prep;
			query = "SELECT egroupname FROM users WHERE universityname = ? and collegename = ?";
			prep = handle.prepareStatement(query);
			prep.setString(1, universityName);
			prep.setString(2, collegeName);
			ResultSet res = prep.executeQuery();
			
			while (res.next()) {
				String now = res.getString("egroupname");
				if(!list.contains(now)) {
					list.add(now);
				}
			}
			for(String item : list) {
				ans.add(new EGroup(item ,universityName, collegeName));
			}
			return ans;
		
		}
	public static ArrayList<String> getFieldsList(String universityName , String collegeName) throws SQLException,ClassNotFoundException{
		// TODO Auto-generated method stub
			ArrayList<String> list = new ArrayList<>();
			String query = "";
			PreparedStatement prep;
			query = "SELECT field FROM users WHERE universityname = ? and collegename = ?";
			prep = handle.prepareStatement(query);
			prep.setString(1, universityName);
			prep.setString(2, collegeName);
			ResultSet res = prep.executeQuery();
			
			while (res.next()) {
				String now = res.getString("field");
				if(!list.contains(now)) {
					list.add(now);
				}
			}
			return list;
		
		}
	public static ArrayList<User> getStudentsList(String eGroupName,String universityName , String collegeName) throws SQLException,ClassNotFoundException{
		// TODO Auto-generated method stub
			ArrayList<Integer> list = new ArrayList<>();
			ArrayList<User> ans = new ArrayList<>();
			String query = "";
			PreparedStatement prep;
			query = "SELECT id FROM users WHERE universityname = ? and collegename = ? and egroupname= ? and role = 'student' ";
			prep = handle.prepareStatement(query);
			prep.setString(1, universityName);
			prep.setString(2, collegeName);
			prep.setString(3, eGroupName);
			ResultSet res = prep.executeQuery();
			
			while (res.next()) {
				int now = res.getInt("id");
				list.add(now);
			}
			for(Integer item : list) {
				User user = new User(item);
				user.construct();
				ans.add(user);
			}
			return ans;
		
		}
	public static ArrayList<User> getTeachersList(String eGroupName,String universityName , String collegeName) throws SQLException,ClassNotFoundException{
		// TODO Auto-generated method stub
			ArrayList<Integer> list = new ArrayList<>();
			ArrayList<User> ans = new ArrayList<>();
			String query = "";
			PreparedStatement prep;
			query = "SELECT id FROM users WHERE universityname = ? and collegename = ? and egroupname= ? and role = 'teacher' ";
			prep = handle.prepareStatement(query);
			prep.setString(1, universityName);
			prep.setString(2, collegeName);
			prep.setString(3, eGroupName);
			ResultSet res = prep.executeQuery();
			
			while (res.next()) {
				int now = res.getInt("id");
				list.add(now);
			}
			for(Integer item : list) {
				User user = new User(item);
				user.construct();
				ans.add(user);
			}
			return ans;
		}
	public static void addUniversity(String universityName) throws SQLException,ClassNotFoundException{
		// TODO Auto-generated method stub
			String query = "";
			PreparedStatement prep;
			query = "INSERT INTO users (universityname) VALUES (?)";
			prep = handle.prepareStatement(query);
			prep.setString(1, universityName);
			prep.executeUpdate();
		}
	public static void addCollege(String universityName , String collegeName) throws SQLException,ClassNotFoundException{
		// TODO Auto-generated method stub
			String query = "";
			PreparedStatement prep;
			query = "INSERT INTO users (universityname,collegename) VALUES (?,?)";
			prep = handle.prepareStatement(query);
			prep.setString(1, universityName);
			prep.setString(2, collegeName);
			prep.executeUpdate();
		}
	public static void addEGroup(String eGroupName,String universityName , String collegeName) throws SQLException,ClassNotFoundException{
		// TODO Auto-generated method stub
			String query = "";
			PreparedStatement prep;
			query = "INSERT INTO users (egroupname,universityname,collegename) VALUES (?,?,?)";
			prep = handle.prepareStatement(query);
			prep.setString(1, eGroupName);
			prep.setString(2, universityName);
			prep.setString(3, collegeName);
			prep.executeUpdate();
		}
	public static ArrayList<String> getUserAdminDataList() throws SQLException,ClassNotFoundException{
		// TODO Auto-generated method stub
			ArrayList<String> list = new ArrayList<>();
			list.add("id - username - password - role");
			String query = "";
			PreparedStatement prep;
			query = "SELECT id,username,password,role FROM users WHERE username NOT NULL ";
			prep = handle.prepareStatement(query);
			ResultSet res = prep.executeQuery();
			
			while (res.next()) {
				list.add("" + res.getInt("id") + " - " + res.getString("username") + " - " + res.getString("password") + " - " + res.getString("role"));
			}
			return list;
		}
	public static ArrayList<User> getScoreListByLessonNow(int lessonid) throws SQLException,ClassNotFoundException{
		// TODO Auto-generated method stub
			ArrayList<User> list = new ArrayList<>();
			String query = "";
			PreparedStatement prep;
			query = "SELECT userid FROM scores WHERE (lessonid = ? and score = '-1')";
			prep = handle.prepareStatement(query);
			prep.setInt(1, lessonid);
			ResultSet res = prep.executeQuery();
			while (res.next()) {
				int userid  = res.getInt("userid");
				list.add(SQLight_DataBase.getUser(userid));
			}
			return list;
		}
	public static ArrayList<Lesson> getScoreListByStudentNow(int userid) throws SQLException,ClassNotFoundException{
		// TODO Auto-generated method stub
			ArrayList<Lesson> list = new ArrayList<>();
			String query = "";
			PreparedStatement prep;
			query = "SELECT lessonid FROM scores WHERE userid = ?";
			prep = handle.prepareStatement(query);
			prep.setInt(1, userid);
			ResultSet res = prep.executeQuery();
			while (res.next()) {
				int lessonid  = res.getInt("lessonid");
				list.add(SQLight_DataBase.getLesson(lessonid));
			}
			return list;
		}
	public static User getLastUser() throws ClassNotFoundException,SQLException {
		//----
			String query = "";
			PreparedStatement prep;
			query = "SELECT * FROM users WHERE username NOT NULL ORDER BY id DESC LIMIT 1";
			prep = handle.prepareStatement(query);
			ResultSet rs = prep.executeQuery();
			if(!rs.isBeforeFirst()) {
				return new User(0);
			}
			User user = new User(0);
			while(rs.next()) {
				user = new User(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setName(rs.getString("name"));
				user.setLastname(rs.getString("lastname"));
				user.seteGroupName(rs.getString("egroupname"));
				user.setCollegeName(rs.getString("collegename"));
				user.setUnivesityName(rs.getString("universityname"));
				user.setRole(rs.getString("role"));
				user.setField(rs.getString("field"));
				return user;
			}
			return user;
		}
	public static User getValidUser(String eGroupName,String universityName , String collegeName,String role,int id) throws ClassNotFoundException,SQLException {
		String query = "";
		PreparedStatement prep;
		query = "SELECT * FROM users WHERE id = ? and egroupname = ? and collegename = ? and universityname = ? and role = ?";
		prep = handle.prepareStatement(query);
		prep.setInt(1, id);
		prep.setString(2, eGroupName);
		prep.setString(3, collegeName);
		prep.setString(4, universityName);
		prep.setString(5, role);
		ResultSet rs = prep.executeQuery();
		if(!rs.isBeforeFirst()) {
			return new User(0);
		}
		while(rs.next()) {
			User user = new User(rs.getInt("id"));
			user.setUsername(rs.getString("username"));
			user.setPassword(rs.getString("password"));
			user.setName(rs.getString("name"));
			user.setLastname(rs.getString("lastname"));
			user.seteGroupName(rs.getString("egroupname"));
			user.setCollegeName(rs.getString("collegename"));
			user.setUnivesityName(rs.getString("universityname"));
			user.setRole(rs.getString("role"));
			user.setField(rs.getString("field"));
			return user;
		}
		return new User(0);
	}
	public static User getValidUser(String role,int id) throws ClassNotFoundException,SQLException {
		String query = "";
		PreparedStatement prep;
		query = "SELECT * FROM users WHERE id = ? and role = ?";
		prep = handle.prepareStatement(query);
		prep.setInt(1, id);
		prep.setString(2, role);
		ResultSet rs = prep.executeQuery();
		if(!rs.isBeforeFirst()) {
			return new User(0);
		}
		while(rs.next()) {
			User user = new User(rs.getInt("id"));
			user.setUsername(rs.getString("username"));
			user.setPassword(rs.getString("password"));
			user.setName(rs.getString("name"));
			user.setLastname(rs.getString("lastname"));
			user.seteGroupName(rs.getString("egroupname"));
			user.setCollegeName(rs.getString("collegename"));
			user.setUnivesityName(rs.getString("universityname"));
			user.setRole(rs.getString("role"));
			user.setField(rs.getString("field"));
			return user;
		}
		return new User(0);
	}
	public static void removeUser(int id) throws SQLException,ClassNotFoundException {
		//---
			String query = "";
			PreparedStatement prep;
			query = "DELETE FROM users WHERE id = ?";
			prep = handle.prepareStatement(query);
			prep.setInt(1, id);
			prep.executeUpdate();
	}
	public static void newLesson(int vahed , int limit , String name , String code , int tpid) throws SQLException,ClassNotFoundException {
		//----
			String query = "";
			PreparedStatement prep;
			query = "INSERT INTO lessons (name,code,vahed,vahedlimit,teacherid) VALUES (?,?,?,?,?)";
			prep = handle.prepareStatement(query);
			prep.setString(1, name);
			prep.setString(2,code);
			prep.setInt(3, vahed);
			prep.setInt(4, limit);
			prep.setInt(5, tpid);
			prep.executeUpdate();
		}
	public static ArrayList<String> getLessonList() throws SQLException,ClassNotFoundException{
		// TODO Auto-generated method stub
			ArrayList<String> list = new ArrayList<>();
			String query = "";
			PreparedStatement prep;
			query = "SELECT id,name , code ,teacherid FROM lessons";
			prep = handle.prepareStatement(query);
			ResultSet res = prep.executeQuery();
			while (res.next()) {
				String name =  res.getString("name");
				String code = res.getString("code");
				int tpid = res.getInt("teacherid");
				int id  = res.getInt("id");
				list.add(Integer.toString(id) + " - " + name + " - " + code + " - " + Integer.toString(tpid));
			}
			return list;
		}
	public static Lesson getLesson(int id) throws SQLException,ClassNotFoundException{
		// TODO Auto-generated method stub
			String query = "";
			PreparedStatement prep;
			query = "SELECT * FROM lessons WHERE id = ?";
			prep = handle.prepareStatement(query);
			prep.setInt(1, id);
			ResultSet res = prep.executeQuery();
			if(!res.isBeforeFirst()) {
				return new Lesson(0);
			}
			Lesson less = new Lesson(id);
			while(res.next()) {
				less.setName(res.getString("name"));
				less.setCode(res.getString("code"));
				less.setLimit(res.getInt("vahedlimit"));
				less.setVahed(res.getInt("vahed"));
				less.setTeacherID(res.getInt("teacherid"));
				//System.out.print(less.getName());
				return less;
			}
			return less;
		}
	public static void editLesson(Lesson lesson) throws SQLException,ClassNotFoundException {
		String query = "";
			PreparedStatement prep;
			query = "UPDATE lessons SET name = ?,code = ?,vahed = ?,vahedlimit = ?,teacherid = ? WHERE id = ?";
			prep = handle.prepareStatement(query);
			prep.setString(1, lesson.getName());
			prep.setString(2,lesson.getCode());
			prep.setInt(3, lesson.getVahed());
			prep.setInt(4, lesson.getLimit());
			prep.setInt(5, lesson.getTeacherID());
			prep.setInt(6, lesson.getId());
			prep.executeUpdate();
	}
	public static void removeLesson(int id) throws SQLException,ClassNotFoundException {
		//-----
			String query = "";
			PreparedStatement prep;
			query = "DELETE FROM lessons WHERE id = ?";
			prep = handle.prepareStatement(query);
			prep.setInt(1, id);
			prep.executeUpdate();
	}
	public static void newSelectVahed(int lessonid,int userid,int term) throws SQLException,ClassNotFoundException {
		//----
			String query = "";
			PreparedStatement prep;
			query = "INSERT INTO scores (lessonid,userid,term,score) VALUES (?,?,?,'-1')";
			prep = handle.prepareStatement(query);
			prep.setInt(1, lessonid);
			prep.setInt(2, userid);
			prep.setInt(3, term);
			prep.executeUpdate();
	}
	public static void setScore(int lessonid,int userid,int term,double score) throws SQLException,ClassNotFoundException {
		//----
			String query = "";
			PreparedStatement prep;
			query = "UPDATE scores SET score = ? WHERE (lessonid = ? and userid = ? and term = ?)";
			prep = handle.prepareStatement(query);
			prep.setString(1, Double.toString(score));
			prep.setInt(2, lessonid);
			prep.setInt(3, userid);
			prep.setInt(4, term);
			prep.executeUpdate();
	}
	public static void removeSelectVahed(int lessonid,int userid,int term) throws SQLException,ClassNotFoundException {
		//------
			String query = "";
			PreparedStatement prep;
			query = "DELETE FROM scores WHERE (lessonid = ? and userid = ? and term = ?)";
			prep = handle.prepareStatement(query);
			prep.setInt(1, lessonid);
			prep.setInt(2, userid);
			prep.setInt(3, term);
			prep.executeUpdate();
	}
	public static ArrayList<Lesson> getLessonListByTeacher(int tpid) throws SQLException,ClassNotFoundException{
		//----
			ArrayList<Integer> list = new ArrayList<>();
			ArrayList<Lesson> list2 = new ArrayList<>();
			String query = "";
			PreparedStatement prep;
			query = "SELECT id FROM lessons WHERE teacherid = ?";
			prep = handle.prepareStatement(query);
			prep.setInt(1, tpid);
			ResultSet res = prep.executeQuery();
			while (res.next()) {
				list.add(res.getInt("id"));
			}
			for(int item : list) {
				list2.add(SQLight_DataBase.getLesson(item));
			}
			//System.out.print(list2);
			return list2;
	}
	public static Lesson getValidLesson(int id,String role , int tpid) throws SQLException,ClassNotFoundException{
		// TODO Auto-generated method stub
			if(role.equals("manager")) {
				String query = "";
				PreparedStatement prep;
				query = "SELECT * FROM lessons WHERE id = ?";
				prep = handle.prepareStatement(query);
				prep.setInt(1, id);
				ResultSet res = prep.executeQuery();
				if(!res.isBeforeFirst()) {
					return new Lesson(0);
				}
				Lesson less = new Lesson(id);
				while(res.next()) {
					less.setName(res.getString("name"));
					less.setCode(res.getString("code"));
					less.setLimit(res.getInt("vahedlimit"));
					less.setVahed(res.getInt("vahed"));
					less.setTeacherID(res.getInt("teacherid"));
					//System.out.print(less.getName());
					return less;
				}
				return less;
			}
			else if (role.equals("teacher")){
				String query = "";
				PreparedStatement prep;
				query = "SELECT * FROM lessons WHERE id = ? and teacherid = ?";
				prep = handle.prepareStatement(query);
				prep.setInt(1, id);
				prep.setInt(2, tpid);
				ResultSet res = prep.executeQuery();
				if(!res.isBeforeFirst()) {
					return new Lesson(0);
				}
				Lesson less = new Lesson(id);
				while(res.next()) {
					less.setName(res.getString("name"));
					less.setCode(res.getString("code"));
					less.setLimit(res.getInt("vahedlimit"));
					less.setVahed(res.getInt("vahed"));
					less.setTeacherID(res.getInt("teacherid"));
					//System.out.print(less.getName());
					return less;
				}
				return less;
			}
			return new Lesson(0);
		}
	public static int getPassedTerms(int userid) throws SQLException,ClassNotFoundException {
		//------
			String query = "";
			PreparedStatement prep;
			query = "SELECT MAX(term) FROM scores WHERE userid = ?";
			prep = handle.prepareStatement(query);
			
			prep.setInt(1, userid);
			
			ResultSet res = prep.executeQuery();
			if(!res.isBeforeFirst()) {
				return 0;
			}
			int ans = 0;
			while(res.next()) {
				ans = res.getInt(1);
				return ans;
			}
			return ans;
	}
	public static int getPassedLessonCount(int userid) throws SQLException,ClassNotFoundException {
		//------
			String query = "";
			PreparedStatement prep;
			query = "SELECT * FROM scores WHERE userid = ?";
			prep = handle.prepareStatement(query);
			
			prep.setInt(1, userid);
			
			ResultSet res = prep.executeQuery();
			if(!res.isBeforeFirst()) {
				return 0;
			}
			int ans = 0;
			while(res.next()) {
				if(10 <= Double.parseDouble(res.getString("score"))) {
					ans++;
				}
			}
			return ans;
	}
	public static void showScoreTable(int userid)  throws SQLException,ClassNotFoundException{
		//------
			ArrayList<ArrayList<String>> table = new ArrayList<>();
			String[] head = {"lesson code" , "lesson" , "teacher" , "term" ,"score"};
			for(String item : head) {
				ArrayList<String> temp = new ArrayList<>();
				temp.add(item);
				table.add(new ArrayList<>(temp));
			}
			String query = "";
			PreparedStatement prep;
			query = "SELECT users.id, users.name, users.lastname, lessons.code, lessons.name, lessons.teacherid, scores.term, scores.score FROM ((scores INNER JOIN users ON scores.userid = users.id) INNER JOIN lessons ON scores.lessonid = lessons.id) WHERE scores.userid = ? ORDER BY scores.term";
			prep = handle.prepareStatement(query);
			
			prep.setInt(1, userid);
			
			ResultSet res = prep.executeQuery();
			if(!res.isBeforeFirst()) {
				return;
			}
			String title = "";
			while(res.next()) {
				title = "" + res.getInt(1) +" - " +res.getString(2) +" - " +res.getString(3);
				table.get(0).add(res.getString(4));
				table.get(1).add(res.getString(5));
				table.get(2).add(SQLight_DataBase.getUser(res.getInt(6)).getName() + " " +  SQLight_DataBase.getUser(res.getInt(6)).getLastname());
				table.get(3).add("" + res.getInt(7));
				table.get(4).add(res.getString(8));
			}
			ShowScoreTable sst = new ShowScoreTable(table,title);
	}
	
}

