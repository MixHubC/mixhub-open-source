package online.themixhub.demo.utils;

/**
 * Created by John on 4/23/2017.
 */
public class InvoiceUtils {

	public static String stageToStringDescription(int stage) {
		if(stage == 0) {
			return "Unpaid";
		} else {
			return "Paid";
		}
	}
}