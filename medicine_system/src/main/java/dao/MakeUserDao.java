package dao;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entity.Page;
import entity.User;

public class MakeUserDao {
	//添加用户
	public static boolean addUser(User user) throws SQLException {
		if(!ifExist(user.getAccount(),user.getUsername()))
			return false;
		DBDao DB=new DBDao();
		Connection conn=DB.getconn();
		DB.setConn(conn);
		
		String sql="insert into Users (ACCOUNT,PASSWORD,AUTHORITY,M_TABLE,P_TABLE,S_TABLE,USERNAME,SHOP,ADDRESS) VALUES(?,?,?,?,?,?,?,?,?)";
		String sql1="select account from Users where authority=0";
		DB.query(sql1);
		ResultSet rs=DB.getRs();
		int id=1;
		while(rs.next())
		{
			id++;
		}
		String sql2="create table medicine"+id+"("+"ID INTEGER NOT NULL,"+"NAME VARCHAR(20) NOT NULL,"
		+"NUM INTEGER NOT NULL, "+"PURCHASE FLOAT NOT NULL,"+"PRICE FLOAT NOT NULL,"
				+"ACCOUNT VARCHAR(20) NOT NULL,"
		+"PRIMARY KEY(ID)"+")";
		String sql3="create table S_message"+id+"("+"S_ID INTEGER NOT NULL,"+"S_TIME VARCHAR(20) NOT NULL,"
		+"ID INTEGER NOT NULL, "+"NAME VARCHAR(40) NOT NULL,"+"S_NUM INTEGER NOT NULL,"
				+"S_TOTAL FLOAT NOT NULL,"+"S_REASON VARCHAR(20) NOT NULL,"+"PRICE FLOAT NOT NULL,"
		+"ACCOUNT VARCHAR(20) NOT NULL,"+" PRIMARY KEY(S_ID)"+")";
		String sql4="create table P_message"+id+"("+"P_ID INTEGER NOT NULL,"+"P_TIME VARCHAR(20) NOT NULL,"
		+"ID INTEGER NOT NULL,"+"NAME VARCHAR(40) NOT NULL,"+"P_NUM INTEGER NOT NULL,"+"P_TOTAL FLOAT NOT NULL,"
				+"P_REASON VARCHAR(20) NOT NULL,"+"PURCHASE FLOAT NOT NULL,"+"ACCOUNT VARCHAR(20) NOT NULL,"
		+"PRIMARY KEY(P_ID)"+")";
		String sql5="create table Shop"+id+"("+"ID INTEGER NOT NULL,"+"NAME VARCHAR(20) NOT NULL,"
		+"NUM INTEGER NOT NULL,"+"PRICE FLOAT NOT NULL,"+"SERIAL INTEGER NOT NULL"+")";
		String sql6="select * from Medicine";
		String sql7="insert into Medicine"+id+" values(?,?,?,?,?,?)";
		Statement stmt = null;
		stmt=conn.createStatement();
		System.out.println(sql2);
		System.out.println(sql3);
		System.out.println(sql4);
		System.out.println(sql5);
		PreparedStatement stmt1=null;
		stmt1=conn.prepareStatement(sql);
		stmt1.setString(1,user.getAccount());
		stmt1.setString(2, user.getPassword());
		stmt1.setInt(3,user.getAuthority());
		String s="Medicine"+id;
		stmt1.setString(4, s);
		s="P_message"+id;
		stmt1.setString(5,s);
		s="S_message"+id;
		stmt1.setString(6, s);
		stmt1.setString(7,user.getUsername());
		s="Shop"+id;
		stmt1.setString(8, s);
		stmt1.setString(9,user.getAddress());
		stmt1.executeUpdate();
		stmt.execute(sql2);
		stmt.execute(sql3);
		stmt.execute(sql4);
		stmt.execute(sql5);	
		DB.query(sql6);
		rs=DB.getRs();System.out.println("table has been created");
		while(rs.next())
		{
			stmt1=conn.prepareStatement(sql7);
			stmt1.setInt(1,rs.getInt(1));
			stmt1.setString(2, rs.getString(2));
			stmt1.setInt(3, 0);
			stmt1.setFloat(4, rs.getFloat(4));
			stmt1.setFloat(5,rs.getFloat(5));
			stmt1.setString(6, user.getAccount());
			stmt1.executeUpdate();
		}
		
		rs.close();
		stmt1.close();
		stmt.close();
		conn.close();
		DB.closeall();
		System.out.println("全部关闭");
		return true;
	}
	//判断是否重复注册
	public static boolean ifExist(String account,String username) throws SQLException //true存在，false不存在。
	{
		String sql="select username,account from USERS where Account="+"'"+account+"'";
		DBDao DB=new DBDao();
		Connection conn=DB.getconn();
		DB.setConn(conn);
		DB.query(sql);
		ResultSet rs=DB.getRs();
		boolean b=false;
		boolean c=false;
		if(rs==null)
		{
				b=false;
		}
			else
				b=true;
		
		sql="select username,account from USERS where username="+"'"+username+"'";
		DB.query(sql);
		rs=DB.getRs();
		if(rs==null)
		{
			c=false;
		}
		else
			c=true;
		if(rs!=null)
			rs.close();
		DB.closeall();
		conn.close();
		return b&c;
	}
	//删除用户
	public static boolean deleteUser(String account) throws SQLException {
		String sql="delete from Users where Account="+"'"+account+"'";
		String sql1="select M_table,P_table,S_table,Shop from Users where account="+"'"+account+"'";
		
		DBDao DB=new DBDao();
		Connection conn=DB.getconn();
		DB.setConn(conn);
		DB.query(sql1);
		ResultSet rs=DB.getRs();
		if(rs.next())
		{
			String sql2="drop table "+rs.getString(1);
			String sql3="drop table "+rs.getString(2);
			String sql4="drop table "+rs.getString(3);
			String sql5="drop table "+rs.getString(4);
			DB.update(sql2);
			DB.update(sql3);
			DB.update(sql4);
			DB.update(sql5);
		}
		boolean b=DB.update(sql);
		rs.close();
		DB.closeall();
		conn.close();
		if(b)
			return true;
		else
			return false;
	}
	//显示全部用户
	public static List<User> getUsers(int page) throws SQLException{
		DBDao DB=new DBDao();
		Connection conn=DB.getconn();
		DB.setConn(conn);
		int count=DB.count("Users");//总条数
		int totalp=count/10+1;
		int p_num=0;	//每页数据量
		if(page+1<totalp)
			p_num=10;
		else
			p_num=count%10;
		int p=page*10;
		String sql="select * from "+"USERS"+" where rownum>="+page*10+" and rownum <="+p_num+" and authority=0";
		List<User> users=new ArrayList();
		DB.query(sql);
		ResultSet rs=DB.getRs();
		while(rs.next())
		{
			User u=new User();
			u.setAccount(rs.getString(1));
			u.setUsername(rs.getString(7));
			u.setAddress(rs.getString(9));
			users.add(u);
			
		}
		rs.close();
		DB.closeall();
		conn.close();
		return users;
	}
}
