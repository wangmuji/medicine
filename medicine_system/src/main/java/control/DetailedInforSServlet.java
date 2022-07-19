package control;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.S_Message;


@WebServlet(name = "DetailedInforSServlet" , value="/DetailedInforSServlet")
public class DetailedInforSServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("DetailedInforSServlet.......");
		request. setCharacterEncoding("UTF-8");
		String Id = request.getParameter("ID");
    	int id =  Integer.parseInt(Id);
        S_Message message = new S_Message();
        message.setId(id);
    	String s_id = request.getParameter("S_ID");
    	int sid=Integer.parseInt(s_id);
    	message.setS_id(sid);
    	String s_time = request.getParameter("S_time");
    	message.setS_time(s_time);
    	String name= new String(request.getParameter("Name"));
    	message.setName(name);
    	System.out.println(name);
    	String s_num = request.getParameter("S_Num");
    	int snum=Integer.parseInt(s_num);
    	message.setS_num(snum);
    	String s_total = request.getParameter("S_Total");
    	float stotal = Float.parseFloat(s_total);
    	message.setS_total(stotal);
    	String s_reason = request.getParameter("S_Reason");
    	message.setS_reason(s_reason);
    	String Price = request.getParameter("Price");
    	float price= Float.parseFloat(Price);
    	message.setPrice(price);
    	String account = request.getParameter("Account");
    	message.setAccount(account);
        
        request.setAttribute("message", message);
        request.getRequestDispatcher("/detailed_inforS.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
