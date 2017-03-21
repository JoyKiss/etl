package util;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class RequestParam2Map {
	public static Map<String, String> transf(HttpServletRequest req) {
		Enumeration flds = req.getParameterNames();
		Map<String, String> map = new HashMap<String,String>();
		while (flds.hasMoreElements()) {
			String field = (String) flds.nextElement();
			String value = req.getParameter(field);
			map.put(field, value);
		}
		return map;
	}
}
