package online.themixhub.demo;

import org.jooby.Jooby;
import org.jooby.Results;
import org.jooby.hbs.Hbs;

import online.themixhub.MiscUtils;
import online.themixhub.demo.pages.Download;
import online.themixhub.demo.pages.Index;
import online.themixhub.demo.pages.Upload;

/**
 * An example Jooby App using the MVC API
 * 
 * @author The Mix Hub Online
 */

public class Demo extends Jooby {

	{
		/**
		 * Classpath resources under /assets are accessible.
		 */
		assets("/favicon.ico"); //placeholder favicon is currently Google's 8-)
		assets("/css/**.css");
		
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
		//use(new Jdbc("db"));
		
		/**
		 * Use handlebars engine (or freemarker, etc...)
		 */
		use(new Hbs());
		
		/**
		 * Define pages
		 */
		use(Index.class);
		use(Download.class);
		use(Upload.class);
		
		/**
		 * Schedule tasks
		 */
		//use(new Quartz().with(Tasks.class).with(FlatFileSession.class));
	}
	
	public static void main(final String[] args) {
		//((Logger)LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME)).setLevel(Level.DEBUG);
		Demo panel = new Demo();
		panel.start();
	}

}
