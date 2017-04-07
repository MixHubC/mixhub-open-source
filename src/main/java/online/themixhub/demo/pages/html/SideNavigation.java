package online.themixhub.demo.pages.html;

import online.themixhub.demo.sql.impl.Account;
import online.themixhub.demo.utils.PermissionUtils;
import org.jooby.Request;

/**
 * Created by John on 4/7/2017.
 */
public class SideNavigation {

	public static String generate(Request req, Account account) {
		if(PermissionUtils.isAdmin(account)) {
			return generateSideBarAdmin(req, account);
		} else if(PermissionUtils.isEngineer(account)) {
			return generateSideBarEngineer(req, account);
		} else {
			return generateSideBarUser(req, account);
		}
	}

	private static String generateSideBarAdmin(Request req, Account account) {
		StringBuilder sb = new StringBuilder();

		sb.append("     <ul class=\"collapsible collapsible-accordion\">\n" +
				"                    <li><a href=\"/dashboard\" class=\"collapsible-header waves-effect arrow-r\"><i class=\"fa fa-code\"></i> Dashboard</a>\n" +
				"                    <li><a href=\"invoice.html\" class=\"collapsible-header waves-effect arrow-r\"><i class=\"fa fa-money\"></i> Invoice</a>\n" +
				"                    <li><a href=\"support.html\" class=\"collapsible-header waves-effect arrow-r\"><i class=\"fa fa-support\"></i> Support</a>\n" +
				"                    <li><a href=\"faq.html\" class=\"collapsible-header waves-effect arrow-r\"><i class=\"fa fa-question-circle\" aria-hidden=\"true\"></i> FAQ</a>\n" +
				"                </ul>");

		return sb.toString();
	}

	private static String generateSideBarEngineer(Request req, Account account) {
		StringBuilder sb = new StringBuilder();

		sb.append("     <ul class=\"collapsible collapsible-accordion\">\n" +
				"                    <li><a href=\"/dashboard\" class=\"collapsible-header waves-effect arrow-r\"><i class=\"fa fa-code\"></i> Dashboard</a>\n" +
				"                    <li><a href=\"invoice.html\" class=\"collapsible-header waves-effect arrow-r\"><i class=\"fa fa-money\"></i> Invoice</a>\n" +
				"                    <li><a href=\"support.html\" class=\"collapsible-header waves-effect arrow-r\"><i class=\"fa fa-support\"></i> Support</a>\n" +
				"                    <li><a href=\"faq.html\" class=\"collapsible-header waves-effect arrow-r\"><i class=\"fa fa-question-circle\" aria-hidden=\"true\"></i> FAQ</a>\n" +
				"                </ul>");
		return sb.toString();
	}

	private static String generateSideBarUser(Request req, Account account) {
		StringBuilder sb = new StringBuilder();

		sb.append("     <ul class=\"collapsible collapsible-accordion\">\n" +
				"                    <li><a href=\"/dashboard\" class=\"collapsible-header waves-effect arrow-r\"><i class=\"fa fa-code\"></i> Dashboard</a>\n" +
				"                    <li><a href=\"invoice.html\" class=\"collapsible-header waves-effect arrow-r\"><i class=\"fa fa-money\"></i> Invoice</a>\n" +
				"                    <li><a href=\"support.html\" class=\"collapsible-header waves-effect arrow-r\"><i class=\"fa fa-support\"></i> Support</a>\n" +
				"                    <li><a href=\"faq.html\" class=\"collapsible-header waves-effect arrow-r\"><i class=\"fa fa-question-circle\" aria-hidden=\"true\"></i> FAQ</a>\n" +
				"                </ul>");

		return sb.toString();
	}

}
