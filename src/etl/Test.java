package etl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.rpc.ServiceException;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

import edu.sjtu.webservice.dto.User;

/**
 * Servlet implementation class Test
 */
@WebServlet("/Test")
public class Test extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String endpointUrl = "http://192.168.154.3:8080/WebServiceTest1/services/UserGetMessageService";
		Service service = new Service();
		Call call;
		try {
			call = (Call) service.createCall();
			call.setTargetEndpointAddress(new URL(endpointUrl));
			call.setOperationName("getUser");
			User user = new User();
			user.setName("jack");
			user.setId("11");
			user.setAge("10");
			user.setPasswd("asd123");
			System.out.println("start");
			File file = new File("/common/crontab/tmp/100.csv");

			FileInputStream in = new FileInputStream(file);

			byte[] bs = new byte[in.available()];
			in.read(bs);
			in.close();
			user.setUserJob(bs);
			System.out.println(bs.length);
			String res = (String) call.invoke(new Object[] { user });
			System.out.println(res);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
