package biz;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import dao.DBDao;
import dao.P_Messagedao;
import entity.P_Message;
import entity.User;
//����4��ʾ�����¼��ҵ���߼���
public class ShowInBound {
	P_Messagedao sm=P_Messagedao.getInstance();
	public List<P_Message> queryall(User user) throws SQLException//��ȫ��
	{
		String p_message=user.getP_table();
		DBDao DB=new DBDao();
		String sql1="select P_table from Users where account= '"+user.getAccount()+"'";
		DB.setConn(DB.getconn());
		DB.query(sql1);
		ResultSet rs=DB.getRs();
		if(rs.next())
		{
			p_message=rs.getString(1);
		}
		    String sql = "select * from "+p_message+" order by P_id";
		    List<P_Message> result=sm.query(sql);
		    return result;
		
	}
	public List<P_Message> querybyname(User user,String name) throws SQLException//�����Ʋ�ѯ
	{
		String p_message=user.getP_table();
		DBDao DB=new DBDao();
		String sql1="select P_table from Users where account= '"+user.getAccount()+"'";
		DB.setConn(DB.getconn());
		DB.query(sql1);
		ResultSet rs=DB.getRs();
		if(rs.next())
		{
			p_message=rs.getString(1);
		}
			
		    String sql = "select * from "+p_message+" where Name="+"'"+name+"' order by P_id";
		    List<P_Message> result=sm.query(sql);
		    return result;
		
	}
	public List<P_Message> querybytime(User user,String time1,String time2) throws SQLException//��ʱ���ѯ
	{
		String p_message=user.getP_table();
		DBDao DB=new DBDao();
		String sql1="select P_table from Users where account= '"+user.getAccount()+"'";
		DB.setConn(DB.getconn());
		DB.query(sql1);
		ResultSet rs=DB.getRs();
		if(rs.next())
		{
			p_message=rs.getString(1);
		}
			
		    String sql = "select * from "+p_message+" where P_Time between '"+time1+"' and '"+time2+"' order by P_id";
		    System.out.println(sql);
		    List<P_Message> result=sm.query(sql);
		    return result;
		
	}

}

