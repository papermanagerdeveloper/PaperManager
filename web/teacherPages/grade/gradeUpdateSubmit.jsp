<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="beans.*"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%--从session中获取教师ID --%>
	<%!String teacherID="";%>
	<% 	
		User user = (User)session.getAttribute("user"); 
		teacherID = user.getID();
	%>
	<%--根据教师的ID查询属于他/她的所有学生 --%>
	<%!List<Map> studentList=new ArrayList<Map>(); %>
	<%
		studentList = (ArrayList<Map>)Student.getStudentByTeacherID(teacherID); 
	%>
	<p>提交完毕，其中失败的项如下(为空则表示全部录入成功)：</p>
	<p>start------------</p>
	<%--把提交的成绩录入数据库 --%>
	<% 
	    Enumeration<String> studentIDs = request.getParameterNames();
		String studentID = "";
		String studentGrade = "";
		int result = 1;
		while(studentIDs.hasMoreElements()){
			studentID = studentIDs.nextElement();
			studentGrade = request.getParameter(studentID);
			result = Grade.gradeUpdate(studentID, studentGrade);
			if(result<=0){
				out.print("此学生成绩录入失败："+studentID+"  成绩："+studentGrade+"<br />");
			}
		}
	%>
	<p>--------------end</p>
	<a href = "../index.html">返回</a>
</body>
</html>