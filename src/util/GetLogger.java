package util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GetLogger {
	public static List<String> getLogger() {
		// 读取echo.log文件
		String fileName = "echo.log";
		String filePath = "/common/crontab/tmp/";
		List<String> logger = ReadFromFile.readFileByLines(filePath, fileName);
		
		List<String> data = new ArrayList<String>();
		try {
			ReadFromFile.fileWrite(fileName, filePath, data);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return logger;
	}
}
