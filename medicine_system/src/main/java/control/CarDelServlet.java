package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.SellDao;
import entity.Page;
import entity.User;

@WebServlet(name = "carDelServlet" , value="/carDelServlet")
public class CarDelServlet extends HttpServlet {
       
    public CarDelServlet() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("carDelServlet......");
		User user=(User)request.getSession().getAttribute("user");
		String pageIndex= request.getParameter("pageIndex");
    	String id=request.getParameter("id");
    	/**************以上为读参区*********************/
    	SellDao SD = new SellDao();
    	try {
			SD.set_account(user.getAccount());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	int ID=Integer.parseInt(id);
    	
    	//后端根据account和id删除数据库中某条数据
		try {
			if(!SD.deleteShopping(ID,user.getAccount())) {
				//删除失败;
				response.setContentType("text/html; charset=UTF-8");
				response.getWriter().print("<script language='javascript'> alert('删除失败');  window.location.href='carPageServlet?pageIndex="+pageIndex+"'</script>");

			}
			//删除后跳转到删除项原来所在页
			else{
				request.getRequestDispatcher("/carPageServlet?pageIndex="+pageIndex).forward(request, response);
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
