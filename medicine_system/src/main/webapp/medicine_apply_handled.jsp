
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
            药品申请记录（已处理）  
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

        </style>
    </head>
    <body>

        <header>

            <h1>已处理药品申请记录</h1>

        </header>
        <table>
            <tbody>
                <tr id="thead">
                	<td>申请编号</td>
                    <td>药品编号</td>
                    <td>药品名称</td>
                    <td>数量</td>
                    <td>申请者</td>
                    <td>申请时间</td>                  
                    <td>处理时间</td>
                    <td>处理状态</td>
                    
                </tr>
                
                
                    
                <c:forEach var="application" items="${applications}">
                    <tr>                    
                        <td>${application.a_id}</td>
                        <td>${application.id }</td>
                        <td>${application.name }</td>
                        <td>${application.num }</td>
                        <td>${application.account }</td>
                        <td>${application.a_time }</td>
                    	<td>${application.h_time }</td>
                    	<c:if test="${ application.state==1}">
                    	<td>已同意</td>
                    	</c:if>
                    	<c:if test="${ application.state==2}">
                    	<td>已拒绝</td>
                    	</c:if>
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
                		<a href="${pageContext.request.contextPath}/applyHandledPageServlet?pageIndex=${page.pageIndex-1}"><button class="gre" id="lastpage">上一页</button></a>
                    	
                    </c:if>
                    
                    <c:if test="${page.pageIndex==page.totalPages }">
                    	<button class="red" id="nextpage">下一页</button>
                    </c:if>
                    
                    <c:if test="${page.pageIndex < page.totalPages }">
                    <a href="${pageContext.request.contextPath}/applyHandledPageServlet?pageIndex=${page.pageIndex+1}"><button class="red" id="nextpage">下一页</button></a>
            		</c:if>
            </div>
        </div>

    </body>


</html>