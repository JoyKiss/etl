package util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.LineNumberReader;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.TimerTask;

public class ParseLogTask extends TimerTask {

	public static void main(String[] args) {
		ParseLogTask ParseLogTask = new ParseLogTask();
		ParseLogTask.run();
	}

	@Override
	public void run() {
		List<String> list = new ArrayList<String>();
		File file = new File("E:/eif/randomAFile.log");
		// 取得文件大小
		long filesize = file.length();
		if (filesize != 0) {
			int lines = 0;
			// 取得文件总行数
			lines = getLine(filesize);
			// 读取日志信息记录文件中的标识、文件大小、文件总行数
			// String[] strarr = readString4();
			String[] strarr = getPro();
			// 本次读文件要开始的位置
			long chars = Long.valueOf(strarr[0]);
			// 上次读文件时，文件的大小
			long oldfilesize = Long.valueOf(strarr[1]);
			// 取得上次读取行数
			int oldFileCount = Integer.valueOf(strarr[2]);
			if (filesize != oldfilesize) {
				String tempstr = "";
				RandomAccessFile rf = null;
				try {
					rf = new RandomAccessFile("E:/eif/randomAFile.log", "r");
					rf.seek(chars);
					for (int i = oldFileCount; i < lines; i++) {
						tempstr = rf.readLine();
						// 日志末尾有换行符时，最后一次读出的值为null，末尾无换行符时，最后一次读出的值为实际值
						System.out.println("tempstr=" + tempstr);
						if (i == lines && tempstr == null) {
							break;
						}
						list.add(tempstr);
					}
					chars = rf.getFilePointer();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					if (rf != null) {
						try {
							rf.close();
						} catch (IOException e1) {
						}
					}
				}
				setPro(String.valueOf(chars), String.valueOf(filesize),
						String.valueOf(lines));
				for (String str : list) {
					System.out.println("增加的日志内容=" + str);
				}
				// write("E:\\test\\parselog\\record.txt",String.valueOf(chars+1),filesize);
			} else {
				System.out.println("文件大小一致,什么也不做！");
			}
		}
	}

	//
	// private static String[] readString4() {
	// int len=0;
	// String filePointer = "";
	// String filesize="";
	// File file = new File("E:\\test\\parselog\\record.txt");
	// try {
	// FileInputStream is=new FileInputStream(file);
	// InputStreamReader isr= new InputStreamReader(is,"UTF-8");
	// BufferedReader in= new BufferedReader(isr);
	// String line=null;
	// while( (line=in.readLine())!=null ) {
	// if (len == 0) {
	// filePointer = line;
	// System.out.println("one="+filePointer);
	// }
	// if (len == 1) {
	// filesize = line;
	// System.out.println("two="+filesize);
	// }
	// len++;
	// }
	// in.close();
	// is.close();
	// } catch (IOException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// String[] str = new String[2];
	// str[0]=filePointer;
	// str[1]=filesize;
	// return str;
	// }
	// 取得文件总行数
	public static int getLine(long filesize) {
		LineNumberReader lineread = null;
		int lines = 0;
		try {
			lineread = new LineNumberReader(
					new FileReader("E:/eif/randomAFile.log"));
			if (lineread != null) {

				lineread.skip(filesize);
				lines = lineread.getLineNumber();
				lineread.close();
			}
		} catch (IOException e) {
			if (lineread != null) {
				try {
					lineread.close();
				} catch (IOException ee) {
				}
			}
		}
		return lines;
	}

	// 取得上次读取信息
	public static String[] getPro() {
		Properties prop = new Properties();
		String[] strarr = new String[3];
		try {
			// 读取属性文件a.properties
			InputStream in = new BufferedInputStream(
					new FileInputStream("E:/eif/record.properties"));
			prop.load(in);     /// 加载属性列表
			strarr[0] = prop.getProperty("start");
			strarr[1] = prop.getProperty("oldFileSize");
			strarr[2] = prop.getProperty("oldFileCount");
			in.close();
		} catch (Exception e) {
			System.out.println("getPro出错");
		}
		return strarr;
	}

	// 设定上次读取信息
	public static void setPro(String start, String oldFileSize,
			String oldFileCount) {
		Properties prop1 = new Properties();
		FileInputStream fis;
		try {
			fis = new FileInputStream("E:/eif/record.properties");// 属性文件输入流
			prop1.load(fis);// 将属性文件流装载到Properties对象中
			fis.close();
			FileOutputStream oFile = new FileOutputStream(
					"E:/eif/record.properties");// true表示追加打开
			prop1.setProperty("start", start);
			prop1.setProperty("oldFileSize", oldFileSize);
			prop1.setProperty("oldFileCount", oldFileCount);
			prop1.store(oFile, null);
			oFile.close();
		} catch (Exception e) {
			System.out.println("setPro出错");
		}

	}
	//
	// public static void write(String fileName, String content,long filesize) {
	// FileWriter writer = null;
	// try {
	// // 打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件,false表示覆盖的方式写入
	// writer = new FileWriter(fileName, false);
	// writer.write(content);
	// writer.write("\r\n");
	// writer.write(String.valueOf(filesize));
	// } catch (IOException e) {
	// e.printStackTrace();
	// } finally {
	// try {
	// if(writer != null){
	// writer.close();
	// }
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// }
	// }
}
