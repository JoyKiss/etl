package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {

	/**
	 * 写文件
	 * 
	 * @param fileName
	 * @param filePath
	 * @param data
	 * @throws IOException
	 */
	public static void fileWrite(String fileName, String filePath,
			List<String> data) throws IOException {
		File file = new File(filePath + fileName);

		if (!file.exists()) {
			file.createNewFile();
		}

		// true = append file
		FileWriter fileWritter = new FileWriter(file);
		for (String str : data) {
			fileWritter.write(str + "\n");
			fileWritter.flush();
		}
		fileWritter.close();
	}

	/**
	 * 以行为单位读取文件，常用于读面向行的格式化文件
	 * 
	 * @param fileName
	 *            文件名
	 */
	public static List<String> readFileByLines(String filePath,
			String fileName) {
		File file = new File(filePath, fileName);

		List<String> tempStringList = new ArrayList<String>();
		if (!file.exists()) {
			return tempStringList;
		}
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;

			// 一次读入一行，直到读入null为文件结束
			while ((tempString = reader.readLine()) != null) {
				tempStringList.add(tempString);
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}

		return tempStringList;
	}

	// 读取文件指定行。
	public static List<String> readAppointedLineNumber(File sourceFile,
			int lineNumber) throws IOException {
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
