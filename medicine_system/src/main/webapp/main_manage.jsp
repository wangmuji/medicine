<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <meta charset="UTF-8">
    <style>
        body {
            margin:0;
            background-color: beige;
        }

        .contain {
            height: 720px;
            width: 100%;
            background-color: aqua;
            /* position: relative; */

        }

        .contain .top_information {
            background-color: rgb(152, 152, 244);
            /* position: fixed; */
            float: right;
            width: 100%;
            height: 100px;
            z-index: 300;
        }

        .contain .left_nav {
            z-index: 1;
            float:left;
            /* width: 260px; */
            width: 15%;
            height: 620px;
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
            height: 620px;
        }
        #inner_manage_page{
            float: left;
            width: 100%;
            height: 620px;
            border:0;
        }
    </style>
    
</head>

<body>
    <%
        String abc = new String("/medicine_system/inner_index.jsp");
        request.setAttribute("abc",abc);
    %>
    <div class="contain">
        <div class="top_information">
            <div class="welcome_sentence">
                <span style="font-size: 25px;line-height: 100px;padding-left: 25px;">欢迎使用药店管理系统</span>
                <span style="font-size: 20px;line-height: 150px;float:right;padding-right: 20px;">当前用户为：${user.username}</span>
            </div>
        </div>
        <div class="left_nav">
            <ul class="menu_list">
                <!--
                    点击后动态改变右边的页面  看哔哩哔哩
                -->
                <!-- 在login处放一个session范围的信息 :账号、密码、使得修改密码时可以进行读取-->
                <li>
                    <a href="${pageContext.request.contextPath}/medSellPageServlet?Account=111111" target="inner_manage_page" >药品销售</a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/medicine_distribute.jsp" target="inner_manage_page">药品分发</a>
                </li>
                
                <li>
                    <a href="${pageContext.request.contextPath}/medicine_purchase.jsp" target="inner_manage_page">购入药品</a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/inveQueryPageServlet" target="inner_manage_page">库存查询</a>
                </li>
                
                <li>
                    <a href="${pageContext.request.contextPath}/medicine_apply_record.jsp" target="inner_manage_page">药品申请记录</a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/chuku.jsp" target="inner_manage_page">出库记录</a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/ruku.jsp" target="inner_manage_page">入库记录</a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/changePasswdServlet" target="inner_manage_page">修改密码</a><br/>                    
                </li>

            </ul>
        </div>
        <div class="manage_page">
        
            <iframe name="inner_manage_page" id="inner_manage_page" src="${abc}">
            
            </iframe>
            
        </div>

    </div>
</body>

</html>