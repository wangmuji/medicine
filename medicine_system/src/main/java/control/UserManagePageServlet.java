
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

import dao.DBDao;
import dao.MakeUserDao;
import entity.Application;
import entity.Medicine;
import entity.Page;
import entity.User;

@WebServlet(name = "userManagePageServlet" , value="/userManagePageServlet")
public class UserManagePageServlet extends HttpServlet{
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
    	
        Page page = new Page(Integer.valueOf(pageIndex),10);    
        DBDao DB=new DBDao();
        int count=DB.count("Users");
        DB.closeall();
        page.setTotalCounts(count);
        page.setTotalPages(count/10+1);
        page.getTotalCounts();		//totalcounts
        
        if(page.getTotalPages()<page.getPageIndex()) {	//在处理申请后未处理记录会减少，当所处理记录在最后一页且只有一条时，
        	page.setPageIndex(page.getTotalPages());	//由delServlet等servlet传入的pageIndex大于总页数的情况可能发生，
        }												//此时置pageIndex为最大页
        
        
        List<User> users=new ArrayList();
		try {
			users = MakeUserDao.getUsers(page.getPageIndex()-1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        request.setAttribute("users", users);
        request.setAttribute("page", page);
        request.getRequestDispatcher("/user_manage.jsp").forward(request, response);
    }

   
    
}
