package online.themixhub.demo.pages;

import com.google.inject.Inject;
import online.themixhub.demo.pages.forms.JobCommentForm;
import online.themixhub.demo.pages.forms.RegisterForm;
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

@Path("/job_comment")
public class Job_Comment {

	private DataSource ds;

	//for when we use SQL
	@Inject
	public Job_Comment(DataSource ds) {
		this.ds = ds;
	}

	@GET
	public Result getPageGet(Request req) throws IOException {
		SessionUtils.handleSessionDestroy(req);
		if (req.session().isSet("set")) {
			return Results.redirect("/dashboard");
		} else {
			Result result = Results.html("register");
			return result;
		}
	}

	@POST
	public Result getPagePost(Request req) throws IOException {
			JobCommentForm jobCommentForm = req.form(JobCommentForm.class, "js", "html", "uri");
			Account account = MySQL.getAccounts(ds).queryAccountFromID(req.session().get("id").intValue());

			File moveTo = null;
			if(!jobCommentForm.file.toString().isEmpty()) {
				String uniqueName = MiscUtils.generateUniqueFileName("uploads", jobCommentForm.file.name());
				moveTo = new File("uploads" + File.separator + uniqueName);
				jobCommentForm.file.file().renameTo(moveTo);
			}

			JobComment newComment = new JobComment();
			newComment.setParent_account_id(account.getId());
			newComment.setReply_to_id(-1);
			newComment.setParent_job_id(Integer.parseInt(jobCommentForm.jobID));
			if(jobCommentForm.comments != null)
				newComment.setComment(jobCommentForm.comments);
			if(moveTo != null && moveTo.exists())
				newComment.setFilepaths(moveTo.getAbsolutePath());

			MySQL.getJobComments(ds).insert(newComment);

			return Results.redirect("/jobs?id=" + jobCommentForm.jobID);
	}
}
