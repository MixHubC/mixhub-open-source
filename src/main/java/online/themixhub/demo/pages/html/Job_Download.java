package online.themixhub.demo.pages.html;

import com.google.inject.Inject;
import online.themixhub.demo.sql.MySQL;
import online.themixhub.demo.sql.impl.Account;
import online.themixhub.demo.utils.SessionUtils;
import org.jooby.Request;
import org.jooby.Result;
import org.jooby.Results;
import org.jooby.mvc.GET;
import org.jooby.mvc.Path;

import javax.sql.DataSource;
import java.io.File;

@Path("/job_download")
public class Job_Download {

	private DataSource ds;

	//for when we use SQL
	@Inject
	public Job_Download(DataSource ds) {
		this.ds = ds;
	}

	@GET
	public Result getPage(Request req) {
		SessionUtils.handleSessionDestroy(req);
		if (!req.session().isSet("set")) {
			return Results.redirect("/");
		} else {
			String fileName = req.param("file", "js", "html", "uri").value();
			File path = new File("uploads");
			File download = new File(path.getAbsolutePath()+File.separator+fileName);
			//System.out.println("D: " + download.getParentFile().getAbsolutePath()+", P: " + path.getAbsolutePath());
			if(download.getParentFile().getAbsolutePath().equals(path.getAbsolutePath())) {
				if(!download.exists()) {
					return Results.redirect("/jobs");
				} else {
					return Results.ok(download).header("Content-Disposition", "attachment; filename="+download.getName()+";");
				}
			} else { //trying to escape our directory, probably by doing http://localhost:8080/demo_download?file=../../../
				Account account = MySQL.getAccounts(ds).queryAccountFromID(req.session().get("id").intValue());
				Result result = Results.html("dashboard_page_template").
						put("content", "File not found!").
						put("title", "The Mix Hub Online - Dashboard").
						put("full_name", account.getFirstname() + " " + account.getLastname()).
						put("sidenav", SideNavigation.generate(req, account)).
						put("notification_count", Notifications.count(req, account)).
						put("notification_list", Notifications.generate(req, account));
				return result;
			}
		}
	}
}
