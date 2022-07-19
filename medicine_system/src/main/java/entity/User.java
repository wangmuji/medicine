package entity;

public class User {
	private String account;
	private String password;
	private int authority;
	private String M_table;
	private String P_table;
	private String S_table;
	private String Shop;
	private String username;
	private String Address;
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getAuthority() {
		return authority;
	}
	public void setAuthority(int authority) {
		this.authority = authority;
	}
	public String getM_table() {
		return M_table;
	}
	public void setM_table(String m_table) {
		M_table = m_table;
	}
	public String getP_table() {
		return P_table;
	}
	public void setP_table(String p_table) {
		P_table = p_table;
	}
	public String getS_table() {
		return S_table;
	}
	public void setS_table(String s_table) {
		S_table = s_table;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getShop() {
		return Shop;
	}
	public void setShop(String shop) {
		Shop = shop;
	}

	
}
