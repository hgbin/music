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
 * Servlet implementation class GetUserLoveMusic
 */
@WebServlet("/GetUserLoveMusic")
public class GetUserLoveMusic extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetUserLoveMusic() {
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
		UserLoveDao userLoveDao = new UserLoveDao();
		// -------------------------------------------------------------
		//获取前端发送过来的信息(userid
		String userid = request.getParameter("userid");
		System.out.println("userid " + userid);
		int newuserid = Integer.parseInt(userid);
		//-------------------------------------------------------------
		//调用Dao的获取列表函数
		ResultSet rs = userLoveDao.getUerLovelist(newuserid);
		List<Music> allLovemusiclist = new ArrayList<Music>();
		Getinterface getinterface = new Getinterface();
		try {
			while(rs.next()) {
				Music music = new Music();
				music.musicid = rs.getInt("musicid");
				music.musicname = rs.getString("musicname");
				System.out.println(" 获取到的音乐列表的名称 " + music.musicname );
				music.musicsinger = rs.getString("musicsinger");
				music.musicurl = rs.getString("musicurl");	
				music.musicurl = getinterface.getmusicurl() + music.musicurl;
				music.musicDatetime = rs.getString("musicDatetime");
				music.musicType = rs.getString("musicType");
				music.musicbackurl = rs.getString("musicbackurl");
				music.musicbackurl = getinterface.getimgurl() + music.musicbackurl;
				music.musicoutlink = rs.getString("musicoutlink");
				allLovemusiclist.add(music);
			}
			map.put("lovelist", allLovemusiclist);
			String Json = JSON.toJSONString(map);
			out.print(Json);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
