package etl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.ExecutCommand;

/**
 * Servlet implementation class CommandServlet
 */
@WebServlet("/CommandServlet")
public class CommandServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String command = request.getParameter("command");
		String[] commandList = command.split(";");
		List<String> list = new ArrayList<String>();
		List<String> messageList = new ArrayList<String>();
		for(String str : commandList){
			list.add(str);
		}
		
		if(list.size() > 0){
			try {
				ExecutCommand.execute(list);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		String message = "";
		if (messageList != null) {
			for (String str : messageList) {
				message = message + "<br />" + str;
			}
			request.setAttribute("message", message);
		}
		
	
	}

}
