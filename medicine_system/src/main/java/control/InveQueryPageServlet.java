
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

@WebServlet(name = "inveQueryPageServlet" , value="/inveQueryPageServlet")
public class InveQueryPageServlet extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        this.doGet(request, response);
    }



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	queryInventory qI=new queryInventory();
    	//User  user = (User)request.getSession().getAttribute("user");
    	User user=(User)request.getSession().getAttribute("user");
    	
    	String pageIndex= request.getParameter("pageIndex");

    	if(pageIndex==null) {
    		 pageIndex="1";
    	}
        Page page = new Page(Integer.valueOf(pageIndex),10);	//第一个参数是第几页，第二个参数是页大小
        List<Medicine> all_medicines = new ArrayList();
		try {
			all_medicines = qI.operate(user);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        page.setTotalCounts(all_medicines.size());		//此处传进totalcounts
        List<Medicine> medicines =new ArrayList<Medicine>();		//此处将10条库存信息赋给medicines

        for(int i=page.getStartRows();(i<=page.getStartRows()+9)&&(i<all_medicines.size());i++){
        	Medicine m=null;
        	m=(Medicine)all_medicines.get(i);
        	medicines.add(m);
        }
        request.setAttribute("medicines", medicines);
        request.setAttribute("page", page);
        request.getRequestDispatcher("/iventory_query.jsp").forward(request, response);
    }

    
    
}
