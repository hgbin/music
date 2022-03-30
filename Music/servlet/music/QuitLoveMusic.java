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

/**
 * Servlet implementation class QuitLoveMusic
 */
@WebServlet("/QuitLoveMusic")
public class QuitLoveMusic extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuitLoveMusic() {
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
		//获取前端发送过来的数据
		String musicid = request.getParameter("musicid");
		String userid = request.getParameter("userid");
		System.out.println("JOIN" + musicid + "  " +userid);
		int newuserid = Integer.parseInt(userid);
		int newmusicid = Integer.parseInt(musicid);
		//--------------------------------------------------------------
		boolean isQuitsuccess = userLoveDao.QuitLove(newuserid, newmusicid);
		if(isQuitsuccess) {
			map.put("tip", true);
		}else {
			map.put("tip", false);
		}
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
