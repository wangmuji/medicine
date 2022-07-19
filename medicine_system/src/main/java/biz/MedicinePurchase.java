package biz;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import dao.DBDao;

public class MedicinePurchase {
	public static Connection coon;
	public static PreparedStatement prsmt;
	public static ResultSet rst;
	public static String sql="insert into P_Message values(?,?,?,?,?,?,?,?,?)";
	public static String sql2="select P_id from P_Message order by P_id desc";
	public static String sql1="select * from P_Message ";//调试语句
	public static String sql3="update Medicine set num=? where ID=?";
	
    static int purId=1;//订单编号
	public static boolean Purchase(String account, int ID,  int num,float price) throws SQLException {
		//获取系统时间
		Date date=new Date();
		
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time=simpleDateFormat.format(date);
		System.out.println(time);
		
		float total=(num*price);//计算总价 保留两位
		System.out.println(total);
		String name=" ";
		String sql4="select num ,name from Medicine where ID="+ID+"";
		try {
			DBDao DB=new DBDao();
			coon=DB.getconn();
			DB.setConn(coon);
			DB.query(sql2);
			rst=DB.getRs();
			if(rst.next())
			{
				purId=rst.getInt(1)+1;
				
			}
			DB.query(sql4);
			rst=DB.getRs();
			int nums=0;
			if(rst.next())
			{
				nums=rst.getInt(1);
				name=rst.getString(2);
			}
			prsmt=coon.prepareStatement(sql);
			prsmt.setInt(1, purId);//写入数据
			prsmt.setString(2, time);
			//prsmt.setDate(2, date);
			prsmt.setInt(3, ID);
			prsmt.setString(4, name);
			prsmt.setInt(5, num);
			prsmt.setFloat(6, total);
			prsmt.setString(7, "购入");
			prsmt.setFloat(8, price);
			prsmt.setString(9,account);
			rst=(ResultSet) prsmt.executeQuery();
			prsmt=coon.prepareStatement(sql3);
			prsmt.setInt(1, num+nums);
			prsmt.setInt(2, ID);
			rst=(ResultSet)prsmt.executeQuery();
			System.out.println(purId);
			//调试 查看表内信息是否完整
			/*
			while (rst.next()) {
				String account1=rst.getString("Account");
				
				String P_ID=rst.getString("P_ID");
				int ID1=rst.getInt("ID");
				String name1= rst.getString("Name");
				String P_Num= rst.getString("P_Num");
				String P_Total=rst.getString("P_Total");
				String p_Reason=rst.getString("P_Reason");
				Double purchase=rst.getDouble("Purchase");
				System.out.println("用户名："+account1+" 订单ID："+P_ID+" 药品ID："+ID+" 药品名称："+name1);
				System.out.println("购入数量："+P_Num+" 单价："+purchase+" 原因："+p_Reason+" 总价："+P_Total);
				
			}*/
			DB.closeall();
			rst.close();
			prsmt.close();
			coon.close();
				System.out.println("1");
			return true;//该买成功，即插入数据库数据成功返回1
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		
	}
	//主函数测试
	/*
	public static void main(String[] args) throws SQLException {
		int isGet;
		  isGet = Purchase("lyb1", 99, "n95", 18,  0.9f);
		  
		}
		*/
	
}
