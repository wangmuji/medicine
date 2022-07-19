
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page language="java" import="entity.Page" %>
<%@ page language="java" import="entity.Medicine" %>
<%@ page language="java" import="entity.User" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>
            药品申请记录(未处理)    
        </title>
        <style>
            body{
                background :#fff;
            }

            table {
                margin: 10px auto;
                width: 90%;
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
                
            }
            header h1 {
                color: #9a9897;
                margin: 15px;
                text-align:center;

            }
            #tpbt{
                text-align:center;
            }
            #handled_btn{
                float:right;
                margin-right:5%;
                width:110px;
                height:35px;
                font-size:19px;
                margin-top:15px;
                border-radius:5px;
            }
            #agree_btn{
            	background:#8ceb8cc4;
            	margin-right:15px;
            	font-size:16px;
            	border-radius:5px;
            }
            #refuse_btn{
            	background:#f72301d1;
            	font-size:16px;
            	border-radius:5px;
            	
            }
            #reverse_btn{
            	
            	font-size:16px;
            	border-radius:5px;
            }

        </style>
    </head>
    <body>
    	<header>
            <a id = "handled_btn" href="${pageContext.request.contextPath}/applyHandledPageServlet?pageIndex=1" target="inner_manage_page">已处理记录</a>
            <h1>未处理申请记录</h1>
            
        </header>
        
        <table>
            <tbody>
                
                
                <c:if test="${user.getAuthority()==1 }"> 
	                <tr id="thead">
	                	<td>申请编号</td>
	                    <td>药品编号</td>
	                    <td>药品名称</td>
	                    <td>数量</td>
	                    <td>申请者</td>
	                    <td>申请时间</td>
	                    <td></td>
	                    <td></td>
	                </tr>
                	
                   
	                <c:forEach var="application" items="${applications}">
	                    <tr>                    
	                        <td>${application.a_id}</td>
	                        <td>${application.id }</td>
	                        <td>${application.name }</td>
	                        <td>${application.num }</td>
	                        <td>${application.account }</td>
	                        <td>${application.a_time }</td>
	                    	<td><a href="${pageContext.request.contextPath}/applyAgreeServlet?pageIndex=${page.pageIndex}&a_id=${application.a_id}"><button id="agree_btn">同意</button></a>
	                    	<a href="${pageContext.request.contextPath}/applyRefuseServlet?pageIndex=${page.pageIndex}&a_id=${application.a_id}"><button id="refuse_btn">拒绝</button></a></td>
	                    </tr>
	                </c:forEach>
                </c:if>
                
                <c:if test="${user.getAuthority()==0 }"> 
                	<tr id="thead">
	                	<td>申请编号</td>
	                    <td>药品编号</td>
	                    <td>药品名称</td>
	                    <td>数量</td>
	                    <td>申请时间</td>
	                    <td></td>
	                   
	                </tr>
                	
                
	                <c:forEach var="application" items="${applications}">
	                    <tr>                    
	                        <td>${application.a_id}</td>
	                        <td>${application.id }</td>
	                        <td>${application.name }</td>
	                        <td>${application.num }</td>
	                        <td>${application.a_time }</td>
	                    	<td><a href="${pageContext.request.contextPath}/applyReverseServlet?pageIndex=${page.pageIndex}&a_id=${application.a_id}"><button id="reverse_btn">撤销</button></a></td>
	                    </tr>
	                </c:forEach>
                </c:if>
                
            </tbody>
        </table>
        <div id="navigate">
                <p>第<span id="pgn">${page.pageIndex}</span>页,共<span id="en">${page.totalPages}</span>页 (每页最多显示${page.pageSize}条)</p><!--Number of entries-->
                <div id="tpbt">
                	<c:if test="${page.pageIndex==1 }">
                    	<button class="gre" id="lastpage">上一页</button>
                    </c:if>
                	<c:if test="${page.pageIndex>1  }">
                		<a href="${pageContext.request.contextPath}/applyUnhandledPageServlet?pageIndex=${page.pageIndex-1}"><button class="gre" id="lastpage">上一页</button></a>
                    	
                    </c:if>
                    
                    <c:if test="${page.pageIndex==page.totalPages }">
                    	<button class="red" id="nextpage">下一页</button>
                    </c:if>
                    
                    <c:if test="${page.pageIndex < page.totalPages }">
                    <a href="${pageContext.request.contextPath}/applyUnhandledPageServlet?pageIndex=${page.pageIndex+1}"><button class="red" id="nextpage">下一页</button></a>
            		</c:if>
            </div>
        </div>

    </body>


</html>