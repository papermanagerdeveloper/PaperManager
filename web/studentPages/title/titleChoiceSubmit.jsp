<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import = "beans.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>教师添加题目</title>
</head>
<body>
<% 
	Titles tu = new Titles();
	String ID = request.getParameter("choiceResult");
	User studentUser = (User)session.getAttribute("user");
	String studentID = studentUser.getID();
	int result = tu.titleSubmit(ID,studentID);
	if(result==1) {
		out.println("选题成功！<br />");
	}
	else {
		out.println("该题目已被其他学生选择或者您已经选过题了！<br />");
		out.println("3s后返回选题页面。");
		response.setHeader("Refresh","3;url=titleChoice.jsp");	
	}
%>
<a href = "../index.html">现在返回</a>
</body>
</html>