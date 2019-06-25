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
		String caption = request.getParameter("caption");
		String type = request.getParameter("type");
		String introduction = request.getParameter("introduction");
		
		int result = Titles.titleAdd(caption,type,introduction);
		
		/*返回到添加题目的页面*/
		response.sendRedirect("titleAdd.jsp");
	%>
</body>
</html>