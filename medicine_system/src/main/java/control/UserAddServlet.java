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
    	/**************����Ϊ������*********************/
    	//����û���������û���Ϊ�ֵ��û�������Զ����ɶ�ӦҩƷ����¼�ȱ�
    	System.out.println("username is "+username);
    	System.out.println("account is "+account);
    	System.out.println("passwd is "+passwd);
    	System.out.println("address is "+address);
    	User newuser=new User();
    	String error="���ʧ��";
    	newuser.setAccount(account);
    	newuser.setPassword(passwd);
    	newuser.setUsername(username);
    	newuser.setAddress(address);
		try {
			
				if((MakeUserDao.addUser(newuser))) {
				//���ʧ��;
					response.setContentType("text/html; charset=UTF-8");
					response.getWriter().print("<script language='javascript'> alert('�ɹ�����û�');  window.location.href='userManagePageServlet'</script>");
				}
					
			
			//��Ӻ���ת���û�����ҳ
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
