
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page language="java" import="entity.Page" %>
<%@ page language="java" import="entity.Medicine" %>
<%@ page language="java" import="entity.Shopping" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>
            已选药品    
        </title>
        <style>
            body{
                background :#fff;
            }

            table {
                margin: 10px auto;
                width: 100%;
                text-align: center;
                border-spacing: 0;
            }
            table tbody tr:first-of-type {
                background-color: #dadee1 !important;
                height: 60px;
            }
            table tbody tr {
                height: 40px;
                word-break: break-all;
                max-width:120px;
            }
            table tbody tr td{
                
                max-width:120px;
            }
            table tbody tr:nth-child(odd) {
                
                background-color: #eef1f8;
            }
            table tbody tr:nth-child(even) {
                background-color: #ffffff;
            }
            table tbody tr:not([id=thead]):hover {
                /* cursor: pointer; */
                background-color: #e9e9e9;
            }
            #navigate{
                float:right;
            }

            header {
                margin:auto;
                width:50%;
            }
            header h1 {
                color: #9a9897;
                margin-left:35%;
                margin-bottom:10px;

            }
            #tpbt{
                text-align:center;
            }
            #container{
                margin:auto;
                width:50%;
            }
            #pay_btn{
                float:right;
                /* margin-right:5%; */
                width:70px;
                height:35px;
                font-size:19px;
                margin-top:15px;
                border-radius:5px;
            }
			#empty_btn{
				float: left;
			    /* margin-right: 5%; */
			    width: 70px;
			    height: 35px;
			    font-size: 19px;
			    margin-top: 15px;
			    border-radius: 5px;
			}
        </style>
    </head>
    <script type="text/javascript">
    	function settle(path,totalPrice,pageIndex){
    		
    		if(window.confirm("总价为: "+totalPrice+" \n是否结算")){
    			window.location.href= path+"/carSettleServlet?"+"pageIndex="+pageIndex;
    	      }else{
    	    	  alert("取消结算");
    	      }
    	}
    
    </script>
    <body>
        <header>
        	<a href="${pageContext.request.contextPath}/carEmptyServlet"><button id="empty_btn" >清空</button></a>
            <button id="pay_btn" onclick="settle('${pageContext.request.contextPath}','${totalPrice}',${page.pageIndex });">结算</button>
            <h1>已选药品</h1>
        </header>
        <div id="container">
            <table>
                <tbody>
                    <tr id="thead">                       
                        <td>药品编号</td>
                        <td>药品名称</td>
                        <td>数量</td>
                        <td>单价</td>
                        <td>总价</td>   
                        <td></td>
                    </tr>
                    
                    <c:forEach var="shopping" items="${cars}">
                    <tr id=${shopping.ID }>                    
                        <td>${shopping.ID}</td>
                        <td>${shopping.name }</td>
                        <td>${shopping.num }</td>
                        <td>${shopping.price }</td>           
                        <td>${shopping.price*shopping.num }</td>            
                        <td><a href="${pageContext.request.contextPath}/carDelServlet?id=${shopping.ID}&pageIndex=${page.pageIndex}"><button>删除</button></a></td>
                        
                     
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <div id="navigate">
                <p>第<span id="pgn">${page.pageIndex}</span>页,共<span id="en">${page.totalPages}</span>页 (每页最多显示${page.pageSize}条)</p><!--Number of entries-->
                <div id="tpbt">
                	<c:if test="${page.pageIndex==1 }">
                    	<button class="gre" id="lastpage">上一页</button>
                    </c:if>
                	<c:if test="${page.pageIndex>1  }">
                		<a href="${pageContext.request.contextPath}/carPageServlet?pageIndex=${page.pageIndex-1}"><button class="gre" id="lastpage">上一页</button></a>
                    	
                    </c:if>
                    
                    <c:if test="${page.pageIndex==page.totalPages }">
                    	<button class="red" id="nextpage">下一页</button>
                    </c:if>
                    
                    <c:if test="${page.pageIndex < page.totalPages }">
                    <a href="${pageContext.request.contextPath}/carPageServlet?pageIndex=${page.pageIndex+1}"><button class="red" id="nextpage">下一页</button></a>
            		</c:if>
            </div>
        </div>
        </div>
    </body>
</html>