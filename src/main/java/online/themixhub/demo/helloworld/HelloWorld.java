package online.themixhub.demo.helloworld;

import org.jooby.Jooby;
import org.jooby.Results;
import org.jooby.hbs.Hbs;

/**
 * An example Jooby App using the MVC API
 * 
 * @author The Mix Hub Online
 */

public class HelloWorld extends Jooby {

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
				rsp.send(Results.html("500"));
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
		
		/**
		 * Schedule tasks
		 */
		//use(new Quartz().with(Tasks.class).with(FlatFileSession.class));
	}
	
	public static void main(final String[] args) {
		//((Logger)LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME)).setLevel(Level.DEBUG);
		HelloWorld panel = new HelloWorld();
		panel.start();
	}

}
