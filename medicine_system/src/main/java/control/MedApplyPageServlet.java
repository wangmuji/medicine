
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

import biz.MedApplyBiz;
import entity.Application;
import entity.Medicine;
import entity.Page;
import entity.User;

@WebServlet(name = "medApplyPageServlet" , value="/medApplyPageServlet")
public class MedApplyPageServlet extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        this.doGet(req, resp);
    }



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	System.out.println("medApplyPageServlet......");
    	String pageIndex= request.getParameter("pageIndex");	
    	User user=(User)request.getSession().getAttribute("user");    	
    	String	account=user.getAccount();
    	
    	if(pageIndex==null) {
    		 pageIndex="1";
    	}
    	
        Page page = new Page(Integer.valueOf(pageIndex),10);
       
        List<Medicine> all_medicine=new ArrayList<Medicine>();
        List<Medicine> medicines=new ArrayList<Medicine>();
        try {
			all_medicine = MedApplyBiz.all_medicine(account);//传入用户名
			
			//System.out.println(all_medicine.get(1));
		} catch (SQLException e) {
			e.printStackTrace();
		}
        page.setTotalCounts(all_medicine.size());		//totalcounts
        for(int i=page.getStartRows();(i<=page.getStartRows()+9)&&(i<all_medicine.size());i++){
        	medicines.add(all_medicine.get(i));
        	}
        
        
        
        request.setAttribute("medicines", medicines);
        request.setAttribute("page", page);
        request.getRequestDispatcher("/medicine_apply.jsp").forward(request, response);
    }

   
    
}
