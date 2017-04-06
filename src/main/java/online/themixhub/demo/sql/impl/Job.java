package online.themixhub.demo.sql.impl;

import online.themixhub.demo.sql.MySQL;

import javax.sql.DataSource;
import java.util.List;

/**
 * Used to define the job object
 *
 * @author The Mix Hub Online
 */
public class Job {

    private int id;
    private int owner_id;
    private int engineer_id;
    private int stage;
    private String title;
    private long date;
    private int anonymous_engineer;
    private long last_activity_date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOwner_id() {
	return owner_id;
    }

    public void setOwner_id(int owner_id) {
	this.owner_id = owner_id;
    }

    public int getEngineer_id() {
	return engineer_id;
    }

    public void setEngineer_id(int engineer_id) {
	this.engineer_id = engineer_id;
    }

    public int getStage() {
	return stage;
    }

    public void setStage(int stage) {
	this.stage = stage;
    }

    public String getTitle() {
	return title;
    }

    public void setTitle(String title) {
	this.title = title;
    }

    public long getDate() {
	return date;
    }

    public void setDate(long date) {
	this.date = date;
    }

    public int getAnonymous_engineer() {
	return anonymous_engineer;
    }

    public void setAnonymous_engineer(int anonymous_engineer) {
	this.anonymous_engineer = anonymous_engineer;
    }

    public long getLast_activity_date() {
	return last_activity_date;
    }

    public void setLast_activity_date(long last_activity_date) {
	this.last_activity_date = last_activity_date;
    }
}
