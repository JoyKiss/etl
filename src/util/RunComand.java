package util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RunComand {

	public static String[] creatWorkAddCmd(Map<String, String> map) {

		String name = map.get("name");
		String passwd = map.get("passwd");
		String ip = map.get("ip");
		String fileName = map.get("fileName");
		String filePath = map.get("filePath");

		String[] cmd = { "/bin/sh", "-c",
				"/common/crontab/expect/workAdd.exp " + name + " " + passwd + " " + ip + " " + filePath + fileName };

		return cmd;
	}

	public static String[] creatCommandCmd(Map<String, String> map) {

		String name = map.get("name");
		String passwd = map.get("passwd");
		String ip = map.get("ip");
		String command = map.get("command");

		String[] cmd = { "/bin/sh", "-c",
				"/common/crontab/expect/command.exp " + name + " " + passwd + " " + ip + " '" + command +"'"};

		return cmd;
	}

	public static List<String> runComand(String[] cmds) throws Exception {
		for(String str : cmds){
			System.out.println(str);
		}
		Process pro = Runtime.getRuntime().exec(cmds);
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
}
