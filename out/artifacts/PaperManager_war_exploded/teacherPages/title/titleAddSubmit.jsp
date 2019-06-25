<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="beans.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		/*获取选题页面提交的题目信息*/
		String caption = request.getParameter("caption");
		String type = request.getParameter("type");
		String introduction = request.getParameter("introduction");
		/*读取本次对话中的老师的id*/
		User teacherUser = (User)session.getAttribute("user");
		String teacherID = teacherUser.getID();
		/*添加选题，执行结果报存在result中*/
		Titles til = new Titles();
		int result = til.titleAdd(teacherID,caption,type,introduction);
		if(result!=1){
			out.println("题目添加失败！<br />");
			out.println("<a href='titleAdd.jsp'>返回</a>");
		}
		else{
			/*返回到添加题目的页面*/
			response.sendRedirect("titleAdd.jsp");
		}
	%>
</body>
</html>