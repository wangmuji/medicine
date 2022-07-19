package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Page;
import entity.User;
import biz.MedicinePurchase;

@WebServlet(name = "medPurchaseServlet" , value="/medPurchaseServlet")
public class MedPurchaseServlet extends HttpServlet {
       
    public MedPurchaseServlet() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("medPurchaseServlet......");
		User user=(User)request.getSession().getAttribute("user");
		
		String id= request.getParameter("id");
		int ID=Integer.parseInt(id);
    	int num = Integer.valueOf(request.getParameter("num"));
    	float purchase_price = Float.valueOf(request.getParameter("purchase_price")) ;
		/**************以上为读参区*********************/
    	System.out.println("id is "+id);
    	System.out.println("num is "+num);
    	System.out.println("pp is "+purchase_price);
    	//1、判断药品名称和编号是否在库中存在并对应
    	//2、按入库表具有属性插入入库表
    	
    	
    	
    	
    	try {
			if(MedicinePurchase.Purchase(user.getAccount(),ID,num,purchase_price)) {
				//添加成功;
				response.setContentType("text/html; charset=UTF-8");
				response.getWriter().print("<script language='javascript'>   window.location.href='medicine_purchase.jsp';alert('购入成功');</script>");

			}
			//跳转到原来所在页
			else{
				
				response.setContentType("text/html; charset=UTF-8");
				response.getWriter().print("<script language='javascript'>   window.location.href='medicine_purchase.jsp';alert('购入失败');</script>");

				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
