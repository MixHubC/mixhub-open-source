package online.themixhub.demo.sql.impl;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by John on 4/3/2017.
 */
public class JobSQL {

	private static JobSQL thisSQL;
	private DataSource ds;
	private JdbcTemplate sql;

	private JobSQL() { }

	public static JobSQL newInstance(DataSource ds) {
		if(thisSQL == null) {
			thisSQL = new JobSQL();
			thisSQL.ds = ds;
			thisSQL.sql =  new JdbcTemplate(ds);
		}
		return thisSQL;
	}

	/**
	 * Returns a job by userid, returns null if id doesn't exist
	 */
	public synchronized Job queryJobFromID(int id) {
		String query = "SELECT * FROM jobs WHERE id = ?";
		List<Job> jobs = sql.query(query,
				new Object[]{id},
				new JobMapper()
		);

		if(!jobs.isEmpty()) {
			return jobs.get(0);
		}

		return null;
	}

	/**
	 * Returns a list of jobs owned/created by the specific user, returns null if none
	 */
	public synchronized List<Job> queryAllJobsFromUserID(int userid) {
		String query = "SELECT * FROM jobs WHERE owner_id = ?";
		List<Job> jobs = sql.query(query,
				new Object[]{userid},
				new JobMapper()
		);

		if(!jobs.isEmpty()) {
			return jobs;
		}

		return null;
	}

	/**
	 * Returns a list of jobs claimed by the specific engineer, returns null if none
	 */
	public synchronized List<Job> queryAllUnclaimedJobs() {
		String query = "SELECT * FROM jobs WHERE stage = ?";
		List<Job> jobs = sql.query(query,
				new Object[]{0},
				new JobMapper()
		);

		if(!jobs.isEmpty()) {
			return jobs;
		}

		return null;
	}

	/**
	 * Returns a list of jobs claimed by the specific engineer, returns null if none
	 */
	public synchronized List<Job> queryAllJobsFromEngineerID(int engineerid) {
		String query = "SELECT * FROM jobs WHERE engineer_id = ?";
		List<Job> jobs = sql.query(query,
				new Object[]{engineerid},
				new JobMapper()
		);

		if(!jobs.isEmpty()) {
			return jobs;
		}

		return null;
	}

	/**
	 * Returns a simple count of all the jobs, there has to be a way to do this via SQL without mapping all the objects out.
	 */
	public synchronized int queryTotalCount() {
		String query = "SELECT * FROM jobs";
		List<Job> jobs = sql.query(query,
				new Object[]{},
				new JobMapper()
		);

		return jobs.size();
	}

	/**
	 * Creates a new job with the first job comment
	 */
	public synchronized int insert(Job job, String comment, String filepaths) {
		int nextID = (queryTotalCount() + 1);
		long now = System.currentTimeMillis();

		String query = "INSERT INTO jobs (" +
				"id, " +
				"owner_id, " +
				"title, " +
				"date, " +
				"last_activity_date " +
				")"+
				" values (?, ?, ?, ?, ?)";
		sql.update(query,
				new Object[]{
						nextID,
						job.getOwner_id(),
						job.getTitle(),
						now,
						now
				}
		);

		query = "INSERT INTO job_comments (" +
				"date, " +
				"parent_account_id, " +
				"parent_job_id, " +
				"comment, " +
				"filepaths" +
				")"+
				" values (?, ?, ?, ?, ?)";
		sql.update(query,
				new Object[]{
						now,
						job.getOwner_id(),
						nextID,
						comment,
						filepaths
				}
		);


		return nextID;
	}

	public synchronized void acceptJob(int job_id, int engineerID, boolean anonymous) {
		Job job = queryJobFromID(job_id);
		if(job.getStage() == 0) { //able to be accepted
			String query = "UPDATE jobs SET stage = ?, engineer_id = ?, anonymous_engineer = ?, last_activity_date = ? WHERE id = ?";
			int isAnonymous = anonymous ? 1 : 0;
			sql.update(query,
					new Object[]{1, engineerID, isAnonymous, System.currentTimeMillis(), job_id}
			);
		}
	}

	public synchronized void setJobStage(int job_id, int engineerID, int stage) {
		String query = "UPDATE jobs SET stage = ?, engineer_id = ?, last_activity_date = ? WHERE id = ?";
		sql.update(query,
				new Object[]{stage, engineerID, System.currentTimeMillis(), job_id}
		);
	}
}
