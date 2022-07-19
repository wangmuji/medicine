
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

import dao.MedDistributionDao;
import entity.Application;
import entity.Medicine;
import entity.Page;
import entity.User;

@WebServlet(name = "medDistrCarServlet" , value="/medDistrCarServlet")
public class MedDistrCarServlet extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        this.doGet(req, resp);
    }



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	System.out.println("userManagePageServlet......");
    	String pageIndex= request.getParameter("pageIndex");
    	/**************以上为读参区*********************/
    	if(pageIndex==null) {
    		 pageIndex="1";
    	}	
    	 MedDistributionDao md=new MedDistributionDao();
         List<User> users = md.showUsers();
         
        Page page = new Page(Integer.valueOf(pageIndex),10);       
        page.setTotalCounts(users.size());		//totalcounts
        
        if(page.getTotalPages()<page.getPageIndex()) {	//在处理申请后未处理记录会减少，当所处理记录在最后一页且只有一条时，
        	page.setPageIndex(page.getTotalPages());	//由AgreeServlet等servlet传入的pageIndex大于总页数的情况可能发生，
        }												//此时置pageIndex为最大页
        
        request.setAttribute("users", users);
        request.setAttribute("page", page);
        request.getRequestDispatcher("/medicine_distr.jsp").forward(request, response);
    }

   
    
}
