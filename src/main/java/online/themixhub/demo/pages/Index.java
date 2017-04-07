package online.themixhub.demo.pages;

import javax.sql.DataSource;

import online.themixhub.demo.utils.SessionUtils;
import org.jooby.*;
import org.jooby.mvc.*;

import com.google.inject.Inject;

@Path("/")
public class Index {
	
	private DataSource ds;

	 //for when we use SQL
	@Inject
	public Index(com.typesafe.config.Config conf, DataSource ds) {
		System.out.println(conf.getString("hbs.cache"));
		this.ds = ds;
	}

	@GET
	public Result getPage(Request req) {
		SessionUtils.handleSessionDestroy(req);
		if (req.session().isSet("set")) {
			return Results.redirect("/dashboard");
		} else {
			return Results.redirect("/login");
		}
	}
}
