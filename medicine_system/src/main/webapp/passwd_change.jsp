
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page language="java" import="entity.Page" %>
<%@ page language="java" import="entity.Medicine" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>

        <style>
            body{
                margin:0;
                background:white;
            }
            #title{
                width:100%;
                text-align:center;
                font-size:30px;
                padding-top:40px;
            }
            #contain {
                border:0;
                width: 100%;
                height: 100%;
                background: #fff;
                float:left;
            }   
            #change_wrapper{
                background-color: #fff;
                width: 358px;
                height: 388px;
                border-radius: 15px;
                padding: 0 50px;
                /* position: relative; */
                /* left: 50%; */
                /* margin-top: 10%; */
                /* transform: translate(-50%, -50%); */
                
                margin: auto;

            }
            #new_passwd0 {
                display: block;
                width: 100%;
                margin-bottom: 20px;
                border: 0;
                padding: 10px;
                border-bottom: 1px solid rgb(128, 125, 125);
                font-size: 15px;
                outline: none;
            }
            #new_passwd1 {
                display: block;
                width: 100%;
                margin-bottom: 20px;
                border: 0;
                padding: 10px;
                border-bottom: 1px solid rgb(128, 125, 125);
                font-size: 15px;
                outline: none;
            }
            #input-item:placeholder {
                text-transform: uppercase;
            }
            #empty{
                height:100px;
                weight:100%;
            }
            #change_btn {
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
        </style>

			<script type="text/javascript">
            function check()    //id为药品编号
            {
            	
            	if(document.getElementById("new_passwd0").value=='')
    			{
    				alert("请填写新密码");
    				return false;    				
    			}
    			if(document.getElementById("new_passwd1").value=='')
    			{
    				alert("请确认新密码");
    				return false;    				
    			}
 
				 return true;
               
            }
        </script>
        
    </head>
    
    
    <body>
        
        <div id="contain">
            
            <div id="empty"></div>
            <div id="change_wrapper">
                <div id="title">修 改 密 码</div>
                <form name="changeForm"  action="${pageContext.request.contextPath}/changePasswdServlet?account=${user.account}" method="post" style="padding-top:30px" > 
                    <input type="password" name="new_passwd0" placeholder="输入新密码" text="" id="new_passwd0">
                    <input type="password" name="new_passwd1" placeholder="再次输入新密码" text="" id="new_passwd1">
                    
                    <input id="change_btn" onclick="return check();" type="submit" value="提交"/>
                    
                </form>
            </div>
        </div>
    </body>
	

</html>