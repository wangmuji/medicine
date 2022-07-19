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

@WebServlet(name = "applyRefuseServlet" , value="/applyRefuseServlet")
public class ApplyRefuseServlet extends HttpServlet {
       
    public ApplyRefuseServlet() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("applyRefuseServlet......");
		String pageIndex= request.getParameter("pageIndex");
		User user=(User)request.getSession().getAttribute("user");
    	String A_id=request.getParameter("a_id");
    	int a_id=Integer.parseInt(A_id);

		/**************以上为读参区*********************/
    	//总店进行拒绝处理：使用update语句修改申请状态
    	user.setM_table("medicine");
    	user.setP_table("p_message");
    	user.setS_table("s_message");
    	user.setShop("Shop");
    	ApplyDao ad=new ApplyDao();
    	try {
			ad.handle_Apply(2, a_id, user);
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	String error="";//
    	
    	if(true) {//处理成功
    		System.out.println("处理成功");
    		request.getRequestDispatcher("/applyUnhandledPageServlet?pageIndex="+pageIndex).forward(request, response);
    	}
    	else {//处理失败
    		response.setContentType("text/html; charset=UTF-8");
			response.getWriter().print("<script language='javascript'> alert('处理失败,原因："+error+"');  window.location.href='applyUnhandledPageServlet?pageIndex="+pageIndex+"'</script>");

    	}
    	
		
    	
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
