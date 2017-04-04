package online.themixhub.demo.sql;

import online.themixhub.demo.sql.impl.*;

import javax.sql.DataSource;

/**
 * Used as a main entry point to the semi-oop MySQL setup
 *
 * @author The Mix Hub Online
 */
public class MySQL {

	/**
	 * Gets the account SQL controller
	 */
	public static AccountSQL getAccounts(DataSource ds) {
		return AccountSQL.newInstance(ds);
	}

	/**
	 * Gets the job SQL controller
	 */
	public static JobSQL getJobs(DataSource ds) {
		return JobSQL.newInstance(ds);
	}

	/**
	 * Gets the job comment SQL controller
	 */
	public static JobCommentSQL getJobComments(DataSource ds) {
		return JobCommentSQL.newInstance(ds);
	}
}
