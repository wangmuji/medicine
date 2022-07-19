
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
    	/**************����Ϊ������*********************/
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
        
        if(page.getTotalPages()<page.getPageIndex()) {	//�ڴ��������δ�����¼����٣����������¼�����һҳ��ֻ��һ��ʱ��
        	page.setPageIndex(page.getTotalPages());	//��delServlet��servlet�����pageIndex������ҳ����������ܷ�����
        }												//��ʱ��pageIndexΪ���ҳ
        
        
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
