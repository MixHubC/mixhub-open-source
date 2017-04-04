package online.themixhub.demo.utils;

import online.themixhub.demo.sql.impl.Account;

/**
 * @author The Mix Hub Online
 */
public class PermissionUtils {

	public static boolean isAdmin(Account account) {
		return account.getPermission() == 2;
	}

	public static boolean isEngineer(Account account) {
		return account.getPermission() == 1;
	}

	public static boolean isUser(Account account) {
		return account.getPermission() == 0;
	}

}
