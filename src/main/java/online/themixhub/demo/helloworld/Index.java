package online.themixhub.demo.helloworld;

import javax.sql.DataSource;

import org.jooby.*;
import org.jooby.mvc.*;

import com.google.inject.Inject;

@Path("/")
public class Index {
	
	private DataSource ds;

	 //for when we use SQL
	/*@Inject
	public Index(DataSource ds) {
		this.ds = ds;
	}*/

	@GET
	public Result getPage(Request req) {
		Result result = Results.html("index").
			put("username", "username-goes-here!");
		return result;
		/*Sessions.handleSessionDestroy(req);
		if (req.session().isSet("set")) {
			int id = Integer.parseInt(req.session().get("id").value());
			User user = MySQL.queryAccountFromID(ds, id);
			
			Result result = Results.html("client_about");
			return result;
		} else {
			return Results.redirect("/");
		}*/
	}
}
