package test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import dao.dto.UserDto;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JsonTest {
	
	public static void main(String[] args) {
		name2(name(""));
	}
	public static String name(String js) {
//		JSONObject jb = JSONObject.fromObject(js);
//        JSONArray ja = jb.getJSONArray("JobReport");
//		
		// JSON格式数据解析对象
        JSONObject jo = new JSONObject();
        List<UserDto> list = new ArrayList<UserDto>();
        UserDto user = new UserDto();
        user.setUserId("1");
        user.setUserPasswd("1");
        user.setUserName("1");
        UserDto user2 = new UserDto();
        user2.setUserId("12");
        user2.setUserPasswd("12");
        user2.setUserName("12");
        list.add(user);
        list.add(user2);
        // 将List转换为JSONArray数据
        JSONArray ja2 = JSONArray.fromObject(list);
        jo.put("aaa", ja2);
        jo.put("bbb","bbbbbbb");
        System.out.println(jo.toString());
        return jo.toString();
	}
	
	public static void name2(String jo) {
        JSONArray ja = JSONObject.fromObject(jo).getJSONArray("aaa");	
        String  bbb = JSONObject.fromObject(jo).getString("bbb");
        System.out.println(bbb);
        List<UserDto> list = new ArrayList<UserDto>();
        
        for (int i = 0; i < ja.size(); i++) {
        	UserDto UserDto = new UserDto();
        	UserDto.setUserId(ja.getJSONObject(i).getString("userId"));
        	UserDto.setUserPasswd(ja.getJSONObject(i).getString("userPasswd"));
        	UserDto.setUserName(ja.getJSONObject(i).getString("userName"));
        	list.add(UserDto);
        }
        
        System.out.println(list.size());
	}
	
	
}
