package music;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

/**
 * Servlet implementation class SearchAll
 */
@WebServlet("/SearchAll")
public class SearchAll extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchAll() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		request.setCharacterEncoding("utf-8");
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		Map<String, Object> map = new HashMap<String, Object>();
		MusicDao musicDao = new MusicDao();
		// -------------------------------------------------------------
		//获取前端发送来的数据（search）
		String search = request.getParameter("search");
		System.out.println("获取到的搜索" + search);
		
		//--------------------------------------------------------------
		//调用Dao模糊查询后台数据库对应的歌手和歌曲
		Getinterface getinterface = new Getinterface();
		List<Music> allmusiclist = new ArrayList<Music>();
		List<Music> allsinger = new ArrayList<Music>();
		ResultSet rs = musicDao.getSearchMusic(search);
		try {
			while(rs.next()) {
				Music music = new Music();
				music.musicid = rs.getInt("musicid");
				music.musicname = rs.getString("musicname");
				System.out.println(music.musicname);  
				music.musicsinger = rs.getString("musicsinger");
				music.musicurl = rs.getString("musicurl");	
				music.musicurl = getinterface.getmusicurl() + music.musicurl;
				music.musicDatetime = rs.getString("musicDatetime");
				music.musicType = rs.getString("musicType");
				music.musicbackurl = rs.getString("musicbackurl");
				music.musicbackurl = getinterface.getimgurl() + music.musicbackurl;
				music.musicoutlink = rs.getString("musicoutlink");
				allmusiclist.add(music);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ResultSet rs1 = musicDao.getSearchSinger(search);
		try {
			while(rs1.next()) {
				Music music = new Music();
				music.musicsinger = rs1.getString("musicsinger");
				System.out.println(music.musicsinger);  
				allsinger.add(music);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		map.put("searchsongs", allmusiclist);
		map.put("searchsingers", allsinger);
		String Json = JSON.toJSONString(map);
		out.print(Json);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
