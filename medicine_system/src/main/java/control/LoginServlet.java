package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import biz.LoginBiz;
import entity.User;

@WebServlet(name = "loginServlet" , value="/loginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginServlet() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("loginServlet......");
		String account =request.getParameter("account");
		String password= request.getParameter("password");
		String type= request.getParameter("type");	//type决定了登录前台还是后台
		System.out.println("type is :"+type);
    	/**************以上为读参区*********************/

//		System.out.println(account+"++++++");
		User user= null;
		User user1 =null;
		
		LoginBiz loginbiz = new LoginBiz();
		try {
			user = loginbiz.find(account, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		if(user !=null)
		{
			if(user.getAuthority()==1)
			{
				request.getSession().setAttribute("user", user);
				if(type.equals("front")) {	//前台front
					request.getRequestDispatcher("/main_manage_front.jsp").forward(request, response);
				}else {	//后台back
					request.getRequestDispatcher("/main_manage_back.jsp").forward(request, response);
				}
			}
			else
			{
				request.getSession().setAttribute("user", user);
				if(type.equals("front")) {
					request.getRequestDispatcher("/branch_manage_front.jsp").forward(request, response);
				}else {
					request.getRequestDispatcher("/branch_manage_back.jsp").forward(request, response);
				}
				
			}
		}
		else {
			request.setAttribute("error","loginError");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			//response.sendRedirect(request.getContextPath()+"/login.jsp");
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
