package etl;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.ReadFromFile;

/**
 * Servlet implementation class FileCopyServlet
 */
@WebServlet("/FileCopyServlet")
public class FileCopyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileCopyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fileFrom = request.getParameter("fileFrom");
		String fileTo = request.getParameter("fileTo");
		
		boolean copyFlag = ReadFromFile.copyFile(fileFrom, fileTo, true);
				
		request.setAttribute("message", copyFlag);
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

}
