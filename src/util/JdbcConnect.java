package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import dao.dto.UserDto;



public class JdbcConnect {

	/**
	 * 获取连接驱动
	 * @return
	 */
	private static Connection getConn() {
	    String driver = "com.mysql.jdbc.Driver";
	    String url = "jdbc:mysql://192.168.154.2:3306/dmp";
	    String username = "dmp";
	    String password = "dmp";
	    Connection conn = null;
	    try {
	        Class.forName(driver); //classLoader,加载对应驱动
	        conn = (Connection) DriverManager.getConnection(url, username, password);
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return conn;
	}
	
	private static int insert(UserDto user) {
	    Connection conn = getConn();
	    int i = 0;
	    String sql = "insert into USER (userId,userName,userPasswd) values(?,?,?)";
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement) conn.prepareStatement(sql);
	        pstmt.setString(1, user.getUserId());
	        pstmt.setString(2, user.getUserName());
	        pstmt.setString(3, user.getUserPasswd());
	        i = pstmt.executeUpdate();
	        pstmt.close();
	        conn.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return i;
	}
	
	private static int update(UserDto user) {
	    Connection conn = getConn();
	    int i = 0;
	    String sql = "update USER set userPasswd='" + user.getUserPasswd() + "' where userId='" + user.getUserId() + "'";
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement) conn.prepareStatement(sql);
	        i = pstmt.executeUpdate();
	        System.out.println("resutl: " + i);
	        pstmt.close();
	        conn.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return i;
	}
	
	public static void main(String[] args) {
		UserDto user = new UserDto();
		user.setUserId("2");
		user.setUserName("biski");
		user.setUserPasswd("biski");
		insert(user);
	}
}
