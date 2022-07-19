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
	public static String sql1="select * from P_Message ";//�������
	public static String sql3="update Medicine set num=? where ID=?";
	
    static int purId=1;//�������
	public static boolean Purchase(String account, int ID,  int num,float price) throws SQLException {
		//��ȡϵͳʱ��
		Date date=new Date();
		
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time=simpleDateFormat.format(date);
		System.out.println(time);
		
		float total=(num*price);//�����ܼ� ������λ
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
			prsmt.setInt(1, purId);//д������
			prsmt.setString(2, time);
			//prsmt.setDate(2, date);
			prsmt.setInt(3, ID);
			prsmt.setString(4, name);
			prsmt.setInt(5, num);
			prsmt.setFloat(6, total);
			prsmt.setString(7, "����");
			prsmt.setFloat(8, price);
			prsmt.setString(9,account);
			rst=(ResultSet) prsmt.executeQuery();
			prsmt=coon.prepareStatement(sql3);
			prsmt.setInt(1, num+nums);
			prsmt.setInt(2, ID);
			rst=(ResultSet)prsmt.executeQuery();
			System.out.println(purId);
			//���� �鿴������Ϣ�Ƿ�����
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
				System.out.println("�û�����"+account1+" ����ID��"+P_ID+" ҩƷID��"+ID+" ҩƷ���ƣ�"+name1);
				System.out.println("����������"+P_Num+" ���ۣ�"+purchase+" ԭ��"+p_Reason+" �ܼۣ�"+P_Total);
				
			}*/
			DB.closeall();
			rst.close();
			prsmt.close();
			coon.close();
				System.out.println("1");
			return true;//����ɹ������������ݿ����ݳɹ�����1
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		
	}
	//����������
	/*
	public static void main(String[] args) throws SQLException {
		int isGet;
		  isGet = Purchase("lyb1", 99, "n95", 18,  0.9f);
		  
		}
		*/
	
}
