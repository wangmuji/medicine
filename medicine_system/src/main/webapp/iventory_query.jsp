<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page language="java" import="entity.Page" %>
<%@ page language="java" import="entity.Medicine" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>
            库存查询    
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
        <script>
        function NameQuery1(NAME) 
        {
            window.location.href="OutboundServlet?Name="+NAME;
        }
        function NameQuery2(NAME) 
        {
            window.location.href="InboundServlet?Name="+NAME;
        }
        </script>
    </head>
    <body>
        <header>

            <h1>库存查询</h1>

        </header>
        <table>
            <tbody>
                <tr id="thead">
                    <td>药品编号</td>
                    <td>药品名称</td>
                    <td>数量</td>
                    <td>购入信息</td>
                    <td>售出信息</td>
                    
                </tr>
                
                
                <c:forEach var="medicine" items="${medicines}">
                    <tr>                    
                        <td>${medicine.id}</td>
                        <td>${medicine.name }</td>
                        <td>${medicine.num }</td>
                        <td><button onclick="NameQuery2('${medicine.name}')">查看</button></td>
                    	<td><button onclick="NameQuery1('${medicine.name}')">查看</button></td>  
     
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
                		<a href="${pageContext.request.contextPath}/inveQueryPageServlet?pageIndex=${page.pageIndex-1}"><button class="gre" id="lastpage">上一页</button></a>
                    	
                    </c:if>
                    
                    <c:if test="${page.pageIndex==page.totalPages }">
                    	<button class="red" id="nextpage">下一页</button>
                    </c:if>
                    
                    <c:if test="${page.pageIndex < page.totalPages }">
                    	<a href="${pageContext.request.contextPath}/inveQueryPageServlet?pageIndex=${page.pageIndex+1}"><button class="red" id="nextpage">下一页</button></a>
            		</c:if>
            </div>
        </div>

    </body>


</html>