
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

import dao.ApplyDao;
import entity.Application;
import entity.Medicine;
import entity.Page;
import entity.User;

@WebServlet(name = "applyHandledPageServlet" , value="/applyHandledPageServlet")
public class ApplyHandledPageServlet extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        this.doGet(req, resp);
    }



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	System.out.println("applyHandledPageServlet......");
    	String pageIndex= request.getParameter("pageIndex");
    	User user=(User)request.getSession().getAttribute("user");

    	if(pageIndex==null) {
    		 pageIndex="1";
    	}
    	
        Page page = new Page(Integer.valueOf(pageIndex),10);
        ApplyDao ad=new ApplyDao();
        List<Application> applications=new ArrayList<Application>();
		try {
			applications = ad.handle(user);
		} catch (SQLException e) {
			e.printStackTrace();
		}
        
        page.setTotalCounts(applications.size());		//totalcounts

        
        request.setAttribute("applications", applications);
        request.setAttribute("page", page);
        request.getRequestDispatcher("/medicine_apply_handled.jsp").forward(request, response);
    }

   
    
}
