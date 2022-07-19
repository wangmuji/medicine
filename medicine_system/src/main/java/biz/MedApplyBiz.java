package biz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dao.DBDao;
import entity.Medicine;
import entity.User;

public class MedApplyBiz {

	public static Connection coon;
	public static Connection coon1;//用于查找的连接
	public static PreparedStatement prsmt;
	public static PreparedStatement prsmt1;//用于查找
	public static ResultSet rst;
	public static ResultSet rst1;//用于查找
	public static String sql_apply="insert into A_Message values(?,?,?,?,?,?,?,?)";
	public static String sql_search="select * from Medicine where ID= ?";
	public static String m_name;
	public static int A_ID=0;//申请编号
	public static String account;
	public static String medicine;
	
	
	
	public static List<Medicine> all_medicine(String account) throws SQLException {//根据用户读取所有药品信息
		
		try {
			DBDao DB=new DBDao();
			coon=DB.getconn();
			prsmt=coon.prepareStatement("select * from Medicine order by id ");//数据库列名的大小写注意下
			rst=(ResultSet) prsmt.executeQuery();
			List<Medicine> medicines=new ArrayList<Medicine>();			
			while(rst.next()) {
				Medicine medicine=new Medicine();
				medicine.setId(rst.getInt("ID"));
				//int iDString=rst.getInt("ID");
				
				medicine.setName(rst.getString("Name"));
				medicine.setNum(rst.getInt("Num"));
				medicine.setPrice(rst.getFloat("Price"));
			//	medicine.setAccount(rst.getString("Account"));
				medicines.add(medicine);
				System.out.println(medicine.getId()+medicine.getName());
			}
			rst.close();
			prsmt.close();
			coon.close();
			System.out.println("0关闭成功");
			return medicines;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
		
	}
	
	
	public static void search(int ID) throws SQLException {//根据ID从medicine表中查找药品name
		
		try {
			DBDao DB=new DBDao();
			coon1=DB.getconn();
			prsmt1=coon1.prepareStatement(sql_search);
			prsmt1.setInt(1, ID);
			
			rst1=(ResultSet) prsmt1.executeQuery();
			
			while(rst1.next()) {
				m_name=rst1.getString("Name");
				System.out.println(m_name);
			}
			rst1.close();
			prsmt1.close();
			coon1.close();
			System.out.println("1关闭成功");
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static int med_apply(String account , int ID , int num) throws SQLException{
		//插入申请记录到A_apply表中
		try {
			DBDao DB=new DBDao();
			coon=DB.getconn();
			String sql4="select A_ID from A_message order by A_ID desc";
			DB.setConn(coon);
			DB.query(sql4);
			rst=DB.getRs();
			if(rst.next())
			{
				A_ID=rst.getInt(1)+1;
			}
			prsmt=coon.prepareStatement(sql_apply);
			System.out.println("此时AID是"+A_ID);
			prsmt.setInt(1, A_ID);
			prsmt.setInt(2, ID);
			search(ID);
			prsmt.setString(3, m_name);
			prsmt.setInt(4, num);
			prsmt.setString(5, account);
			prsmt.setInt(6, 0);		
			Date date = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
			String A_time = formatter.format(date);
			prsmt.setString(7, A_time);
			prsmt.setString(8, null);
			rst=(ResultSet) prsmt.executeQuery();
			
			System.out.println(A_ID+"插入成功");
			
			rst.close();
			prsmt.close();
			coon.close();
			System.out.println("2关闭成功");
			
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return 0;
		}
		
		
	}
	//调试主函数
	/*
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//search(1234);
		int a;
		List<Medicine> medicines=new ArrayList<Medicine>();
		try {
			medicines=all_medicine("lyb");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}*/

}
