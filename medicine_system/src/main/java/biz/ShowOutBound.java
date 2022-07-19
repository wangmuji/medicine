package biz;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import dao.DBDao;
import dao.S_Messagedao;
import entity.S_Message;
import entity.User;
//����4��ʾ�����¼��ҵ���߼���
public class ShowOutBound {
	S_Messagedao sm=S_Messagedao.getInstance();
	public List<S_Message> queryall(User user) throws SQLException//��ȫ��
	{
		String s_message=user.getS_table();
		DBDao DB=new DBDao();
		String sql1="select S_table from Users where account= '"+user.getAccount()+"'";
		DB.setConn(DB.getconn());
		DB.query(sql1);
		ResultSet rs=DB.getRs();
		if(rs.next())
		{
			s_message=rs.getString(1);
		}
		    String sql = "select * from "+s_message+" order by S_id";
		    List<S_Message> result=sm.query(sql);
		    return result;
		
	}
	public List<S_Message> querybyname(User user,String name) throws SQLException//�����Ʋ�ѯ
	{
		String s_message=user.getS_table();
		DBDao DB=new DBDao();
		String sql1="select S_table from Users where account= '"+user.getAccount()+"'";
		DB.setConn(DB.getconn());
		DB.query(sql1);
		ResultSet rs=DB.getRs();
		if(rs.next())
		{
			s_message=rs.getString(1);
		}
			
		    String sql = "select * from "+s_message+" where Name="+"'"+name+"' order by S_id";
		    List<S_Message> result=sm.query(sql);
		    return result;
		
	}
	public List<S_Message> querybytime(User user,String time1,String time2) throws SQLException//��ʱ���ѯ
	{
		String s_message=user.getS_table();
		DBDao DB=new DBDao();
		String sql1="select S_table from Users where account= '"+user.getAccount()+"' ";
		DB.setConn(DB.getconn());
		DB.query(sql1);
		ResultSet rs=DB.getRs();
		if(rs.next())
		{
			s_message=rs.getString(1);
		}
			
		    String sql = "select * from "+s_message+" where S_time between '"+time1+"' and '"+time2+"' order by S_id";
		    //System.out.println(sql);
		    List<S_Message> result=sm.query(sql);
		    return result;
		
	}

}
