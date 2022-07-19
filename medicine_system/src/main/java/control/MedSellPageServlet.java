
package control;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.SellDao;
import entity.Medicine;
import entity.Page;
import entity.Shopping;
import entity.User;

@WebServlet(name = "medSellPageServlet" , value="/medSellPageServlet")
public class MedSellPageServlet extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        this.doGet(req, resp);
    }



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String pageIndex= request.getParameter("pageIndex");
    	User user=(User)request.getSession().getAttribute("user");
    	/**************以上为读参区*********************/

    	System.out.println("Account is : "+user.getAccount());
    	if(pageIndex==null) {
    		 pageIndex="1";
    	}
    	SellDao SD=new SellDao();
    	try {
			SD.set_account(user.getAccount());
			System.out.println("嗨嗨嗨嗨嗨嗨");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        Page page = new Page(Integer.valueOf(pageIndex),10);
        page.setTotalCounts(13);
        List<Medicine> medicines = new ArrayList<Medicine>();
		try {
			medicines = SD.getMedicine(page.getPageIndex()-1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        request.setAttribute("medicines", medicines);
        request.setAttribute("page", page);
        request.getRequestDispatcher("/medicine_sell.jsp").forward(request, response);
    }

    
    
}
