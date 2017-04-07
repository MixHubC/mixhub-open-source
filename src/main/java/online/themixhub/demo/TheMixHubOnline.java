package online.themixhub.demo;

import org.jooby.Jooby;
import org.jooby.Results;
import org.jooby.hbs.Hbs;
import org.jooby.jdbc.Jdbc;

import online.themixhub.demo.utils.MiscUtils;
import online.themixhub.demo.pages.*;
import online.themixhub.demo.pages.html.*;

/**
 * An example Jooby App using the MVC API
 *
 * @author The Mix Hub Online
 */

public class TheMixHubOnline extends Jooby {

	{
		/**
		 * Set purely cookie + cryptographic signature based session (LOL WHY IS THIS AN OPTION?)
		 */
		cookieSession();

		/**
		 * Classpath resources under /assets are accessible.
		 */
		assets("/favicon.ico"); //placeholder favicon is currently Google's 8-)
		assets("/css/**.css");
		assets("/js/**.js");
		assets("**");

		/**
		 * Error handling such as 403, 404 and 500
		 */
		err((req, rsp, err) -> {
			if (rsp.status().get().toString().equals("Not Found (404)")) {
				rsp.send(Results.html("404"));
			} else {
				/*boolean sessionFailure = false;
				try {
					req.session();
				} catch(NullPointerException npe) {
					sessionFailure = true;
				}
				
				if(sessionFailure) {
					rsp.send(Results.html("403"));
					//log session-modification attempt?
				} else {
					rsp.send(Results.html("500"));
					
					CriticalDiskLogger.logException(err); //log all errors to disk to review at a later date
				}*/
				rsp.send(Results.html("500").
						put("stacktrace", MiscUtils.exceptionToString(err))); //NOTE, this is extremely unsafe for production, add a check in the future
			}
		});
		
		/**
		 * Use JDBC
		 */
		use(new Jdbc("db"));
		
		/**
		 * Use handlebars engine (or freemarker, etc...)
		 */
		use(new Hbs());
		
		/**
		 * Define pages
		 */
		use(Index.class);
		use(Register.class);
		use(Login.class);
		use(Logout.class);
		use(Dashboard.class);
		use(Jobs.class);
		use(Job_Create.class);
		use(Job_Comment.class);
		use(Job_Download.class);
		use(Job_Accept.class);
		use(Job_Accept_Anonymous.class);
		use(Job_Progress_Engineer.class);
		use(Job_Progress_User.class);
		use(Job_Finish_User.class);
		
		/**
		 * Schedule tasks
		 */
		//use(new Quartz().with(Tasks.class).with(FlatFileSession.class));
	}
	
	public static void main(final String[] args) {
		//((Logger)LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME)).setLevel(Level.DEBUG);
		TheMixHubOnline panel = new TheMixHubOnline();
		panel.start();
	}

}
