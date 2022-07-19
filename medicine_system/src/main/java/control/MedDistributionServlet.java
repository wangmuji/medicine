
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

import dao.MedDistributionDao;
import entity.Medicine;
import entity.Page;
import entity.User;

@WebServlet(name = "medDistributionServlet" , value="/medDistributionServlet")
public class MedDistributionServlet extends HttpServlet{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;



	@Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        this.doGet(req, resp);
    }



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String pageIndex= request.getParameter("pageIndex");
    	String account= request.getParameter("account");//分店的用户名
    	User  user = (User)request.getSession().getAttribute("user");//总店用户
    	/*User user=new User();
    	user.setAccount("user0");
    	user.setAuthority(1);
    	user.setM_table("medicine0");
    	user.setP_table("p_message0");
    	user.setS_table("s_message0");
    	request.getSession().setAttribute("user",user);
    	*/
    	/**************以上为读参区*********************/

    	 MedDistributionDao md=new MedDistributionDao();
         List<Medicine> all_medicines=new ArrayList();
		try {
			all_medicines = md.showMymedicine(user);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	if(pageIndex==null) {
    		 pageIndex="1";
    	}
    	
        Page page = new Page(Integer.valueOf(pageIndex),10);
        page.setTotalCounts(all_medicines.size());
        List<Medicine> medicines =new ArrayList<Medicine>();		//此处将10条库存信息赋给medicines

        for(int i=page.getStartRows();(i<=page.getStartRows()+9)&&(i<all_medicines.size());i++){
        	medicines.add(all_medicines.get(i));
        }

        
        request.setAttribute("account", account);
        request.setAttribute("medicines", medicines);
        request.setAttribute("page", page);
        request.getRequestDispatcher("/medicine_distr_car.jsp").forward(request, response);
    }

    
    
}
