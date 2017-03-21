package etl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.ExecutCommand;
import util.RequestParam2Map;

/**
 * Servlet implementation class CommandServlet
 */
@WebServlet("/Command3Servlet")
public class Command3Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<String> messageList = new ArrayList<String>();
		String type = request.getParameter("type");
		String[] checkList = { "name", "passwd", "ip", "command", "filePath", "fileName" };
		Map<String, String> paramMap = new HashMap<String,String>();
		if (!emptyCheck(checkList, request) && !"3".equals(type)) {
			messageList.add("存在未输入项目。");
		} else {
			String command = request.getParameter("command");
			String[] commandList = command.split(";");
			List<String> list = new ArrayList<String>();
			paramMap = RequestParam2Map.transf(request);
			for (String str : commandList) {
				list.add(str);
			}

			if (list.size() > 0) {
				try {
					messageList = ExecutCommand.execute2(list, paramMap);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					messageList.add(e.toString());
				}

			}
		}
		String message = "";
		if (messageList != null) {
			for (String str : messageList) {
				message = message + "<br />" + str;
			}
			request.setAttribute("message", message);
		}
		
		if(paramMap != null){
			Set<String> keySet = paramMap.keySet();
			keySet.iterator();
			for(Iterator<String> it= keySet.iterator();it.hasNext();){
				String key = it.next();
				String value = paramMap.get(key);
				request.setAttribute(key, value);
			}
		}
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

	private boolean emptyCheck(String[] checkList, HttpServletRequest request) {
		for (String cehckStr : checkList) {
			String param = request.getParameter(cehckStr);
			if (param == null || "".equals(param.trim())) {
				return false;
			}
		}
		return true;

	}

}
