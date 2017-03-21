package test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class Test3 {
	@SuppressWarnings("deprecation")
	public static void main(String[] args)
			throws IOException, InterruptedException {
		// Properties props=System.getProperties(); //获得系统属性集
		// String osName = props.getProperty("os.name"); //操作系统名称
		// String osArch = props.getProperty("os.arch"); //操作系统构架
		// String osVersion = props.getProperty("os.version"); //操作系统版本
		// System.out.println(osName);
		// System.out.println(osArch);
		// System.out.println(osVersion);
		// 指定读取的行号
		// int lineNumber = 4;
		// // 读取文件
		// File sourceFile = new File("D:\\text.log");
		// // 读取指定的行
		// readAppointedLineNumber(sourceFile, lineNumber);
		// // 获取文件的内容的总行数
		// System.out.println(getTotalLines(sourceFile));
		//
		// List<String> list = new ArrayList<String>();
		// list.add("aaaaaaaaaaaaaaaaaaaaa6");
		// FileUtil.fileWrite("text.log", "D:\\", list);

		// File file = new File("D:\\vm\\CentOS 64 位 的克隆\\vmware.log");
		//
		// System.out.println(file.getName());
		// System.out.println(file.getPath());
		// String a = "cron.c";
		// System.err.println(a.contains("cron"));
		// List<String> list = new ArrayList<String>();
		// list.add("aaa");
		// list.add("aaa");
		// list.add("aaa");
		// list.add("aaa");
		// list.add("aaa");
		// JSONArray jsa = JSONArray.fromObject(list);
		// String jss = jsa.toString();
		// System.out.println(jss);
		// JSONArray jsa2 = JSONArray.fromObject(jss);
		// List<String> list2 = JSONArray.toList(jsa2);
		// System.out.println(list2.toString());
		// String a = "aa";
		// String[] aList = a.split("/");
		// System.out.println(aList[aList.length - 1]);
		//
		// System.out.println(System.getProperty("user.name"));

		// LineNumberReader br = new LineNumberReader(new InputStreamReader(
		// new FileInputStream(new File("D:\\tmp\\bklog\\cron.txt.bk"))));
		// StringBuffer sb = new StringBuffer();
		// String line;
		// while ((line = br.readLine()) != null) {
		// sb.append(line).append("\n");
		// }
		// System.out.println(sb.toString());

		// Path path = Paths.get("D:/", "/a.xt");
		// System.out.println(path);
		//
		// String a = "/var/log/mail/";
		// System.out.println(a.contains("/var/log"));
		// List<String> a = new ArrayList<String>();
		// a.add("666666");
		// a.add("666666");
		// a.add("666666");
		// a.add("666666");
		// //
		// // logger("输出", a, Test3.class);
		// JSONArray jsonObject = JSONArray.fromObject(a);
		// List<String> b = (List<String>) JSONArray.toCollection(jsonObject,
		// String.class);
		// for (String str : b) {
		// System.out.println(str);
		// }
		// System.err.println(a.getClass() == java.util.ArrayList.class);
		// int i = 10;
		// while (true) {
		// Thread.sleep(1000);
		// try {
		// i--;
		// System.out.println(10000 / i);
		// } catch (Exception e) {
		// } finally {
		// continue;
		// }
		// }

		// long interval = TimeUnit.SECONDS.toMillis(60);
		// System.out.println(interval);
		// String a = "* * * * * type=kjb/usr/job/program/clearJobLog.kjb
		// /usr/log/joblog/ 20";
		// String b = "";
		// if (StringUtils.isNotBlank(a)) {
		//
		// String kjbktrPath = a.substring(0, 3);
		// if (kjbktrPath.contains("type=kjb")) {
		// b = a.replace("type=kjb", kjbktrPath);
		// } else if (kjbktrPath.contains("type=ktr")) {
		// b = a.replace("type=ktr", kjbktrPath);
		// }
		// }
		// // System.out.println(b);
		// Integer a = 1;
		// System.out.println(null == a);
		// String s = "$123456$";
		//
		// System.out.println(escapeExprSpecialWord(s));

		System.out.println(new BigDecimal(5).divide(new BigDecimal(1000), 2,
				BigDecimal.ROUND_HALF_UP));
	}

	/**
	 * 转义正则特殊字符 （$()*+.[]?\^{},|）
	 * 
	 * @param keyword
	 * @return
	 */
	public static String escapeExprSpecialWord(String keyword) {
		String[] fbsArr = {"\\", "$", "(", ")", "*", "+", ".", "[", "]", "?",
				"^", "{", "}", "|"};
		for (String key : fbsArr) {
			if (keyword.contains(key)) {
				keyword = keyword.replace(key, "\\" + key);
			}
		}
		return keyword;
	}

	public static void output(Object... object) {

	}

	public static void logger(String info, Object obj, Class clazz) {
		String logContent = "";
		if (obj != null) {
			if (obj.getClass() == java.util.ArrayList.class) {
				JSONArray jsonArray = JSONArray.fromObject(obj);
				logContent = jsonArray.toString();
			} else if (obj.getClass() == String.class) {
				logContent = obj.toString();
			} else {
				JSONObject jsonObject = JSONObject.fromObject(obj);
				logContent = jsonObject.toString();
			}
		}
		Logger log = Logger.getLogger(clazz);
		log.info(info + logContent);
	}

	public static void logger2(String info, Object obj, Class clazz) {
		JSONObject jsonArray = JSONObject.fromObject(obj);
		String logContent = jsonArray.toString();
		Logger log = Logger.getLogger(clazz);
		log.info(info + logContent);
	}

	// 读取文件指定行。
	static List<String> readAppointedLineNumber(File sourceFile, int lineNumber)
			throws IOException {
		List<String> result = new ArrayList<String>();
		FileReader in = new FileReader(sourceFile);
		LineNumberReader reader = new LineNumberReader(in);
		String s = reader.readLine();
		if (lineNumber < 0 || lineNumber > getTotalLines(sourceFile)) {
			System.out.println("不在文件的行数范围之内。");
		} else {
			while (s != null) {
				result.add(s);
				s = reader.readLine();
			}
		}
		reader.close();
		in.close();
		return result;
	}

	// 文件内容的总行数。
	static int getTotalLines(File file) throws IOException {
		FileReader in = new FileReader(file);
		LineNumberReader reader = new LineNumberReader(in);
		String s = reader.readLine();
		int lines = 0;
		while (s != null) {
			lines++;
			s = reader.readLine();
		}
		reader.close();
		in.close();
		return lines;
	}
}
