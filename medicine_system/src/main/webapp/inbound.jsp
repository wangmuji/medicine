<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page language="java" import="entity.P_Message" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%-- <%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %> --%>
<html>
    <head>
        <title>
            入库记录    
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
            #query_btn{
                float:right;
                margin-right:5%;
                width:110px;
                height:35px;
                font-size:19px;
                margin-top:15px;
            }
			
        </style>
        <script>
        function NameQuery() 
        {
            var name = prompt("请输入药品的名称","");
            if(name)
            {
            	alert("查询成功")
            }
            else
            {
            	alert("查询失败，请输入有效药品名称")
            }
            var NAME = name.toString();
            window.location.href="InboundServlet?Name="+NAME;
        }
        function TimeQuery() 
        {
        	var value = document.getElementById("year1").value;
        	var strValue = value.toString();
        	var year = strValue;
        	    value = document.getElementById("month1").value;
        	    strValue = value.toString();
        	var month = strValue;
        	    value = document.getElementById("day1").value;
        	    strValue = value.toString();
        	var day = strValue;
        	var judge1=false;
    		if(month<1||month>12) {
    			judge1=false;}
    		else if(month==1||month==3||month==5||month==7||month==8||month==10||month==12) {
    			if(day<=31) {
    				judge1=true;}
    			else {
    				judge1=false;
    			}
    			}
    		else if(month==2) {
    			if(year%400==0||(year%4==0&&year%100!=0)) {
    				if(day<=29) {
    					judge1=true;
    				}
    				else {
    					judge1=false;
    				}
    			}
    			else {
    				if(day<=28){
    					judge1=true;
    				}
    				else {
    					judge1=false;
    				}
    			}
    		}
    		else {
    			if(day<=30){
    				judge1=true;
    			}
    			else {
    				judge1=false;
    			}
    		}
        	var time1;
        	if((month<10)&&(day>=10)){
        		time1=year+"-0"+month+"-"+day;
        	}
        	if((month<10)&&(day<10)){
        		time1=year+"-0"+month+"-0"+day;
        	}
        	if((month>=10)&&(day>=10)){
        		time1=year+"-"+month+"-"+day;
        	}
        	if((month>=10)&&(day<10)){
        		time1=year+"-"+month+"-0"+day;
        	}
        	    value = document.getElementById("year2").value;
        	    strValue = value.toString();
        	    year = strValue;
        	    value = document.getElementById("month2").value;
        	    strValue = value.toString();
        	    month = strValue;
        	    value = document.getElementById("day2").value;
        	    strValue = value.toString();
        	    day = strValue;
        	    var judge2=false;
        		if(month<1||month>12) {
        			judge2=false;}
        		else if(month==1||month==3||month==5||month==7||month==8||month==10||month==12) {
        			if(day<=31) {
        				judge2=true;}
        			else {
        				judge2=false;
        			}
        			}
        		else if(month==2) {
        			if(year%400==0||(year%4==0&&year%100!=0)) {
        				if(day<=29) {
        					judge2=true;
        				}
        				else {
        					judge2=false;
        				}
        			}
        			else {
        				if(day<=28){
        					judge2=true;
        				}
        				else {
        					judge2=false;
        				}
        			}
        		}
        		else {
        			if(day<=30){
        				judge2=true;
        			}
        			else {
        				judge2=false;
        			}
        		}

        	var time2;
        	
        	if((month<10)&&(day>=10)){
        		time2=year+"-0"+month+"-"+day;
        	}
        	if((month<10)&&(day<10)){
        		time2=year+"-0"+month+"-0"+day;
        	}
        	if((month>=10)&&(day>=10)){
        		time2=year+"-"+month+"-"+day;
        	}
        	if((month>=10)&&(day<10)){
        		time2=year+"-"+month+"-0"+day;
        	}
        	if(judge1&&judge2)
        		{
	        		value = document.getElementById("hour1").value;
	        	    strValue = value.toString();
	        	    if(value<10)
	        	    	strValue="0"+strValue;
	        	    time1 = time1+" "+strValue;
	        	    value = document.getElementById("minute1").value;
	        	    strValue = value.toString();
	        	    if(value<10)
	        	    	strValue="0"+strValue;
	        	    time1 = time1+":"+strValue;
	        	    value = document.getElementById("second1").value;
	        	    strValue = value.toString();
	        	    if(value<10)
	        	    	strValue="0"+strValue;
	        		time1 = time1+":"+strValue;
	        		value = document.getElementById("hour2").value;
	        	    strValue = value.toString();
	        	    if(value<10)
	        	    	strValue="0"+strValue;
	        	    time2 = time2+" "+strValue;
	        	    value = document.getElementById("minute2").value;
	        	    strValue = value.toString();
	        	    if(value<10)
	        	    	strValue="0"+strValue;
	        	    time2 = time2+":"+strValue;
	        	    value = document.getElementById("second2").value;
	        	    strValue = value.toString();
	        	    if(value<10)
	        	    	strValue="0"+strValue;
	        		time2 = time2+":"+strValue;
	        		
	        		window.location.href="InboundServlet?Time1="+time1+"&Time2="+time2;
	        		
        		}
        	else{
        		alert("日期不合法，请检查修改")
        	}
        }
        function DetailedInfor_P(p_id,id,p_time,name,p_num,p_total,p_reason,purchase,account) 
        {
        	alert("OK")
            window.location.href="DetailedInforPServlet?P_ID="+p_id+"&ID="+id+"&P_time="+p_time+"&Name="+name+"&P_Num="+p_num+"&P_Total="+p_total+"&P_Reason="+p_reason+"&Purchase="+purchase+"&Account="+account;
        }
        </script>
    </head>
    <body>
        <header>
        	<button id="query_btn" onclick="NameQuery()" herf="javaScript:void(0)">名称查询</button>
       		<button id="query_btn" onclick="TimeQuery()" herf="javaScript:void(0)">时间查询</button>
            <h1>入库记录</h1>
            <tr >
        		<td>
							<select id = "year1">
                                <%
                                    for(int i=2000;i<=2050;i++)
                                    {
                                        out.println("<option value='"+i+"'>"+i+"</option>");
                                    }
                                    %>
                            </select>
				</td>
        		<td>年</td>
        		<td>
							<select id = "month1">
                                <%
                                    for(int i=1;i<=12;i++)
                                    {
                                        out.println("<option value='"+i+"'>"+i+"</option>");
                                    }
                                %>
                            </select>
				</td>
        		<td>月</td>
        		<td>
							<select id = "day1">
                                <%
                                    for(int i=1;i<=31;i++)
                                    {
                                        out.println("<option value='"+i+"'>"+i+"</option>");
                                    }
                                %>
                            </select>
				</td>
        		<td>日</td>
        		<td>
        					<select id = "hour1">
                                <%
                                    for(int i=0;i<=23;i++)
                                    {
                                        out.println("<option value='"+i+"'>"+i+"</option>");
                                    }
                                %>
                            </select>
        		</td>
        		<td>:</td>
        		<td>
        					<select id = "minute1">
                                <%
                                    for(int i=0;i<=59;i++)
                                    {
                                        out.println("<option value='"+i+"'>"+i+"</option>");
                                    }
                                %>
                            </select>
				</td>
        		<td>:</td>
        		<td>
							<select id = "second1">
                                <%
                                    for(int i=0;i<=59;i++)
                                    {
                                        out.println("<option value='"+i+"'>"+i+"</option>");
                                    }
                                %>
                            </select>
				</td>
        		<td>-</td>
        		<td>
							<select id = "year2">
                                <%
                                    for(int i=2000;i<=2050;i++)
                                    {
                                        out.println("<option value='"+i+"'>"+i+"</option>");
                                    }
                                %>
                            </select>
				</td>
        		<td>年</td>
        		<td>
							<select id = "month2">
                                <%
                                    for(int i=1;i<=12;i++)
                                    {
                                        out.println("<option value='"+i+"'>"+i+"</option>");
                                    }
                                %>
                            </select>
				</td>
        		<td>月</td>
        		<td>
							<select id = "day2">
                                <%
                                    for(int i=1;i<=31;i++)
                                    {
                                        out.println("<option value='"+i+"'>"+i+"</option>");
                                    }
                                %>
                            </select>
				</td>
        		<td>日</td>
        		<td>
        					<select id = "hour2">
                                <%
                                    for(int i=0;i<=23;i++)
                                    {
                                        out.println("<option value='"+i+"'>"+i+"</option>");
                                    }
                                %>
                            </select>
        		</td>
        		<td>:</td>
        		<td>
        					<select id = "minute2">
                                <%
                                    for(int i=0;i<=59;i++)
                                    {
                                        out.println("<option value='"+i+"'>"+i+"</option>");
                                    }
                                %>
                            </select>
				</td>
        		<td>:</td>
        		<td>
							<select id = "second2">
                                <%
                                    for(int i=0;i<=59;i++)
                                    {
                                        out.println("<option value='"+i+"'>"+i+"</option>");
                                    }
                                %>
                            </select>
				</td>
        	</tr>
        	
        </header>
        <table>
            <tbody>
                <tr id="thead">
                    <td>记录编号</td>
                    <td>药品名称</td>
                    <td>数量</td>
                    <td>入库时间</td>
                    <td>详细信息</td>
                    
                </tr>
                
                
                <c:forEach var="P_message" items="${P_Messages}">
                    <tr>                    
                        <td>${P_message.p_id}</td>
                        <td>${P_message.name }</td>
                        <td>${P_message.p_num }</td>
                        <td>${P_message.p_time }</td>
                        <td><button onclick="DetailedInfor_P('${P_message.p_id}','${P_message.id}','${P_message.p_time}','${P_message.name}','${P_message.p_num}','${P_message.p_total}','${P_message.p_reason}','${P_message.purchase}','${P_message.account}')" herf="javaScript:void(0)">查看</button></td>  
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
                		<a href="${pageContext.request.contextPath}/inboundServlet?pageIndex=${page.pageIndex-1}"><button class="gre" id="lastpage">上一页</button></a>
                    	
                    </c:if>
                    
                    <c:if test="${page.pageIndex==page.totalPages }">
                    	<button class="red" id="nextpage">下一页</button>
                    </c:if>
                    
                    <c:if test="${page.pageIndex < page.totalPages }">
                    	<a href="${pageContext.request.contextPath}/inboundServlet?pageIndex=${page.pageIndex+1}"><button class="red" id="nextpage">下一页</button></a>
            		</c:if>
            </div>
        </div>

    </body>


</html>