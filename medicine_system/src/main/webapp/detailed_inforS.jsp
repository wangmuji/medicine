
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page language="java" import="entity.S_Message" %>
<%@ page language="java" import="entity.S_Message" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>
            详细信息    
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

        </style>
    </head>
    <body>
        <header>
            <h1>详细信息</h1>
            
        </header>
        <div id="container">
            <table>
                <tbody>
                
                    <tr id="thead">
                        
                        <td>项目</td>
                        <td>信息</td>
                        
                        
                    </tr>


                    <tr >                    
                    	<td id ="thead">信息单号</td>
                        <td>${message.s_id}</td>
                    </tr>
                    <tr >                    
                    	<td id ="thead">药品编号</td>
                        <td>${message.id}</td>
                    </tr>
                    <tr >                    
                    	<td id ="thead">药品名称</td>
                        <td>${message.name}</td>
                    </tr>
                    <tr >                    
                    	<td id ="thead">出库时间</td>
                        <td>${message.s_time}</td>
                    </tr>
                    <tr >                    
                    	<td id ="thead">出库数量</td>
                        <td>${message.s_num}</td>
                    </tr>
                    <tr >                    
                    	<td id ="thead">出库单价</td>
                        <td>${message.price}</td>
                    </tr>
                    <tr >                    
                    	<td id ="thead">出库总价</td>
                        <td>${message.price*message.s_num}</td>
                    </tr>
                    <tr >                    
                    	<td id ="thead">售出原因</td>
                        <td>${message.s_reason}</td>
                    </tr>
                    <tr >                    
                    	<td id ="thead">售出账户</td>
                        <td>${message.account}</td>
                    </tr>
                       
                </tbody>
            </table>
    </div>
    </body>
</html>