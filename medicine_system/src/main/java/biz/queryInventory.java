package biz;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import dao.DBDao;
import dao.M_tabledao;
import entity.Medicine;
import entity.User;
//����3����ѯ��ҵ���߼���
public class queryInventory {
	M_tabledao md=M_tabledao.getInstance();
	public List<Medicine> operate(User user) throws SQLException//����3��ѯ���
	{
		String m_table=user.getM_table();
		System.out.println(user.getAccount());
		DBDao DB=new DBDao();
		String sql1="select M_table from Users where account= '"+user.getAccount()+"'";
		DB.setConn(DB.getconn());
		DB.query(sql1);
		ResultSet rs=DB.getRs();
		if(rs.next())
		{
			m_table=rs.getString(1);
		}
		String sql = "select * from "+m_table+" order by id";
		List<Medicine> result=md.query(sql);
		return result;
	}

}
