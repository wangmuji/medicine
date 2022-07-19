<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <meta charset="UTF-8">
    <style>
    	html{
    		height:100%;
    	}
        body {
        	height:100%;
            margin:0;
            background-color: beige;
        }

        .contain {
            height: 100%;
            width: 100%;
            background-color: aqua;
            /* position: relative; */

        }

        .contain .top_information {
            background-color: rgb(152, 152, 244);
            /* position: fixed; */
            float: right;
            width: 100%;
            height: 15%;
            z-index: 300;
        }

        .contain .left_nav {
            z-index: 1;
            float:left;
            /* width: 260px; */
            width: 15%;
            height: 85%;
            top: 108px;
            background-color: rgb(179, 170, 170);
        }

        .menu_list {
            padding-left: 20px;
        }

        .menu_list li {
            padding-top: 5px;
            font-size: 20px;
            list-style-type: none;
           
            
        }

        .menu_list li a {
            text-decoration: none;
            color: #3e3e3e;
        }

        .contain .manage_page {
            background-color: blueviolet;
            /* margin-top: 100px; */
            /* margin-left: 260px; */
            float: left;
            /* position: fixed; */
            /* width: 100%; */
            width: 85%;
            height: 85%;
        }
        #inner_manage_page{
            float: left;
            width: 100%;
            height: 100%;
            border:0;
        }
    </style>
    
</head>

<body>
    <div class="contain">
        <div class="top_information">
            <div class="welcome_sentence">
                <span style="font-size: 25px;float: left;padding-top: 2%;padding-left: 25px;">欢迎使用药店管理系统（后台）</span>
                <span style="font-size: 20px;padding-top:5%;float:right;padding-right: 20px;">当前用户为分店管理员：${user.username}</span>
            </div>
        </div>
        <div class="left_nav">
            <ul class="menu_list">
                <li>
                    <a href="${pageContext.request.contextPath}/medApplyPageServlet" target="inner_manage_page">药品申请</a>
                </li>               
                <li>
                    <a href="${pageContext.request.contextPath}/inveQueryPageServlet" target="inner_manage_page">库存查询</a>
                </li>                
                <li>
                    <a href="${pageContext.request.contextPath}/applyUnhandledPageServlet" target="inner_manage_page">药品申请记录</a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/OutboundServlet" target="inner_manage_page">出库记录</a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/InboundServlet" target="inner_manage_page">入库记录</a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/modifyPriceServlet" target="inner_manage_page">修改售价</a><br/>                    
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/passwd_change.jsp" target="inner_manage_page">修改密码</a><br/>                    
                </li>
				
                <li>
                    <a href="${pageContext.request.contextPath}/logoutServlet" >退出登录</a><br/>  
				</li>
            </ul>
        </div>
        <div class="manage_page">       
            <iframe name="inner_manage_page" id="inner_manage_page" src="${pageContext.request.contextPath}/inner_index.jsp">
            
            </iframe>
            
        </div>

    </div>
</body>

</html>