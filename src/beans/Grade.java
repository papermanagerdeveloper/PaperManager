package beans;

import java.util.Map;

public class Grade {
	String id;
	String grade;
	/*测试代码*/
	public static void main(String[] args) {
		Grade grade = new Grade("19001");
		System.out.print(grade.getGrade());
	}
	public Grade(String id) {
		setId(id);
		queryAll();
	}
	/*教师端给成绩*/
	public static int gradeUpdate(String ID,String grade) {
		String sql = "update grade set grade=? where ID=?";
		String[] params = {grade,ID};
		DBUtil dbutil = new DBUtil();
		int result = dbutil.update(sql, params);
		return result;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getId() {
		return this.id;
	}
	public String getGrade() {
		return this.grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	/*查询该ID的学生成绩及相关信息*/
	protected void queryAll() {
		String sql = "select * from grade where ID=?";
		String[] params = {this.id};

		DBUtil dbutil = new DBUtil();
		Map gradeData = dbutil.getMap(sql, params);

		setGrade((String)gradeData.get("grade"));

	}
}
