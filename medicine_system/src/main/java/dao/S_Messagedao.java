package dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entity.S_Message;

public class S_Messagedao {
	Connection conn = null;
	ResultSet rs=null;
	Statement stmt=null;
	static S_Messagedao instance = null;
	
	S_Messagedao() 
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
	public static S_Messagedao getInstance()
	{
		if(instance == null)
			instance = new S_Messagedao();
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
	
	public List<S_Message> query(String sql){
		try{
		stmt=conn.createStatement();
		rs=stmt.executeQuery(sql);
		List<S_Message> result= new ArrayList();

    	while(rs.next())
        {
    		S_Message s = new S_Message();
    		s.setAccount(rs.getString("Account"));
    		s.setId(rs.getInt("ID"));
    		s.setName(rs.getString("Name"));
    		s.setPrice(rs.getFloat("Price"));
    		s.setS_id(rs.getInt("S_ID"));
    		s.setS_num(rs.getInt("S_Num"));
    		s.setS_reason(rs.getString("S_Reason"));
    		s.setS_time(rs.getString("S_time"));
    		s.setS_total(rs.getFloat("S_Total"));
        	result.add(s);
        	
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
