package music;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
	Connection conn = Getconn.getconn();
	PreparedStatement ps = null;
	ResultSet rs = null;
	String sql = "";
	
	public int checkUser(String username,String userpwd) {
		// TODO Auto-generated method stub
		sql = "select * from user where username = ? and userpwd = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, userpwd);
			rs = ps.executeQuery();
			if(rs.next()) {
				//�ҵ��û�
				return rs.getInt("userid");
			}else {
				//û���ҵ��û�
				return -1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
	}
	
	public boolean insertUser(String username,String userpwd) {
		// TODO Auto-generated method stub
		sql = "insert into user(username,userpwd) values(?,?)";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, userpwd);
			int val = ps.executeUpdate();
			if(val>0) {
				//˵��ע��ɹ�
				System.out.print("�û�ע��ɹ�!");
				return true;
			}else {//ע��ʧ��
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
}
