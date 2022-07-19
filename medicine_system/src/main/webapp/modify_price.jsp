<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page language="java" import="entity.Medicine" %>
<%@ page language="java" import="entity.Medicine" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>
            修改售价    
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
        function ModifyPrice(id) 
        {
            var price = prompt("请输入要修改的价格","");
            if(price)
            {
            	alert("修改成功")
            }
            else
            {
            	alert("修改失败，请输入有效价格")
            }
            var PRICE = price.toString();
            window.location.href="modifyPriceServlet?Price="+price+"&Id="+id;
        }
        </script>
    </head>
    <body>
        <header>

            <h1>修改售价</h1>

        </header>
        <table>
            <tbody>
                <tr id="thead">
                    <td>药品编号</td>
                    <td>药品名称</td>
                    <td>数量</td>
                    <td>当前价格</td>
                    <td>操作</td>
                    
                </tr>
                
               
                <c:forEach var="medicine" items="${medicines}">
                    <tr>                    
                        <td>${medicine.id}</td>
                        <td>${medicine.name }</td>
                        <td>${medicine.num }</td>
                        <td>${medicine.price }</td>
                    	<td><a onclick="ModifyPrice(${medicine.id})" herf="javaScript:void(0)"><button>修改价格</button></a></td>  
     
                    </tr>
                </c:forEach>
                
            </tbody>
        </table>
        <div id="navigate">
                <p>第<span id="pgn">${page.pageIndex}</span>页,共<span id="en">${page.totalPages}</span>页 (每页最多显示${page.pageSize}条)</p><!--Number of entries-->
                <div id="tpbt">
                	<c:if test="${page.pageIndex==1 }">
                    	<button class="gre" id="lastpage">上一页<tton>
                    </c:if>
                	<c:if test="${page.pageIndex>1  }">
                		<a href="${pageContext.request.contextPath}/modifyPriceServlet?pageIndex=${page.pageIndex-1}"><button class="gre" id="lastpage">上一页<tton></a>
                    	
                    </c:if>
                    
                    <c:if test="${page.pageIndex==page.totalPages }">
                    	<button class="red" id="nextpage">下一页<tton>
                    </c:if>
                    
                    <c:if test="${page.pageIndex < page.totalPages }">
                    <a href="${pageContext.request.contextPath}/modifyPriceServlet?pageIndex=${page.pageIndex+1}"><button class="red" id="nextpage">下一页<tton></a>
            		</c:if>
            </div>
        </div>

    </body>


</html>