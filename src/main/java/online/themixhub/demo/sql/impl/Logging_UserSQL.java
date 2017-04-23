package online.themixhub.demo.sql.impl;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * Created by John on 4/23/2017.
 */
public class Logging_UserSQL {

	private static Logging_UserSQL thisSQL;
	private DataSource ds;
	private JdbcTemplate sql;

	private Logging_UserSQL() { }

	public static Logging_UserSQL newInstance(DataSource ds) {
		if(thisSQL == null) {
			thisSQL = new Logging_UserSQL();
			thisSQL.ds = ds;
			thisSQL.sql = new JdbcTemplate(ds);
		}
		return thisSQL;
	}


	public synchronized boolean insert(Logging_User logging_user) {
		String query = "INSERT INTO logging_user (" +
				"date, " +
				"title, " +
				"description, " +
				"owner_id, " +
				"owner_ip, " +
				"owner_useragent, " +
				"owner_referrer" +
				")"+
				" values (?, ?, ?, ?, ?, ?, ?)";
		sql.update(query, new Object[] {
				logging_user.getDate(),
				logging_user.getTitle(),
				logging_user.getDescription(),
				logging_user.getOwner_id(),
				logging_user.getOwner_ip(),
				logging_user.getOwner_useragent(),
				logging_user.getOwner_referrer()
				}
		);

		return true;
	}

}
