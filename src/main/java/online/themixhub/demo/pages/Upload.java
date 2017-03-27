package online.themixhub.demo.pages;

import java.io.File;
import java.io.IOException;

import org.jooby.*;
import org.jooby.mvc.*;

import online.themixhub.MiscUtils;
import online.themixhub.demo.pages.forms.UploadForm;

@Path("/upload")
public class Upload {

	@GET
	@POST
	public Result getPage(Request req) throws IOException {
		if(req.method().toLowerCase().equals("get")) {
			Result result = Results.html("upload");
			return result;
		} else {
			UploadForm uploadForm = req.form(UploadForm.class, "js", "html", "uri");
			File moveTo = new File("demo_uploads" + File.separator + uploadForm.file.name());
			if(moveTo.exists()) {
				Result result = Results.html("upload_fail_exists");
				return result;
			} else {
				uploadForm.file.file().renameTo(moveTo);
				Result result = Results.html("upload_success").
						put("file_name", uploadForm.file.name()).
						put("file_size", MiscUtils.getStringSizeLengthFile(moveTo.length()));
				return result;
			}
		}
	}
}
