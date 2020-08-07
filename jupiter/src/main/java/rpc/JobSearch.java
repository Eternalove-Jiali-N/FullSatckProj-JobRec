package rpc;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import entity.Item;
import external.GitHubClient;

/**
 * Servlet implementation class JobSearch
 */
public class JobSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JobSearch() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession(false);
		if (session == null) {
			response.setStatus(403);
			return;
		}
		
		double lat = Double.parseDouble(request.getParameter("lat"));
		double lon = Double.parseDouble(request.getParameter("lon"));

		GitHubClient client = new GitHubClient();
		List<Item> items = client.search(lat, lon, null);
		JSONArray array = new JSONArray();
		for (Item item : items) {
			array.put(item.toJSONObject());
		}
		RpcHelper.writeJsonArray(response, array);
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		/*response.setContentType("application/json");
		JSONObject obj = new JSONObject();
		obj.put("username","zile wang");
		obj.put("age", "24");
		PrintWriter writer = response.getWriter();
		writer.print(obj);*/
		/*response.setContentType("application/json");
		PrintWriter writer = response.getWriter();
		
		String user = request.getParameter("username");
		JSONArray array = new JSONArray();
		array.put(new JSONObject().put("username", "abcd"));
		array.put(new JSONObject().put("username", "1234"));
		writer.print(array);*/

 	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session == null) {
			response.setStatus(403);
			return;
		}
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
