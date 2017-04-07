package online.themixhub.demo.pages.html;

import com.google.inject.Inject;
import online.themixhub.demo.sql.impl.Account;
import online.themixhub.demo.utils.SessionUtils;
import online.themixhub.demo.pages.forms.RegisterForm;
import online.themixhub.demo.sql.MySQL;
import org.jooby.Request;
import org.jooby.Result;
import org.jooby.Results;
import org.jooby.mvc.GET;
import org.jooby.mvc.POST;
import org.jooby.mvc.Path;

import javax.sql.DataSource;
import java.io.IOException;

@Path("/register")
public class Register {

	private DataSource ds;

	//for when we use SQL
	@Inject
	public Register(DataSource ds) {
		this.ds = ds;
	}

	@GET
	@POST
	public Result getPage(Request req) throws IOException {
		if(req.method().toLowerCase().equals("get")) {
			SessionUtils.handleSessionDestroy(req);
			if (req.session().isSet("set")) {
				return Results.redirect("/dashboard");
			} else {
				Result result = Results.html("register");
				return result;
			}
		} else if(req.method().toLowerCase().equals("post")) {
			RegisterForm registerForm = req.form(RegisterForm.class, "js", "html", "uri");

			Account newAccount = new Account();
			newAccount.setRegister_ip(req.ip());
			newAccount.setEmail(registerForm.email);
			newAccount.setUsername(registerForm.username);
			newAccount.setPassword(registerForm.password);
			newAccount.setFirstname(registerForm.firstname);
			newAccount.setLastname(registerForm.lastname);

			String content = "";


			//do checks here if email is valid, password is secure, etc.
			//NOTE: MySQL.createAccount only sees to see if the E-Mail is unique, we need to update it if we want to support username login (as usernames are not unique at this point)


			if(MySQL.getAccounts(ds).insert(newAccount)) {
				content = "Success! You've register the account '" + registerForm.username + "'. <a href=\"/login\">Click here</a> to login!";
			} else {
				content = "Error! This E-Mail is already in use...";
			}

			Result result = Results.html("register").
					put("content", content).
					put("title", "The Mix Hub ONLINE - Demo").
					put("email", registerForm.email).
					put("username", registerForm.username).
					put("password", registerForm.password).
					put("firstname", registerForm.firstname).
					put("lastname", registerForm.lastname);

			return result;
		}

		return null;
	}
}
