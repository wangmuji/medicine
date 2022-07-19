	package biz;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import dao.DBDao;
import entity.User;

import java.awt.*;
public class LoginBiz {
	//寻找符合账户密码的用户
	public User find(String Account,String password) throws SQLException
	{
		DBDao DB=new DBDao();
		Connection conn=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		User u=null;
		try {
			conn=DB.getconn();
			String Sql="select * from USERS where account=? and Password=?";
			stmt = conn.prepareStatement(Sql);
			stmt.setString(1,Account);
			stmt.setString(2, password);
			rs = stmt.executeQuery();
			if(rs.next())
			{
				u=new User();
				u.setAccount(rs.getString(1));
				u.setPassword(rs.getString(2));
				u.setAuthority(rs.getInt(3));
				u.setUsername(rs.getString(7));
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		finally {
			rs.close();
			stmt.close();
			conn.close();
		}
		return u;
	}

}
