package test;

import java.io.IOException;
import java.nio.charset.Charset;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

public class CsvTest {
	public static void main(String[] args) {
		try {
			CsvReader csvReader = new CsvReader("D:\\a.csv", ',',Charset.forName("UTF-8"));
	        //读取表头
			csvReader.readHeaders();
	        //逐条读取记录，直至读完
	        while (csvReader.readRecord()) {
	            //读取一条记录
	            System.out.println(csvReader.getRawRecord());
	            //按列名读取这条记录的值
	            System.out.println(csvReader.get("id"));
	            System.out.println(csvReader.get("name"));
	        }
	        csvReader.close();
		} catch(Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void writeCsv() throws IOException{
		CsvWriter csvWriter = new CsvWriter("", ',', Charset.forName("UTF-8"));
		String[] a = {"id","name"};
		csvWriter.writeRecord(a);
	}
}
