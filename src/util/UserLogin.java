package util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import common.EtlContents;

public class UserLogin {
	public static void login(Map<String, String> map) throws Exception {
		String name = map.get("name");
		String passwd = map.get("passwd");
		String ip = map.get("ip");
		String loginExpect = EtlContents.loginExpectPath;
		String[] cmds = { "/bin/sh", "-c", loginExpect + " " + name + " " + passwd + " " + ip};
		
		for(String str : cmds){
			System.out.println(str);
		}
		Process pro = Runtime.getRuntime().exec(cmds);
		pro.waitFor();
		InputStream in = pro.getInputStream();
		BufferedReader read = new BufferedReader(new InputStreamReader(in));
		String line = null;
		while ((line = read.readLine()) != null) {
			System.out.println(line);
			System.out.println("----------------------------------------------------");
		}
	}
}
