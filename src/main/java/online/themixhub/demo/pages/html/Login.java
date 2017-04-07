package online.themixhub.demo.pages.html;

import com.google.inject.Inject;
import online.themixhub.demo.utils.SessionUtils;
import online.themixhub.demo.pages.forms.LoginForm;
import online.themixhub.demo.sql.MySQL;
import online.themixhub.demo.sql.impl.Account;
import org.jooby.Request;
import org.jooby.Result;
import org.jooby.Results;
import org.jooby.mvc.GET;
import org.jooby.mvc.POST;
import org.jooby.mvc.Path;

import javax.sql.DataSource;
import java.io.IOException;

@Path("/login")
public class Login {

	private DataSource ds;

	//for when we use SQL
	@Inject
	public Login(DataSource ds) {
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
				Result result = Results.html("login").
						put("title", "The Mix Hub Online - Dashboard");
				return result;
			}
		} else if(req.method().toLowerCase().equals("post")) {
			LoginForm loginForm = req.form(LoginForm.class, "js", "html", "uri");

			//do extra checks to make sure email is valid/etc

			Account account = MySQL.getAccounts(ds).queryValidAccount(loginForm.email, loginForm.password);

			boolean remember = ((loginForm.rememberme != null) ? true : false);

			if(account != null) {
				req.session().set("set", System.currentTimeMillis()); //also doubles as a unique hash/identifer
				req.session().set("id", account.getId()); //instead of email, set ID
				req.session().set("remember", remember);
				req.session().set("ip", req.ip());

				return Results.redirect("dashboard");
			} else {
				Result result = Results.html("login").
						put("content", "Error! Invalid E-Mail or Password.").
						put("title", "The Mix Hub Online - Dashboard");
				return result;
			}
		}

		return null;
	}
}
