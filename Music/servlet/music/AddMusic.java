package music;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * Servlet implementation class AddMusic
 */
@WebServlet("/AddMusic")
public class AddMusic extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddMusic() {
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
		String music = request.getParameter("music");
		JSONObject jsonObject = JSON.parseObject(music);
		System.out.println("?????" + jsonObject);
		Music newmusic = new Music();
		newmusic.musicid = jsonObject.getIntValue("musicid");
		// ---------------------------------------------------------------
		// 需要检查该id是否存在
		boolean isHasMusicid = musicDao.checkHasMusicId(newmusic.musicid);
		System.err.println("???????" + isHasMusicid);
		if (isHasMusicid) {
			System.err.println("???????" + isHasMusicid);
			map.put("tip", "上传失败,MusicId已经存在");
		} else {
			// ---------------------------------------------------------------
			newmusic.musicname = jsonObject.getString("musicname");
			newmusic.musicbackurl = jsonObject.getString("musicbackurl");
			newmusic.musicsinger = jsonObject.getString("musicsinger");
			// ----------------------------处理时间格式-------------
			String getTime = jsonObject.getString("musicDatetime");
			getTime = getTime.replace("T", " ");
			int end = getTime.indexOf('.');
			getTime = getTime.substring(0, end);
			newmusic.musicDatetime = getTime;
			System.out.println("时间" + newmusic.musicDatetime);
			// ----------------------------------------------------
			newmusic.musicType = jsonObject.getString("musicType");
			newmusic.musicoutlink = jsonObject.getString("musicoutlink");

			// --------------------------------------------------------------
			// 添加进数据库
			boolean isAddsuccess = musicDao.AddMusic(newmusic);
			if (isAddsuccess) {
				map.put("tip", true);
			} else {
				map.put("tip", false);
			}
		}
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
