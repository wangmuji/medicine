package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import entity.Medicine;
import entity.Shopping;
public class SellDao {
	private String account;					//当前登录账户
	private String medicine;				//当前登录账户的药品表。
	private String P_message;				//当前账户的购入信息
	private String S_message;				//当前账户的售出信息
	private String Shoping;					//当前账户的购物车
	private int count;//计算当前表的条数
	private float totalPrice;
	
 	public void set_account(String account) throws SQLException //获取当前登录的账户和他的表。
	{
		Connection conn=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		this.account=account;
		DBDao DB=new DBDao();
		try {
			conn=DB.getconn();
			String sql="select * from USERS where Account=?";
			stmt=conn.prepareStatement(sql);
			stmt.setString(1,account);
			rs=stmt.executeQuery();System.out.println("jinlaile");
			if(rs.next())
			{
				this.medicine=rs.getString(4);
				this.P_message=rs.getString(5);
				this.S_message=rs.getString(6);
				this.Shoping=rs.getString(8);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			rs.close();
			stmt.close();
			conn.close();
		}
	}
	//显示药品信息
	public List<Medicine> getMedicine(int page) throws SQLException {
		List<Medicine> S = new ArrayList<Medicine>();
		System.out.println(this.medicine);
		Connection conn=null;
		DBDao DB=new DBDao();
		conn=DB.getconn();
		DB.setConn(conn);
		int count=DB.count(this.medicine);//总条数
		this.count=count;
		int totalp=count/10+1;
		int p_num=0;	//每页数据量
		if(page+1<totalp)
			p_num=10;
		else
			p_num=count%10;
		int p=page*10+1;
		String sql="select * from (select id,name,num,price,rownum ro from "+this.medicine+" where rownum <"+(p+p_num)+" order by id) where ro>="+p;
		System.out.println(sql);
		DB.query(sql);
		ResultSet rs=DB.getRs();	
		while(rs.next()) {	
			Medicine s=new Medicine();
			int a=rs.getInt(1);	
			s.setId(a);
			s.setName(rs.getString(2));
			s.setNum(rs.getInt(3));
			s.setPrice(rs.getFloat(4));
			S.add(s);
			System.out.println(rs.getInt(1));
		}
		DB.closeall();
		rs.close();
		return S;
	}
	//添加购物
	@SuppressWarnings({ "unused", "resource" })
	public boolean Add_Shop(int ID,int num) throws SQLException {
		Connection conn=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		Medicine m=new Medicine();//用一个药品实例来储存
		DBDao DB=new DBDao();
		try {
			conn=DB.getconn();
			String sql="select * from "+this.medicine+" where ID=?";
			stmt=conn.prepareStatement(sql);
			stmt.setInt(1, ID);
			rs=stmt.executeQuery();
			int nums=0;
			String name = null;
			float price=0;
			int drtnum=0;
			if(rs.next())
			{
				m.setId(rs.getInt(1));
				m.setName(rs.getString(2));
				m.setNum(num);
				m.setPrice(rs.getFloat(5));
				name=rs.getString(2);
				if(rs.getInt(3)>=num)
				{
					nums=num;
					drtnum=rs.getInt(3)-num;
				}
				else
				{
					nums=rs.getInt(3);
					drtnum=0;
				}
				price=rs.getFloat(4);
			}
			int serial=1;
			DB.setConn(conn);
			String sql3="select * from "+this.Shoping+" order by serial desc";
			DB.query(sql3);
			ResultSet rs1=DB.getRs();
			if(rs1.next())
			{
				serial=rs1.getInt(5)+1;
			}
			int newnum=0;//如果有就更新数量
			int b=0;
			String sql1="insert into "+this.Shoping+" (ID,Name,num,price,Serial) values ('"+ID+"','"+name+"','"+nums+"','"+price+"','"+serial+"')";
			String sql4="select * from "+this.Shoping+" where ID="+ID+"";
			DB.query(sql4);
			rs1=DB.getRs();
			if(rs1.next())
			{
				newnum=rs1.getInt(3)+nums;
				b=1;//说明有重复的。
			}
			String sql5="update "+this.Shoping+" set num="+newnum+" where id="+ID+"";
			int count1=0,count3=0;
			PreparedStatement ps1=null,ps3=null;
			
			if(b==1) {
				ps3=conn.prepareStatement(sql5);
				count3=ps3.executeUpdate();
			}
			else {
				ps1=conn.prepareStatement(sql1);
				count1=ps1.executeUpdate();
			}		
			//添加到购物信息表
/*			String sql6="select S_ID from "+this.S_message+" order by S_ID desc";
			int s_id=1;
			DB.query(sql6);
			rs=DB.getRs();
			if(rs.next())
			{
				s_id=rs.getInt(1)+1;
			}
			else
				s_id=1;
			System.out.println(m.getId());
			Date date = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
			String time = formatter.format(date);
			int idd=m.getId();
			String gname=m.getName();
			price=m.getPrice()*num;
			float f=m.getPrice();
			String sql7="insert into "+this.S_message+"(S_ID,S_Time,ID,Name,S_Num,S_TOTAL,S_Reason,Price,Account)"+
					" values ('"+s_id+"','"+time+"','"+idd+"','"+gname+"','"+num+"','"+price+"','"+"SELL"+"','"+f+"','"+this.account+"')";
			stmt=conn.prepareStatement(sql7);
			stmt.executeUpdate();
			//添加结束
			 * */
			 
			DB.closeall();
			rs1.close();
			if(b==1)
				ps3.close();
			else
				ps1.close();
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}finally {
			rs.close();
			stmt.close();
			conn.close();
		}
	}
	public boolean Addup() //结算
	{
		String sql="select * from "+this.Shoping;
		float TotalPrice=0;
		DBDao DB=new DBDao();
		DB.setConn(DB.getconn());
		DB.query(sql);
		ResultSet rs=DB.getRs();
		float p=0;
		try {
			while(rs.next())
			{
				System.out.println(this.S_message);
				p+=rs.getInt(3)*rs.getFloat(4);
				int newnum=0;
				Medicine m=new Medicine();
				m.setId(rs.getInt(1));
				m.setName(rs.getString("name"));
				m.setPrice(rs.getFloat("Price"));
				String sql1="select num from "+this.medicine+" where id="+rs.getInt(1);	
				//添加到购物信息表
				String sql6="select S_ID from "+this.S_message+" order by S_ID desc";
				int s_id=1;
				DB.query(sql6);
				ResultSet rs3=DB.getRs();
				if(rs3.next())
				{
					s_id=rs3.getInt(1)+1;
				}
				else
					s_id=1;
				System.out.println(m.getId());
				Date date = new Date();
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
				String time = formatter.format(date);
				int idd=m.getId();
				String gname=m.getName();
				float price=m.getPrice()*rs.getInt(3);
				TotalPrice+=price;
				float f=m.getPrice();
				String sql7="insert into "+this.S_message+"(S_ID,S_Time,ID,Name,S_Num,S_TOTAL,S_Reason,Price,Account)"+
						" values ('"+s_id+"','"+time+"','"+idd+"','"+gname+"','"+rs.getInt(3)+"','"+price+"','"+"SELL"+"','"+f+"','"+this.account+"')";
				DB.update(sql7);
				//添加结束
				DB.query(sql1);
				ResultSet rs1=DB.getRs();
				if(rs1.next())
				{
					newnum=rs1.getInt(1)-rs.getInt(3);
				}
				String sql2="update "+this.medicine+" set num ="+newnum+" where id="+rs.getInt(1);
				DB.update(sql2);
				rs1.close();
				rs3.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		String sql1="truncate table "+ this.Shoping+"";
		DB.update(sql1);
		DB.closeall();
		try {
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		this.totalPrice=TotalPrice;
		return true;
	}
	//获取购物车信息
	public List<Shopping> getShopping(int page) throws SQLException{
		List<Shopping> S = new ArrayList<Shopping>();		
		Connection conn=null;
		DBDao DB=new DBDao();
		conn=DB.getconn();
		DB.setConn(conn);
		int count=DB.count(this.Shoping);//总条数
		this.count=count;
		int totalp=count/10+1;
		int p_num=0;	//每页数据量
		if(page+1<totalp)
			p_num=10;
		else
			p_num=count%10;
		int p=page*10;
		String sql="select * from "+this.Shoping+" where rownum>="+page*10+" and rownum <="+p_num;
		
		DB.query(sql);
		ResultSet rs=DB.getRs();	
		int times=0;
		while(rs.next()) {
			Shopping s=new Shopping();
			s.setID(rs.getInt(1));
			s.setName(rs.getString(2));
			s.setNum(rs.getInt(3));
			s.setPrice(rs.getFloat(4));
			S.add(s);
			times++;
		}
		DB.closeall();
		rs.close();
		return S;
	}
	//删除某一条购物车信息
	public Boolean deleteShopping(int id,String account) throws SQLException{
		String sql="delete from "+this.Shoping+" where id="+id+"";
		DBDao DB=new DBDao();
		DB.setConn(DB.getconn());
		if(DB.update(sql))
		{
			boolean b=true;
			DB.closeall();
			return b;
		}
		else
			return false;
	}
	//清空购物车
	public void emptyShopping() throws SQLException{
		String sql="truncate table "+ this.Shoping+"";
		DBDao DB=new DBDao();
		Connection conn=DB.getconn();
		DB.setConn(conn);
		DB.update(sql);
		DB.closeall();
	}
	public int getCount() {
		return count;
	}
	public void setTotalPrice() throws SQLException {
			
		Connection conn=null;
		DBDao DB=new DBDao();
		conn=DB.getconn();
		DB.setConn(conn);
		float TotalPrice=0;
		String sql="select num,price from "+this.Shoping;
		DB.query(sql);
		ResultSet rs=DB.getRs();	
		while(rs.next()) {
			TotalPrice+=rs.getInt(1)*rs.getFloat(2);
		}
		this.totalPrice=TotalPrice;
		DB.closeall();
		rs.close();
	}
	public float getTotalPrice() {
		return totalPrice;
	}
}
