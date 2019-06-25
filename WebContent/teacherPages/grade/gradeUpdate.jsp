<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="beans.*"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>请更新学生的成绩</title>
</head>
<body>
	<%--从session中获取教师ID --%>
	<%!String teacherID;%>
	<% 	
		User user = (User)session.getAttribute("user"); 
		teacherID = user.getID();
	%>
	<%--根据教师的ID查询属于他/她的所有学生 --%>
	<%!List<Map> studentList=(List<Map>)Student.getStudentByTeacherID(teacherID); %>
	
	<p>请录入学生的成绩</p>
	
	<form action="index.html" method = "get">
	<input type = "submit" value = "提交" align = "center" />
	<table align = "center" border = "1">
		<tr>
			<th>学生ID</th>
			<th>学生姓名</th>
			<th>论文ID</th>
			<th>论文题目</th>
			<th>论文类型</th>
			<th>学生成绩</th>
		</tr>
		<%
			for(Map student:studentList) {
				/*在表格中添加一行*/
				out.print("<tr>");
				/*添加该行的每一项*/
				
				/*学生ID*/
				out.print("<td>");
				out.print(student.get("ID"));
				out.print("</td>");
				
				/*学生姓名*/
				out.print("<td>");
				out.print(student.get("name"));
				out.print("</td>");
				
				/*对于这一行的学生，查询他对应的论文题目信息*/
				String titleID = (String)student.get("titleID");
				Map title = Titles.titleQuery(titleID);
				
				/*论文ID*/
				out.print("<td>");
				out.print(title.get("ID"));
				out.print("</td>");
				
				/*论文题目*/
				out.print("<td>");
				out.print(title.get("caption"));
				out.print("</td>");
				
				/*论文类型*/
				out.print("<td>");
				out.print(title.get("type"));
				out.print("</td>");
				
				/*学生成绩*/
				out.print("<td>");
				out.print("<input type = 'text' name = '"+student.get("ID")+"' />");
				out.print("</td>");
				
				out.print("</tr>");
			}
		%>
	</table>
	</form>
</body>
</html>