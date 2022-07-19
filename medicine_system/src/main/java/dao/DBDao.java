package dao;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
public class DBDao {
	
	static String driverClass="oracle.jdbc.driver.OracleDriver"; //oracle������
    static String url="jdbc:oracle:thin:@localhost:1521/workspace";  //����oracle·����ʽ ����gfs������Ҫ�������ӵ����ݿ��� 1521�˿�
    static String user="System";   //user�����ݿ���û���
    static String password="123456";  //�û���¼����
    private Connection conn;
	private Statement stm;
	private ResultSet rs;
    public Connection getconn() {  //Ϊ�˷�������Ľ��⣬����ר�Ž�����һ���������ݿ����ӵ�һ������
        Connection conn=null;
        try {

         //���Ƚ�������
         Class.forName(driverClass);
         //�����ɹ����������
         conn=DriverManager.getConnection(url, user, password);

        System.out.println("���ӳɹ�");
     } catch (SQLException e) {
    	 System.out.println("����ʧ��");
         e.printStackTrace();
     } catch (Exception e) {
    	 System.out.println("ʧ����");
         e.printStackTrace();
     }
        return conn; //����һ������
    }
  //�޸�
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
  	//��ѯ
  	public void query(String sql){
  		try {
  			stm = conn.createStatement();
  			System.out.println("������jinlaile");
  			rs = stm.executeQuery(sql);
  		} catch (SQLException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		}
  	}
  	//�ж���������
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
  	//��ȡ���ֶ�ֵ
  	public String getValue(String field) {
  		//�ǵõ���next
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
  	//���������������
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
  	//�ر�����
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
  	//�ر�statement
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
  	//�ر�ResultSet
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
