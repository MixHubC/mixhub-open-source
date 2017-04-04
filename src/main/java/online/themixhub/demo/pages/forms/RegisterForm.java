package online.themixhub.demo.pages.forms;

import org.jooby.MediaType;
import org.jooby.Upload;

import java.util.List;

public class RegisterForm {

	public String email;
	public String username;
	public String password;
	public String firstname;
	public String lastname;

	public List<MediaType> list;

	public RegisterForm(String email) {
		this.email = email;
	}
}
