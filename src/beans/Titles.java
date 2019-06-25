package beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Titles {
	/*
	 * 数组tr，保存  生成表格的  html代码中的  固定部分。
	 */
	protected String[] tr;
	public Titles() {
		/*给成员变量tr数组赋值*/
		tr = new String[6];
		tr[0] = "<tr height = '80px' align = 'center'><td name = 'ID' width = '100px' align = 'center'>";
		tr[1] = "</td><td name = 'caption' width = '100px' align = 'center'>";
		tr[2] = "</td><td name = 'type' width = '100px' align = 'center'>";
		tr[3] = "</td><td name = 'introduction' width = '300px' align = 'center'>";
		tr[4] = "</td><td name = 'choice' width = '50px' align = 'center'><input type = 'radio' name = 'choiceResult' value='";
		tr[5] = "'/></td></tr>";
	}
	/*
	 * 根据查询的所以题目信息，生成表格部分的html代码
	 */
	public String getTrs() {
		/*调用DBUtil查询所有可选的题目*/
		String 	sql = "select * from title where chosen='F'";
		String[] params = null;
		DBUtil dbUtil = new DBUtil();
		ArrayList<HashMap> items = (ArrayList<HashMap>)dbUtil.getList(sql, params);
		
		/*调用函数生成题目表格的html代码，并将查询结果插入到html代码中*/
		String finalTrs = "";
		for(HashMap<String,String> item:items) {
			finalTrs += createTr(item);
		}
		return finalTrs;
	}
	/*
	 * 学生提交选题
	 */
	public int titleSubmit(String ID,String studentID ) {
		String[] params1 = {ID};
		String[] params2 = {ID,studentID};
		String titleQuerySql = "select * from title  where ID = ? and chosen ='F'";
		String titleUpdateSql = "update title set chosen = 'T' where ID = ?";
		String studentUpdateSql = "update studentInfo set titleID = ? where ID = ?";
		
		DBUtil dbUtil = new DBUtil();
		List list = dbUtil.getList(titleQuerySql, params1);
		if(list.isEmpty()) {
			 return 0;
		}else {
			 dbUtil.update(titleUpdateSql, params1);
			 dbUtil.update(studentUpdateSql, params2);
			 return 1;
		}
	}
	/* 教师添加题目*/
	public static int titleAdd() {
		/*String sql = "update grade set grade=? where ID=?";
		String[] params = {grade,ID};
		DBUtil dbutil = new DBUtil();
		int result = dbutil.update(sql, params);*/
		return 1;
	}
	/*根据题目ID查询题目信息*/
	public static Map titleQuery(String titleID) {
		Map map = new HashMap();
		DBUtil dbutil = new DBUtil();
		String 	sql = "select * from title where ID=?";
		String[] params = {titleID};
		
		map = dbutil.getMap(sql, params);
		return map;
	}
	/*
	 * 根据查询到的一个题目信息，生成表格中的某一行的代码
	 */
	protected String createTr(HashMap<String,String> item) {
		String finalTr = "";
		finalTr += tr[0];
		finalTr += item.get("ID");
		finalTr += tr[1];
		finalTr += item.get("caption");
		finalTr += tr[2];
		finalTr += item.get("type");
		finalTr += tr[3];
		finalTr += item.get("introduction");
		finalTr += tr[4];
		finalTr += item.get("ID");
		finalTr += tr[5];
		return finalTr;
	}
}
