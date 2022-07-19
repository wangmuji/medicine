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
import biz.ModifyPrice;
import biz.queryInventory;
import entity.Medicine;
import entity.Page;
import entity.User;

@WebServlet(name = "modifyPriceServlet" , value="/modifyPriceServlet")
public class ModifyPriceServlet extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        this.doGet(request, response);
    }



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	String pageIndex= request.getParameter("pageIndex");
    	User  user = (User)request.getSession().getAttribute("user");
    	//读参区
    	System.out.println("ModifyPriceServlet....");
    	queryInventory qI=new queryInventory();
    	ModifyPrice mp=new ModifyPrice();
    	
    	/*if(user==null){
    	user=new User();
    	user.setM_table("medicine0");
    	request.getSession().setAttribute("user",user);
    	}*/
    	
    	System.out.println(user.getAccount());
    	if(pageIndex==null) {
    		 pageIndex="1";
    	}
    	String Price = request.getParameter("Price");
    	String ID = request.getParameter("Id");
    	if((Price!=null)&&(ID!=null)){
    	int id=Integer.parseInt(ID);
    	float price=Float.parseFloat(Price);
    	//修改售价
    	try {
			mp.operate(user, id, price);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	}
    	//再次显示所有库存
    	Page page = new Page(Integer.valueOf(pageIndex),10);
    	List<Medicine> all_medicines=new ArrayList();
		try {
			all_medicines = qI.operate(user);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        page.setTotalCounts(all_medicines.size());		//此处传进totalcounts
        List<Medicine> medicines =new ArrayList<Medicine>();		//此处将10条库存信息赋给medicines
        for(int i=page.getStartRows();(i<=page.getStartRows()+9)&&(i<all_medicines.size());i++){
        	Medicine m=null;
        	m=(Medicine)all_medicines.get(i);
        	medicines.add(m);
        }
        
        request.setAttribute("medicines", medicines);
        request.setAttribute("page", page);
        request.getRequestDispatcher("/modify_price.jsp").forward(request, response);
    }
}
