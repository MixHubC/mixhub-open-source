package online.themixhub.demo.utils;

import org.jooby.Request;

/**
 * @author The Mix Hub Online
 */
public class SessionUtils {

	public static void handleSessionDestroy(Request req) {
		boolean destroyed = false;
		if(req.session().isSet("set")) {
			long timeSince = System.currentTimeMillis() - req.session().get("set").longValue();

			//if don't remember me & your session is over 24 hours old
			if(!req.session().get("remember").booleanValue()) {
				if(timeSince > 86400000) { //24 hours in milliseconds
					//log("DISABLED - OUTDATED");

					req.session().destroy();
					destroyed = true;
				}
			}

			//if your ip doesn't match the ip in your session
			if(!destroyed) {
				if(!req.ip().equals(req.session().get("ip").value())) {
					//log("DISABLED - IP = " +req.ip()+ " and " + req.session().get("ip"));

					req.session().destroy();
					destroyed = true;
				}
			}
		}
	}
}
