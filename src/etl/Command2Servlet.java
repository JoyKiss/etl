package etl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

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
@WebServlet("/Command2Servlet")
public class Command2Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String command = request.getParameter("command");
		String[] commandList = command.split(";");
		List<String> list = new ArrayList<String>();
		Map<String ,String> paramMap = RequestParam2Map.transf(request);
		for(String str : commandList){
			list.add(str);
		}
		List<String> messageList = new ArrayList<String>();
		if(list.size() > 0){
			try {
				messageList = ExecutCommand.execute(list,paramMap);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
		}
		
		String message = "";
		for (String str : messageList){
			message = message + str;
		}
		request.setAttribute("message", message);
	
	}

}
