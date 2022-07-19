
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page language="java" import="entity.Page" %>
<%@ page language="java" import="entity.Medicine" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <script>
        function login_btm_func(){

        }
    </script>
    <style>
        * {
            margin: 0;
            padding: 0;
        }
        html {
            height: 100%;
        }
        body {
            height: 100%;
        }
        .container {
            height: 100%;
            background-image: linear-gradient(to right, #58b9fa, #a6c1ee);
        }
        .title{
            text-align:center;
            font-size: 35px;;
            height: 30%;
            width: 100%;
            
        }
        .login-wrapper {
            background-color: #fff;
            width: 358px;
            height: 388px;
            border-radius: 15px;
            padding: 0 50px;
            position: relative;
            left: 50%;
            margin-top: 10%;
            transform: translate(-50%, -50%);
        }
        .header {
            font-size: 38px;
            font-weight: bold;
            text-align: center;
            line-height: 100px;
            font-style:normal;
        }
        .input-item {
            display: block;
            width: 100%;
            margin-top:20px;
            margin-bottom: 20px;
            border: 0;
            padding: 10px;
            border-bottom: 1px solid rgb(128, 125, 125);
            font-size: 17px;
            outline: none;
        }
        .input-item:placeholder {
            text-transform: uppercase;
        }
        #login_btn {
            text-align: center;
            padding: 10px;
            font-size: 20px;
            width: 100%;
            margin-top: 20px;
            background-image: linear-gradient(to right, #a6c1ee, #c2d9fb);
            color: #fff;
            border-radius: 15px;
            border: 0ch;
        }
        
        .msg {
            text-align: center;
            line-height: 88px;
        }
        a {
            text-decoration-line: none;
            color: #abc1ee;
        }
        #login_fail{
        	color: #ef0b0b;
    		font-size: 14px;
        	
        }
        
    </style>
</head>
<body>
    <%
    	String error=(String)request.getAttribute("error");
    	
    	System.out.println(error+"---------------");	
    %>
    <div class="container">
        <div class="title">药店管理系统</div>
        <div class="login-wrapper" >
            <div class="header" >登录</div>
            <div class="form-wrapper">
                <form action="${pageContext.request.contextPath}/loginServlet" method="post">
                	<input type="radio" name="type" value="front" checked>前台销售界面
					<input type="radio" name="type" value="back" style="margin-left: 35px;/* float: right; */">后台管理界面
                    <input type="text" name="account" placeholder="account" class="input-item">
                    
                    <input type="password" name="password" placeholder="password" class="input-item">
                    <c:if test="${error!=null }">
                    	<p id="login_fail"> 登录失败，请重新输入 </p>
                    </c:if>
                    <input id="login_btn" type="submit" value="login"/>
                </form>
            </div>
            
        </div>
    </div>
</body>
</html>
