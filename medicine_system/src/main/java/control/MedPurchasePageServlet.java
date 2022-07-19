
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

import biz.queryInventory;
import entity.Medicine;
import entity.Page;
import entity.User;
import dao.SellDao;

@WebServlet(name = "medPurchasePageServlet" , value="/medPurchasePageServlet")
public class MedPurchasePageServlet extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        this.doGet(req, resp);
    }



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	System.out.println("medPurchasePageServlet......");
    	User user=(User)request.getSession().getAttribute("user");
    	/**************����Ϊ������*********************/

    	queryInventory qI=new queryInventory();
    	
        List<Medicine> medicines=new ArrayList();
		try {
			medicines = qI.operate(user);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//�˴���������ҩƷ���ɣ�����page

        
        
        request.setAttribute("medicines", medicines);
        request.getRequestDispatcher("/medicine_purchase.jsp").forward(request, response);
    }

    
    
}
