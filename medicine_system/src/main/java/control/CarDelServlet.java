package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.SellDao;
import entity.Page;
import entity.User;

@WebServlet(name = "carDelServlet" , value="/carDelServlet")
public class CarDelServlet extends HttpServlet {
       
    public CarDelServlet() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("carDelServlet......");
		User user=(User)request.getSession().getAttribute("user");
		String pageIndex= request.getParameter("pageIndex");
    	String id=request.getParameter("id");
    	/**************����Ϊ������*********************/
    	SellDao SD = new SellDao();
    	try {
			SD.set_account(user.getAccount());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	int ID=Integer.parseInt(id);
    	
    	//��˸���account��idɾ�����ݿ���ĳ������
		try {
			if(!SD.deleteShopping(ID,user.getAccount())) {
				//ɾ��ʧ��;
				response.setContentType("text/html; charset=UTF-8");
				response.getWriter().print("<script language='javascript'> alert('ɾ��ʧ��');  window.location.href='carPageServlet?pageIndex="+pageIndex+"'</script>");

			}
			//ɾ������ת��ɾ����ԭ������ҳ
			else{
				request.getRequestDispatcher("/carPageServlet?pageIndex="+pageIndex).forward(request, response);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
