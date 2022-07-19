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

@WebServlet(name = "userAddServlet" , value="/userAddServlet")
public class UserAddServlet extends HttpServlet {
       
    public UserAddServlet() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("userAddServlet......");

		request. setCharacterEncoding("UTF-8");

		User user=(User)request.getSession().getAttribute("user");
		String username= request.getParameter("username");
		String account= request.getParameter("account");
		String passwd= request.getParameter("passwd");
    	String address=request.getParameter("address");
    	/**************以上为读参区*********************/
    	//添加用户，所添加用户必为分店用户，后端自动生成对应药品、记录等表
    	System.out.println("username is "+username);
    	System.out.println("account is "+account);
    	System.out.println("passwd is "+passwd);
    	System.out.println("address is "+address);
    	User newuser=new User();
    	String error="添加失败";
    	newuser.setAccount(account);
    	newuser.setPassword(passwd);
    	newuser.setUsername(username);
    	newuser.setAddress(address);
		try {
			
				if((MakeUserDao.addUser(newuser))) {
				//添加失败;
					response.setContentType("text/html; charset=UTF-8");
					response.getWriter().print("<script language='javascript'> alert('成功添加用户');  window.location.href='userManagePageServlet'</script>");
				}
					
			
			//添加后跳转到用户管理页
			else{
				
				response.setContentType("text/html; charset=UTF-8");
				response.getWriter().print("<script language='javascript'>  window.location.href='add_user.jsp'; alert('"+error+"'); </script>");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
