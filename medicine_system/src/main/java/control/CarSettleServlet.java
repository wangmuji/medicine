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

@WebServlet(name = "carSettleServlet" , value="/carSettleServlet")
public class CarSettleServlet extends HttpServlet {

	private static final String Intenger = null;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("carSettleServlet......");
		User user=(User)request.getSession().getAttribute("user");
		String pageIndex= request.getParameter("pageIndex");
		String numbers=request.getParameter("num");
		String total=request.getParameter("totalPrice");
		
    	/**************以上为读参区*********************/
		SellDao SD=new SellDao();
		try {
			SD.set_account(user.getAccount());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(SD.Addup()) {//结算成功
			//request.getRequestDispatcher("/carPageServlet?pageIndex="+pageIndex).forward(request, response);
			response.setContentType("text/html; charset=UTF-8");
			response.getWriter().print("<script language='javascript'> alert('结算成功');  window.location.href='carPageServlet'</script>");

		}
		else{
			//失败;
    		response.setContentType("text/html; charset=UTF-8");
			response.getWriter().print("<script language='javascript'> alert('结算失败');  window.location.href='carPageServlet?pageIndex="+pageIndex+"'</script>");

		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
