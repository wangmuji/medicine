package biz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.spi.DirStateFactory.Result;

import org.apache.taglibs.standard.tag.el.sql.UpdateTag;

import dao.DBDao;

public class PassswordChange {
	
	
	public static Connection coon;
	public static PreparedStatement prsmt;
	public static ResultSet rst;
	public static String sql="UPDATE USERS set PASSWORD = ? where ACCOUNT = ?";
	public static String sql1="select PASSWORD from USERS WHERE ACCOUNT=? ";
	//public static String sql="insert into USERS(ACCOUNT,PASSWORD,AUTHORITY,M_TABLE,P_TABLE) values('1','1',1,'1','1')";
	
	public static int update(String useraccount, String password) {//�޸ĺ���
		try {
			DBDao DB=new DBDao();
			coon=DB.getconn();
			prsmt=coon.prepareStatement(sql);
			
			prsmt.setString(1, password);
			prsmt.setString(2, useraccount);
			System.out.println(sql);
			
			
			
			rst=(ResultSet) prsmt.executeQuery();
			System.out.println("���³ɹ�");
			/*
			
			//���Ա��
			while (rst.next()) {
				String account=rst.getString("ACCOUNT");
				
				String pass=rst.getString("PASSWORD");
				int authority=rst.getInt("AUTHORITY");
				String m_Table= rst.getString("M_TABLE");
				String s_Table= rst.getString("P_TABLE");
				System.out.println("�û�����"+account+" ���룺"+pass+" Ȩ�ޣ�"+authority);
				
			}
			*/
			rst.close();
			prsmt.close();
			coon.close();
			System.out.println("1");
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return 0;
		}
		
	}
	public static String GetPassword(String account) {//�����ݿ��еõ�ԭ�����룬�������ã����ݴ���Userʵ������ɾ��
		String Password = null;
		try {
			DBDao DB=new DBDao();
			coon=DB.getconn();
			prsmt=coon.prepareStatement(sql1);
			
			prsmt.setString(1,account);
			
			System.out.println(sql1);
			
			
			
			rst=(ResultSet) prsmt.executeQuery();
			while (rst.next()) {
				
				Password=rst.getString(1);
				System.out.println(Password+" ��ȡ�ɹ�");
			}
			
			
			
			rst.close();
			prsmt.close();
			coon.close();
			System.out.println("1");
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
		}
		return Password;
		
	}
	//public static void main(String[] args) throws SQLException {
		
	  //GetPassword("lyb");
	//}

}
