package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import entity.Application;
import entity.User;
public class ApplyDao {
	//显示未处理的申请，总店可以看全部，分店只能看自己的
	public List<Application> un_handle(User user) throws SQLException{
		
		int authority=user.getAuthority();
		String account=user.getAccount();
		String sql=null;
		if(authority==1)
			sql="select * from A_Message where State = 0";
		else
			sql="select * from A_Message where State = 0 and Account="+"'"+account+"'";
		
		DBDao DB=new DBDao();
		DB.setConn(DB.getconn());
		DB.query(sql);
		ResultSet rs=DB.getRs();
		List<Application> LA=new ArrayList<Application>();
		while(rs.next()) {
			Application a=new Application(0);
			a.setA_id(rs.getInt(1));
			a.setId(rs.getInt(2));
			a.setName(rs.getString(3));
			a.setNum(rs.getInt(4));
			a.setAccount(rs.getString(5));
			a.setState(0);
			a.setA_time(rs.getString(7));
			a.setH_time(rs.getString(8));
			LA.add(a);
		}
		DB.closeall();
		rs.close();
		return LA;
	}
	
	//显示已处理的申请，总店可以看全部，分店只能看自己的
	public List<Application> handle(User user) throws SQLException{
		int authority=user.getAuthority();
		String account=user.getAccount();
		String sql=null;
		if(authority==1)
			sql="select * from A_message where State <> 0";
		else
			sql="select * from A_message where State <> 0 and Account='"+account+"'";
		//System.out.println(sql);
		DBDao DB=new DBDao();
		DB.setConn(DB.getconn());
		DB.query(sql);
		ResultSet rs=DB.getRs();
		List<Application> LA=new ArrayList<Application>();
		while(rs.next()) {
			Application a=new Application(0);
			a.setA_id(rs.getInt(1));
			a.setId(rs.getInt(2));
			a.setName(rs.getString(3));
			a.setNum(rs.getInt(4));
			a.setAccount(rs.getString(5));
			a.setState(0);
			a.setA_time(rs.getString(7));
			a.setH_time(rs.getString(8));
			LA.add(a);
		}
		DB.closeall();
		rs.close();
		return LA;
		
	}
	
	
	//总店处理申请,1同意，2拒绝
	public void handle_Apply(int state,int A_ID,User user)throws SQLException
	{
		//account1是总店 account2是分店
		String account1=user.getAccount();
		//修改A_Message表
		String sql="update A_Message set State = ?,H_Time=? where A_ID=?";
			DBDao DB=new DBDao();
			Connection conn=DB.getconn();
			DB.setConn(conn);
			PreparedStatement pstmt=conn.prepareStatement(sql);
			ResultSet rs=null;
			Date date = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
			String time = formatter.format(date);
			pstmt.setInt(1, state);
			pstmt.setString(2,time);
			pstmt.setInt(3, A_ID);
			pstmt.executeUpdate();
			
			
			if(state==1){//同意申请，进行分发    
				String sql1="select * from A_Message where A_ID="+A_ID+"";
				DB.query(sql1);
				rs=DB.getRs();
				int ID=0;
				String Name=null;
				int Num=0;
				String account2=null;
				String H_Time=null;
				int P_ID=1;
				int S_ID=1;
				if(rs.next()){					
					ID=rs.getInt(2);
					Name=rs.getString(3);
					Num=rs.getInt(4);
					account2=rs.getString(5);
					H_Time=rs.getString(8);
				}
				//获取分店对应的表名
				String sql2="select * from Users where Account="+"'"+account2+"'";
				String medicine1=null,medicine2=null,p_message=null,s_message=null;
				medicine1=user.getM_table();
				System.out.println(medicine1);
				s_message=user.getS_table();
				DB.query(sql2);
				rs=DB.getRs();
				if(rs.next()){
					medicine2=rs.getString("M_Table");
					p_message=rs.getString("P_Table");
				}
				//修改Medicine表的库存数量
				String sql3="update "+medicine1+" set Num=Num-"+Num+" where ID="+ID+"";
				System.out.println(sql3);
				DB.update(sql3);
				String sql4="update "+medicine2+" set Num=Num+"+Num+" where ID="+ID+"";
				System.out.println(sql4);
				DB.update(sql4);
				//生成新的P_Message和S_Message
				String sql5="select * from "+p_message+" order by P_ID desc";
				DB.query(sql5);
				rs=DB.getRs();
				if(rs.next())
				{
					P_ID=rs.getInt("P_ID")+1;
				}
				String sql6="select * from "+s_message+" order by S_ID desc";
				DB.query(sql6);
				rs=DB.getRs();
				if(rs.next())
				{
					S_ID=rs.getInt("S_ID")+1;
				}

				String sql7="insert into "+p_message+"(P_ID,P_Time,ID,Name,P_Num,P_Total,P_Reason,Purchase,Account)"+
				" values (?,?,?,?,?,?,?,?,?)";
				pstmt=conn.prepareStatement(sql7);
				pstmt.setInt(1, P_ID);
				pstmt.setString(2,H_Time);
				pstmt.setInt(3, ID);
				pstmt.setString(4, Name);
				pstmt.setInt(5, Num);
				pstmt.setFloat(6, 0);//购入总价为0
				pstmt.setString(7, "Get");
				pstmt.setFloat(8, 0);//购入单价为0
				pstmt.setString(9, account2);//购入者是分店
				
				pstmt.executeUpdate();
				
				String sql8="insert into "+s_message+"(S_ID,S_Time,ID,Name,S_Num,S_Total,S_Reason,Price,Account)"+
						" values (?,?,?,?,?,?,?,?,?)";
						pstmt=conn.prepareStatement(sql8);
						pstmt.setInt(1, S_ID);
						pstmt.setString(2,H_Time);
						pstmt.setInt(3, ID);
						pstmt.setString(4, Name);
						pstmt.setInt(5, Num);
						pstmt.setFloat(6, 0);//售出总价为0
						pstmt.setString(7, "Hand");
						pstmt.setFloat(8, 0);//售出单价为0
						pstmt.setString(9, account1);//售出者是总店
						
						pstmt.executeUpdate();
			}
			DB.closeall();
			if(conn!=null){
				conn.close();
			}
			if(pstmt!=null){
			pstmt.close();
			}
			if(rs!=null){
			rs.close();
			}
			
	}
	
	//分店撤销申请
	public void revoke_Apply(int a_id,User user)throws SQLException{
		DBDao DB=new DBDao();
		DB.setConn(DB.getconn());
		String sql="delete from A_message where a_id="+a_id;
		DB.update(sql);
		DB.closeall();
	}
}
