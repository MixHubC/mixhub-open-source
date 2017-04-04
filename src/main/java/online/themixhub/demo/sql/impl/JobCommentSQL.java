package online.themixhub.demo.sql.impl;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.io.File;
import java.util.List;

/**
 * Created by John on 4/3/2017.
 */
public class JobCommentSQL {

	private static JobCommentSQL thisSQL;
	private DataSource ds;
	private JdbcTemplate sql;

	private JobCommentSQL() { }

	public static JobCommentSQL newInstance(DataSource ds) {
		if(thisSQL == null) {
			thisSQL = new JobCommentSQL();
			thisSQL.ds = ds;
			thisSQL.sql =  new JdbcTemplate(ds);
		}
		return thisSQL;
	}

	/**
	 * Returns the job comment by id, returns null if none
	 */
	public synchronized JobComment queryFromJobCommentID(int id) {
		String query = "SELECT * FROM job_comments WHERE id = ?";
		List<JobComment> jobComments = sql.query(query,
				new Object[]{id},
				new JobCommentMapper()
		);

		if(!jobComments.isEmpty()) {
			return jobComments.get(0);
		}

		return null;
	}

	/**
	 * Returns a job comment list by parent_job_id, returns null if none
	 */
	public synchronized List<JobComment> queryAllFromJobID(int jobID) {
		String query = "SELECT * FROM job_comments WHERE parent_job_id = ?";
		List<JobComment> jobComments = sql.query(query,
				new Object[]{jobID},
				new JobCommentMapper()
		);

		if(!jobComments.isEmpty()) {
			return jobComments;
		}

		return null;
	}

	/**
	 * Returns a simple count of all the jobs, there has to be a way to do this via SQL without mapping all the objects out.
	 */
	public synchronized int queryTotalCount() {
		String query = "SELECT * FROM jobs_comments";
		List<JobComment> jobComments = sql.query(query,
				new Object[]{},
				new JobCommentMapper()
		);

		return jobComments.size();
	}

	/**
	 * Creates a new job comment
	 */
	public synchronized boolean insert(JobComment comment) {
		long now = System.currentTimeMillis();

		String query = "INSERT INTO job_comments (" +
				"date, " +
				"reply_to_id, " +
				"parent_account_id, " +
				"parent_job_id, " +
				"comment, " +
				"filepaths" +
				")" +
				" values (?, ?, ?, ?, ?, ?)";
		sql.update(query,
				new Object[]{
						now,
						comment.getReply_to_id(),
						comment.getParent_account_id(),
						comment.getParent_job_id(),
						comment.getComment(),
						comment.getFilepaths()
				}
		);

		return true;
	}
}
