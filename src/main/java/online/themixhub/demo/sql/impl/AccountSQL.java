package online.themixhub.demo.sql.impl;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by John on 4/3/2017.
 */
public class AccountSQL {

	private static AccountSQL thisSQL;
	private DataSource ds;
	private JdbcTemplate sql;

	private AccountSQL() { }

	public static AccountSQL newInstance(DataSource ds) {
		if(thisSQL == null) {
			thisSQL = new AccountSQL();
			thisSQL.ds = ds;
			thisSQL.sql =  new JdbcTemplate(ds);
		}
		return thisSQL;
	}

	/**
	 * Returns an account is valid login, if not it returns null
	 */
	public synchronized Account queryValidAccount(String email, String password) {
		String query = "SELECT * FROM accounts WHERE email = ? AND password = ?";
		List<Account> accounts = sql.query(query,
				new Object[]{email, password},
				new AccountMapper()
		);

		if(!accounts.isEmpty()) {
			return accounts.get(0);
		}

		return null;
	}

	/**
	 * Returns an account by userid, returns null if id doesn't exist
	 */
	public synchronized Account queryAccountFromID(int id) {
		String query = "SELECT * FROM accounts WHERE id = ?";
		List<Account> accounts = sql.query(query,
				new Object[]{id},
				new AccountMapper()
		);

		if(!accounts.isEmpty()) {
			return accounts.get(0);
		}

		return null;
	}

	/**
	 * Returns a simple count of all the users, there has to be a way to do this via SQL without mapping all the objects out.
	 */
	public synchronized int getTotalCount() {
		String query = "SELECT * FROM accounts";
		List<Account> accounts = sql.query(query,
				new Object[]{},
				new AccountMapper()
		);

		return accounts.size();
	}

	/*
	* NOTE: This is only configured for E-Mail login, username was never fully implemented for checks, add that if we're going to support username login
	* */
	public synchronized boolean insert(Account account) {
		String query = "SELECT * FROM accounts WHERE email = ?";
		List<Account> accounts = sql.query(query,
				new Object[]{account.email},
				new AccountMapper()
		);

		if(!accounts.isEmpty()) {
			return false;
		} else {
			query = "INSERT INTO accounts (" +
					"permission, " +
					"date, " +
					"register_ip, " +
					"email, " +
					"username, " +
					"password, " +
					"firstname, " +
					"lastname, " +
					"address_1, " +
					"address_2, " +
					"city, " +
					"state, " +
					"country, " +
					"zip, " +
					"phone" +
					")"+
					" values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			sql.update(query,
					new Object[]{
							0,
							System.currentTimeMillis(),
							account.register_ip,
							account.email,
							account.username,
							account.password,
							account.firstname,
							account.lastname,
							account.address_1,
							account.address_2,
							account.city,
							account.state,
							account.country,
							account.zip,
							account.phone
					}
			);

			return true;
		}
	}

}
