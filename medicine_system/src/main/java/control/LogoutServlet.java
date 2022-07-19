package control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Page;
import entity.User;

@WebServlet(name = "logoutServlet" , value="/logoutServlet")
public class LogoutServlet extends HttpServlet {
       


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("logoutServlet......");
		
    	/**************以上为读参区*********************/
    	request.getSession().removeAttribute("user");
    	request.getRequestDispatcher("/login.jsp").forward(request, response);
    	
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
