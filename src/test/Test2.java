package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import javax.xml.rpc.ServiceException;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

import com.csvreader.CsvWriter;

import edu.sjtu.webservice.dto.User;

public class Test2 {
	public static void main(String[] args) throws Exception {
		// String a = "#job 123,456,789";
		// int b = a.lastIndexOf("#job ");
		// a = a.replace("#job ", "");
		// String[] c = a.split(",");
		// try {
		// InetAddress addr = InetAddress.getLocalHost();
		// String ip = addr.getHostAddress().toString();// 获得本机IP
		// String address = addr.getHostName().toString();// 获得本机名称
		// System.out.println(ip);
		// System.out.println(address);
		// } catch (UnknownHostException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		// String fileName="aaa.aaa.ab";
		// String prefix=fileName.substring(fileName.lastIndexOf(".")+1);
		// System.out.println(prefix);

		// String endpointUrl =
		// "http://192.168.154.2:8080/WebServiceTest1/services/CalculateService";
		// Service service = new Service();
		// Call call = (Call) service.createCall();
		// call.setTargetEndpointAddress(new URL(endpointUrl));
		// call.setOperationName("plus");
		// String res = (String) call.invoke(new Object[] {1,2});
		// System.out.println(res);

//		writeCsv();
//		String endpointUrl = "http://localhost:8080/WebServiceTest1/services/JobReportService";
//		File file = new File("a.csv");
//
//		FileInputStream in = new FileInputStream(file);
//
//		byte[] bs = new byte[in.available()];
//		in.read(bs);
//        in.close();
//		Service service = new Service();
//		Call call = (Call) service.createCall();
//		call.setTargetEndpointAddress(new URL(endpointUrl));
//		call.setOperationName("uploadFile");
//		List<byte[]> list = new ArrayList<byte[]>();
//		list.add(bs);
//		String res = (String) call.invoke(new Object[]{ list, "b.csv" });
//		System.out.println(res);
		
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
			File file = new File("C:\\Users\\Administrator\\Desktop\\SecureCRT\\download\\100.csv");

			FileInputStream in = new FileInputStream(file);

			byte[] bs = new byte[in.available()];
			in.read(bs);
			in.close();
			user.setUserJob(bs);
			String res = (String) call.invoke(new Object[] { user });
			System.out.println(res);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void writeCsv() throws IOException {
		CsvWriter csvWriter = new CsvWriter("a.csv", ',', Charset.forName("UTF-8"));
		String[] a = { "id", "name" };
		String[] b = { "1", "jack" };
		String[] c = { "2", "kara" };
		csvWriter.writeRecord(a);
		csvWriter.writeRecord(b);
		csvWriter.writeRecord(c);
		csvWriter.close();
		System.out.println("csv write OK");
	}
}
