package online.themixhub.demo.pages;

import org.jooby.Request;
import org.jooby.Result;
import org.jooby.Results;
import org.jooby.mvc.GET;
import org.jooby.mvc.Path;

import java.io.File;

@Path("/job_download")
public class Job_Download {

	@GET
	public Result getPage(Request req) {
		String fileName = req.param("file", "js", "html", "uri").value();
		File path = new File("uploads");
		File download = new File(path.getAbsolutePath()+File.separator+fileName);
		//System.out.println("D: " + download.getParentFile().getAbsolutePath()+", P: " + path.getAbsolutePath());
		if(download.getParentFile().getAbsolutePath().equals(path.getAbsolutePath())) {
			if(!download.exists()) {
				return Results.redirect("/jobs");
			} else {
				return Results.ok(download).header("Content-Disposition", "attachment; filename="+download.getName()+";");
			}
		} else { //trying to escape our directory, probably by doing http://localhost:8080/demo_download?file=../../../
			Result result = Results.html("jobs_download_fail").
					put("message", "File not found!");
			return result;
		}
	}
}
