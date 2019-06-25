<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ page import="beans.*"%>
<%@ page import="java.util.*"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="viewport" charset="UTF-8" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" type="text/css" href="../../bootstrap-4.3.1-dist/css/bootstrap.css">
    <title>学生成绩查询页面</title>
    <style type="text/css">
        table.imagetable{
            font-family: Verdana,Arial,sans-serif;
            font-size: 16px;
            color: #333333;
            border-width: 1px;
            border-color:  #999999;
            border-collapse: collapse;
            margin-top: 100px;
        }
        table.imagetable th {
            background: #b5cfd2 url("cell-blue.jpg");
            border-width: 1px;
            padding: 8px;
            border-style: solid;
            border-color: #dcddc0;
            width: 200px;
            height: 50px;
        }
        table.imagetable td {
            background: #dcddc0 url("cell-grey.jpg");
            border-width: 1px;
            padding: 8px;
            border-stytle: solid;
            border-color: #999999;
            width: 400px;
            height: 50px;
        }
    </style>
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
		Titles til = new Titles();
		Map<String,String> titleMap = (Map<String,String>)til.titleQuery(titleID);
		
		studentName = student.getName();
		yourGrade = grade.getGrade();
		titleName = titleMap.get("caption");
		titleType = titleMap.get("type");
	%>
	<table class="imagetable" align = "center">
	    <tr >
	        <th >姓名：</th>
	        <td onmouseover="this.style.backgroundColor='#ecffdf';"onmouseout="this.style.backgroundColor='#e5cfd0'"><%=studentName %></td>
	    </tr>
	    <tr>
	        <th >学号：</th>
	        <td><%=studentID %></td>
	    </tr>
	    <tr>
	        <th>题目ID：</th>
	        <td><%=titleID %></td>
	    </tr>
	    <tr>
	        <th>题目名称：</th>
	        <td><%=titleName %></td>
	    </tr>
	    <tr>
	        <th>题目类别：</th>
	        <td><%=titleType %></td>
	    </tr>
	    <tr>
	        <th>成绩：</th>
	        <td><%=yourGrade %></td>
	    </tr>
	    <tr>
	    	<td>-</td>
			<td align = "center"><a href = "../index.html"><h3>返&nbsp;&nbsp;回</h3></a></td>
		</tr>
	</table>
</body>
</html>


<%--
  废弃代码
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
		<tr>
			<td colspan = "2" align = "center"><a href = "../index.html">返回</a></td>
		</tr>
	</table>
</body>
</html>
 --%>