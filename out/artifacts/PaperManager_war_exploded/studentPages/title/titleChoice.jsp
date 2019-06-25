<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="beans.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>学生选题</title>
</head>
<body>
	<div>
		<form id = "titleChoice" method = "post" action = "titleChoiceSubmit.jsp">
			<table id = "titleChoiceTable" align = "center" border = "1">
				<jsp:useBean id = "titlesquery"
							class = "beans.Titles"
							scope = "page"
				/>
				<jsp:getProperty name = "titlesquery"
								property = "trs"
				/>
				<tr>
					<td colspan = "3" align = "center"><a href = "../index.html">返&nbsp;回</a></td>
					<td colspan = "2" align = "right"><input type = "submit" value = "提交选题" /></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>