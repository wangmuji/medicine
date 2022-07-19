<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page language="java" import="entity.Page" %>
<%@ page language="java" import="entity.Medicine" %>
<%@ page language="java" import="entity.User" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>新增用户 </title>
        <style>
            body{
                background:white;
            }
            .content{
                /* display: none; */
                /* position: absolute; */
                /* top: 20%; */
                /* left: 34%; */
                margin:auto;
                background-color: white;
                z-index: 2;
                width: 500px;
                /* height: 420px; */
                text-align:center;
                border:solid;
            }
            .item{
                margin:15px;
                font-size:20px;
            }
        </style>
        <script type="text/javascript">
    		
    		function check(){
    			var account= document.getElementById("account").value;
    			var passwd =document.getElementById("passwd").value; 
    			
    			if(document.getElementById("name").value=="")
    			{
    				alert("请完整填写信息");
    				return false;    				
    			}else if(document.getElementById("account").value==''){
    				alert("请完整填写信息");
    				return false;  
    			}
    			else if(document.getElementById("passwd").value==''){
    				alert("请完整填写信息");
    				return false;  
    			}
    			else if(document.getElementById("address").value==''){
    				alert("请完整填写信息");
    				return false;  
    			}
    			for(let i=0;i<account.length;i++)
    			{
    				if((account.charAt(i)>='0'&&account.charAt(i)<='9')||(account.charAt(i)>='a'&&account.charAt(i)<='z')||(account.charAt(i)>='A'&&account.charAt(i)<='Z'))
    				{
    					
    				}
    				else
    				{
    					alert("账户处输入格式错误,账户由数字+字母组成");
						return false;
    				}
    			}
    			if(passwd.length<6){
    				alert("密码至少为6位");
    				return false;
    			}
    			
    			return true;
    		}
    		
    		
    	</script>
    </head>
    
    <body>
    	
        <div class="container" >
            <div style="height:150px"></div>
            
            <div class="content">
                <h1 style="text-align:center">新增用户</h1>
                <form action="${pageContext.request.contextPath}/userAddServlet" method="post" >
                    <div class="item"><label >名称:</label> 
                    <input type="text" text="" name="username" id="username">
                    </div>
                    <div class="item"><label >账户:</label>
                     <input type="text" text="" name="account" id="account">
                     </div>
                    <div class="item"><label >密码:</label> 
                    <input type="text" text="" name="passwd" id="passwd">
                    </div> 
                    <div class="item"><label >地址:</label> 
                    <input type="text" text="" name="address"  id="address">
                    </div>
                    <input  type="submit" value="提交" onclick="return check();">
                </form>
              
            </div>
            
        
        </div>
    </body>


</html>