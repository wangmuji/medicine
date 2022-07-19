
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
    	/**************����Ϊ������*********************/
    	if(pageIndex==null) {
    		 pageIndex="1";
    	}	
    	 MedDistributionDao md=new MedDistributionDao();
         List<User> users = md.showUsers();
         
        Page page = new Page(Integer.valueOf(pageIndex),10);       
        page.setTotalCounts(users.size());		//totalcounts
        
        if(page.getTotalPages()<page.getPageIndex()) {	//�ڴ��������δ�����¼����٣����������¼�����һҳ��ֻ��һ��ʱ��
        	page.setPageIndex(page.getTotalPages());	//��AgreeServlet��servlet�����pageIndex������ҳ����������ܷ�����
        }												//��ʱ��pageIndexΪ���ҳ
        
        request.setAttribute("users", users);
        request.setAttribute("page", page);
        request.getRequestDispatcher("/medicine_distr.jsp").forward(request, response);
    }

   
    
}
