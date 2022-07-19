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
import entity.Medicine;
import entity.User;

public class MedDistributionDao {
	private static String account;
	public void setAccount(String account)
	{
		if(MedDistributionDao.account==null)
			MedDistributionDao.account=account;
		else
			System.out.println(MedDistributionDao.account);
	}
	public String getAccount() {
		return MedDistributionDao.account;
	}
	public List<User>showUsers() {//显示所有分店
		DBDao DB=new DBDao();
		DB.setConn(DB.getconn());
		String sql="select * from users where Authority=0";
		DB.query(sql);
		ResultSet rs=DB.getRs();
		List<User> users=new ArrayList<User>();
		try{
		while(rs.next()) {
			User u=new User();
			u.setAccount(rs.getString("Account"));
			u.setAuthority(rs.getInt("Authority"));
			u.setM_table("M_table");
			u.setP_table("P_table");
			u.setPassword("Password");
			u.setS_table("S_Table");
			u.setUsername(rs.getString("Username"));
			u.setAddress(rs.getString("Address"));
			users.add(u);
		}
		DB.closeall();
		rs.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return users;
	}
	
	public List<Medicine>showMymedicine(User user) throws SQLException {
		DBDao DB=new DBDao();
		DB.setConn(DB.getconn());
		String sql1="select M_table from Users where account='"+user.getAccount()+"'";
		DB.query(sql1);
		ResultSet rs=DB.getRs();
		String m_table=" ";
		if(rs.next())
		{
			m_table=rs.getString(1);
		}
		String sql="select * from "+m_table+" order by id";
		DB.query(sql);
		rs=DB.getRs();
		List<Medicine> medicines=new ArrayList<Medicine>();
		try{
		while(rs.next()) {
			Medicine m = new Medicine();
    		m.setId(rs.getInt("Id"));
        	m.setName(rs.getString("Name"));
        	m.setNum(rs.getInt("Num"));
        	m.setPrice(rs.getFloat("Price"));
        	medicines.add(m);
		}
		DB.closeall();
		rs.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return medicines;
	}
	
	public void Distribute(User user,String account2,int id,int num){
		try {
			//account1是总店 account2是分店
					    String account1=user.getAccount();
					    String m_table=user.getM_table();
					    String medicine1=null,medicine2=null,p_message=null,s_message=null;
						DBDao DB=new DBDao();
						Connection conn=DB.getconn();
						DB.setConn(conn);
						String sqls="select M_table,S_table,P_table from Users where account='"+user.getAccount()+"'";
						DB.query(sqls);
						ResultSet rs=DB.getRs();
						
						if(rs.next())
						{
							m_table=rs.getString(1);
							s_message=rs.getString(2);
							p_message=rs.getString(3);
						}
						PreparedStatement pstmt=null;
						rs=null;
						Date date = new Date();
						SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
						String time = formatter.format(date);
						int P_ID=1;
						int S_ID=1;
						String medicinename=null;
						String sql1="select * from "+m_table+" where id="+id;
						System.out.println(sql1);
						DB.query(sql1);
						rs=DB.getRs();
						if(rs.next()){
							medicinename=rs.getString("Name");
						}
							//获取分店对应的表名
							String sql2="select * from Users where Account="+"'"+account2+"'";							
							medicine1=m_table;
							DB.query(sql2);
							rs=DB.getRs();
							if(rs.next()){
								medicine2=rs.getString("M_Table");
								p_message=rs.getString("P_Table");
							}
							//修改Medicine表的库存数量
							String sql3="update "+medicine1+" set Num=Num-"+num+" where ID="+id+"";
							System.out.println(sql3);
							DB.update(sql3);
							String sql4="update "+medicine2+" set Num=Num+"+num+" where ID="+id+"";
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
							pstmt.setString(2,time);
							pstmt.setInt(3, id);
							pstmt.setString(4, medicinename);
							pstmt.setInt(5, num);
							pstmt.setFloat(6, 0);//购入总价为0
							pstmt.setString(7, "Get");
							pstmt.setFloat(8, 0);//购入单价为0
							pstmt.setString(9, account2);//购入者是分店
							
							pstmt.executeUpdate();
							
							String sql8="insert into "+s_message+"(S_ID,S_Time,ID,Name,S_Num,S_Total,S_Reason,Price,Account)"+
									" values (?,?,?,?,?,?,?,?,?)";
									pstmt=conn.prepareStatement(sql8);
									pstmt.setInt(1, S_ID);
									pstmt.setString(2,time);
									pstmt.setInt(3, id);
									pstmt.setString(4, medicinename);
									pstmt.setInt(5, num);
									pstmt.setFloat(6, 0);//售出总价为0
									pstmt.setString(7, "Hand");
									pstmt.setFloat(8, 0);//售出单价为0
									pstmt.setString(9, account1);//售出者是总店
									
									pstmt.executeUpdate();
						
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
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	

}
