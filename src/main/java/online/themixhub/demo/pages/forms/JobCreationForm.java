package online.themixhub.demo.pages.forms;

import org.jooby.MediaType;
import org.jooby.Upload;

import java.util.List;

public class JobCreationForm {

	public String title;
	public String comments;
	public Upload file;

	public List<MediaType> list;

	public JobCreationForm(Upload file) {
		this.file = file;
	}
}
