package dao;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
public class DBDao {
	
	static String driverClass="oracle.jdbc.driver.OracleDriver"; //oracle的驱动
    static String url="jdbc:oracle:thin:@localhost:1521/workspace";  //连接oracle路径方式 “”gfs“”是要建立连接的数据库名 1521端口
    static String user="System";   //user是数据库的用户名
    static String password="123456";  //用户登录密码
    private Connection conn;
	private Statement stm;
	private ResultSet rs;
    public Connection getconn() {  //为了方便下面的讲解，这里专门建立了一个用于数据库连接的一个方法
        Connection conn=null;
        try {

         //首先建立驱动
         Class.forName(driverClass);
         //驱动成功后进行连接
         conn=DriverManager.getConnection(url, user, password);

        System.out.println("连接成功");
     } catch (SQLException e) {
    	 System.out.println("连接失败");
         e.printStackTrace();
     } catch (Exception e) {
    	 System.out.println("失败了");
         e.printStackTrace();
     }
        return conn; //返回一个连接
    }
  //修改
  	public boolean update(String sql){
  		boolean b = false;
  		try {
  			stm = conn.createStatement();
  			stm.execute(sql);
  			b = true;
  		} catch (SQLException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		}
  		return b;
  	}
  	//查询
  	public void query(String sql){
  		try {
  			stm = conn.createStatement();
  			System.out.println("草拟吗jinlaile");
  			rs = stm.executeQuery(sql);
  		} catch (SQLException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		}
  	}
  	//判断有无数据
  	public boolean next(){
  		boolean b = false;
  		try {
  			if(rs.next()){
  				b = true;
  			}
  		} catch (SQLException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		}
  		return b;
  	}
  	//获取表字段值
  	public String getValue(String field) {
  		//记得调用next
  		String value = null;
  		try {
  			if (rs != null) {
  				value = rs.getString(field);
  			}
  		} catch (SQLException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		}
  		return value;
  	}
  	//计算表中数据条数
  	public Integer count(String table) {
  		String sql="select * from "+table+"";
  		if(this.conn==null)
  			this.conn=this.getconn();
  		int cou=0;
  		this.query(sql);
  		ResultSet rs=this.getRs();
  		try {
			while(rs.next())
			{
				cou++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
  	//	this.closeall();
  		return cou;
  	}
  	//关闭连接
  	public void closeConn() {
  		try {
  			if (conn != null) {
  				conn.close();
  			}
  		} catch (SQLException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		}
  	}
  	//关闭statement
  	public void closeStm() {
  		try {
  			if (stm != null) {
  				stm.close();
  			}
  		} catch (SQLException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		}
  	}
  	//关闭ResultSet
  	public void closeRs() {
  		try {
  			if (rs != null) {
  				rs.close();
  			}
  		} catch (SQLException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		}
  	}
  	public void closeall() {
  		closeRs();
  		closeStm();
  		closeConn();
  	}
  	public Statement getStm() {
		return stm;
	}
	public void setStm(Statement stm) {
		this.stm = stm;
	}
	public ResultSet getRs() {
		return rs;
	}
	public void setRs(ResultSet rs) {
		this.rs = rs;
	}
	public void setConn(Connection conn) {
		this.conn=conn;
	}
}
