package online.themixhub.demo.sql.impl;

/**
 * Used to define the job comment object
 *
 * @author The Mix Hub Online
 */
public class JobComment {

    int id;
    long date;
	int reply_to_id;
	int parent_account_id;
	int parent_job_id;
	String comment;
	String filepaths; //CSV separated by ,

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

	public int getReply_to_id() {
		return reply_to_id;
	}

	public void setReply_to_id(int reply_to_id) {
		this.reply_to_id = reply_to_id;
	}

	public int getParent_account_id() {
		return parent_account_id;
	}

	public void setParent_account_id(int parent_account_id) {
		this.parent_account_id = parent_account_id;
	}

	public int getParent_job_id() {
		return parent_job_id;
	}

	public void setParent_job_id(int parent_job_id) {
		this.parent_job_id = parent_job_id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getFilepaths() {
		return filepaths;
	}

	public void setFilepaths(String filepaths) {
		this.filepaths = filepaths;
	}
}