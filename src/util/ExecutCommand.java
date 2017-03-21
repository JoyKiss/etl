package util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

//import org.apache.commons.codec.digest.DigestUtils;

import common.EtlContents;

public class ExecutCommand {
	public static List<String> execute(List<String> list) throws Exception {
		list.add(0, "/bin/sh");
		list.add(1, "-c");
		Object[] cmds = list.toArray();
		for (Object str : cmds) {
			System.out.println((String) str);
		}
		String[] strs = Arrays.asList(cmds).toArray(new String[0]);
		Process pro = Runtime.getRuntime().exec(strs);
		pro.waitFor();
		InputStream in = pro.getInputStream();
		BufferedReader read = new BufferedReader(new InputStreamReader(in));
		String line = null;
		List<String> messageList = new ArrayList<String>();
		while ((line = read.readLine()) != null) {
			System.out.println(line);
			messageList.add(line);
			System.out.println("----------------------------------------------------");
		}
		return messageList;

	}

	public static List<String> execute(List<String> list, Map<String, String> map) throws Exception {
		list.add(0, "/bin/sh");
		list.add(1, "-c");
		String name = map.get("name");
		String passwd = map.get("passwd");
		String ip = map.get("ip");
		String loginExpect = EtlContents.loginExpectPath + " " + name + " " + passwd + " " + ip;
		list.add(2, loginExpect);

		Object[] cmds = list.toArray();
		String[] strs = Arrays.asList(cmds).toArray(new String[0]);
		for (Object str : strs) {
			System.out.println((String) str);
		}
		Process pro = Runtime.getRuntime().exec(strs);
		pro.waitFor();
		InputStream in = pro.getInputStream();
		BufferedReader read = new BufferedReader(new InputStreamReader(in));
		String line = null;
		List<String> messageList = new ArrayList<String>();
		while ((line = read.readLine()) != null) {
			messageList.add(line);
		}

		return messageList;
	}

	public static List<String> execute2(List<String> list, Map<String, String> map) throws Exception {
		String type = map.get("type");

		List<String> messageList = new ArrayList<String>();
		if ("3".equals(type)) {
			// 执行command命令
			String[] cmds = RunComand.creatCommandCmd(map);
			messageList.addAll(RunComand.runComand(cmds));

			// log
			messageList.addAll(GetLogger.getLogger());
		} else {

			// 创建cron文件
			String fileName = map.get("fileName");
			String filePath = map.get("filePath");
			
			// cron安装文件copy到自定义服务目录下
			String copyFrom = "/var/spool/cron/" + map.get("name");
			String copyTo = filePath + fileName;
			boolean copyFlag = ReadFromFile.copyFile(copyFrom, copyTo, true);
			if(copyFlag){
				messageList.add("用户的cron文件copy成功:"+ copyFrom + " -----> " + copyTo);
			} else {
				messageList.add("copy失败");
			}

			// 读取源文件MD5比较
			List<String> date = ReadFromFile.readFileByLines(filePath, fileName);
			// 启用
			List<String> datetemp = new ArrayList<String>();
			if ("1".equals(type)) {
				List<String> addList = new ArrayList<String>();
				if (date != null && date.size() > 0) {
					for (String str1 : list) {
						// for (String str : list) {
						// // if (!DigestUtils.md5Hex(str1.replace("\r\n",
						// // "")).equals(DigestUtils.md5Hex(str.replace("\r\n",
						// // "")))) {
						// if (str.equals(str1)) {
						// addList.add(str1);
						// }
						// }
						if (!date.contains(str1)) {
							addList.add(str1);
						}
					}
				} else {
					date.addAll(list);
					date.add(0, "#"+ map.get("name") + " " + map.get("ip") + " " +filePath + fileName);
				}

				date.addAll(addList);

				for (int i = 0; i < date.size(); i++) {
					if (!"".equals(date.get(i).trim())) {
						datetemp.add(date.get(i));
					}
				}
			} else if ("2".equals(type)) {
				if (date != null && date.size() > 0) {
					for (int i = 0; i < date.size(); i++) {
						if (list.contains(date.get(i))) {
							date.remove(i);
						}
					}
				}
				datetemp = date;
			}

			// 写文件至服务器指定地址
			ReadFromFile.fileWrite(fileName, filePath, datetemp);

			messageList.add(fileName + "作成成功,path：" + filePath + fileName);

			// 安装已经生成的cron文件
			String[] cmds = RunComand.creatWorkAddCmd(map);
			messageList.addAll(RunComand.runComand(cmds));

			messageList.addAll(GetLogger.getLogger());

			// // ---------------
			// list.add(0, "/bin/sh");
			// list.add(1, "-c");
			// String name = map.get("name");
			// String passwd = map.get("passwd");
			// String ip = map.get("ip");
			// String loginExpect = EtlContents.loginExpectPath + " " + name + "
			// " +
			// passwd + " " + ip;
			// list.add(2, loginExpect);
			//
			// Object[] cmds = list.toArray();
			// String[] strs = Arrays.asList(cmds).toArray(new String[0]);
			// for (Object str : strs) {
			// System.out.println((String) str);
			// }
			// Process pro = Runtime.getRuntime().exec(strs);
			// pro.waitFor();
			// InputStream in = pro.getInputStream();
			// BufferedReader read = new BufferedReader(new
			// InputStreamReader(in));
			// String line = null;
			// List<String> messageList = new ArrayList<String>();
			// while ((line = read.readLine()) != null) {
			// messageList.add(line);
			// }
		}
		return messageList;
	}

}