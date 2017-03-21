package etl;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

//Eclipse运行sevlet和jsppublic 
public class EchoForm extends HttpServlet {

	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException {
		// Eclipse运行sevlet和jsp
		res.setContentType("text/html");

		PrintWriter out = res.getWriter();

		Enumeration flds = req.getParameterNames();

		// Eclipse运行sevlet和jsp
		if (!flds.hasMoreElements()) {

			out.print("<html>");
			out.print("<form method=\"POST\"" + "action=\"EchoForm\">");
			for (int i = 0; i < 10; i++)
				out.print("<b>Field" + i + "</b> " + "<input type=\"text\"" + " size=\"20\" name=\"Field" + i
						+ "\" value=\"Value" + i + "\"><br>");
			out.print("<INPUT TYPE=submit name=submit Value=\"Submit\"></form></html>");

			// Eclipse运行sevlet和jsp
		} else {

			out.print("<h1>Your form contained:</h1>");

			// Eclipse运行sevlet和jsp
			while (flds.hasMoreElements()) {
				String field = (String) flds.nextElement();
				String value = req.getParameter(field);
				out.print(field + " = " + value + "<br>");
			}
		}

		out.close();

	}
}