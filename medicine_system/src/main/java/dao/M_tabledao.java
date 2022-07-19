package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entity.Medicine;


public class M_tabledao {
	Connection conn = null;
	ResultSet rs=null;
	Statement stmt=null;
	static M_tabledao  instance = null;
	
	M_tabledao () 
	{
		init();
	}
	public void init() 
	{
		try {
                Class.forName("oracle.jdbc.driver.OracleDriver");
				String url="jdbc:oracle:thin:@localhost:1521/workspace"; 
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
	public static M_tabledao  getInstance()
	{
		if(instance == null)
			instance = new M_tabledao();
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
	
	public List<Medicine> query(String sql){
		try{
		stmt=conn.createStatement();
		rs=stmt.executeQuery(sql);
		List<Medicine> result= new ArrayList();

    	while(rs.next())
        {
    		Medicine m = new Medicine();
    		m.setId(rs.getInt("Id"));
        	m.setName(rs.getString("Name"));
        	m.setNum(rs.getInt("Num"));
        	m.setPrice(rs.getFloat("Price"));
        	result.add(m);
        	
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
