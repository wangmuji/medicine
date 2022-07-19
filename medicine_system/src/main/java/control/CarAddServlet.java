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

@WebServlet(name = "carAddServlet" , value="/carAddServlet")
public class CarAddServlet extends HttpServlet {
       
    public CarAddServlet() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("carAddServlet......");
		String pageIndex= request.getParameter("pageIndex");
		User user=(User)request.getSession().getAttribute("user");
    	String id=request.getParameter("id");
    	String numbers=request.getParameter("num");
    	int nums=Integer.parseInt(numbers);
		/**************以上为读参区*********************/
    	SellDao SD=new SellDao();
    	try {
			SD.set_account(user.getAccount());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	int ID=Integer.parseInt(id);
    	try {
			if(!SD.Add_Shop(ID,nums)) {
				//添加失败;
				response.setContentType("text/html; charset=UTF-8");
				response.getWriter().print("<script language='javascript'> alert('添加失败');  window.location.href='medSellPageServlet?pageIndex="+pageIndex+"'</script>");

			}
			//跳转到原来所在页
			else{
				request.getRequestDispatcher("/medSellPageServlet?pageIndex="+pageIndex).forward(request, response);
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
		//request.getRequestDispatcher("/medSellPageServlet?pageIndex="+pageIndex).forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
