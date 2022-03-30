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

	// 获取音乐类型
	public ResultSet getmusictype() {
		// TODO Auto-generated method stub
		sql = "select distinct musicType from music";
		try {
			ps = conn.prepareStatement(sql);
			// 获取数据
			rs = ps.executeQuery();
			if (rs.next()) {
				System.out.println("查询音乐类型成功");
				rs = ps.executeQuery();
				return rs;
			} else {
				System.out.println("查询音乐类型失败");
				return rs;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return rs;
		}
	}

	// 分页获取音乐信息
	public ResultSet getfenyemusic(int currentpage, int pagesize, String searchtype) {
		// TODO Auto-generated method stub
		//
		sql = "select * from music where musicType like ? limit ?,? ";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, "%" + searchtype + "%");
			ps.setInt(2, (currentpage - 1) * pagesize);
			ps.setInt(3, pagesize);

			// 获取数据
			rs = ps.executeQuery();
			if (rs.next()) {
				System.out.println("分页查询成功");
				rs = ps.executeQuery();
				return rs;
			} else {
				System.out.println("分页查询失败");
				return rs;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return rs;
		}
	}

	// 获取音乐记录最大页数
	public int gettotal(int pagesize) {
		// 需要对参数进行转化
		//
		sql = "select count(*) num from music";
		try {
			ps = conn.prepareStatement(sql);
			// 获取数据
			rs = ps.executeQuery();
			if (rs.next()) {
				rs.first();
				int total = rs.getInt("num");
				total = total % pagesize == 0 ? (total / pagesize) : (total / pagesize + 1);
				System.out.println("总记录查询成功" + total);
				return total;
			} else {
				System.out.println("总记录查询成功");
				return 0;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}

	// 通过模糊查询获取歌曲
	public ResultSet getSearchMusic(String search) {
		// TODO Auto-generated method stub
		sql = "select * from music where musicname like ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, "%" + search + "%");
			// 获取数据
			rs = ps.executeQuery();
			if (rs.next()) {
				rs = ps.executeQuery();
				System.out.println("查询歌曲成功");
				return rs;
			} else {
				System.out.println("查询歌曲失败");
				return rs;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return rs;
		}
	}

	// 通过模糊查询获取歌曲
	public ResultSet getSearchSinger(String search) {
		// TODO Auto-generated method stub
		sql = "select distinct musicsinger from music where musicsinger like ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, "%" + search + "%");
			// 获取数据
			rs = ps.executeQuery();
			if (rs.next()) {
				rs = ps.executeQuery();
				System.out.println("查询歌手成功");
				return rs;
			} else {
				System.out.println("查询歌手失败");
				return rs;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return rs;
		}
	}
	
	//添加音乐信息
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
				System.out.print("添加成功！");
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
	
	//检查musicid是否存在
	public boolean checkHasMusicId(int musicid) {
		// TODO Auto-generated method stub
		sql = "select * from music where musicid = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, musicid);
			rs = ps.executeQuery();
			if(rs.next()) {
				System.err.println("已经存在");
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
	
	//修改音乐基本信息(通过id来修改)
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
				//修改成功
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
