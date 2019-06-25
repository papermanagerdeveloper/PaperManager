<%@ page import="beans.DBUtil" %>
<%@ page import="beans.User" %>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="../bootstrap-4.3.1-dist/css/bootstrap.css">
    <title>上传报告</title>
    <%
        DBUtil db = new DBUtil();
        String query = "select schedule from studentInfo where ID = " +((User)session.getAttribute("user")).getID();
        Map result = db.getMap(query,new String[]{});
        String schedule = (String)result.get("schedule");
        String stage = "";
        if(schedule.equals("taskPlan"))    stage="报告书";
        else if(schedule.equals("midtermReport"))   stage="中期报告";
        else if(schedule.equals("finalReport"))     stage="结题报告";

    %>
</head>
<body>
<div align="center">
    <h1>上传<%=stage%></h1>
    <form action="../UploadHandleServlet" enctype="multipart/form-data" method="post">
        选择文件：
        <div style="height: 5px;"></div>
        <input type="file"><br/>
        <div style="height: 5px;"></div>
        <input class="btn btn-primary" style="width: 70px;height: 43px;font-size:15px" type="submit" value="提交">
    </form>
</div>


</body>
</html>