package online.themixhub.demo.pages;

import com.google.inject.Inject;
import online.themixhub.demo.pages.forms.JobCreationForm;
import online.themixhub.demo.sql.MySQL;
import online.themixhub.demo.sql.impl.Account;
import online.themixhub.demo.sql.impl.Job;
import online.themixhub.demo.utils.MiscUtils;
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

@Path("/job_create")
public class Job_Create {

	private DataSource ds;

	//for when we use SQL
	@Inject
	public Job_Create(DataSource ds) {
		this.ds = ds;
	}

	@GET
	public Result getPageGet(Request req) throws IOException {
		return Results.redirect("/dashboard");
	}

	@POST
	public Result getPage(Request req) throws IOException {
		SessionUtils.handleSessionDestroy(req);
		if (!req.session().isSet("set")) {
			return Results.redirect("/");
		} else {
			Account account = MySQL.getAccounts(ds).queryAccountFromID(req.session().get("id").intValue());
			JobCreationForm jobCreationForm = req.form(JobCreationForm.class, "js", "html", "uri");

			if(jobCreationForm.comments == null || jobCreationForm.comments.isEmpty()) {
				Result result = Results.html("job_create").
						put("message", "Job creation failed - Fill out the comments");
				return result;
			} else if(jobCreationForm.title == null || jobCreationForm.title.isEmpty()) {
				Result result = Results.html("job_create").
						put("message", "Job creation failed - Fill out the title");
				return result;
			} else if(jobCreationForm.file == null || jobCreationForm.file.toString().isEmpty()) {
				Result result = Results.html("job_create").
						put("message", "Job creation failed - Upload your WAV/MP3");
				return result;
			}

			String uniqueName = MiscUtils.generateUniqueFileName("uploads", jobCreationForm.file.name());
			File moveTo = new File("uploads" + File.separator + uniqueName);
			jobCreationForm.file.file().renameTo(moveTo);

			Job newJob = new Job();
			newJob.setTitle(jobCreationForm.title);
			newJob.setDate(System.currentTimeMillis());
			newJob.setOwner_id(account.getId());

			int jobID = MySQL.getJobs(ds).insert(newJob, jobCreationForm.comments, moveTo.getAbsolutePath());

			if(jobID != -1) {
				return Results.redirect("/jobs?id=" + jobID);
			} else {
				Result result = Results.html("job_create").
						put("message", "Job creation failed");
				return result;
			}
		}
	}
}
