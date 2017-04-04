package online.themixhub.demo.pages;

import com.google.inject.Inject;
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

@Path("/job_progress_engineer")
public class Job_Progress_Engineer {

	private DataSource ds;

	//for when we use SQL
	@Inject
	public Job_Progress_Engineer(DataSource ds) {
		this.ds = ds;
	}

	@POST
	public Result getPage(Request req) throws IOException {
		SessionUtils.handleSessionDestroy(req);
		if (!req.session().isSet("set")) {
			return Results.redirect("/");
		} else {
			Account account = MySQL.getAccounts(ds).queryAccountFromID(req.session().get("id").intValue());
			JobProgressEngineerForm jobProgressEngineerForm = req.form(JobProgressEngineerForm.class, "js", "html", "uri");

			int id = Integer.parseInt(jobProgressEngineerForm.jobID);
			Job job = MySQL.getJobs(ds).queryJobFromID(id);

			if(PermissionUtils.isEngineer(account)) {
				if(job.getStage() == 5)
					MySQL.getJobs(ds).setJobStage(id, job.getEngineer_id(), 98);
				else
					MySQL.getJobs(ds).setJobStage(id, job.getEngineer_id(), (job.getStage() + 1));

				JobComment newComment = new JobComment();

				if(job.getStage() == 5)
					newComment.setComment("I've completed the review, after 3 revisions I am now automatically closing this.");
				else
					newComment.setComment("I've completed the review, it's now up to you to decide if you want anything changed or to close the ticket.");
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
