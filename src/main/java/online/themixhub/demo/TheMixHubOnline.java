package online.themixhub.demo;

import org.jooby.Jooby;
import org.jooby.Results;
import org.jooby.hbs.Hbs;
import org.jooby.jdbc.Jdbc;

import online.themixhub.demo.utils.MiscUtils;
import online.themixhub.demo.pages.*;

/**
 * An example Jooby App using the MVC API
 *
 * TODO (MOVE TO BACKLOG BEFORE GIT PUSH):
 * 	+ Security
 * 		Job_Download - Needs security, anyone can download any file (they'd have to brute 'em, but still)
 * 		Cookie Session - Really insecure, move to Flat File or Database storage for production.
 * 		MySQL.createAccount - Only verifies unique E-Mail not unique username so usernames cannot be used for login, change if this is required.
 * 		JobComment - Doesn't verify if the user or engineer has access to post replies.
 * 		All of the checks inside of login and register are insecure
 * 		All of the stage progress pages have no security at all, this has to be fixed for production.
 * 		MySQL doesnt check caps during password - adding a hash will fix this
 * 		Assets allows ** should only allow **.filextension along with specific directories
 *
 * 	+ Functionality
 * 		Mock up payment system
 * 		Force close after 3 revisions
 * 		Make revision lock a dynamic number down the road
 *
 * 	+ Caching/Speed
 * 		For preformance look into if we can cache the Data Source object along with the JdbcTemplate object
 * 		Create some type of easy to use/re-usable cache system for account lookups so we're not constantly grabbing username from id via SQL mutliple times per single page
 *
 * 	+ Current Obvious Pitfalls
 * 		The engineer relies on waiting for the user right now
 * 			before they can continue to the finished stage,
 * 			we need to make it automatically move to finished after X hours of their revision.
 * 		The engineer can move the review to finished without uploading anything, add a check for this
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
		assets("**");
		
		/**
		 * Error handling such as 403, 404 and 500
		 */
		err((req, rsp, err) -> {
			if (rsp.status().get().toString().equals("Not Found (404)")) {
				rsp.send(Results.html("404").
				put("username", "username-goes-here!")); //example dynamic strings
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
