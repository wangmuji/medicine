
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

@WebServlet(name = "applyUnhandledPageServlet" , value="/applyUnhandledPageServlet")
public class ApplyUnhandledPageServlet extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        this.doGet(req, resp);
    }



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	System.out.println("applyUnhandledPageServlet......");
    	String pageIndex= request.getParameter("pageIndex");
    	User user=(User)request.getSession().getAttribute("user");
    	/*User user=new User();
    	user.setAccount("user0");
    	user.setAuthority(1);
    	user.setM_table("medicine0");
    	user.setP_table("p_message0");
    	user.setS_table("s_message0");
    	request.getSession().setAttribute("user",user);
    	*/
    	/**************以上为读参区*********************/
    	if(pageIndex==null) {
    		 pageIndex="1";
    	}
    	
        Page page = new Page(Integer.valueOf(pageIndex),10);
        ApplyDao ad=new ApplyDao();
        List<Application> applications=new ArrayList<Application>();
		try {
			applications = ad.un_handle(user);
		} catch (SQLException e) {
			e.printStackTrace();
		}
        page.setTotalCounts(applications.size());		//totalcounts
        
         if(page.getTotalPages()<page.getPageIndex()) {	//在处理申请后未处理记录会减少，当所处理记录在最后一页且只有一条时，
        	page.setPageIndex(page.getTotalPages());	//由AgreeServlet等servlet传入的pageIndex大于总页数的情况可能发生，
        }											//此时置pageIndex为最大页
        
        
        request.setAttribute("applications", applications);
        request.setAttribute("page", page);
        request.getRequestDispatcher("/medicine_apply_unhandled.jsp").forward(request, response);
    }

   
    
}
