package UserRegistration;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UserDTO {
	
	private String login;
	private String password;
	private UserGameDTO userGame;
	
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public UserGameDTO getUserGame() {
		return userGame;
	}
	public void setUserGame(UserGameDTO userGame) {
		this.userGame = userGame;
	}
	
	@Override
	public String toString() {
		return "UserDTO [login=" + login + ", password=" + password
				+ ", userGame=" + userGame + "]";
	}
}
