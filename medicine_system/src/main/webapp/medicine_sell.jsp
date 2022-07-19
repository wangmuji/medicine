
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
           	 药品销售  
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
            .add_btn{
                background:#fff;
            }
            
        </style>
    </head>
    <script type="text/javascript">
            function add_medicine(id,path,pageIndex)    //id为药品编号
            {
                var num = document.getElementById(id).value;
                if(num==0){
                	alert("数量不能为0");
                	return false;
                }
                //alert(num+"+"+path+"+"+account);
                window.location.href=path+"/carAddServlet?id="+id+"&num="+num+"&pageIndex="+pageIndex;
            }
        </script>
    <body>
        
        
        <header>
            <a id = "selected_medicine_btn" href="${pageContext.request.contextPath}/carPageServlet?pageIndex=1" target="inner_manage_page">已选药品</a>
            <h1>药品销售</h1>
            
        </header>
        <table>
            <tbody>
                <tr id="thead">
                    
                    <td>药品编号</td>
                    <td>药品名称</td>
                    <td>库存数量</td>
                    <td>单价</td>
                    <td>添加个数</td>
                    <td></td>
  
                </tr>
                <%
                	int a=0;
                	List<Medicine> ms = (List<Medicine>) request.getAttribute("medicines");
                	List<Integer> n = new ArrayList();
                	for(Medicine m : ms){
                		n.add(m.getNum());
                	}
                	
                	
                %>
                <c:forEach var="medicine" items="${medicines}">
                    <tr>                    
                        <td>${medicine.id}</td>
                        <td>${medicine.name }</td>
                        <td>${medicine.num }</td>
                        <td>${medicine.price }</td>
                        <td>
                        
                            <select id='${medicine.id}'>
                                <%
                                	
                                	
                                    for(int i=0;i<=n.get(a);i++)
                                    {
                                        out.println("<option value='"+i+"'>"+i+"</option>");
                                    }
                                    a++;
                                %>
                                
                            </select>
                        </td>
                        <td><button class="add_btn" onclick="add_medicine('${medicine.id}','${pageContext.request.contextPath}',${page.pageIndex } )">添加</button></td>

                    </tr>
                </c:forEach>

                
                <%-- <c:choose></c:choose> --%>
            </tbody>
        </table>
        <div id="navigate">
                <p>第<span id="pgn">${page.pageIndex}</span>页,共<span id="en">${page.totalPages}</span>页 (每页最多显示${page.pageSize}条)</p><!--Number of entries-->
                <div id="tpbt">
                	<c:if test="${page.pageIndex==1 }">
                    	<button class="gre" id="lastpage">上一页</button>
                    </c:if>
                	<c:if test="${page.pageIndex>1  }">
                		<a href="${pageContext.request.contextPath}/medSellPageServlet?pageIndex=${page.pageIndex-1}"><button class="gre" id="lastpage">上一页</button></a>
                    	
                    </c:if>
                    
                    <c:if test="${page.pageIndex==page.totalPages }">
                    	<button class="red" id="nextpage">下一页</button>
                    </c:if>
                    
                    <c:if test="${page.pageIndex < page.totalPages }">
                    <a href="${pageContext.request.contextPath}/medSellPageServlet?pageIndex=${page.pageIndex+1}"><button class="red" id="nextpage">下一页</button></a>
            		</c:if>
            </div>
        </div>

    </body>


</html>