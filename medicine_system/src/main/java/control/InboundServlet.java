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

import biz.ShowInBound;
import entity.User;
import entity.P_Message;
import entity.Page;

@WebServlet(name = "InboundServlet" , value="/InboundServlet")
public class InboundServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;



	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        this.doGet(request, response);
    }



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	ShowInBound sib=new ShowInBound();
    	User  user = (User)request.getSession().getAttribute("user");
    	/*User user=new User();
    	user.setAccount("user1");
    	user.setP_table("p_message1");
    	*/String pageIndex= request.getParameter("pageIndex");

    	if(pageIndex==null) {
    		 pageIndex="1";
    	}
    	String Name=request.getParameter("Name");
    	String name=null; 
    	if(Name!=null)name=new String(Name);
    	String time1 = request.getParameter("Time1");
    	String time2 = request.getParameter("Time2");
    	List<P_Message> all_message=new ArrayList<P_Message>();
    	if((name==null)&&(time1==null)&&(time2==null)){
    	      try {
				all_message=sib.queryall(user);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	if((name!=null)&&(time1==null)&&(time2==null)){
  	      try {
			all_message=sib.querybyname(user, name);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
  	    }
    	if((name==null)&&(time1!=null)&&(time2!=null)){
    	    try {
				all_message=sib.querybytime(user, time1, time2);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	    
    	}
    	
        Page page = new Page(Integer.valueOf(pageIndex),10);	//第一个参数是第几页，第二个参数是页大小
        page.setTotalCounts(all_message.size());		//此处传进totalcounts

        List<P_Message> P_Messages = new ArrayList<P_Message>();		
        for(int i=page.getStartRows();(i<=page.getStartRows()+9)&&(i<all_message.size());i++){
        	P_Messages.add(all_message.get(i));
        }
        request.setAttribute("P_Messages", P_Messages);
        request.setAttribute("page", page);
        request.getRequestDispatcher("/inbound.jsp").forward(request, response);
    }
    

    
    
}
