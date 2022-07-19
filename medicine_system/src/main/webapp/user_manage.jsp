
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page language="java" import="entity.Page" %>
<%@ page language="java" import="entity.Medicine" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%-- <%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %> --%>
<html>
    <head>
        <title>
            分店管理    
        </title>
        

        <style>
            body{
                background :#fff;
            }

            table {
                margin: 10px auto;
                width: 70%;
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
                width:70%;
            }
            header h1 {
                color: #9a9897;
                /* margin: 15px; */
                margin-bottom:10px;
                margin-left:45%;
                /* text-align:center; */

            }
            #tpbt{
                text-align:center;
            }
            #pay_btn{
                float:right;
                margin-right:5%;
                width:70px;
                height:35px;
                font-size:19px;
                margin-top:15px;
                border-radius:5px;
            }
            #selected_medicine_btn{
                float:right;
                margin-right:5%;
                width:110px;
                height:35px;
                font-size:19px;
                margin-top:15px;
                border-radius:5px;
            }
            #add_user_btn{
            	float:right;
                /* margin-right:5%; */
                width:70px;
                height:35px;
                font-size:19px;
                margin-top:15px;
                border-radius:5px;
            }
            
        </style>
    </head>
    
    <body>
        
        <header>
        	<a href="${pageContext.request.contextPath}/add_user.jsp" target="inner_manage_page"><button id = "add_user_btn">新增</button></a>
            <h1>分店管理</h1>            
        </header>
        <table>
            <tbody>
                <tr id="thead">                  
                    <td>用户名称</td>
                    <td>账号</td>
                    <td>店铺地址</td>                   
                    <td></td>
                                                         
                </tr>    
                <c:forEach var="branchManager" items="${users}">
                <tr>
                    <td>${branchManager.username}</td>
                    <td>${branchManager.account}</td>
                    <td>${branchManager.address}</td>                   
                    <td><a href="userDelServlet?account=${branchManager.account }&pageIndex=${page.pageIndex}"><button>删除</button></a></td>
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
                		<a href="${pageContext.request.contextPath}/userManagePageServlet?pageIndex=${page.pageIndex-1}"><button class="gre" id="lastpage">上一页</button></a>
                    	
                    </c:if>
                    
                    <c:if test="${page.pageIndex==page.totalPages }">
                    	<button class="red" id="nextpage">下一页</button>
                    </c:if>
                    
                    <c:if test="${page.pageIndex < page.totalPages }">
                    <a href="${pageContext.request.contextPath}/userManagePageServlet?pageIndex=${page.pageIndex+1}"><button class="red" id="nextpage">下一页</button></a>
            		</c:if>
            </div>
        </div>

    </body>


</html>