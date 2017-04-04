package online.themixhub.demo.utils;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.DecimalFormat;
import java.util.Random;

/**
 * A collection of miscellaneous functions
 * 
 * @author The Mix Hub Online
 *
 */

public class MiscUtils {

	private static String candidateChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
	private static Random random = new Random();

	public static String generateUniqueFileName(String path, String originalFileName) {
		String uniqueName = MiscUtils.generateRandomChars(12) + "_themixhubonline_" + originalFileName;
		while (new File(path+File.separator+uniqueName).exists()) {
			uniqueName = MiscUtils.generateRandomChars(12) + "_themixhubonline_" + originalFileName;
		}
		return uniqueName;
	}
	
	public static String exceptionToString(Exception e) { 
		StringBuilder sb = new StringBuilder();
		
		StringWriter sw = new StringWriter();
		e.printStackTrace(new PrintWriter(sw));
		sb.append(sw.toString());
		
		if(e.getCause() != null) {
			sw = new StringWriter();
			e.getCause().printStackTrace(new PrintWriter(sw));
			sb.append(sw.toString());
		}
		
		return sb.toString();
	}

	/**
	 * @author http://stackoverflow.com/a/22425846
	 */
	public static String getStringSizeLengthFile(long size) {

	    DecimalFormat df = new DecimalFormat("0.00");

	    float sizeKb = 1024.0f;
	    float sizeMo = sizeKb * sizeKb;
	    float sizeGo = sizeMo * sizeKb;
	    float sizeTerra = sizeGo * sizeKb;

	    if(size < sizeMo)
	        return df.format(size / sizeKb)+ " Kb";
	    else if(size < sizeGo)
	        return df.format(size / sizeMo) + " Mb";
	    else if(size < sizeTerra)
	        return df.format(size / sizeGo) + " Gb";

	    return "";
	}

	public static String generateRandomChars(int length) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < length; i++) {
			sb.append(candidateChars.charAt(random.nextInt(candidateChars
					.length())));
		}

		return sb.toString();
	}

}
