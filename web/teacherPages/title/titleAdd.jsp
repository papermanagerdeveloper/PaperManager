<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="beans.*"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>请添加毕业论文的题目</title>
</head>
<body>
	<form method = "post" action = "titleAddSubmit.jsp" >
		<table align = "center">
			<tr>
				<td>题目名称:</td>
				<td><input type = "text"  name = "caption" /></td>
			</tr>
			<tr>
				<td>类型:</td>
				<td><input type = "text"  name = "type" /></td>
			</tr>
			<tr>
				<td>题目简介:</td>
				<td><textarea  rows = "4" cols = "50" name = "introduction" ></textarea></td>
			</tr>
			<tr>
				<td><input type = "submit"  value = "提交题目" /></td>
			</tr>
			<tr>
				<td></td><td><a href = "../index.html">返回</a></td>
			</tr>
		</table>
	</form>
</body>
</html>