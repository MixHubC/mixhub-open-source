package online.themixhub.demo.pages.forms;

import org.jooby.MediaType;
import org.jooby.Upload;

import java.util.List;

public class JobCommentForm {

	public String jobID;
	public String comments;
	public Upload file;

	public List<MediaType> list;

	public JobCommentForm(Upload file) {
		this.file = file;
	}
}
