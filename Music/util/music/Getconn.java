package music;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Getconn {
	public static Connection getconn() {
		String url = "jdbc:mysql://localhost:3306/music?user=root&password=123";
		url += "&useSSL=false&serverTimezone=Asia/Shanghai";
		url += "&charaterEncoding=utf-8";

		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url);
			return conn;
		} catch (SQLException e) {
			System.out.println("�����ݿ�ʧ��...");
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public static void stop(Connection conn, PreparedStatement ps) {
		// �ر����ݿ����
		try {
			if (conn != null) {
				conn.close();
			}
			if (ps != null) {
				ps.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("�ر����ݿ�ʧ��...");
			e.printStackTrace();
		}
	}
}
