package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import biz.MedApplyBiz;
import entity.Page;
import entity.User;

@WebServlet(name = "medApplyServlet" , value="/medApplyServlet")
public class MedApplyServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("medApplyServlet......");
		String pageIndex= request.getParameter("pageIndex");
		User user=(User)request.getSession().getAttribute("user");
		//String account="lyb";//��̬���β�����,����ʹ������һ��
		String account=user.getAccount();
		int id= Integer.valueOf(request.getParameter("id")).intValue();
    	int num = Integer.valueOf(request.getParameter("num"));
		/**************����Ϊ������*********************/
    	int isSucceed=0;
    	
    	try {
			isSucceed=MedApplyBiz.med_apply(account, id, num);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    	if(isSucceed==1) {
    		System.out.println("id is"+id+"   num is"+num+"   ����ɹ�");
			request.getRequestDispatcher("/medApplyPageServlet?pageIndex="+pageIndex).forward(request, response);
		}
    	//��ת��ԭ������ҳ
		else{
			//�������ʧ��;
    		response.setContentType("text/html; charset=UTF-8");
			response.getWriter().print("<script language='javascript'> alert('����ʧ��');  window.location.href='medApplyPageServlet?pageIndex="+pageIndex+"'</script>");

		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
