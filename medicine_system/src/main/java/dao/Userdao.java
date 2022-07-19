package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entity.Medicine;
import entity.User;

public class Userdao {
	Connection conn = null;
	ResultSet rs=null;
	Statement stmt=null;
	static Userdao instance = null;
	
	Userdao() 
	{
		init();
	}
	public void init() 
	{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url="jjdbc:oracle:thin:@localhost:1521/workspace"; 
		    String user="system"; 
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
	public static Userdao getInstance()
	{
		if(instance == null)
			instance = new Userdao();
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
	
	public List<User> query(String sql){
		try{
		stmt=conn.createStatement();
		rs=stmt.executeQuery(sql);
		List<User> result= new ArrayList();

    	while(rs.next())
        {
        	User u = new User();
        	u.setAccount(rs.getString("Account"));
        	u.setAuthority(rs.getInt("Authority"));
        	u.setPassword(rs.getString("Password"));
        	u.setM_table(rs.getString("M_table"));
        	u.setP_table(rs.getString("p_table"));
        	u.setS_table(rs.getString("s_table"));
        	result.add(u);
        	
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