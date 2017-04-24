package online.themixhub.demo.sql.impl;

/**
 * Created by John on 4/23/2017.
 */
public class Logging_User {

	private int id;
	private long date;
	private String title;
	private String description;
	private int owner_id;
	private String owner_ip;
	private String owner_useragent;
	private String owner_referrer;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public long getDate() {
		return date;
	}

	public void setDate(long date) {
		this.date = date;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getOwner_id() {
		return owner_id;
	}

	public void setOwner_id(int owner_id) {
		this.owner_id = owner_id;
	}

	public String getOwner_ip() {
		return owner_ip;
	}

	public void setOwner_ip(String owner_ip) {
		this.owner_ip = owner_ip;
	}

	public String getOwner_useragent() {
		return owner_useragent;
	}

	public void setOwner_useragent(String owner_useragent) {
		this.owner_useragent = owner_useragent;
	}

	public String getOwner_referrer() {
		return owner_referrer;
	}

	public void setOwner_referrer(String owner_referrer) {
		this.owner_referrer = owner_referrer;
	}
}
