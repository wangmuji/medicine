<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page language="java" import="entity.Page" %>
<%@ page language="java" import="entity.Medicine" %>
<%@ page language="java" import="entity.User" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>药品购入 </title>
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
    			var num= document.getElementById("num").value;
    			var p_price =document.getElementById("purchase_price").value; 
    			
    			if(document.getElementById("id").value=="")
    			{
    				alert("请完整填写信息");
    				return false;    				
    			}else if(document.getElementById("name").value==''){
    				alert("请完整填写信息");
    				return false;  
    			}
    			else if(document.getElementById("purchase_price").value==''){
    				alert("请完整填写信息");
    				return false;  
    			}
    			else if(document.getElementById("num").value==''){
    				alert("请完整填写信息");
    				return false;  
    			}
    			for(let i=0;i<num.length;i++)
    			{
    				if(num.charAt(i)>='0'&&num.charAt(i)<='9')
    				{
    					if(i==0&&num.charAt(i)=='0')
    					{
    							alert("数量处输入格式错误");
    							return false;
    					}
    				}
    				else
    				{
    					alert("数量处输入格式错误");
						return false;
    				}
    			}
    			let point=0;
    			for(let i=0;i<p_price.length;i++)
    			{
    				if(p_price.charAt(i)>='0'&&p_price.charAt(i)<='9')
    				{
    					if(i==0&&p_price.charAt(i)=='0')
    					{
    						if(p_price.length<3 || p_price.charAt(i+1)!='.'){
    							alert("购入单价处输入格式错误");
    							return false;
    						}
    							
    							
    					}
    				}
    				else if(p_price.charAt(i)=='.'){
    					if(i==0||i==p_price.length-1)
    					{
    						alert("购入单价处输入格式错误");
							return false;
    					}else if(point>0)
    					{
    						alert("购入单价处输入格式错误");
							return false;
    					}else point++;
    				}
    				else
    				{
    					alert("购入单价处输入格式错误");
						return false;
    				}
    			}
    			
    					
    			
 
				return true;
    		}
    		
    		
    	</script>
    </head>
    
    <body>
    	
        <div class="container" >
            <div style="height:150px"></div>
            
            <div class="content">
                <h1 style="text-align:center">购入药品</h1>
                <form action="${pageContext.request.contextPath}/medPurchaseServlet" method="post" >
                    <div class="item"><label >药品:</label> 
                    	<select	name="id">
                    		<c:forEach var="medicine" items="${medicines}">
	                    		<option value="${medicine.id }">${medicine.id } -- ${medicine.name }</option>
	                		</c:forEach>
                    	</select>
                    </div>
                    
                    <div class="item"><label >数量:</label> 
                    <input type="text" text="" name="num" id="num">
                    </div> 
                    <div class="item"><label >购入单价:</label> 
                    <input type="text" text="" name="purchase_price"  id="purchase_price">
                    </div>
                    <input  type="submit" value="提交" onclick="return check();">
                </form>
              
            </div>
            
        
        </div>
    </body>


</html>