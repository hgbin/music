package music;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MusicDao {
	Connection conn = Getconn.getconn();
	PreparedStatement ps = null;
	ResultSet rs = null;
	String sql = "";

	// ��ȡ��������
	public ResultSet getmusictype() {
		// TODO Auto-generated method stub
		sql = "select distinct musicType from music";
		try {
			ps = conn.prepareStatement(sql);
			// ��ȡ����
			rs = ps.executeQuery();
			if (rs.next()) {
				System.out.println("��ѯ�������ͳɹ�");
				rs = ps.executeQuery();
				return rs;
			} else {
				System.out.println("��ѯ��������ʧ��");
				return rs;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return rs;
		}
	}

	// ��ҳ��ȡ������Ϣ
	public ResultSet getfenyemusic(int currentpage, int pagesize, String searchtype) {
		// TODO Auto-generated method stub
		//
		sql = "select * from music where musicType like ? limit ?,? ";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, "%" + searchtype + "%");
			ps.setInt(2, (currentpage - 1) * pagesize);
			ps.setInt(3, pagesize);

			// ��ȡ����
			rs = ps.executeQuery();
			if (rs.next()) {
				System.out.println("��ҳ��ѯ�ɹ�");
				rs = ps.executeQuery();
				return rs;
			} else {
				System.out.println("��ҳ��ѯʧ��");
				return rs;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return rs;
		}
	}

	// ��ȡ���ּ�¼���ҳ��
	public int gettotal(int pagesize) {
		// ��Ҫ�Բ�������ת��
		//
		sql = "select count(*) num from music";
		try {
			ps = conn.prepareStatement(sql);
			// ��ȡ����
			rs = ps.executeQuery();
			if (rs.next()) {
				rs.first();
				int total = rs.getInt("num");
				total = total % pagesize == 0 ? (total / pagesize) : (total / pagesize + 1);
				System.out.println("�ܼ�¼��ѯ�ɹ�" + total);
				return total;
			} else {
				System.out.println("�ܼ�¼��ѯ�ɹ�");
				return 0;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}

	// ͨ��ģ����ѯ��ȡ����
	public ResultSet getSearchMusic(String search) {
		// TODO Auto-generated method stub
		sql = "select * from music where musicname like ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, "%" + search + "%");
			// ��ȡ����
			rs = ps.executeQuery();
			if (rs.next()) {
				rs = ps.executeQuery();
				System.out.println("��ѯ�����ɹ�");
				return rs;
			} else {
				System.out.println("��ѯ����ʧ��");
				return rs;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return rs;
		}
	}

	// ͨ��ģ����ѯ��ȡ����
	public ResultSet getSearchSinger(String search) {
		// TODO Auto-generated method stub
		sql = "select distinct musicsinger from music where musicsinger like ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, "%" + search + "%");
			// ��ȡ����
			rs = ps.executeQuery();
			if (rs.next()) {
				rs = ps.executeQuery();
				System.out.println("��ѯ���ֳɹ�");
				return rs;
			} else {
				System.out.println("��ѯ����ʧ��");
				return rs;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return rs;
		}
	}
	
	//���������Ϣ
	public boolean AddMusic(Music music) {
		// TODO Auto-generated method stub
		sql = "insert into music(musicid,musicname,musicsinger"
				+ ",musicDatetime,musicType,musicbackurl,"
				+ "musicoutlink) values(?,?,?,?,?,?,?)";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, music.musicid);
			ps.setString(2, music.musicname);
			ps.setString(3, music.musicsinger);
			ps.setString(4, music.musicDatetime);
			ps.setString(5, music.musicType);
			ps.setString(6, music.musicbackurl);
			ps.setString(7, music.musicoutlink);
			
			int val = ps.executeUpdate();
			if(val>0) {
				System.out.print("��ӳɹ���");
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
	
	//���musicid�Ƿ����
	public boolean checkHasMusicId(int musicid) {
		// TODO Auto-generated method stub
		sql = "select * from music where musicid = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, musicid);
			rs = ps.executeQuery();
			if(rs.next()) {
				System.err.println("�Ѿ�����");
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
	
	//�޸����ֻ�����Ϣ(ͨ��id���޸�)
	public boolean UpdateMusic(Music music) {
		// TODO Auto-generated method stub
		sql = "update music set musicname = ?,"
				+ "musicsinger = ?, musicDatetime = ?,"
				+ "musicType = ?,musicbackurl = ?,"
				+ "musicoutlink = ? where musicid = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, music.musicname);
			ps.setString(2, music.musicsinger);
			ps.setString(3, music.musicDatetime);
			ps.setString(4, music.musicType);
			ps.setString(5, music.musicbackurl);
			ps.setString(6, music.musicoutlink);
			ps.setInt(7, music.musicid);
			
			int val = ps.executeUpdate();
			if(val>0) {
				//�޸ĳɹ�
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
}
