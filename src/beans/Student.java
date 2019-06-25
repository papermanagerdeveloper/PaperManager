package beans;

import java.util.*;

public class Student {
	String id;
	String name;
	String sex;
	String passwd;
	String telNumber;
	String schedule;
	String titleID;
	/*测试代码*/
	public static void main(String[] args) {
		List list = Student.getStudentByTeacherID("190601");
		System.out.print(list.get(0).toString());
	}
	
	/*构造函数*/
	public Student(String id) {
		setId(id);
		queryAll();
	}
	/*根据学生的指导老师的ID来查询学生信息*/
	public static ArrayList getStudentByTeacherID(String teacherID) {
		String sql = "select * from studentInfo where teacherID=?";
		String[] params = {teacherID};
		DBUtil dbutil = new DBUtil();

		ArrayList list = (ArrayList)dbutil.getList(sql, params);
		return list;
	}
	/* 学生注册功能
	      先用其它函数设置好学生的个人信息，之后调用本函数把该学生添加进数据库，完成注册
	      这里还应该在grade表中添加一个空的学生成绩，为以后的给成绩做准备
	 */	
	public int addStudent() {
		return 1;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	public String getId() {
		return this.id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return this.name;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getSex() {
		return this.sex;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getPasswd() {
		return this.passwd;
	}
	public String getTelNumber() {
		return this.telNumber;
	}
	public void setTelNumber(String telNumber) {
		this.telNumber = telNumber;
	}
	public String getSchedule() {
		return this.schedule;
	}
	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}
	public void setTitleID(String titleID) {
		this.titleID = titleID;
	}
	public String getTitleID() {
		return this.titleID;
	}
	/*根据已有的学生ID，查询学生的其他信息，并保存在本对象中*/
	protected void queryAll(){
		String sql = "select * from studentInfo where ID=?";
		String[] params = {this.id};
		
		DBUtil dbutil = new DBUtil();
		Map studentData = dbutil.getMap(sql, params);
		
		this.setName((String)studentData.get("name"));
		this.setSex((String)studentData.get("sex"));
		this.setPasswd((String)studentData.get("passwd"));
		this.setSchedule((String)studentData.get("schedule"));
		this.setTelNumber((String)studentData.get("telNumber"));
		this.setTitleID((String)studentData.get("titleID"));
	}
}
