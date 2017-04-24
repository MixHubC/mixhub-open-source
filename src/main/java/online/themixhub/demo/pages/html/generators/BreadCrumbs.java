package online.themixhub.demo.pages.html.generators;

/**
 * Created by John on 4/14/2017.
 */
public class BreadCrumbs {

	private static BreadCrumbs singleton = new BreadCrumbs();

	public static BreadCrumb instance() {
		return singleton.new BreadCrumb(new StringBuilder());
	}

	public class BreadCrumb {

		private StringBuilder sb;
		private BreadCrumb(StringBuilder sb) {
			this.sb = sb;
		}

		public BreadCrumb href(String url, String title) {
			sb.append("<li class=\"breadcrumb-item active\"><a href=\"" + url + "\">"+title+"</a></li>");
			return this;
		}

		public BreadCrumb title(String title) {
			sb.append("<li class=\"breadcrumb-item active\">"+title+"</li>");
			return this;
		}

		@Override
		public String toString() {
			return sb.toString();
		}

	}

}

