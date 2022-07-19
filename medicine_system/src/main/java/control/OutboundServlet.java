
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

import biz.ShowOutBound;
import biz.queryInventory;
import entity.Medicine;
import entity.S_Message;
import entity.Page;
import entity.User;

@WebServlet(name = "OutboundServlet" , value="/OutboundServlet")
public class OutboundServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;



	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        this.doGet(request, response);
    }



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	ShowOutBound sob=new ShowOutBound();
    	User  user = (User)request.getSession().getAttribute("user");
    	//User user=new User();
    	//user.setS_table("s_message0");
    	String pageIndex= request.getParameter("pageIndex");

    	if(pageIndex==null) {
    		 pageIndex="1";
    	}
    	String Name=request.getParameter("Name");
    	String name=null; 
    	if(Name!=null)name=new String(Name);
    	String time1 = request.getParameter("Time1");
    	String time2 = request.getParameter("Time2");
    	List<S_Message> all_message=new ArrayList<S_Message>();
    	if((name==null)&&(time1==null)&&(time2==null)){
    	      try {
				all_message=sob.queryall(user);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	if((name!=null)&&(time1==null)&&(time2==null)){
  	      try {
			all_message=sob.querybyname(user, name);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
  	    }
    	if((name==null)&&(time1!=null)&&(time2!=null)){
    	      try {
				all_message=sob.querybytime(user, time1, time2);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	    
    	}
    	//querybytime
        Page page = new Page(Integer.valueOf(pageIndex),10);	//第一个参数是第几页，第二个参数是页大小
        page.setTotalCounts(all_message.size());		//此处传进totalcounts

        List<S_Message> S_Messages = new ArrayList<S_Message>();		//此处将10条库存信息赋给S_Messages
        for(int i=page.getStartRows();(i<=page.getStartRows()+9)&&(i<all_message.size());i++){
        	S_Messages.add(all_message.get(i));
        }
        request.setAttribute("S_Messages", S_Messages);
        request.setAttribute("page", page);
        request.getRequestDispatcher("/outbound.jsp").forward(request, response);
    }

    
    
}
