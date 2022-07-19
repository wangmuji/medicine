package control;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.SellDao;
import entity.Medicine;
import entity.Page;
import entity.Shopping;
import entity.User;
import dao.DBDao;

@SuppressWarnings("serial")
@WebServlet(name = "carPageServlet" , value="/carPageServlet")
public class CarPageServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("carPageServlet......");
		String pageIndex= request.getParameter("pageIndex");
		User user=(User)request.getSession().getAttribute("user");
		
    	/**************以上为读参区*********************/
		System.out.println("pageIndex is:"+pageIndex);
    	System.out.println("Account is : "+user.getAccount());
    	SellDao SD=new SellDao();
    	try {
			SD.set_account(user.getAccount());
			System.out.println("这里也进来了，设置账户处");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println("设置账户失败");
		}
    	if(pageIndex==null) {
    		 pageIndex="1";
    	}
        Page page = new Page(Integer.valueOf(pageIndex),10);
    
       
        List<Shopping> cars = new ArrayList<Shopping>();
		try {
			cars = SD.getShopping(page.getPageIndex()-1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		page.setTotalCounts(  SD.getCount()   );
        if(page.getPageIndex()>page.getTotalPages())	//可能出现情况：在CarDelServlet中删除某项后，会把删除项所在pageIndex传入
        {												//如该页是最后一页且只有一项，则pageIndex应-1，因为该页已不存在
        	int i=page.getPageIndex();				    //例如第五页只有一条记录，删除后本应继续留在第五页，但此时最大页数为四
        	page.setPageIndex(i-1);						//所以此时跳转到第四页即可        	
        	page.setStartRows((i - 2) * page.getPageSize());
        }
        try {
			SD.setTotalPrice();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		float totalPrice=SD.getTotalPrice();
		System.out.println(totalPrice);
        request.setAttribute("totalPrice", totalPrice);
		request.setAttribute("page", page);
		request.setAttribute("cars", cars);
		request.getRequestDispatcher("/medicine_sell_car.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
