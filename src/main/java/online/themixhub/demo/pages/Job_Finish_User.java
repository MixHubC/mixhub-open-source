package online.themixhub.demo.pages;

import com.google.inject.Inject;
import online.themixhub.demo.pages.forms.JobFinishUserForm;
import online.themixhub.demo.pages.forms.JobProgressEngineerForm;
import online.themixhub.demo.sql.MySQL;
import online.themixhub.demo.sql.impl.Account;
import online.themixhub.demo.sql.impl.Job;
import online.themixhub.demo.sql.impl.JobComment;
import online.themixhub.demo.utils.PermissionUtils;
import online.themixhub.demo.utils.SessionUtils;
import org.jooby.Request;
import org.jooby.Result;
import org.jooby.Results;
import org.jooby.mvc.GET;
import org.jooby.mvc.POST;
import org.jooby.mvc.Path;

import javax.sql.DataSource;
import java.io.IOException;

@Path("/job_finish_user")
public class Job_Finish_User {

	private DataSource ds;

	//for when we use SQL
	@Inject
	public Job_Finish_User(DataSource ds) {
		this.ds = ds;
	}

	@POST
	public Result getPage(Request req) throws IOException {
		SessionUtils.handleSessionDestroy(req);
		if (!req.session().isSet("set")) {
			return Results.redirect("/");
		} else {
			Account account = MySQL.getAccounts(ds).queryAccountFromID(req.session().get("id").intValue());
			JobFinishUserForm jobFinishUserForm = req.form(JobFinishUserForm.class, "js", "html", "uri");

			int id = Integer.parseInt(jobFinishUserForm.jobID);
			Job job = MySQL.getJobs(ds).queryJobFromID(id);

			if(PermissionUtils.isUser(account)) {
				MySQL.getJobs(ds).setJobStage(id, job.getEngineer_id(), 99);

				JobComment newComment = new JobComment();
				newComment.setComment("This is great! I'm satisfied, Thank you.");
				newComment.setParent_account_id(account.getId());
				newComment.setReply_to_id(-1);
				newComment.setParent_job_id(id);
				MySQL.getJobComments(ds).insert(newComment);

				return Results.redirect("/jobs?id=" + id);
			} else {
				return Results.redirect("/dashboard");
			}
		}
	}

	@GET
	public Result getPageGet(Request req) {
		return Results.redirect("/dashboard");
	}
}
