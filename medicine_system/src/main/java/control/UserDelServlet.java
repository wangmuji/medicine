package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MakeUserDao;
import entity.Page;
import entity.User;

@WebServlet(name = "userDelServlet" , value="/userDelServlet")
public class UserDelServlet extends HttpServlet {
       
    public UserDelServlet() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("userDelServlet......");
		User user=(User)request.getSession().getAttribute("user");
		String pageIndex= request.getParameter("pageIndex");
    	String id=request.getParameter("account");
    	/**************以上为读参区*********************/
    	
    	//后端根据account和id删除数据库中某条数据
		try {
			if(!MakeUserDao.deleteUser(id)) {
				//删除失败;
				response.setContentType("text/html; charset=UTF-8");
				response.getWriter().print("<script language='javascript'>   window.location.href='userManagePageServlet?pageIndex="+pageIndex+"';alert('删除失败');</script>");

			}
			
			
			
			//删除后跳转到删除项原来所在页
			else{
				request.getRequestDispatcher("/userManagePageServlet?pageIndex="+pageIndex).forward(request, response);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
