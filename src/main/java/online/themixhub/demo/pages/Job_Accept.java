package online.themixhub.demo.pages;

import com.google.inject.Inject;
import online.themixhub.demo.pages.forms.JobCreationForm;
import online.themixhub.demo.sql.MySQL;
import online.themixhub.demo.sql.impl.Account;
import online.themixhub.demo.sql.impl.JobComment;
import online.themixhub.demo.utils.MiscUtils;
import online.themixhub.demo.utils.PermissionUtils;
import online.themixhub.demo.utils.SessionUtils;
import org.jooby.Request;
import org.jooby.Result;
import org.jooby.Results;
import org.jooby.mvc.GET;
import org.jooby.mvc.POST;
import org.jooby.mvc.Path;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;

@Path("/job_accept")
public class Job_Accept {

	private DataSource ds;

	//for when we use SQL
	@Inject
	public Job_Accept(DataSource ds) {
		this.ds = ds;
	}

	@GET
	public Result getPageGet(Request req) throws IOException {
		SessionUtils.handleSessionDestroy(req);
		if (!req.session().isSet("set")) {
			return Results.redirect("/");
		} else {
			Account account = MySQL.getAccounts(ds).queryAccountFromID(req.session().get("id").intValue());

			if(PermissionUtils.isEngineer(account)) {
				if (req.param("id").isSet()) {
					int id = req.param("id", "js", "html", "uri").intValue();

					MySQL.getJobs(ds).acceptJob(id, account.getId(), false);

					JobComment newComment = new JobComment();
					newComment.setParent_account_id(account.getId());
					newComment.setReply_to_id(-1);
					newComment.setParent_job_id(id);
					newComment.setComment("Hello, I'm your mix hub engineer! I am currently reviewing your submitted mix.");

					MySQL.getJobComments(ds).insert(newComment);

					return Results.redirect("/jobs?id=" + id);
				} else {
					return Results.redirect("/dashboard");
				}
			} else {
				return Results.redirect("/dashboard");
			}
		}
	}
}
