package online.themixhub.demo.pages;

import java.io.File;

import org.jooby.*;
import org.jooby.mvc.*;

@Path("/download")
public class Download {

	@GET
	public Result getPage(Request req) {
		String fileName = req.param("file", "js", "html", "uri").value();
		File path = new File("demo_uploads");
		File download = new File(path.getAbsolutePath()+File.separator+fileName);
		System.out.println("D: " + download.getParentFile().getAbsolutePath()+", P: " + path.getAbsolutePath());
		if(download.getParentFile().getAbsolutePath().equals(path.getAbsolutePath())) {
			if(!download.exists()) {
				Result result = Results.html("download_fail_exists");
				return result;
			} else {
				return Results.ok(download).header("Content-Disposition", "attachment; filename="+download.getName()+";");
			}
		} else { //trying to escape our directory, probably by doing download?file=../../../
			Result result = Results.html("download_fail");
			return result;
		}
	}
}
