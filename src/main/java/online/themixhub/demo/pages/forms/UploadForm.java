package online.themixhub.demo.pages.forms;

import java.util.List;

import org.jooby.MediaType;
import org.jooby.Upload;

public class UploadForm {

	public Upload file;

	public List<MediaType> list;
	
	public UploadForm(Upload file) {
		this.file = file;
	}
}
