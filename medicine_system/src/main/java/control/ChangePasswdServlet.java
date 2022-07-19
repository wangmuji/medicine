package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.User;
@WebServlet(name = "changePasswdServlet" , value="/changePasswdServlet")
public class ChangePasswdServlet extends HttpServlet {

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("ChangePasswdServlet......");

		
		User user=(User)request.getSession().getAttribute("user");
		String account =user.getAccount();//由于为登录后状态，所以先设置静态值，后期可更改
		String new_password0= request.getParameter("new_passwd0");
		String new_password1= request.getParameter("new_passwd1");
		String old_password = biz.PassswordChange.GetPassword(account); //获得原始密码
		System.out.println("+++++"+account);
		
		String error0 =" 两次密码输入不相同";	//失败原因，如失败需要判断完赋值后传给前端，可能为：与现有密码相同
		String error1 = "与旧密码一致";
		if(new_password0.equals(new_password1)) //判断是否相等
		{
			if(new_password0.equals(old_password)||new_password1.equals(old_password)) {//判断是否与旧密码相同，可改为User实体类判断
				request.setAttribute("error", error1);//给入失败原因
				request.getRequestDispatcher("/passwd_change_fail.jsp").forward(request, response);
			}
			
			if(biz.PassswordChange.update(account, new_password1)==1)	//判断是否成功修改,需要后端后续修改条件
			{
			//update函数传入用户名和第一次输入的新密码外一层if已判断两次新密码是否相当
				response.sendRedirect(request.getContextPath()+"/passwd_change_succese.jsp");
			}
		}
		else {	//失败
			request.setAttribute("error", error0);//给入失败原因
			request.getRequestDispatcher("/passwd_change_fail.jsp").forward(request, response);
			
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
