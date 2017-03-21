package test;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class DomTest {
public static void main(String[] args) throws IOException {
//	File input = new File("D:\\workspace\\javaTest\\src\\javaTest\\tmp.kjb");
//	Document doc = Jsoup.parse(input, "UTF-8");
//
//	Elements links = doc.select("description"); //带有href属性的a元素
//	System.out.println(links.text());
	
	Document doc = Jsoup.connect("http://www.baidu.com/")
			  .data("query", "Java")
			  .userAgent("Mozilla")
			  .cookie("auth", "token")
			  .timeout(3000)
			  .post();
	Elements ele = doc.select("#head");
	System.out.println(ele.attr("value"));
}
}
