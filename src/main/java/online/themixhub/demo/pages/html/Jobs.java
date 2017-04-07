package online.themixhub.demo.pages.html;

import com.google.inject.Inject;
import online.themixhub.demo.sql.MySQL;
import online.themixhub.demo.sql.impl.Account;
import online.themixhub.demo.sql.impl.Job;
import online.themixhub.demo.sql.impl.JobComment;
import online.themixhub.demo.utils.PermissionUtils;
import online.themixhub.demo.utils.SessionUtils;
import online.themixhub.demo.utils.StageUtils;
import org.jooby.Request;
import org.jooby.Result;
import org.jooby.Results;
import org.jooby.mvc.GET;
import org.jooby.mvc.Path;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Path("/jobs")
public class Jobs {

	private DataSource ds;

	//for when we use SQL
	@Inject
	public Jobs(DataSource ds) {
		this.ds = ds;
	}

	@GET
	public Result getPageGet(Request req) throws IOException {
		SessionUtils.handleSessionDestroy(req);
		if (!req.session().isSet("set")) {
			return Results.redirect("/");
		} else {
			Account account = MySQL.getAccounts(ds).queryAccountFromID(req.session().get("id").intValue());

			HashMap<Integer, Account> accountCacheMap = new HashMap<Integer, Account>();
			accountCacheMap.put(account.getId(), account);

			if(req.param("id").isSet()) {
				int id = req.param("id", "js", "html", "uri").intValue();
				Job job = MySQL.getJobs(ds).queryJobFromID(id);

				if(job == null) {
					Result result = Results.html("jobs").
							put("message", "Invalid job");
					return result;
				}

				if(PermissionUtils.isEngineer(account) ||
						PermissionUtils.isAdmin(account) ||
						job.getOwner_id() == account.getId())
				{
					List<JobComment> comments = MySQL.getJobComments(ds).queryAllFromJobID(job.getId());

					Account author;
					if(accountCacheMap.containsKey(job.getOwner_id())) {
						author = accountCacheMap.get(job.getOwner_id());
					} else {
						author = MySQL.getAccounts(ds).queryAccountFromID(job.getOwner_id());
						accountCacheMap.put(job.getOwner_id(), author);
					}

					String jobDetails = "<table style=\"width:100%\">\n" +
							"  <tr align=\"right\">\n" +
							"    <th>Stage</th> \n" +
							"    <th>Title</th>\n" +
							"    <th>Owner</th> \n" +
							"    <th>Date</th>\n" +
							"  </tr>\n" +
							"  <tr align=\"right\">\n" +
							"    <td>"+ StageUtils.stageToString(job.getStage())+"</td>\n" +
							"    <td>"+job.getTitle()+"</td>\n" +
							"    <td>"+author.getFirstname()+ " " + author.getLastname()+"</td> \n" +
							"    <td>"+new Date(job.getDate())+"</td>\n" +
							"  </tr>\n" +
							"</table></br></br>\n" +
							"\n" +
							"\n" +
							"\n" +
							"<table>\n" +
							"  <tr>\n" +
							"    <th>Author</th>\n" +
							"    <th>Comment</th> \n" +
							"    <th>File</th>\n" +
							"  </tr>\n";
					for(JobComment jobComment : comments) {
						Account commetAuthor;
						if(accountCacheMap.containsKey(jobComment.getParent_account_id())) {
							commetAuthor = accountCacheMap.get(jobComment.getParent_account_id());
						} else {
							commetAuthor = MySQL.getAccounts(ds).queryAccountFromID(jobComment.getParent_account_id());
							accountCacheMap.put(jobComment.getParent_account_id(), commetAuthor);
						}
						jobDetails += "  <tr>\n";
						if(PermissionUtils.isEngineer(commetAuthor)) {
							if(job.getAnonymous_engineer() == 0) {
								jobDetails += "    <td>Engineer: " + commetAuthor.getFirstname() + " " + commetAuthor.getLastname() + ":</td>\n";
							} else {
								jobDetails += "    <td>Engineer: Anonymous:</td>\n";
							}
						} else {
							jobDetails += "    <td>User: " + commetAuthor.getFirstname() + " " + commetAuthor.getLastname() + ":</td>\n";
						}

						jobDetails += "    <td>"+jobComment.getComment()+"</td> \n";
						if(jobComment.getFilepaths() == null)
							jobDetails += "    <td></td>\n";
						else
							jobDetails += "    <td><a href=\"/job_download?file="+new File(jobComment.getFilepaths()).getName()+"\">Click Here</a></td>\n";
						jobDetails += "  </tr>\n";
					}

					jobDetails += "</table></br></br>\n" +
							"\n" +
							"Create A New Job Comment:\n" +
							"<form action=\"/job_comment\" method=\"post\" enctype=\"multipart/form-data\" id=\"jobform\"> </br>\n" +
							"  Comment: </br>\n" +
							"  <textarea name=\"comments\" form=\"jobform\" rows=\"4\" cols=\"50\"></textarea></br>\n" +
							"  MP3 or WAV: </br>\n" +
							"  <input type=\"file\" name=\"file\" accept=\"*\"></br></br>\n" +
							"  <input type=\"hidden\" name=\"jobID\" value=\""+job.getId()+"\"></br></br>\n" +
							"  <input type=\"submit\">\n" +
							"</form>";

					if(PermissionUtils.isEngineer(account) && (job.getStage() == 1 || job.getStage() == 3 || job.getStage() == 5)) {
						jobDetails += "<form action=\"/job_progress_engineer\" method=\"post\">\n" +
								"  <input type=\"hidden\" name=\"jobID\" value=\""+job.getId()+"\"><br>\n" +
								"  <button type=\"submit\">Set Review As Finished</button>\n" +
								"</form>";
					} else if(PermissionUtils.isUser(account) && (job.getStage() == 2 || job.getStage() == 4)) {
						jobDetails += "<form action=\"/job_progress_user\" method=\"post\">\n" +
								"  <input type=\"hidden\" name=\"jobID\" value=\""+job.getId()+"\"><br>\n" +
								"  <button type=\"submit\">Request Revision</button>\n" +
								"  <button type=\"submit\"formaction=\"/job_finish_user\" >Accept As Finished</button>\n" +
								"</form>";
					}

					Result result = Results.html("dashboard_page_template").
							put("content", jobDetails).
							put("title", "The Mix Hub Online - Dashboard").
							put("full_name", account.getFirstname() + " " + account.getLastname()).
							put("sidenav", SideNavigation.generate(req, account)).
							put("notification_count", Notifications.count(req, account)).
							put("notification_list", Notifications.generate(req, account));
					return result;
				} else {
					return Results.redirect("/dashboard");
				}
			} else if(req.param("preview").isSet()) {
				int id = req.param("preview", "js", "html", "uri").intValue();
				Job job = MySQL.getJobs(ds).queryJobFromID(id);

				if(job == null) {
					Result result = Results.html("jobs").
							put("message", "Invalid job");
					return result;
				}

				if(PermissionUtils.isEngineer(account))
				{
					Account author;
					if(accountCacheMap.containsKey(job.getOwner_id())) {
						author = accountCacheMap.get(job.getOwner_id());
					} else {
						author = MySQL.getAccounts(ds).queryAccountFromID(job.getOwner_id());
						accountCacheMap.put(job.getOwner_id(), author);
					}

					String jobDetails = "<a href=\"/job_accept?id="+job.getId() +
							"\">Accept This Job Normally</a></br><a href=\"/job_accept_anonymous?id=" + job.getId() +
							"\">Accept This Job Anonymously</a></br></br></br>" +
							"<table style=\"width:100%\">\n" +
							"  <tr align=\"right\">\n" +
							"    <th>Stage</th> \n" +
							"    <th>Title</th>\n" +
							"    <th>Owner</th> \n" +
							"    <th>Date</th>\n" +
							"  </tr>\n" +
							"  <tr align=\"right\">\n" +
							"    <td>"+StageUtils.stageToString(job.getStage())+"</td>\n" +
							"    <td>"+job.getTitle()+"</td>\n" +
							"    <td>"+author.getFirstname()+ " " + author.getLastname()+"</td> \n" +
							"    <td>"+new Date(job.getDate())+"</td>\n" +
							"  </tr>\n" +
							"</table></br></br>\n" +
							"\n" +
							"\n" +
							"\n" +
							"<table>\n" +
							"  <tr>\n" +
							"    <th>Author</th>\n" +
							"    <th>Comment</th> \n" +
							"    <th>File</th>\n" +
							"  </tr>\n";
					List<JobComment> comments = MySQL.getJobComments(ds).queryAllFromJobID(job.getId());

					for(JobComment jobComment : comments) {
						Account commetAuthor;
						if(accountCacheMap.containsKey(jobComment.getParent_account_id())) {
							commetAuthor = accountCacheMap.get(jobComment.getParent_account_id());
						} else {
							commetAuthor = MySQL.getAccounts(ds).queryAccountFromID(jobComment.getParent_account_id());
							accountCacheMap.put(jobComment.getParent_account_id(), commetAuthor);
						}
						jobDetails += "  <tr>\n";
						if(PermissionUtils.isEngineer(commetAuthor))
							jobDetails += "    <td>Engineer: "+commetAuthor.getFirstname()+ " " + commetAuthor.getLastname()+":</td>\n";
						else
							jobDetails += "    <td>User: "+commetAuthor.getFirstname()+ " " + commetAuthor.getLastname()+":</td>\n";
						jobDetails += "    <td>"+jobComment.getComment()+"</td> \n";
						if(jobComment.getFilepaths() == null)
							jobDetails += "    <td></td>\n";
						else
							jobDetails += "    <td><a href=\"/job_download?file="+new File(jobComment.getFilepaths()).getName()+"\">Click Here</a></td>\n";
						jobDetails += "  </tr>\n";
					}

					jobDetails += "</table></br></br>\n";

					Result result = Results.html("dashboard_page_template").
							put("content", jobDetails).
							put("title", "The Mix Hub Online - Jobs").
							put("full_name", account.getFirstname() + " " + account.getLastname()).
							put("sidenav", SideNavigation.generate(req, account)).
							put("notification_count", Notifications.count(req, account)).
							put("notification_list", Notifications.generate(req, account));
					return result;
				} else {
					return Results.redirect("/dashboard");
				}
			} else {
				return Results.redirect("/dashboard");
			}
		}
	}
}