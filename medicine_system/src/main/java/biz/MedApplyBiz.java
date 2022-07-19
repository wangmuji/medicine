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
	public static Connection coon1;//���ڲ��ҵ�����
	public static PreparedStatement prsmt;
	public static PreparedStatement prsmt1;//���ڲ���
	public static ResultSet rst;
	public static ResultSet rst1;//���ڲ���
	public static String sql_apply="insert into A_Message values(?,?,?,?,?,?,?,?)";
	public static String sql_search="select * from Medicine where ID= ?";
	public static String m_name;
	public static int A_ID=0;//������
	public static String account;
	public static String medicine;
	
	
	
	public static List<Medicine> all_medicine(String account) throws SQLException {//�����û���ȡ����ҩƷ��Ϣ
		
		try {
			DBDao DB=new DBDao();
			coon=DB.getconn();
			prsmt=coon.prepareStatement("select * from Medicine order by id ");//���ݿ������Ĵ�Сдע����
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
			System.out.println("0�رճɹ�");
			return medicines;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
		
	}
	
	
	public static void search(int ID) throws SQLException {//����ID��medicine���в���ҩƷname
		
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
			System.out.println("1�رճɹ�");
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static int med_apply(String account , int ID , int num) throws SQLException{
		//���������¼��A_apply����
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
			System.out.println("��ʱAID��"+A_ID);
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
			
			System.out.println(A_ID+"����ɹ�");
			
			rst.close();
			prsmt.close();
			coon.close();
			System.out.println("2�رճɹ�");
			
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return 0;
		}
		
		
	}
	//����������
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
