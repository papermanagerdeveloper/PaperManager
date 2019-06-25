<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ page import="beans.*"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%!String studentID; %>
	<%!String studentName; %>
	<%!String yourGrade; %>
	<%!String titleID; %>
	<%!String titleName; %>
	<%!String titleType; %>
	<%
		User user = (User)session.getAttribute("user");
		studentID = user.getID();
		
		Grade grade = new Grade(studentID);
		Student student = new Student(studentID);
		
		titleID = student.getTitleID();
		Map<String,String> titleMap = (Map<String,String>)Titles.titleQuery(titleID);
		
		studentName = student.getName();
		yourGrade = grade.getGrade();
		titleName = titleMap.get("caption");
		titleType = titleMap.get("type");
	%>
	<table align = "center" border = "1">
		<tr height = "50px" align = "center">
			<td width = "100px">姓名：</td>
			<td width = "100px"><%=studentName %></td>
		</tr>
		<tr height = "50px" align = "center">
			<td width = "100px">学号：</td>
			<td width = "100px"><%=studentID %></td>
		</tr>
		<tr height="50px" align = "center">
			<td width = "100px">题目ID：</td>
			<td width = "100px"><%=titleID %></td>
		</tr>
		<tr height="50px" align = "center">
			<td width = "100px">题目名称：</td>
			<td width = "100px"><%=titleName %></td>
		</tr>
		<tr height="50px" align = "center">
			<td width = "100px">题目类别：</td>
			<td width = "100px"><%=titleType %></td>
		</tr>
		<tr height="50px" align = "center">
			<td width = "100px">成绩：</td>
			<td width = "100px"><%=yourGrade %></td>
		</tr>
	</table>
	<a href = "../index.html">返回</a>
</body>
</html>