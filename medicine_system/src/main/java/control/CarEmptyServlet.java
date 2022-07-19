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

@WebServlet(name = "carEmptyServlet" , value="/carEmptyServlet")
public class CarEmptyServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("carEmptyServlet......");
		User user=(User)request.getSession().getAttribute("user");
		
    	/**************以上为读参区*********************/
    	
    	//清空购物车
		SellDao SD=new SellDao();
		try {
			SD.set_account(user.getAccount());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			SD.emptyShopping();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.getRequestDispatcher("/carPageServlet").forward(request, response);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
