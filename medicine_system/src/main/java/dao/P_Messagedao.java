package dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entity.P_Message;

public class P_Messagedao {
	Connection conn = null;
	ResultSet rs=null;
	Statement stmt=null;
	static P_Messagedao instance = null;
	
	P_Messagedao() 
	{
		init();
	}
	public void init() 
	{
		try {
                Class.forName("oracle.jdbc.driver.OracleDriver");
				String url="jdbc:oracle:thin:@localhost:1521/workspace"; 
			    String user="System"; 
				String password="123456"; 
				conn=DriverManager.getConnection(url,user,password);
			  
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("加载数据库驱动失败");
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("连接数据库失败");
		}

	}
	public static P_Messagedao getInstance()
	{
		if(instance == null)
			instance = new P_Messagedao();
		return instance;
	}
	public int upDate(String sql)
	{
		String m_table=null;
		try{
			stmt=conn.createStatement();
			int result=stmt.executeUpdate(sql);
			if(stmt!=null){
		    	   stmt.close();
		    }
			return result ;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public List<P_Message> query(String sql){
		try{
		stmt=conn.createStatement();
		rs=stmt.executeQuery(sql);
		List<P_Message> result= new ArrayList();

    	while(rs.next())
        {
    		P_Message p = new P_Message();
    		p.setAccount(rs.getString("Account"));
    		p.setId(rs.getInt("ID"));
    		p.setName(rs.getString("Name"));
    		p.setPurchase(rs.getFloat("Purchase"));
    		p.setP_id(rs.getInt("P_ID"));
    		p.setP_num(rs.getInt("P_Num"));
    		p.setP_reason(rs.getString("P_Reason"));
    		p.setP_time(rs.getString("P_time"));
    		p.setP_total(rs.getFloat("P_Total"));
        	result.add(p);
        	
        }
	    if(rs!=null){
	    	  rs.close();
	       }
		if(stmt!=null){
	    	   stmt.close();
	    }
		return result;
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return null;
	}
	public void close(){
		try{
			if(conn!=null){
			conn.close();
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	


}

