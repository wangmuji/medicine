<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>

<head>
    <title>修改密码失败 </title>
    <style>
    	 body{
                margin:0;
                background:white;
            }
    
    </style>
</head>

<body>
    <h3>修改密码失败,原因：${error}</h3>
    <a href="${pageContext.request.contextPath}/passwd_change.jsp">返回修改密码界面</a>
</body>


</html>