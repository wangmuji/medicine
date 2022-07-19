package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ApplyDao;
import entity.Page;
import entity.User;

@WebServlet(name = "applyReverseServlet" , value="/applyReverseServlet")
public class ApplyReverseServlet extends HttpServlet {
       
    public ApplyReverseServlet() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("applyReverseServlet......");
		String pageIndex= request.getParameter("pageIndex");
		User user=(User)request.getSession().getAttribute("user");
    	String A_id=request.getParameter("a_id");
    	int a_id=Integer.parseInt(A_id);

		/**************����Ϊ������*********************/
    	//�ֵ곷������
    	ApplyDao ad=new ApplyDao();
    	try {
			ad.revoke_Apply(a_id, user);
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	String error="";//
    	
    	if(true) {//����ɹ�
    		System.out.println("����ɹ�");
    		request.getRequestDispatcher("/applyUnhandledPageServlet?pageIndex="+pageIndex).forward(request, response);
    	}
    	else {//����ʧ��
    		response.setContentType("text/html; charset=UTF-8");
			response.getWriter().print("<script language='javascript'> alert('����ʧ��,ԭ��"+error+"');  window.location.href='applyUnhandledPageServlet?pageIndex="+pageIndex+"'</script>");

    	}
    	
		
    	
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
