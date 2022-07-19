package control;
import java.io.IOException;



import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.P_Message;


@WebServlet(name = "DetailedInforPServlet" , value="/DetailedInforPServlet")
public class DetailedInforPServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("DetailedInforPServlet.......");
		request. setCharacterEncoding("UTF-8");
		String Id = request.getParameter("ID");
    	int id =  Integer.parseInt(Id);
        P_Message message = new P_Message();
        message.setId(id);
    	String p_id = request.getParameter("P_ID");
    	int pid=Integer.parseInt(p_id);
    	message.setP_id(pid);
    	String p_time = request.getParameter("P_time");
    	message.setP_time(p_time);
    	String name= new String(request.getParameter("Name"));
    	message.setName(name);
    	String p_num = request.getParameter("P_Num");
    	int pnum=Integer.parseInt(p_num);
    	message.setP_num(pnum);
    	String p_total = request.getParameter("P_Total");
    	float ptotal = Float.parseFloat(p_total);
    	message.setP_total(ptotal);
    	String p_reason = request.getParameter("P_Reason");
    	message.setP_reason(p_reason);
    	String Purchase = request.getParameter("Purchase");
    	float purchase= Float.parseFloat(Purchase);
    	message.setPurchase(purchase);
    	String account = request.getParameter("Account");
    	message.setAccount(account);
        
        request.setAttribute("message", message);
        request.getRequestDispatcher("/detailed_inforP.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
