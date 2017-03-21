package dao.dto;

public class UserDto {
	
	private String userId;
	
	private String userName;
	
	private String userPasswd;

	public String getUserName() {
		return userName;
	}

	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPasswd() {
		return userPasswd;
	}

	public void setUserPasswd(String userPasswd) {
		this.userPasswd = userPasswd;
	}

	@Override
	public String toString() {
		return "User [id=" + userId + ", name=" + userName + ", passwd=" + userPasswd + "]";
	}
	
	
}
