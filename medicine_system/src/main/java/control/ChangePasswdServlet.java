package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.User;
@WebServlet(name = "changePasswdServlet" , value="/changePasswdServlet")
public class ChangePasswdServlet extends HttpServlet {

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("ChangePasswdServlet......");

		
		User user=(User)request.getSession().getAttribute("user");
		String account =user.getAccount();//����Ϊ��¼��״̬�����������þ�ֵ̬�����ڿɸ���
		String new_password0= request.getParameter("new_passwd0");
		String new_password1= request.getParameter("new_passwd1");
		String old_password = biz.PassswordChange.GetPassword(account); //���ԭʼ����
		System.out.println("+++++"+account);
		
		String error0 =" �����������벻��ͬ";	//ʧ��ԭ����ʧ����Ҫ�ж��긳ֵ�󴫸�ǰ�ˣ�����Ϊ��������������ͬ
		String error1 = "�������һ��";
		if(new_password0.equals(new_password1)) //�ж��Ƿ����
		{
			if(new_password0.equals(old_password)||new_password1.equals(old_password)) {//�ж��Ƿ����������ͬ���ɸ�ΪUserʵ�����ж�
				request.setAttribute("error", error1);//����ʧ��ԭ��
				request.getRequestDispatcher("/passwd_change_fail.jsp").forward(request, response);
			}
			
			if(biz.PassswordChange.update(account, new_password1)==1)	//�ж��Ƿ�ɹ��޸�,��Ҫ��˺����޸�����
			{
			//update���������û����͵�һ���������������һ��if���ж������������Ƿ��൱
				response.sendRedirect(request.getContextPath()+"/passwd_change_succese.jsp");
			}
		}
		else {	//ʧ��
			request.setAttribute("error", error0);//����ʧ��ԭ��
			request.getRequestDispatcher("/passwd_change_fail.jsp").forward(request, response);
			
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
