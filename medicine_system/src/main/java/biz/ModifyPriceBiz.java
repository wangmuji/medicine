package biz;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import dao.DBDao;
import dao.M_tabledao;
import entity.Medicine;
import entity.User;

public class ModifyPriceBiz {
	M_tabledao md=M_tabledao.getInstance();
	public boolean operate(User user,int id,float price) throws SQLException//修改药品单价
	{
		String m_table=user.getM_table();
		DBDao DB=new DBDao();
		String sql1="select M_table from Users where account= '"+user.getAccount()+"'";
		DB.setConn(DB.getconn());
		DB.query(sql1);
		ResultSet rs=DB.getRs();
		if(rs.next())
		{
			m_table=rs.getString(1);
		}
		String sql = "update "+m_table+" set Price="+price+" where ID="+id;
		//System.out.println(sql);
		int result=md.upDate(sql);//成功修改的行数
		if(result==0)return false;
		return true;
	}

}
