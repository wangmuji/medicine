
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
    	/**************����Ϊ������*********************/
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
        
         if(page.getTotalPages()<page.getPageIndex()) {	//�ڴ��������δ�����¼����٣����������¼�����һҳ��ֻ��һ��ʱ��
        	page.setPageIndex(page.getTotalPages());	//��AgreeServlet��servlet�����pageIndex������ҳ����������ܷ�����
        }											//��ʱ��pageIndexΪ���ҳ
        
        
        request.setAttribute("applications", applications);
        request.setAttribute("page", page);
        request.getRequestDispatcher("/medicine_apply_unhandled.jsp").forward(request, response);
    }

   
    
}
