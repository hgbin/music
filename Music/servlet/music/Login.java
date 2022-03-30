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
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
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
		UserDao userDao = new UserDao();
		//---------------------------------
		//获取前端传递过来的username、pwd
		String username = request.getParameter("username");
		String pwd = request.getParameter("pwd");
		System.out.print(username + pwd);
		//-----------------------------------
		//接着需要进行对User表查询
		int userid = -1;
		boolean isExist = false;
		userid = userDao.checkUser(username, pwd);
		if(userid>=1) {//如果用户存在
			System.out.print("该用户存在");
			isExist = true;
			map.put("isExist", isExist);
			map.put("userid", userid);
		}else {
			map.put("isExist", isExist);
			map.put("userid", userid);
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
