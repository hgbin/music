package music;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserLoveDao {
	Connection conn = Getconn.getconn();
	PreparedStatement ps = null;
	ResultSet rs = null;
	String sql = "";
	
	public boolean CheckUserLove(int userid , int musicid) {
		// TODO Auto-generated method stub
		sql = "select * from userlove where userid = ? and musicid =?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, userid);
			ps.setInt(2, musicid);
			rs = ps.executeQuery();
			if(rs.next()) {
				return true;
			}else {
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean JoinIntoLove(int userid , int musicid) {
		// TODO Auto-generated method stub
		sql = "insert into userlove values(?,?)";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, userid);
			ps.setInt(2, musicid);
			int val = ps.executeUpdate();
			if(val > 0) {
				return true;
			}else {
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean QuitLove(int userid , int musicid) {
		// TODO Auto-generated method stub
		sql = "delete from userlove where userid = ? and musicid = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, userid);
			ps.setInt(2, musicid);
			int val = ps.executeUpdate();
			if(val > 0) {
				return true;
			}else {
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}
	
	public ResultSet getUerLovelist(int userid) {
		// TODO Auto-generated method stub
		sql = "select a.* from music a, userlove b "
				+ "where a.musicid  = b.musicid and b.userid = ? ";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, userid);
			rs = ps.executeQuery();
			if(rs.next()) {
				rs = ps.executeQuery();
				return rs;
			}else {
				return rs;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return rs;
		}
		
	}
}
