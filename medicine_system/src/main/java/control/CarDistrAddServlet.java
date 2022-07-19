package control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MedDistributionDao;
import entity.Page;
import entity.User;

@WebServlet(name = "carDistrAddServlet" , value="/carDistrAddServlet")
public class CarDistrAddServlet extends HttpServlet {
       
    public CarDistrAddServlet() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("carDistribute......");
		String pageIndex= request.getParameter("pageIndex");
		User user=(User)request.getSession().getAttribute("user");
		String account= request.getParameter("account");//�ֵ���û���
		int id = Integer.valueOf(request.getParameter("id"));
    	int num = Integer.valueOf(request.getParameter("num"));System.out.println(account);
		/**************����Ϊ������*********************/
    	MedDistributionDao md=new MedDistributionDao();
    	md.setAccount(account);
    	md.Distribute(user, md.getAccount(), id, num);
    	
    	if(false) {
			//���ʧ��;
    		response.setContentType("text/html; charset=UTF-8");
			response.getWriter().print("<script language='javascript'>   window.location.href='medDistributionServlet?pageIndex="+pageIndex+"';  alert('���ʧ��');</script>");

		}

		else{
			response.setContentType("text/html; charset=UTF-8");
			response.getWriter().print("<script language='javascript'>   window.location.href='medDistributionServlet?pageIndex="+pageIndex+"';  alert('��ӳɹ�');</script>");
	
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
