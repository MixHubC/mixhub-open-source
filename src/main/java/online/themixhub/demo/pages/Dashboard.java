package online.themixhub.demo.pages;

import com.google.inject.Inject;
import online.themixhub.demo.sql.impl.Job;
import online.themixhub.demo.sql.impl.JobComment;
import online.themixhub.demo.utils.PermissionUtils;
import online.themixhub.demo.utils.SessionUtils;
import online.themixhub.demo.sql.MySQL;
import online.themixhub.demo.sql.impl.Account;
import online.themixhub.demo.utils.StageUtils;
import org.jooby.Request;
import org.jooby.Result;
import org.jooby.Results;
import org.jooby.mvc.GET;
import org.jooby.mvc.Path;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Path("/dashboard")
public class Dashboard {

	private DataSource ds;

	//for when we use SQL
	@Inject
	public Dashboard(DataSource ds) {
		this.ds = ds;
	}

	@GET
	public Result getPage(Request req) throws IOException {
		SessionUtils.handleSessionDestroy(req);
		if (!req.session().isSet("set")) {
			return Results.redirect("/");
		} else {
			Account account = MySQL.getAccounts(ds).queryAccountFromID(req.session().get("id").intValue());
			if(PermissionUtils.isAdmin(account)) {
				return getPageAdmin(req, account);
			} else if(PermissionUtils.isEngineer(account)) {
				return getPageEngineer(req, account);
			} else {
				return getPageUser(req, account);
			}
		}
	}

	public Result getPageAdmin(Request req, Account account) {
		Result result = Results.html("dashboard_admin");
		return result;
	}

	public Result getPageEngineer(Request req, Account account) {
		List<Job> engineerJobs = MySQL.getJobs(ds).queryAllJobsFromEngineerID(account.getId());

		List<Job> unclaimedJobs = MySQL.getJobs(ds).queryAllUnclaimedJobs();

		StringBuilder engineerSB = new StringBuilder();
		StringBuilder unclaimedSB = new StringBuilder();

		HashMap<Integer, Account> accountCacheMap = new HashMap<Integer, Account>();
		accountCacheMap.put(account.getId(), account);

		if(unclaimedJobs != null) {
			unclaimedSB.append("</br></br>Unclaimed Engineer Jobs:");
			unclaimedSB.append("<table style=\"width:100%\">\n" +
					"  <tr>\n" +
					"    <th>Date</th>\n" +
					"    <th>Author</th> \n" +
					"    <th>Title</th>\n" +
					"    <th>Action</th>\n" +
					"  </tr>");
			for (Job job : unclaimedJobs) {
				Account ownerAccount;
				if(accountCacheMap.containsKey(job.getOwner_id())) {
					ownerAccount = accountCacheMap.get(job.getOwner_id());
				} else {
					ownerAccount = MySQL.getAccounts(ds).queryAccountFromID(job.getOwner_id());
					accountCacheMap.put(job.getOwner_id(), ownerAccount);
				}

				List<JobComment> commentList = MySQL.getJobComments(ds).queryAllFromJobID(job.getId());
				String title = "";
				String file = "";

				if(commentList != null && !commentList.isEmpty()) {
					title = job.getTitle();
					file = commentList.get(0).getFilepaths();

					if(title.length() > 32) {
						title = title.substring(0, 32) + "...";
					}
				}

				unclaimedSB.append("  <tr>\n" +
						"    <td>"+new Date(job.getDate())+"</td>\n" +
						"    <td>"+ownerAccount.getFirstname()+" "+ownerAccount.getLastname()+"</td> \n" +
						"    <td>"+title+"</td>\n" +
						"    <td><a href=\"/jobs?preview="+job.getId()+"\">Preview</a></td>\n" +
						"  </tr>");
			}
			unclaimedSB.append("</table>");
		} else {
			unclaimedSB.append("</br></br>No unclaimed jobs! Check back later.");
		}

		if(engineerJobs == null) {
			Result result = Results.html("dashboard_engineer").
					put("jobs_accepted", "You currently have no jobs claimed!").
					put("job_queue", unclaimedSB.toString());
			return result;
		} else {
			engineerSB.append("Your Claimed Jobs:</br>");
			engineerSB.append("<table style=\"width:100%\">\n" +
					"  <tr>\n" +
					"    <th>Date</th>\n" +
					"    <th>Author</th> \n" +
					"    <th>Title</th>\n" +
					"    <th>Stage</th>\n" +
					"    <th>Action</th>\n" +
					"  </tr>");
			for (Job job : engineerJobs) {
				Account ownerAccount;
				if(accountCacheMap.containsKey(job.getOwner_id())) {
					ownerAccount = accountCacheMap.get(job.getOwner_id());
				} else {
					ownerAccount = MySQL.getAccounts(ds).queryAccountFromID(job.getOwner_id());
					accountCacheMap.put(job.getOwner_id(), ownerAccount);
				}

				List<JobComment> commentList = MySQL.getJobComments(ds).queryAllFromJobID(job.getId());
				String comment = "";
				String file = "";

				if(commentList != null && !commentList.isEmpty()) {
					comment = commentList.get(0).getComment();
					file = commentList.get(0).getFilepaths();

					if(comment.length() > 32) {
						comment = comment.substring(0, 32) + "...";
					}
				}

				engineerSB.append("  <tr>\n" +
						"    <td>"+new Date(job.getDate())+"</td>\n" +
						"    <td>"+ownerAccount.getFirstname()+" "+ownerAccount.getLastname()+"</td> \n" +
						"    <td>"+comment+"</td>\n" +
						"    <td>"+ StageUtils.stageToString(job.getStage())+"</td>\n" +
						"    <td><a href=\"/jobs?id="+job.getId()+"\">View</a></td>\n" +
						"  </tr>");
			}
			engineerSB.append("</table>");

			Result result = Results.html("dashboard_engineer").
					put("jobs_accepted", engineerSB.toString()).
					put("job_queue", unclaimedSB.toString());
			return result;
		}
	}

	public Result getPageUser(Request req, Account account) {
		String createNewJob = "Create A New Job:\n" +
				"<form action=\"/job_create\" method=\"post\" enctype=\"multipart/form-data\" id=\"jobform\"> </br>\n" +
				"  Title: </br>\n" +
				"  <input type=\"text\" name=\"title\"> </br>\n" +
				"  Comments: </br>\n" +
				"  <textarea name=\"comments\" form=\"jobform\" rows=\"4\" cols=\"50\"></textarea></br>\n" +
				"  MP3 or WAV: </br>\n" +
				"  <input type=\"file\" name=\"file\" accept=\"*\"></br></br>\n" +
				"  <input type=\"submit\">\n" +
				"</form>";

		List<Job> jobs = MySQL.getJobs(ds).queryAllJobsFromUserID(account.getId());
		if(jobs == null) {
			Result result = Results.html("dashboard_user").
					put("job", createNewJob);
			return result;
		} else {
			StringBuilder jobSB = new StringBuilder();
			jobSB.append("Jobs:</br>");
			for(Job job : jobs) {
				jobSB.append("+ Job: <a href=\"/jobs?id="+job.getId()+"\">"+job.getTitle()+"</a> - "+StageUtils.stageToString(job.getStage())+"</br>");
			}


			Result result = Results.html("dashboard_user").
					put("job", jobSB.toString()+"</br></br>"+createNewJob);
			return result;
		}
	}


}
