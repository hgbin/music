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
 * Servlet implementation class UpdateMusic
 */
@WebServlet("/UpdateMusic")
public class UpdateMusic extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateMusic() {
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
		newmusic.musicname = jsonObject.getString("musicname");
		String tempurl = jsonObject.getString("musicbackurl");
		System.out.println("��Ƭ" + tempurl);
		int start = tempurl.lastIndexOf('/');
		tempurl = tempurl.substring(start+1);
		System.out.println("��Ƭ" + tempurl);
		newmusic.musicbackurl = tempurl;
		//��Ҫ��ͼƬ��ʽ���д���
//		System.out.println("��Ƭ" + newmusic.musicbackurl);
		//----------------------------------------------------------------
		newmusic.musicsinger = jsonObject.getString("musicsinger");
		String getTime = jsonObject.getString("musicDatetime");
		int isHasT = getTime.indexOf('T');
		if(isHasT>=0) {//���ʱ���ʽ�Ǵ���ģ��Ž���ת��
			getTime = getTime.replace("T", " ");
			int end = getTime.indexOf('.');
			getTime = getTime.substring(0, end);
		}
		newmusic.musicDatetime = getTime;
		System.out.println("ʱ��" + newmusic.musicDatetime);
		// ----------------------------------------------------
		newmusic.musicType = jsonObject.getString("musicType");
		newmusic.musicoutlink = jsonObject.getString("musicoutlink");

		// --------------------------------------------------------------
		// �޸����ݿ�
		boolean isUpdatesuccess = musicDao.UpdateMusic(newmusic);
		if (isUpdatesuccess) {
			map.put("tip", true);
		} else {
			map.put("tip", false);
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
