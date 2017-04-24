package online.themixhub.demo.utils;

import online.themixhub.demo.sql.MySQL;
import online.themixhub.demo.sql.impl.Logging_System;
import online.themixhub.demo.sql.impl.Logging_User;
import org.jooby.Request;

import javax.sql.DataSource;

/**
 * Created by John on 4/23/2017.
 */
public class LoggingUtil {

	public static void insertSystemLog(DataSource ds, String title, String description) {
		Logging_System log = new Logging_System();
		log.setDate(System.currentTimeMillis());
		log.setTitle(title);
		log.setDescription(description);
		MySQL.getLoggingSystem(ds).insert(log);
	}

	public static void insertUserLog(DataSource ds, String title, String description, int owner_id, Request req) {
		Logging_User log = new Logging_User();
		log.setDate(System.currentTimeMillis());
		log.setTitle(title);
		log.setDescription(description);
		log.setOwner_id(owner_id);
		log.setOwner_ip(req.ip());
		log.setOwner_useragent(req.headers().get("User-Agent").value());
		if(req.headers().get("Referer") != null)
			log.setOwner_referrer(req.headers().get("Referer").value());
		MySQL.getLoggingUser(ds).insert(log);
	}
}