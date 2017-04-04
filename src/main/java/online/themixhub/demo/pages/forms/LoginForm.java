package online.themixhub.demo.pages.forms;

import org.jooby.MediaType;

import java.util.List;

public class LoginForm {

	public String email;
	public String password;
	public String rememberme;

	public List<MediaType> list;

	public LoginForm(String email) {
		this.email = email;
	}
}
