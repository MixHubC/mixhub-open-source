package online.themixhub.demo.sql.impl;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * Created by John on 4/23/2017.
 */
public class Logging_SystemSQL {

	private static Logging_SystemSQL thisSQL;
	private DataSource ds;
	private JdbcTemplate sql;

	private Logging_SystemSQL() { }

	public static Logging_SystemSQL newInstance(DataSource ds) {
		if(thisSQL == null) {
			thisSQL = new Logging_SystemSQL();
			thisSQL.ds = ds;
			thisSQL.sql = new JdbcTemplate(ds);
		}
		return thisSQL;
	}


	public synchronized boolean insert(Logging_System logging_system) {
		String query = "INSERT INTO logging_system (" +
				"date, " +
				"title, " +
				"description" +
				")"+
				" values (?, ?, ?)";
		sql.update(query, new Object[] {
				logging_system.getDate(),
				logging_system.getTitle(),
				logging_system.getDescription()
				}
		);

		return true;
	}

}
