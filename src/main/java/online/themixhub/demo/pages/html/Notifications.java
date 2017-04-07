package online.themixhub.demo.pages.html;

import online.themixhub.demo.sql.impl.Account;
import org.jooby.Request;

/**
 * Created by John on 4/7/2017.
 */
public class Notifications {

	public static String generate(Request req, Account account) {
		StringBuilder sb = new StringBuilder();

		sb.append("                        <ul>\n" +
				"                            <li class=\"active-notification\"><i class=\"fa fa-user\" aria-hidden=\"true\"></i> New order received <span class=\"float-right grey-text\"><i class=\"fa fa-clock-o\" aria-hidden=\"true\"></i> 13 min</span></li>\n" +
				"                            <li class=\"active-notification\"><i class=\"fa fa-life-saver\" aria-hidden=\"true\"></i> Something else <span class=\"float-right grey-text\"><i class=\"fa fa-clock-o\" aria-hidden=\"true\"></i> 1 day</span></li>\n" +
				"                        </ul>");

		return sb.toString();
	}

	public static int count(Request req, Account account) {
		return 2;
	}
}
