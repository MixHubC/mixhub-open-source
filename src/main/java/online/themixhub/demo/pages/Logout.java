package online.themixhub.demo.pages;

import org.jooby.Request;
import org.jooby.Result;
import org.jooby.Results;
import org.jooby.mvc.GET;
import org.jooby.mvc.Path;

import java.io.IOException;

@Path("/logout")
public class Logout {

	@GET
	public Result getPage(Request req) throws IOException {
		req.session().destroy();
		return Results.redirect("/");
	}
}
