package music;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
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
 * Servlet implementation class SearchmusicInit
 */
@WebServlet("/SearchmusicInit")
public class SearchmusicInit extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchmusicInit() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		request.setCharacterEncoding("utf-8");
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		Map<String, Object> map = new HashMap<String, Object>();
		MusicDao musicDao = new MusicDao();
		// -------------------------------------------------------------
		// 获取前台发送过来的数据
		String currentpage = request.getParameter("currentpage");
		String pagesize = request.getParameter("pagesize");
		String searchtype = request.getParameter("searchtype");
		System.out.println("???" + searchtype);

		String musiclist = request.getParameter("musiclist");
		List<Music> allmusiclist = JSON.parseArray(musiclist, Music.class);
		int newcurrent = 1;
		int newpagesize = 0;
		if (currentpage != null && pagesize != null) {
			System.out.println("current  " + currentpage + " page " + pagesize);
			System.out.println("musiclist" + allmusiclist);
			newcurrent = Integer.parseInt(currentpage);
			newpagesize = Integer.parseInt(pagesize);
			if(searchtype == null || searchtype == "") searchtype = "";
		}

		int totalnum = musicDao.gettotal(newpagesize);
		Getinterface getinterface = new Getinterface();
		// 接着继续查询对应页数的图书
		ResultSet rs = musicDao.getfenyemusic(newcurrent, newpagesize,searchtype);
		try {
			while (rs.next()) {
				Music music = new Music();
				music.musicid = rs.getInt("musicid");
				music.musicname = rs.getString("musicname");
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
		
		map.put("musiclist", allmusiclist);
		map.put("totalnum", totalnum);
		String Json = JSON.toJSONString(map);
		out.print(Json);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
